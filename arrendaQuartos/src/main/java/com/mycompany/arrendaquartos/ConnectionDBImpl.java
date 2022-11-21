/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arrendaquartos;
import java.io.*;
import java.sql.*;
import java.sql.DriverManager;


/**
 *
 * @author gui
 */
public class ConnectionDBImpl implements ConnectionDB {
    public static void main(String[] args) throws Exception{
        try{
            Class.forName ("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:9000/bd3";
            String username = "helder";
            String password = "1234";
            java.sql.Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão bem sucedida");
            java.sql.Statement stmt = con.createStatement();

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro na conexão");
        }
    }
}
