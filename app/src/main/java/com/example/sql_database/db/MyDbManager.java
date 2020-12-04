package com.example.sql_database.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Класс для удобной записи и чтения в базу данных
 */

public class MyDbManager {
    private Context context;
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) {
        this.context = context;
        myDBHelper = new MyDBHelper(context);
    }
    //метод для открытия бд
    public void openDb(){
        db = myDBHelper.getWritableDatabase();
    }
    public void insertToDb(String title, String disc){
        //Sqlite напрямую не принимает данные, надо использовать специальную hashmap
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TITLE, title);
        cv.put(MyConstants.DISC, disc);
        //загружаем данные hashmap в нашу дб
        db.insert(MyConstants.TABLE_NAME, null, cv);
    }
    //метод возвращает данные из таблици
    public List<String> getFromDb(){

    }
}
