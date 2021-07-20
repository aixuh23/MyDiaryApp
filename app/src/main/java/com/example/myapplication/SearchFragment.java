package com.example.myapplication;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    EditText search_Auto;
    Button searchBtn;
    ListView listView;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    String res = "";
    ArrayAdapter<String> adapter;

    final ArrayList<String> datas = new ArrayList<>();
    final ArrayList<String> t1 = new ArrayList<>();
    final ArrayList<String> texts = new ArrayList<>();
    final ArrayList<String> list = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_search, container, false);
        View vi = inflater.inflate(R.layout.fragment_search, container, false);
        search_Auto = (EditText) vi.findViewById(R.id.search_Auto);
        searchBtn = (Button) vi.findViewById(R.id.searchBtn);
        listView = (ListView) vi.findViewById(R.id.listView);
        myDBHelper = new MyDBHelper(getContext());
        sqlDB = myDBHelper.getWritableDatabase();

        Cursor cursor = sqlDB.rawQuery("SELECT rName, rTitle, rText FROM diary", null);
        while (cursor.moveToNext()) {
            datas.add(cursor.getString(0));
            list.add(cursor.getString(0));
            t1.add(cursor.getString(1));
            texts.add(cursor.getString(2));
        }
        cursor.close();
        sqlDB.close();

        adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, datas);

        listView.setAdapter(adapter);

        search_Auto.addTextChangedListener(new TextWatcher() {

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
        return vi;
    }

    public void search(String charText) {
        datas.clear();

        if (charText.length() == 0) {
            datas.addAll(list);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).toLowerCase().contains(charText)) {
                    datas.add(list.get(i));
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}

