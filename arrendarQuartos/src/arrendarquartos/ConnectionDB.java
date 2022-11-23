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
    public void insertIntoTableAdvertisement(Ad ad) throws java.rmi.RemoteException;
    public void consultTableAdvertisement(String fields);
    public void insertIntoTableMessages(Message msg) throws java.rmi.RemoteException;
    public void consultTableMessages(String fields);
    public void closeConnection();
}
