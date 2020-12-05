package com.example.sql_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        edDisc = findViewById(R.id.edDisc);
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
    public void onClickSave(View view) {
        myDbManager.insertToDb("Text", "Test");
        mainAdapter.updateAdapter(myDbManager.getFromDb());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        myDbManager.closeDb();
    }
}