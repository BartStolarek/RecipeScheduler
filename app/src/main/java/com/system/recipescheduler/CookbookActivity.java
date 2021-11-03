package com.system.recipescheduler;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.system.recipescheduler.database.ConnectionHelper;
import com.system.recipescheduler.database.MyDatabaseHelper;
import com.system.recipescheduler.database.MyDatabaseHelperExample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookbookActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelper myDB;

    ArrayList<String> recipe_id, name, favourite, duration, category, health_rating;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookbook);
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CookbookActivity.this, AddRecipeActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(CookbookActivity.this);
        recipe_id = new ArrayList<>();
        name = new ArrayList<>();
        favourite = new ArrayList<>();
        duration = new ArrayList<>();
        category = new ArrayList<>();
        health_rating = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(CookbookActivity.this, this, recipe_id, name, favourite, duration,
                category, health_rating);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CookbookActivity.this));
    }

    void storeDataInArrays(){
        ResultSet rs = myDB.readAllData("recipes");
        if (rs != null) {
            try {
                if (rs.next() == false) {
                    System.out.println("rs.next() == false");
                    empty_imageview.setVisibility(View.VISIBLE);
                    no_data.setVisibility(View.VISIBLE);
                } else {
                    empty_imageview.setVisibility(View.GONE);
                    no_data.setVisibility(View.GONE);
                    do {
                        recipe_id.add(rs.getString("recipe_id"));
                        name.add(rs.getString("name"));
                        favourite.add(rs.getString("favourite"));
                        duration.add(rs.getString("duration"));
                        category.add(rs.getString("category"));
                        health_rating.add(rs.getString("health_rating"));
                    } while (rs.next());

                }
            } catch (Exception e) {
                Log.e("Error reading ResultSet: ", e.getMessage());
            }
        }else{
            System.out.println("ResultSet error: Result set obtained from readAllData = null");
        }
        myDB.closeConnection();
    }

    // This is the method to connect to SQL server using the ConnectionHelper class
    //Learnt from: https://www.youtube.com/watch?v=dYt763QgaTg
    public void GetTextFromSQL(View v) {


    }



}
