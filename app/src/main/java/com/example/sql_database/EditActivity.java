package com.example.sql_database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sql_database.adapter.ListItem;
import com.example.sql_database.db.MyConstants;
import com.example.sql_database.db.MyDbManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {
    private ImageView imNewImage;
    private ConstraintLayout imageContainer;
    private FloatingActionButton fbAddImage;
//    private ImageButton imEditImage, imDeleteImage;
    private EditText edTitle, edDesc;
    private MyDbManager myDbManager;
    private final int PICK_IMAGE_CODE = 123;
    private String tempUrl = "empty";
    private boolean isEditState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
        getMyIntents();
    }

    @Override
    protected void onResume(){
        super.onResume();
        myDbManager.openDb();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE_CODE && data != null){
            tempUrl = data.getData().toString();
            imNewImage.setImageURI(data.getData());
        }
    }

    private void init(){
        edTitle = findViewById(R.id.edTitle);
        edDesc = findViewById(R.id.edDesc);
        imNewImage = findViewById(R.id.imNewImage);
        fbAddImage = findViewById(R.id.fbAddImage);
        imageContainer = findViewById(R.id.imageContainer);
        myDbManager = new MyDbManager(this);
    }

    private void getMyIntents(){

        Intent i = getIntent();
        if(i != null){

            ListItem item = (ListItem) i.getSerializableExtra(MyConstants.LIST_ITEM_INTENT);
            isEditState = i.getBooleanExtra(MyConstants.EDIT_STATE, true);

            if (!isEditState){
                edTitle.setText(item.getTitle());
                edDesc.setText(item.getDesc());
            }

        }
    }

    public void onClickSave(View view){
        String title = edTitle.getText().toString();
        String desc = edDesc.getText().toString();

        if(title.equals("")
                || desc.equals("")){
            Toast.makeText(this, R.string.text_empty, Toast.LENGTH_SHORT).show();
        } else {
            myDbManager.insertToDb(title, tempUrl, desc);
            Toast.makeText(this, R.string.text_saved, Toast.LENGTH_SHORT).show();
            finish();
            myDbManager.closeDb();
        }
    }
    public void onClickDeleteImage(View view){
        //картинка по умолчанию
        imNewImage.setImageResource(R.drawable.image_def);
        tempUrl = "empty";

        imageContainer.setVisibility(View.GONE);
        fbAddImage.setVisibility(View.VISIBLE);
    }
    public void onClickAddImage(View view){
        imageContainer.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);

    }
    public void onClickChooseImage(View view){
        Intent chooser = new Intent(Intent.ACTION_PICK);
        chooser.setType("image/*"); //искать во всех местах где есть картинки
        startActivityForResult(chooser, PICK_IMAGE_CODE);

    }
}