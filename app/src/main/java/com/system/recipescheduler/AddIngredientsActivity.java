package com.system.recipescheduler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.system.recipescheduler.database.MyDatabaseHelper;

import java.sql.ResultSet;

public class AddIngredientsActivity extends AppCompatActivity {

    EditText ingredient_name, quantity, base_measurement, category;
    Button add_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ingredient_name = findViewById(R.id.ingredient_name_input_add_ingredients);
        quantity = findViewById(R.id.quantity_input_add_ingredients);
        base_measurement = findViewById(R.id.base_measurement_input_add_ingredients);
        category = findViewById(R.id.category_input_add_ingredients);
        add_button = findViewById(R.id.add_button_add_ingredients);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper db = new MyDatabaseHelper();
                String query = String.format("SELECT ingredient_name, base_measurement, category " +
                        "FROM ingredients " +
                        "WHERE ingredient_name LIKE '%%%s%%'",ingredient_name.getText().toString().trim());
                ResultSet rs = db.selectQuery(query);
                try{
                    if(rs.next()){
                        ingredient_name.getText().toString().trim();

                    }
                }catch(Exception e){
                    Log.e("AddIngredientsActivity add_button onClick: ", e.getMessage());
                }


            }
        });


    }
}
