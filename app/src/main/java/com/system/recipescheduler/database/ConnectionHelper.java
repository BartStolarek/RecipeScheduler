package com.system.recipescheduler.database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    //TODO: need to create the SQL database on microsofts sql database server thing
    //https://www.youtube.com/watch?v=dYt763QgaTg
    Connection con;
    String uname, pass, ip, port, database;

    @SuppressLint("NewApi")
    public Connection Connectionclass(){
        ip = "172.1.1.0";
        database="";
        uname="bartjs";
        pass="fusion93";
        port="1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;

        String ConnectionURL = null;

        try{

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+uname+";password="+pass+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }catch(Exception e){
            Log.e("Error ", e.getMessage());
        }

        return connection;

    }
}
