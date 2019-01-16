package com.e_ucharist.e_ucharist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //make website link clickable
        TextView website = (TextView) findViewById(R.id.website);
        website.setMovementMethod(LinkMovementMethod.getInstance());

        FrameLayout mainLayout = (FrameLayout) findViewById(R.id.main_layout);
        final ImageView wafer = (ImageView) findViewById(R.id.wafer);
        final TextView instructions = (TextView) findViewById(R.id.instructions);

        wafer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    startIntent();
            }
        });

        instructions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startIntent();
            }
        });
    }

    private void startIntent(){
        Intent intent = new Intent(MainActivity.this, EucharistActivity.class);
        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        //Inflate the menu options from the res/menu/main_menu.xml file
//        //This adds menu items to the app bar.
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        // User clicked on a menu option in the app bar overflow menu
//        if(item.getItemId() == R.id.about_menu){
//            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
//            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
