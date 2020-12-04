package com.example.sql_database.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    //конструктор дб
    public MyDBHelper(@Nullable Context context) {
        super(context, MyConstants.DB_NAME, null, MyConstants.DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //передача структуры нашей бд
        db.execSQL(MyConstants.TABLE_STRUCTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //сброс таблицы
        db.execSQL(MyConstants.DROP_TABLE);
        //создать опять новую дб
        onCreate(db);
    }
}
