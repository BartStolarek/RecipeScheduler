package com.system.recipescheduler.database;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String recipe_id, name, favourite, health_rating, duration, category, last_cook;
    List<Ingredient> ingredients = new ArrayList<Ingredient>();

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public String getHealth_rating() {
        return health_rating;
    }

    public void setHealth_rating(String health_rating) {
        this.health_rating = health_rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLast_cook() {
        return last_cook;
    }

    public void setLast_cook(String last_cook) {
        this.last_cook = last_cook;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
