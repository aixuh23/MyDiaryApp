package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button edit, searech, list, btn_play, btn_stop;
    ImageView iVedit, iVcalendar, iVsearch, iVdiary;
    LinearLayout baseLayout, mainContainer;
    InsertFragment insertFragment;
    ListFragment listFragment;
    ClaendarFragment calendarFragment;
    SearchFragment searchFragment;
    MediaPlayer mediaPlayer;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Diary");

        edit = (Button)findViewById(R.id.edit);
        searech = (Button)findViewById(R.id.search);
        btn_play = (Button)findViewById(R.id.btn_play);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        list = (Button)findViewById(R.id.list);
        iVedit = (ImageView)findViewById(R.id.iVedit);
        iVdiary = (ImageView)findViewById(R.id.iVdiary);
        iVcalendar = (ImageView)findViewById(R.id.iVcalendar);
        iVsearch = (ImageView)findViewById(R.id.iVsearch);
        baseLayout = (LinearLayout)findViewById(R.id.baseLayout);
        mainContainer = (LinearLayout)findViewById(R.id.mainContainer);

        insertFragment = new InsertFragment();
        listFragment = new ListFragment();
        calendarFragment = new ClaendarFragment();
        searchFragment = new SearchFragment();

        edit.setOnClickListener(this);
        searech.setOnClickListener(this);
        list.setOnClickListener(this);

        iVedit.setOnClickListener(this);
        iVdiary.setOnClickListener(this);
        iVcalendar.setOnClickListener(this);
        iVsearch.setOnClickListener(this);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.snowman);
                mediaPlayer.start();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemInsert:
                Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
                startActivity(intent);
                break;

            case R.id.itemSearch:
                Intent intent2 = new Intent(getApplicationContext(), SearchActivity2.class);
                startActivity(intent2);
                break;

            case R.id.itemList:
                Intent intent3 = new Intent(getApplicationContext(), ListActivity2.class);
                startActivity(intent3);
                break;

            case R.id.itemHome:
                Intent intent4 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if( v == edit){
            Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
            startActivity(intent);
        } else  if(v == searech) {
            Intent intent = new Intent(getApplicationContext(), SearchActivity2.class);
            startActivity(intent);
        } else if(v == list) {
            Intent intent = new Intent(getApplicationContext(), ListActivity2.class);
            startActivity(intent);
        }
        else if(v == iVedit) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.mainContainer, insertFragment)
                    .commit();

        } else if(v == iVdiary) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.mainContainer, listFragment)
                    .commit();

        } else if(v == iVcalendar) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.mainContainer, calendarFragment)
                    .commit();

        } else if(v == iVsearch) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.mainContainer, searchFragment)
                    .commit();
        }
    }
}
