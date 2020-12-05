package com.example.sql_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sql_database.adapter.MainAdapter;
import com.example.sql_database.db.MyDbManager;

public class MainActivity extends AppCompatActivity {
    private MyDbManager myDbManager;
    private EditText edTitle, edDisc;
    private RecyclerView rcView;
    private MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    //метод для инициализации переменных
    private void init(){
        myDbManager = new MyDbManager(this);
        edTitle = findViewById(R.id.edTitle);
        edDisc = findViewById(R.id.edDesc);
        rcView = findViewById(R.id.rcView);
        mainAdapter = new MainAdapter(this);
        //расположение элементов по вертикали
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setAdapter(mainAdapter);


    }
    //открытие базы данных
    @Override
    protected void onResume() {
        super.onResume();
        myDbManager.openDb();
        //считать с бьазы данных
        mainAdapter.updateAdapter(myDbManager.getFromDb());
    }
    //запись в бд по кнопке save
    public void onClickAdd(View view) {
        //запуск активити с редактором
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        myDbManager.closeDb();
    }
}