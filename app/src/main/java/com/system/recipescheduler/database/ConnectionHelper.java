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
        System.out.println("Inside connection class");
        ip = "192.168.1.181"; //Could be 172.1.2.0
        database="RecipeSchedulerDb";
        uname="bartjs";
        pass="fusion93";
        port="1433";
        //If issue with IP have a look at these two website
        //https://stackoverflow.com/questions/27793477/java-sql-sqlexception-network-error-ioexception-failed-to-connect
        //https://amberpos.zendesk.com/hc/en-us/articles/215978723-How-to-find-your-database-IP-address-and-SQL-port
        //https://stackoverflow.com/questions/142142/sql-query-to-get-servers-ip-address#:~:text=To%20do%20this%2C%20create%20a,ll%20get%20the%20IP%20address.

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        connection = null;

        String ConnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            System.out.println("Assigned JDBC driver");

            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+uname+";password="+pass+";";

            connection = DriverManager.getConnection(ConnectionURL);
            System.out.println("Used drivermanager to get connection via connection URL ");
        }catch(Exception e){
            Log.e("Connection Error is ", e.getMessage());
        }
        System.out.println("Returning connection");
        return connection;

    }
}
