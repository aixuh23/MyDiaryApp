package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    TextView textView;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    String res = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("My Diary");

        textView = (TextView)findViewById(R.id.textView);
        myDBHelper = new MyDBHelper(getApplicationContext());
        sqlDB = myDBHelper.getWritableDatabase();

        Cursor cursor = sqlDB.rawQuery("SELECT rName, rTitle, rText, rStyle, strftime('%Y-%m-%d', created) FROM diary", null);
        while(cursor.moveToNext()) {
            res += "\n\n";
            res += cursor.getString(0) +
                    " " + cursor.getString(1) +
                    " " + cursor.getString(2 ) +
                    " " + cursor.getString(3) +
                    " " + cursor.getString(4);
        }
        cursor.close();
        sqlDB.close();

        textView.setText(res);
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

}
