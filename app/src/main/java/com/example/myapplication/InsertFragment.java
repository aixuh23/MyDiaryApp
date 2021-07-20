package com.example.myapplication;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InsertFragment extends Fragment {
    EditText name, title, text;
    AutoCompleteTextView style;
    Button insertBtn;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;

    public InsertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.activity_main, container, false);


        View vi = inflater.inflate(R.layout.fragment_insert, container, false);

        final String [] styles = {"매우 좋음", "좋음", "보통", "안좋음", "매우 안좋음"};

        name = (EditText)vi.findViewById(R.id.name);
        title = (EditText)vi.findViewById(R.id.title);
        text = (EditText)vi.findViewById(R.id.text);
        style = (AutoCompleteTextView)vi.findViewById(R.id.style);
        insertBtn = (Button)vi.findViewById(R.id.insertBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_dropdown_item_1line, styles );
        style.setAdapter(adapter);

        myDBHelper = new MyDBHelper(this.getContext());

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameVar = name.getText().toString();
                String titleVar = title.getText().toString();
                String textVar = text.getText().toString();
                String styleVar = style.getText().toString();

                int styleInt = 0;
                for(int i=0; i<styles.length; i++) {
                    if(styleVar.equals(styles[i])) {
                        styleInt = i;
                        break;
                    }
                }

                sqlDB = myDBHelper.getWritableDatabase();
                String qry = "INSERT INTO diary (rName, rTitle, rText, rStyle) values ('"
                        + nameVar + "','"
                        + titleVar + "','"
                        + textVar + "','"
                        + styleVar +
                        "')";

                Toast.makeText(getContext(), qry, Toast.LENGTH_SHORT).show();
                sqlDB.execSQL(qry);
                sqlDB.close();
            }
        });

        return vi;

    }

}
