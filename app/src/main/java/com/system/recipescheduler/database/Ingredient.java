package com.system.recipescheduler.database;

import android.util.Log;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private int id;
    private String name;
    private String surname;


    private String recipe_id;
    private String ingredient_id;
    private String quantity;
    private String category;
    private String base_measurement;

    // getters and setters

    public List<Ingredient> getAllFromRecipe(String recipe_id) {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        Ingredient u = null;
        int recipe_id_int = Integer.parseInt(recipe_id);

        // initialise connection etc.
        MyDatabaseHelper db = new MyDatabaseHelper();
        String query = String.format("SELECT * " +
                "FROM ingredient_quantity as iq INNER JOIN ingredients as i on (iq.ingredient_id = i.ingredient_id) " +
                "WHERE recipe_id LIKE '%%%s%%'",recipe_id_int);
        ResultSet resultSet = db.selectQuery(query);
        try {
            while (resultSet.next()) {
                u = new User();
                u.setId(resultSet.getInt(1));
                u.setName(resultSet.getString(2));
                u.setSurname(resultSet.getString(3));
                ingredients.add(u);
            }
        }catch(Exception e){
            Log.e("Error getAllFromRecipe: ",e.getMessage());
            return null;
        }
        return ingredients;
        // in finally block close connection.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
