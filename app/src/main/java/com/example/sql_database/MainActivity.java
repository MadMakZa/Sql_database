package com.example.sql_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sql_database.db.MyDbManager;

public class MainActivity extends AppCompatActivity {
    private MyDbManager myDbManager;
    private EditText edTitle, edDisc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbManager = new MyDbManager(this);
        edTitle = findViewById(R.id.edTitle);
        edDisc = findViewById(R.id.edDisc);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myDbManager.openDb();
    }
    //запись в бд по кнопке save
    public void onClickSave(View view) {
        myDbManager.insertToDb(edTitle.getText().toString(), edDisc.getText().toString());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        myDbManager.closeDb();
    }
}