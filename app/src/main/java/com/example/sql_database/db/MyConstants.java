package com.example.sql_database.db;

public class MyConstants {
    public static final String TABLE_NAME = "my_table";
    //колонки
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String DISC = "disc";
    //имя базы данных
    public static final String DB_NAME = "my_db.db"; // название базы данных с расширением
    public static final int DB_VERSION = 1;
    //структура таблицы
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT, " +
            DISC + " TEXT)";
    //для сброса таблицы
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
