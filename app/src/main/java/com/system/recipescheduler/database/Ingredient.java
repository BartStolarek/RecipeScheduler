package com.system.recipescheduler.database;

import android.util.Log;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Ingredient {



    private String ingredient_name;
    private String recipe_id;
    private String ingredient_id;
    private String quantity;
    private String category;
    private String base_measurement;


    public List<Ingredient> getAllFromRecipe(String recipe_id) {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        Ingredient i = null;
        int recipe_id_int = Integer.parseInt(recipe_id);

        // initialise connection etc.
        MyDatabaseHelper db = new MyDatabaseHelper();
        String query = String.format("SELECT iq.recipe_id,iq.ingredient_id, iq.quantity, i.category, i.base_measurement, i.ingredient_name" +
                "FROM ingredient_quantity as iq INNER JOIN ingredients as i on (iq.ingredient_id = i.ingredient_id) " +
                "WHERE recipe_id LIKE '%%%s%%'",recipe_id_int);
        ResultSet resultSet = db.selectQuery(query);
        try {
            while (resultSet.next()) {
                i = new Ingredient();
                i.setRecipe_id(resultSet.getString(1));
                i.setIngredient_id(resultSet.getString(2));
                i.setQuantity(resultSet.getString(3));
                i.setCategory(resultSet.getString(4));
                i.setBase_measurement(resultSet.getString(5));
                i.setIngredient_name(resultSet.getString(6));
                ingredients.add(i);
            }
        }catch(Exception e){
            Log.e("Error getAllFromRecipe: ",e.getMessage());
            return null;
        }
        db.closeConnection();
        return ingredients;
        // in finally block close connection.
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(String ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBase_measurement() {
        return base_measurement;
    }

    public void setBase_measurement(String base_measurement) {
        this.base_measurement = base_measurement;
    }
}
