package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    EditText name, title, text;
    TextView text_count;
    AutoCompleteTextView style;
    Button insertBtn;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        setTitle("My Diary");

        final String [] styles = {"매우 좋음", "좋음", "보통", "안좋음", "매우 안좋음", "미선택" };

        name = (EditText)findViewById(R.id.name);
        title = (EditText)findViewById(R.id.title);
        text = (EditText)findViewById(R.id.text);
        text_count = (TextView)findViewById(R.id.text_count);
        style = (AutoCompleteTextView)findViewById(R.id.style);
        insertBtn = (Button)findViewById(R.id.insertBtn);

        text.addTextChangedListener(new TextWatcher() {
            String strCnt;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                strCnt = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 1000) {
                    text.setText(strCnt);
                    text.setSelection(start);
                } else {
                    text_count.setText(String.valueOf(s.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, styles );
        style.setAdapter(adapter);

        myDBHelper = new MyDBHelper(this);

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

                Toast.makeText(getApplicationContext(), qry, Toast.LENGTH_SHORT).show();
                sqlDB.execSQL(qry);
                sqlDB.close();
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
