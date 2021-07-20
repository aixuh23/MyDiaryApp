package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 10;   //버전 업그레드 이후에  아래로 변경 못함
    public MyDBHelper(Context context) {
        super(context, "myDB6", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  diary ( "
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"rName CHAR(10), "
                +"rTitle CHAR(20), "
                +"rText CHAR(1000), "
                +"rStyle CHAR(20), "
                +"created datetime DEFAULT CURRENT_TIMESTAMP);");

        db.execSQL("INSERT INTO diary(rName, rTitle) values('안드로이드', '하하');");
        db.execSQL("INSERT INTO diary(rName, rTitle, rText, rStyle) values('김성중', 'My Diary 만들기', '나의 다이어리 만들기 프로젝트를 진행하고있다. 잘 만들어지면 좋겠다.', '보통')");
        db.execSQL("INSERT INTO diary(rName, rTitle, rText, rStyle) values('홍길동', '호부호형', '아버지를 아버지라 부르지못하고 형을 형이라 부르지 못하니 제가 어찌 떠나지 않을 수 있겠습니까..','매우 안좋음')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(DATABASE_VERSION == newVersion) {
            db.execSQL("DROP TABLE IF EXISTS diary");
            onCreate(db);
        }
    }
}
