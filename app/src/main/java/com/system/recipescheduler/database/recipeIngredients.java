package com.system.recipescheduler.database;

import java.util.ArrayList;
import java.util.List;

public class recipeIngredients {
    private int id;
    private String name;
    private String surname;

    // getters and setters

    public List<recipeIngredients> getAll() {
        List<recipeIngredients> users = new ArrayList<recipeIngredients>();
        recipeIngredients u = null;
        // initialise connection etc.
        while (resultSet.next()) {
            u = new User();
            u.setId(resultSet.getInt(1));
            u.setName(resultSet.getString(2));
            u.setSurname(resultSet.getString(3));
            users.add(u);
        }
        return users;
        // in finally block close connection.
    }
}
