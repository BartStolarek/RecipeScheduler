package com.system.recipescheduler;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.system.recipescheduler.database.MyDatabaseHelper;
import com.system.recipescheduler.database.MyDatabaseHelperExample;

public class AddRecipeActivity extends AppCompatActivity {


    EditText name_input, health_input, duration_input, category_input, favourite_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        name_input = findViewById(R.id.name_input);
        health_input = findViewById(R.id.health_input);
        duration_input = findViewById(R.id.duration_input);
        category_input = findViewById(R.id.category_input);
        favourite_input = findViewById(R.id.favourite_input);

        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddRecipeActivity.this);
                myDB.addRecipe(name_input.getText().toString().trim(),
                        Integer.parseInt(health_input.getText().toString().trim()),
                        duration_input.getText().toString().trim(),
                        category_input.getText().toString().trim(),
                        Integer.parseInt(favourite_input.getText().toString().trim()));

                //Refresh Activity
                Intent intent = new Intent(AddRecipeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}

