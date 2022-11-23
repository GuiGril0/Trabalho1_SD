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

            con = DriverManager.getConnection("jdbc:postgresql://localhost/sd", "gui", "umaPass");
            stmt = con.createStatement();
            System.out.println("Conectado com sucesso");
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println("Problems setting the connection");
        }
    }

    //Insert into table

    public void insertIntoTableAdvertisement(Ad ad) {
        try {
            //stmt.executeUpdate("insert into advertisement values ('helder', 'procura', 'ativo', 350, 'masculino', 'elvas', 'casa do harry maguire', 'nsei', '2001-09-11' );");
            stmt.executeUpdate("insert into advertisement values ('"
                    +ad.getAdvertiser()+ "', " +
                    "'" +ad.getType()+ "', " +
                    "'" +ad.getState()+ "' "+ ad.getPrice()+
                    ", '" +ad.getGender()+ "', " +
                    "'" +ad.getLocal()+ "', " +
                    "'" +ad.getDescription()+ "', " +
                    "'" +ad.getTypology()+ "', " +
                    "'" +ad.getDate()+ "' );");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }
    }

    public void insertIntoTableMessages(Message msg) throws java.rmi.RemoteException{
        try {
            //"insert into msg values ('sender', 'content', 'date', 'aid');
            stmt.executeUpdate("insert into messages values ('"
                    +msg.getSender()+ "', " +
                    "'" +msg.getContent()+ ", " +
                    "'" +msg.getDate()+ ", " +
                    "'" +msg.getAid()+ "');");
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }
    }

    public void consultTableAdvertisement(String fields) {
        try {

            String[] arr = fields.split("&");
            String aux = "";
            for(String i : arr) {
                aux += i + " AND";
            }
            aux = aux.substring(0, aux.length() - 3);
            aux = aux.trim();
            ResultSet rs = stmt.executeQuery("SELECT * FROM advertisement WHERE " + aux + ";");

            if(rs.next() == true) {
                while (rs.next()) {
                    String advertiser = rs.getString("advertiser");
                    String typead = rs.getString("typead");
                    String statead = rs.getString("statead");
                    double price = rs.getDouble("price");
                    String gender = rs.getString("gender");
                    String localAd = rs.getString("description");
                    String description = rs.getString("description");
                    String typology = rs.getString("typology");
                    Date date = rs.getDate("date");
                    int aid = rs.getInt("aid");

                    if (aux.startsWith("aid"))
                        System.out.println("Detalhes do anúncio " + aid + ": " + description);
                    else
                        System.out.println("aid: " + aid + " | anunciante: " + advertiser + " | tipo: " +typead+ " | estado: " +statead+ " | preço: " +price+ " | género: " +gender+ " | local: " +localAd+ " | descrição: " +description+ " | tipologia: " +typology+ " | date: " +date);
                }
            }
            else
                System.out.println("Nenhum anúncio encontrado com as especificações desejadas!");
            rs.close(); // muito importante depois da consulta!
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }
    }

    public void consultTableMessages(String fields) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM messages WHERE " + fields + ";");
            if(rs.next() == true) {
                while(rs.next()) {
                    String sender = rs.getString("sender");
                    String content = rs.getString("content");
                    Date date = rs.getDate("date");
                    int aid = rs.getInt("aid");

                    System.out.println("remetente: " + sender + " | conteúdo: " + content + " | data: " + date + " | aid: " + aid);
                }
            }
            else
                System.out.println("Nenhuma mensagem encontrada!");
        } catch(Exception e) {
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

CREATE TABLE message(
aid integer FOREIGN KEY
mensagem varchar(1000)
remetente varchar(30)
date date
)
 */

