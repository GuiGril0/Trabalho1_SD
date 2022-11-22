/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arrendaquartos;
import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author gui
 */
public class ConnectionDBImpl implements ConnectionDB {
    public static void main(String[] args) throws Exception {
        Connection con = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}