 package com.system.recipescheduler;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

 public class MainActivity extends AppCompatActivity {

    TextView week_text, date_text, day_text, cook_text, recipe_text, duration_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        week_text = findViewById(R.id.week_text);
        date_text = findViewById(R.id.date_text);
        day_text = findViewById(R.id.day_text);
        cook_text = findViewById(R.id.cook_text);
        recipe_text = findViewById(R.id.recipe_text);
        duration_text = findViewById(R.id.duration_text);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        date_text.setText(formatter.format(date));

        Calendar calendar = Calendar.getInstance();
        Date day = calendar.getTime();

        day_text.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));


    }
}