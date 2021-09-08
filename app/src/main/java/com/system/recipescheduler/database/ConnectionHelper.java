package com.system.recipescheduler.database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    //TODO: need to create the SQL database on microsofts sql database server thing
    //https://www.youtube.com/watch?v=dYt763QgaTg
    Connection connection;
    String uname, pass, ip, port, database;

    @SuppressLint("NewApi")
    public Connection Connectionclass(){
        ip = "172.1.1.0"; //Could be 172.1.2.0
        database="";
        uname="bartjs";
        pass="fusion93";
        port="1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        connection = null;

        String ConnectionURL = null;

        try{
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
            }catch(Exception error){
                Log.e("Class.forName error:",error.getMessage());
            }
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+uname+";password="+pass+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }catch(Exception e){
            Log.e("Connection Error is ", e.getMessage());
        }

        return connection;

    }
}
