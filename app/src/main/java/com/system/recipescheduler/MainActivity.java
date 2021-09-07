 package com.system.recipescheduler;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.system.recipescheduler.database.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

 public class MainActivity extends AppCompatActivity {

    TextView week_text, date_text, day_text, cook_text, recipe_text, duration_text;
    Button today_button, cookbook_button;

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

        today_button = findViewById(R.id.today_button);
        cookbook_button = findViewById(R.id.cookbook_button);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        date_text.setText(formatter.format(date));

        Calendar calendar = Calendar.getInstance();
        Date day = calendar.getTime();

        day_text.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));

        today_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, todayActivity.class);
                startActivity(intent);

            }
        });

        cookbook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cookbookActivity.class);
                startActivity(intent);

            }
        });



    }


    //TODO: This is the method to connection to SQL server using the ConnectionHelper class
    //Learnt from: https://www.youtube.com/watch?v=dYt763QgaTg
    /*
    public void GetTextFromSQL(View v){
        TextView tx1 = (TextView) findViewById(R.id.textView);
        TextView tx2 = (TextView) findViewById(R.id.textView);
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            Connection connect = connectionHelper.Connectionclass();
            if(connect!=null){
                String query = "SELECT * from table_name";
                Statement st=connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    tx1.setText(rs.getString(1));
                }
            }else{
                //ConnectionResult="Check Connection";
            }
        }catch(Exception e){

        }

    }
    */

}