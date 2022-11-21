/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.arrendaquartos;

import java.time.LocalDate;

/**
 *
 * @author gui
 */
public interface Ad extends java.rmi.Remote {
    // métodos para o cliente geral
    public void setAdvertiser(String advertiser) throws java.rmi.RemoteException;
    public void setType(String type) throws java.rmi.RemoteException;
    public void setPrice(double price) throws java.rmi.RemoteException;
    public void setGender(String gender) throws java.rmi.RemoteException;
    public void setLocal(String local) throws java.rmi.RemoteException;
    public void setDescription(String description) throws java.rmi.RemoteException;
    public void setTypology(String typology) throws java.rmi.RemoteException;
    public void setDate(LocalDate date) throws java.rmi.RemoteException;

    //métodos para o cliente de gestão
    public void setAid(int aid) throws java.rmi.RemoteException;
    public void setState(String state) throws java.rmi.RemoteException;

    //métodos para a base de dados
    public void sendAdToDB(Ad ad) throws java.rmi.RemoteException;
}
