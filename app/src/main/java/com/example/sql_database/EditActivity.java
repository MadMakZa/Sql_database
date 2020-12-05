package com.example.sql_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sql_database.db.MyDbManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {
    private ImageView imNewImage;
    private ConstraintLayout imageContainer;
    private FloatingActionButton fbAddImage;
//    private ImageButton imEditImage, imDeleteImage;
    private EditText edTitle, edDesc;
    private MyDbManager myDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
    }

    @Override
    protected void onResume(){
        super.onResume();
        myDbManager.openDb();
    }

    private void init(){
        edTitle = findViewById(R.id.edTitle);
        edDesc = findViewById(R.id.edDesc);
        fbAddImage = findViewById(R.id.fbAddImage);
        imageContainer = findViewById(R.id.imageContainer);
        myDbManager = new MyDbManager(this);
    }

    public void onClickSave(View view){
        String title = edTitle.getText().toString();
        String desc = edDesc.getText().toString();

        if(title.equals("")
                || desc.equals("")){
            Toast.makeText(this, R.string.text_empty, Toast.LENGTH_SHORT).show();
        } else {
            myDbManager.insertToDb(title, desc);
            Toast.makeText(this, R.string.text_saved, Toast.LENGTH_SHORT).show();
            finish();
            myDbManager.closeDb();
        }
    }
    public void onClickDeleteImage(View view){
        imageContainer.setVisibility(View.GONE);
        fbAddImage.setVisibility(View.VISIBLE);
    }
    public void onClickAddImage(View view){
        imageContainer.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);

    }
}