package com.system.recipescheduler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.stream.DoubleStream;

public class MyDatabaseHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String database_name = "BookLibrary.db";
    private static final int database_version = 1;

    private static final String table_name = "my_library";
    private static final String column_id = "_id";
    private static final String column_title = "book_title";
    private static final String column_author = "book_author";
    private static final String column_pages = "book_pages";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, database_version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + table_name +
                        " (" + column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        column_title + " TEXT, " +
                        column_author + " TEXT, " +
                        column_pages + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

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

    public Cursor readAllData(){
        String query = "SELECT * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

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

    public void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name, "_id=?", new String[] {row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_name);
    }

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
