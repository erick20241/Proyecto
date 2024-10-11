package com.example.mycal.conexion;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBd {
    private String ip= "172.27.108.227";
private String usuario = "sa";
private String password="1234";
    private String basedatos ="Login";
    // no usages
    @SuppressLint("NewApi")
    public Connection connect() {
        Connection connection = null;
        String connectionURL = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + this.ip + "/" + this.basedatos + ";user=" + this.usuario + ";password=" + this.password + ";";
            connection = DriverManager.getConnection(connectionURL);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("tag", "Error de connexion SQL: "+ e.getMessage());
        }
        return connection;
    }
}
