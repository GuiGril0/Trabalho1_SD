/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arrendaquartos;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author gui
 */
public class AdImpl extends UnicastRemoteObject implements Ad, java.io.Serializable{
    public String advertiser;
    public String type;
    public String state;
    public double price;
    public String gender;
    public String local;
    public String description;
    public String typology;
    public String date;

    int aid;

    public AdImpl() throws java.rmi.RemoteException {
        super();
    }

    public void setAdvertiser(String advertiser) throws java.rmi.RemoteException{
        this.advertiser = advertiser;
    }

    public void setType(String type) throws java.rmi.RemoteException {
        this.type = type;
    }

    public void setPrice(double price) throws java.rmi.RemoteException{
        this.price = price;
    }

    public void setGender(String gender) throws java.rmi.RemoteException{
        this.gender = gender;
    }

    public void setLocal(String local) throws java.rmi.RemoteException{
        this.local = local;
    }

    public void setDescription(String description) throws java.rmi.RemoteException{
        this.description = description;
    }

    public void setState(String state) throws java.rmi.RemoteException{
        this.state = state;
    }

    public void setAid(int aid) throws java.rmi.RemoteException {
        this.aid = aid;
    }

    public void registerAd(Ad ad) throws java.rmi.RemoteException {
        ConnectionDB bd = new ConnectionDBImpl();

    }
}
