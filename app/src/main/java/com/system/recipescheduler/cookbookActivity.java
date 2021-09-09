package com.system.recipescheduler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.system.recipescheduler.database.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class cookbookActivity extends AppCompatActivity {

    TextView recipeID_text, recipe_name_text, recipe_duration_text;
    Button update_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookbook);

        update_button = findViewById(R.id.update_sql_button);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button clicked registered");
                GetTextFromSQL(v);
            }
        });


    }

    //TODO: This is the method to connection to SQL server using the ConnectionHelper class
    //Learnt from: https://www.youtube.com/watch?v=dYt763QgaTg

    public void GetTextFromSQL(View v){

        recipeID_text = findViewById(R.id.recipeID_text);
        recipe_name_text = findViewById(R.id.recipe_name_text);
        recipe_duration_text = findViewById(R.id.recipe_duration_text);
        System.out.println("activity ID's have been assigned");
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            System.out.println("Establishing connection..");
            Connection connect = connectionHelper.Connectionclass();
            if(connect!=null){
                String query = "SELECT * from recipes";
                Statement st=connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    recipeID_text.setText(rs.getString(1));
                    recipe_name_text.setText(rs.getString(2));
                    recipe_duration_text.setText(rs.getString(3));
                }
                connect.close();
            }else{


                System.out.println("Connect = null");
            }
        }catch(Exception e){
            Log.e("GetTextFrom SQL Error is ", e.getMessage());
        }

    }
}
