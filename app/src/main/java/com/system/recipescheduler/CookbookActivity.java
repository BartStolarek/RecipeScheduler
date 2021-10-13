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
    ArrayList<String> book_id, book_title, book_author, book_pages;
    ArrayList<String> name, favourite, duration, category;
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
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        name = new ArrayList<>();
        favourite = new ArrayList<>();
        duration = new ArrayList<>();
        category = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(CookbookActivity.this,this, name, favourite, duration,
                category);
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

                        name.add(rs.getString("name"));
                        favourite.add(rs.getString("favourite"));
                        duration.add(rs.getString("duration"));
                        category.add(rs.getString("category"));
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

    //TODO: This is the method to connection to SQL server using the ConnectionHelper class
    //Learnt from: https://www.youtube.com/watch?v=dYt763QgaTg

    public void GetTextFromSQL(View v){
        /*
        recipeID_text = findViewById(R.id.recipeID_text);
        recipe_name_text = findViewById(R.id.recipe_name_text);
        recipe_duration_text = findViewById(R.id.recipe_duration_text);

         */

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();

            Connection connect = connectionHelper.Connectionclass();
            if(connect!=null){
                /*
                String query = "SELECT * from recipes";
                Statement st=connect.createStatement();
                ResultSet rs = st.executeQuery(query);



                while(rs.next()){

                    recipeID_text.setText(rs.getString(1));
                    recipe_name_text.setText(rs.getString(2));
                    recipe_duration_text.setText(rs.getString(3));


                }

                 */
                connect.close();
            }else{


                System.out.println("Connect = null");
            }
        }catch(Exception e){
            Log.e("GetTextFrom SQL Error is ", e.getMessage());
        }

    }

}
