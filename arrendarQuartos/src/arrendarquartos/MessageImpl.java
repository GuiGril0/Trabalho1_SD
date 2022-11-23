/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
/**
 *
 * @author gui
 */
public class MessageImpl extends UnicastRemoteObject implements Message{
    String sender;
    String content;
    Date date;
    int aid;

    public MessageImpl() throws java.rmi.RemoteException{
        super();
    }

    public void setSender(String sender) throws java.rmi.RemoteException{
        this.sender = sender;
    }
    public void setContent(String content) throws java.rmi.RemoteException{
        this.content = content;
    }
    public void setDate(Date date) throws java.rmi.RemoteException{
        this.date = date;
    }

    public void setAid(int aid) throws java.rmi.RemoteException{
        this.aid = aid;
    }

    public String getSender(){
        return sender;
    }
    public String getContent(){
        return content;
    }
    public Date getDate(){
        return date;
    }
    public int getAid(){
        return aid;
    }
}
