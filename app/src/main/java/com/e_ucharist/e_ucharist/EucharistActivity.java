package com.e_ucharist.e_ucharist;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.annotation.NonNull;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class EucharistActivity extends AppCompatActivity {

    //Array with communion prayer separated into indices
    List<String> communion;

    int communionIndex;

    private MediaPlayer player;
    private float volume;

//    private Vibrator vibration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_eucharist);

        volume = 1;

        //call MusicService
        //startService(new Intent(this, MusicService.class));

        //create vibrator object
//        vibration = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        //Creates a global player MediaPlayer object to play sound
        player = MediaPlayer.create(this, R.raw.singing_bowl);
        player.setLooping(true);
        player.start();


        communion = Arrays.asList(
                getString(R.string.communion0),
                getString(R.string.communion1),
                getString(R.string.communion2),
                getString(R.string.communion3),
                getString(R.string.communion4),
                getString(R.string.communion5),
                getString(R.string.communion6)
        );

        communionIndex = 0;

        RelativeLayout eucharistLayout = findViewById(R.id.eucharist_layout);
        final TextView prayer = findViewById(R.id.prayer);
        final ImageView wafer = findViewById(R.id.wafer);

        eucharistLayout.setOnClickListener(v -> {
            if (communionIndex < 7) {
                prayer.setText(communion.get(communionIndex));
                communionIndex++;
            } else if (communionIndex == 7) {
//                    prayer.setText(communion.get(communionIndex));

                //Animating alpha with objectAnimator
                ObjectAnimator objectAnimatorPrayer = ObjectAnimator.ofFloat(prayer,"alpha",1,0);
                ObjectAnimator objectAnimatorWafer = ObjectAnimator.ofFloat(wafer,"alpha",1,0);

                objectAnimatorPrayer.setDuration(2000);
                objectAnimatorPrayer.start();

                objectAnimatorWafer.setDuration(2000);
                objectAnimatorWafer.start();

                objectAnimatorPrayer.addListener(new AnimatorListener(){

                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animation){
                        startFadeOut();
                        Intent intent = new Intent(EucharistActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {

                    }
                });
            }

        });
    }

    @Override
    public void onPause(){
        super.onPause();
        player.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu options from the res/menu/eucharist_menu.xml file
        //This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.eucharist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()){
            // Respond to a click on the "E-ucharist" menu option
            case R.id.eucharist_menu:
                //Create intent and open Home screen/Main Activity
                Intent homeIntent = new Intent(EucharistActivity.this, MainActivity.class);
                startActivity(homeIntent);
                return true;
            // Respond to a click on the "About" menu option
            case R.id.about_menu:
                Intent aboutIntent = new Intent(EucharistActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void startFadeOut(){
        final int FADE_DURATION = 2000; //The duration of the fade
        //The amount of time between volume changes. The smaller this is, the smoother the fade
        final int FADE_INTERVAL = 250;
        final int MAX_VOLUME = 1; //The volume will decrease from 1 to 0
        int numberOfSteps = FADE_DURATION/FADE_INTERVAL; //Calculate the number of fade steps
        //Calculate by how much the volume changes each step
        final float deltaVolume = MAX_VOLUME / (float)numberOfSteps;

        //Create a new Timer and Timer task to run the fading outside the main UI thread
        final Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                fadeOutStep(deltaVolume); //Do a fade step
                //Cancel and Purge the Timer if the desired volume has been reached
                if(volume<=0){
                    timer.cancel();
                    timer.purge();
                    player.stop();
                }

            }
        };

        timer.schedule(timerTask,FADE_INTERVAL,FADE_INTERVAL);
    }

    private void fadeOutStep(float deltaVolume){
        player.setVolume(volume, volume);
        volume -= deltaVolume;

    }
}
