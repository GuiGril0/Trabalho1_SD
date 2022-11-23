/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package arrendarquartos;

import java.sql.Date;

/**
 *
 * @author gui
 */
public interface Message extends java.rmi.Remote {
    public void setSender(String sender) throws java.rmi.RemoteException;
    public void setContent(String content) throws java.rmi.RemoteException;
    public void setDate(Date date) throws java.rmi.RemoteException;
    public void setAid(int aid) throws java.rmi.RemoteException;

    public String getSender();
    public String getContent();
    public Date getDate();
    public int getAid();
}
