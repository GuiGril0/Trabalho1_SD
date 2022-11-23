/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author gui
 */
public class ConnectionDBImpl implements ConnectionDB {

    public static Statement stmt = null;
    public static Connection con;
    //Connect to db

    public void connectDb() {


        try{
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection("jdbc:postgresql://localhost/bd4", "gui", "umaQualquer");
            stmt = con.createStatement();
            System.out.println("Conectado com sucesso");
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println("Problems setting the connection");
        }
    }

    //Insert into table

    public void insertIntoTable(String table, Ad ad) {
        try {
            //stmt.executeUpdate("insert into advertisement values ('helder', 'procura', 'ativo', 350, 'masculino', 'elvas', 'casa do harry maguire', 'nsei', '2001-09-11' );");
            stmt.executeUpdate("insert into" +table+ "values ('"
                    +ad.getAdvertiser()+ "', " +
                    "'" +ad.getType()+ "', " +
                    "'" +ad.getState()+ "' "+ ad.getPrice());
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }
    }

    public void consultTable(String table, String fields) {
        try {

            String[] arr = fields.split("&");
            String aux = "";
            for(String i : arr) {
                aux += i + " ";
            }
            aux = aux.trim();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + "WHERE " + aux + ";");

            while (rs.next()) {
                String advertiser = rs.getString("advertiser");
                String typead = rs.getString("typead");
                String statead = rs.getString("statead");
                double price = rs.getDouble("price");
                String gender = rs.getString("gender");
                String localad = rs.getString("description");
                String description = rs.getString("description");
                String typology = rs.getString("typology");
                Date date = rs.getDate("date");
                int aid = rs.getInt("aid");

                Ad ad = new AdImpl();
                ad.setAdvertiser(advertiser);
                ad.setType(typead);
                ad.setState(statead);
                ad.setPrice(price);
                ad.setGender(gender);
                ad.setLocal(localad);
                ad.setDescription(description);
                ad.setTypology(typology);
                ad.setDate(date);

                System.out.println("adv: "+advertiser+"  typead: "+typead+" statead: "+price+ "   gender: "+gender+ "localad: "+localad+ "description: "+description+ " typology: "+typology+" date: "+date+" aid: " +aid);
            }
            rs.close(); // muito importante depois da consulta!
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }
    }


    public void closeConnection() {
        try {
            stmt.close();
            con.close();
            System.out.println("xau");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*
CREATE TABLE advertisement(
  advertiser varchar(30),
  typead varchar(15),
  statead varchar(15),
  price NUMERIC,
  gender varchar(15),
  localad varchar(15),
  description varchar(200),
  typology varchar(15),
  date DATE,
  aid SERIAL PRIMARY KEY
  );
 */