package com.kaffle.kaffle.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
    private static Connection conn = null;

    public static void closeConnection() {
        if(conn != null){
            try{
                conn.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("doesn\'t connected yet.");
        }
    }

    public static Connection openConnection(){
        if(conn == null){
            try{
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:" + "kaffle/db/kor_dict.db");
                System.out.println("connection successed!");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}
