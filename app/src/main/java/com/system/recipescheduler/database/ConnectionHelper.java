package com.system.recipescheduler.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.util.Log;
import java.sql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

import static android.content.Context.WIFI_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;
import static com.google.android.material.internal.ContextUtils.getActivity;

public class ConnectionHelper {

    //https://www.youtube.com/watch?v=dYt763QgaTg
    Connection connection;
    String uname, pass, ip, port, database;
    Context context;

    @SuppressLint("NewApi")
    public Connection Connectionclass(Context context){

        this.context = context;
        ip = getIPAddress(); // 10.0.2.2 was ip address of virtual device, from here: https://stackoverflow.com/questions/44790597/android-database-network-error-ioexception
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
            System.out.println("Connection obtained");
        }catch(Exception e){
            Log.e("ConnectionHelper - ConnectionClass: ", e.getMessage());
        }
        System.out.println("Returning connection");
        return connection;

    }



    public String getIPAddress(){
        String ipAddress = null;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
            System.out.println("Obtained IP address: " + ipAddress);
        }catch(Exception e){
            Log.e("ConnectionHelper - getIPAddress: ", e.getMessage());
        }
        return ipAddress;
    }
}
