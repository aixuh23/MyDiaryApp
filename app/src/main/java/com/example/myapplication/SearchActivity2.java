package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity2 extends AppCompatActivity {
    EditText search_Auto;
    Button searchBtn;
    EditText searchEdit;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    ListView listView;
    ArrayAdapter<String> adapter;

    final ArrayList<String> datas = new ArrayList<>();
    final ArrayList<String> t1= new ArrayList<>();
    final ArrayList<String> texts = new ArrayList<>();
    final ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        setTitle("일상 찾기");

        search_Auto = (EditText) findViewById(R.id.search_Auto);
        searchBtn = (Button)findViewById(R.id.searchBtn);
        searchEdit = (EditText)findViewById(R.id.searchEdit);
        listView = (ListView)findViewById(R.id.listView);
        myDBHelper = new MyDBHelper(getApplicationContext());
        sqlDB = myDBHelper.getReadableDatabase();





        Cursor cursor = sqlDB.rawQuery("SELECT rName, rTitle, rText FROM diary", null);
        while(cursor.moveToNext()) {
            datas.add(cursor.getString(0) );
            list.add(cursor.getString(0) );
            t1.add(cursor.getString(1));
            texts.add(cursor.getString(2));
        }
        cursor.close();
        sqlDB.close();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,datas);

        listView.setAdapter(adapter);

        search_Auto.addTextChangedListener(new TextWatcher (){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = search_Auto.getText().toString();
                search(text);
            }
        });

    }

    public void search(String charText) {
        datas.clear();

        if(charText.length() == 0) {
            datas.addAll(list);
        } else {
            for(int i=0; i<list.size(); i++) {
                if(list.get(i).toLowerCase().contains(charText)) {
                    datas.add(list.get(i));
                }
            }
        }

        adapter.notifyDataSetChanged();
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
