package com.system.recipescheduler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {

    //TODO: Design recipe card so i can see all the information of the recipe

    TextView recipe_id_txt, name_txt_1, name_txt_2, favourite_txt, health_rating_txt, duration_txt, category_txt, last_cook_txt;
    ImageView instructions_img;
    String recipe_id_string, name_string_1, name_string_2, favourite_string, health_rating_string, duration_string, category_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipe_id_txt = findViewById(R.id.recipe_id_txt_recipe);
        name_txt_1 = findViewById(R.id.name_txt_1_recipe);
        name_txt_2 = findViewById(R.id.name_txt_2_recipe);
        favourite_txt = findViewById(R.id.favourite_txt_recipe);
        health_rating_txt = findViewById(R.id.health_rating_txt_recipe);
        duration_txt = findViewById(R.id.duration_txt_recipe);
        category_txt = findViewById(R.id.category_txt_recipe);
        last_cook_txt = findViewById(R.id.last_cook_txt_recipe);
        instructions_img = findViewById(R.id.instructions_img_recipe);



        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name_string_1);
        }

        /*
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                pages = pages_input.getText().toString().trim();
                myDB.updateData(id, title, author, pages);
                //Refresh Activity
                Intent intent = new Intent(UpdateActivity.this, MainActivityExample.class);
                startActivity(intent);
                finish();
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


         */


    }


    void getAndSetIntentData(){


        if(getIntent().hasExtra("recipe_id") &&
            getIntent().hasExtra("name_1") &&

            getIntent().hasExtra("favourite") &&
            getIntent().hasExtra("duration") &&
            getIntent().hasExtra("category") &&
            getIntent().hasExtra("health_rating")){

            //Getting Data from Intent
            recipe_id_string = getIntent().getStringExtra("recipe_id");
            name_string_1 = getIntent().getStringExtra("name_1");
            if(getIntent().hasExtra("name_2")){
                name_string_2 = getIntent().getStringExtra("name_2");
            }

            favourite_string = getIntent().getStringExtra("favourite");
            duration_string = getIntent().getStringExtra("duration");
            category_string = getIntent().getStringExtra("category");
            health_rating_string = getIntent().getStringExtra("health_rating");

            //Setting Data from Intent
            recipe_id_txt.setText(recipe_id_string);
            name_txt_1.setText(name_string_1);
            if(name_string_2 != null){
                name_txt_2.setText(name_string_2);
            }else{
                name_txt_2.setVisibility(View.GONE);
            }
            favourite_txt.setText(favourite_string);
            duration_txt.setText(duration_string);
            category_txt.setText(category_string);
            health_rating_txt.setText(health_rating_string);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }



    /*

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

     */


}


