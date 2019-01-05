package com.e_ucharist.e_ucharist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout mainLayout = (FrameLayout) findViewById(R.id.main_layout);
        mainLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, EucharistActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu options from the res/menu/main_menu.xml file
        //This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // User clicked on a menu option in the app bar overflow menu
        if(item.getItemId() == R.id.about_menu){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
