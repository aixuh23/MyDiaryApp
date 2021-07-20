package com.example.myapplication;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ListFragment extends Fragment {
    ListView listView;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    String res = "";

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list, container, false);

        View vi = inflater.inflate(R.layout.fragment_list, container, false);

        final ArrayList<String> datas = new ArrayList<>();
        final ArrayList<String> t1= new ArrayList<>();
        final ArrayList<String> texts = new ArrayList<>();

        listView = (ListView)vi.findViewById(R.id.listView);
        myDBHelper = new MyDBHelper(getContext());
        sqlDB = myDBHelper.getWritableDatabase();

        Cursor cursor = sqlDB.rawQuery("SELECT rName, rTitle, rText FROM diary", null);
        while(cursor.moveToNext()) {
            datas.add(cursor.getString(0) );
            t1.add(cursor.getString(1));
            texts.add(cursor.getString(2));
        }
        cursor.close();
        sqlDB.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,t1);
        //ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,titles);
        //ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,texts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Toast.makeText(getApplicationContext(), titles.get(i), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
                dlg.setTitle("오늘의 기록");
                dlg.setMessage(texts.get(i));
                dlg.setIcon(R.drawable.icon_diary);
                dlg.show();
            }
        });



        return vi;
    }

}
