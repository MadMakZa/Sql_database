package com.example.sql_database.db;

/**
 * Класс для всех констант используемых в нашей базе данных
 */

public class MyConstants {
    public static final String LIST_ITEM_INTENT = "list_item_intent";
    public static final String EDIT_STATE = "edit_state";
    public static final String TABLE_NAME = "my_table";
    //колонки
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String DISC = "disc";
    public static final String URL = "url";
    //имя базы данных
    public static final String DB_NAME = "my_db.db"; // название базы данных с расширением
    public static final int DB_VERSION = 2;
    //структура таблицы (разметка)
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT, " +
            URL + " TEXT," +
            DISC + " TEXT)";
    //для сброса таблицы
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
