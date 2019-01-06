package com.e_ucharist.e_ucharist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class EucharistActivity extends AppCompatActivity {

    //Array with communion prayer separated into indices
    List<String> communion;

    int communionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eucharist);

        //call MusicService
        startService(new Intent(this, MusicService.class));



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

        RelativeLayout eucharistLayout = (RelativeLayout) findViewById(R.id.eucharist_layout);
        final TextView prayer = (TextView) findViewById(R.id.prayer);

        eucharistLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (communionIndex < 7) {
                    prayer.setText(communion.get(communionIndex));
                    communionIndex++;
                } else{
                    stopService(new Intent(EucharistActivity.this, MusicService.class));
                    //Animating alpha with objectAnimator
                    /*
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(prayer,"alpha",1,0);
                    objectAnimator.setDuration(2000);
                    objectAnimator.start();
                    */

                    //Animating with AlphaAnimation
                    /*
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
                    alphaAnimation.setDuration(2000);
                    alphaAnimation.setFillAfter(true);
                    eucharistLayout.startAnimation(alphaAnimation);
                    */


                    //Animating with xml: fades out current activity and fades in other


                    Intent intent = new Intent(EucharistActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                }
            }
        });
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


}
