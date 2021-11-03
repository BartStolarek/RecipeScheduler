package com.system.recipescheduler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.system.recipescheduler.database.Ingredient;
import com.system.recipescheduler.database.MyDatabaseHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddIngredientsActivity extends AppCompatActivity {

    EditText ingredient_name, quantity, recipe_measurement, category;
    Button add_button;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ingredient_name = findViewById(R.id.ingredient_name_input_add_ingredients);
        quantity = findViewById(R.id.quantity_input_add_ingredients);
        recipe_measurement = findViewById(R.id.recipe_measurement_input_add_ingredients);
        category = findViewById(R.id.category_input_add_ingredients);
        add_button = findViewById(R.id.add_button_add_ingredients);
        listView = findViewById(R.id.listview_item);

        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        if(ingredients.size()>0) {
            CustomAdapterListView customAdapterListView = new CustomAdapterListView(AddIngredientsActivity.this, ingredients);
            listView.setAdapter(customAdapterListView);
        }

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper db = new MyDatabaseHelper(AddIngredientsActivity.this);
                Ingredient ingredient = new Ingredient();

                String query = String.format("SELECT ingredient_name, base_measurement, category, ingredient_id " +
                        "FROM ingredients " +
                        "WHERE ingredient_name LIKE '%%%s%%'",ingredient_name.getText().toString().trim());
                ResultSet rs = db.selectQuery(query);
                try{
                    if(rs.next()){
                        ingredient.setIngredient_id(rs.getString(4).trim());
                        ingredient.setBase_measurement(rs.getString(2).trim());
                    }else{
                        ingredient.setBase_measurement(recipe_measurement.getText().toString().trim());
                    }
                    ingredient.setRecipe_measurement(recipe_measurement.getText().toString().trim());
                    ingredient.setQuantity(quantity.getText().toString().trim());
                    ingredient.setIngredient_name(ingredient_name.getText().toString().trim());
                    ingredient.setCategory(category.getText().toString().trim());
                    ingredients.add(ingredient);
                    Toast.makeText(AddIngredientsActivity.this, "Added Ingredient to list.", Toast.LENGTH_LONG).show();

                    // Refresh Activity
                    finish();
                    startActivity(getIntent());

                }catch(Exception e){
                    Log.e("AddIngredientsActivity add_button onClick: ", e.getMessage());
                }finally{
                    db.closeConnection();
                }


            }
        });


    }
}
