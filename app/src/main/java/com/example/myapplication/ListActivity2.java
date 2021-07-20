package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity2 extends AppCompatActivity {
    ListView listView;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    String res = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        setTitle("다이어리 리스트");

        final ArrayList<String> datas = new ArrayList<>();
        final ArrayList<String> t1= new ArrayList<>();
        final ArrayList<String> texts = new ArrayList<>();
        final ArrayList<String> styles =  new ArrayList<>();

        listView = (ListView)findViewById(R.id.listView);
        myDBHelper = new MyDBHelper(getApplicationContext());
        sqlDB = myDBHelper.getWritableDatabase();

        Cursor cursor = sqlDB.rawQuery("SELECT rName, rTitle, rText, rStyle FROM diary", null);
        while(cursor.moveToNext()) {
            datas.add(cursor.getString(0) );
            t1.add(cursor.getString(1));
            texts.add(cursor.getString(2));
            styles.add(cursor.getString(3));
        }
        cursor.close();
        sqlDB.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,t1);
        //ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,titles);
        //ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,texts);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Toast.makeText(getApplicationContext(), titles.get(i), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dlg = new AlertDialog.Builder(ListActivity2.this);
                dlg.setTitle("오늘의 기록");
                dlg.setMessage(texts.get(i) + "\n\n"  + "하루 평가: "+ styles.get(i));
                dlg.setIcon(R.drawable.icon_diary);
                dlg.show();
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

}
