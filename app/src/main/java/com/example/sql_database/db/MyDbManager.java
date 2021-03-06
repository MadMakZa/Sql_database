package com.example.sql_database.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sql_database.adapter.ListItem;

import java.util.ArrayList;
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
    public void insertToDb(String title, String disc, String url){
        //Sqlite напрямую не принимает данные, надо использовать специальную hashmap
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TITLE, title);
        cv.put(MyConstants.URL, url);
        cv.put(MyConstants.DISC, disc);
        //загружаем данные hashmap в нашу дб
        db.insert(MyConstants.TABLE_NAME, null, cv);
    }
    //метод возвращает данные из таблици
    public List<ListItem> getFromDb(){
        List<ListItem> tempList = new ArrayList<>();
        //специальный класс для выборки данных из колонок таблицы
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, null, null,null,null,null);
        //пробежать по колонне
        while(cursor.moveToNext()){
            ListItem item = new ListItem();
            String title = cursor.getString(cursor.getColumnIndex(MyConstants.TITLE)); //по колонке индекс
            String disc = cursor.getString(cursor.getColumnIndex(MyConstants.DISC)); //по колонке индекс
            String url = cursor.getString(cursor.getColumnIndex(MyConstants.URL)); //по колонке индекс

            item.setTitle(title);
            item.setTitle(disc);
            item.setTitle(url);

            tempList.add(item); //записать в наш список
        }
        cursor.close();
        return tempList;
    }
    //закрыть базу данных
    public void closeDb(){
        myDBHelper.close();
    }
}
