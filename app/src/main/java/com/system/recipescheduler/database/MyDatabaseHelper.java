package com.system.recipescheduler.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.system.recipescheduler.R;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.DoubleStream;

public class MyDatabaseHelper {

    private Context context;
    private ConnectionHelper connectionHelper;
    private Connection connect;


    public MyDatabaseHelper(@Nullable Context context) {
        this.context = context;


    }

    /*
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + table_name +
                        " (" + column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        column_title + " TEXT, " +
                        column_author + " TEXT, " +
                        column_pages + " INTEGER);";
        db.execSQL(query);
    }

     */

    /*
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
     */

    /*
    public void addBook(String title, String author, int pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(column_title,title);
        cv.put(column_author,author);
        cv.put(column_pages,pages);
        long result = db.insert(table_name, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added succesfully!", Toast.LENGTH_SHORT).show();
        }
    }

     */


    @SuppressLint("DefaultLocale")
    public void addRecipe(String name, int health_rating, String duration, String category, int favourite){

        int time = Integer.parseInt(duration);
        time = time * 60 * 1000;

        duration = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));

        if(name.length()>50){
            Toast.makeText(context, "Failed - name too long (50 characters)", Toast.LENGTH_SHORT).show();
        }else if(health_rating<0 || health_rating > 5){
            Toast.makeText(context, "Failed - health rating must be between 0 and 5", Toast.LENGTH_SHORT).show();
        }else if(category.length()>10){
            Toast.makeText(context, "Failed - category too long (10 characters)", Toast.LENGTH_SHORT).show();
        }else if(favourite<0 || favourite > 1){
            Toast.makeText(context, "Failed - favourite incorrect input", Toast.LENGTH_SHORT).show();
        }else {
            try {
                connect = connectToDb();
                String query1 = String.format(
                        "INSERT INTO recipes (name, favourite, health_rating, duration, category) " +
                                "VALUES (%s, %d, %d, %s, %s);",name, favourite, health_rating, duration, category);

                String query = "INSERT INTO recipes (name, favourite, health_rating, duration, category) VALUES (  ?, ?, ?, ?, ?)";
                PreparedStatement prestmt = connect.prepareStatement(query);
                prestmt.setString(1,name);
                prestmt.setInt(2,favourite);
                prestmt.setInt(3,health_rating);
                prestmt.setString(4,duration);
                prestmt.setString(5,category);
                System.out.println(String.format("Submitting executeUpdate: %s",query));
                prestmt.executeUpdate();
                System.out.println(String.format("Submitted executeUpdate: %s",query));
                /*
                execute method can run both select and insert/update statements.
                executeQuery method execute statements that returns a result set by fetching some data from the database. It executes only select statements.
                executeUpdate method execute sql statements that insert/update/delete data at the database.
                 */
                Toast.makeText(context, "Added succesfully!", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Log.e("Error in addRecipe: ", e.getMessage());
                Toast.makeText(context, "Failed - " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }



    public void closeConnection(){
        try {
            connect.close();
        }catch(Exception e){
            Log.e("Error Closing Connection: ", e.getMessage());
        }
    }

    private Connection connectToDb(){
        try{
            connectionHelper = new ConnectionHelper();
            connect = connectionHelper.Connectionclass();
        }catch(Exception e){
            Log.e("Connection to DB failed: ", e.getMessage());
        }
        return connect;
    }

    public ResultSet readAllData(String table_name){
        ResultSet rs = null;

        /*
        //Map<String, Object> is just a key and value object stored together, like json.
        //List<Map<String, Object>> is just a list of key,value objects.
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> row = null;

         */
        try {

            connect = connectToDb();
            if (connect != null) {
                String query = String.format("SELECT * from %s", table_name);
                Statement st = connect.createStatement();
                rs = st.executeQuery(query);

                /*
                ResultSetMetaData metaData = rs.getMetaData();
                Integer columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    row = new HashMap<String, Object>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                    resultList.add(row);
                }

                 */

                return rs;

            } else {
                System.out.println("Error creating/executing on SQL database: connection is null");
            }
        }catch(Exception e){
            Log.e("Error creating/executing on SQL database: ", e.getMessage());
        }
        return rs;
    }

    /*
    public void updateData(String row_id, String title, String author, String pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_title, title);
        cv.put(column_author, author);
        cv.put(column_pages, pages);

        long result = db.update(table_name, cv, "_id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context,"Failed to Update!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfully Updated!",Toast.LENGTH_SHORT).show();

        }
    }
     */

    /*
    public void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name, "_id=?", new String[] {row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }


    }

     */

    /*
    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_name);
    }

     */

    public boolean test(String phoneNumber) {
        if (phoneNumber.length() == 8 && phoneNumber.startsWith("9")) {
            return true;
        } else {
            return false;
        }
    }

    public static double add(double... operands) {
        return DoubleStream.of(operands)
                .sum();
    }

    public static double multiply(double... operands) {
        return DoubleStream.of(operands)
                .reduce(1, (a, b) -> a * b);
    }



}
