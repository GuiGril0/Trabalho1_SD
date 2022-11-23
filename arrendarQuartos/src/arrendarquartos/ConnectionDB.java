/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package arrendarquartos;

/**
 *
 * @author gui
 */
public interface ConnectionDB {
    public void connectDb();
    public void insertIntoTable(String table, Ad ad) throws java.rmi.RemoteException;
    public void consultTable(String table);
    public void closeConnection();
}
