package com.example.informationfinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {
    public Connection dblink;

    public Connection getConnection (){
        String DB_user = "root";
        String DB_password = "shitload";
        String url = "jdbc:mysql://localhost:3306/simpleCrud";

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        dblink = DriverManager.getConnection(url,DB_user, DB_password);
        }catch (Exception e ) {
            e.printStackTrace();
        }
        return dblink;
    }
}
