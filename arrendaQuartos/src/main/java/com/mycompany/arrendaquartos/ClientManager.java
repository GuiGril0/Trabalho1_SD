/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arrendaquartos;

/**
 *
 * @author gui
 */
public class ClientManager {


    public static void main(String[] args) {
        String regHost = "localhost";
        String regPort = "9000";

        if(args.length != 2) {
            System.out.println("Faltam argumentos!");
            System.exit(1);
        }

        regHost = args[0];
        regPort = args[1];

        try {
            Ad ad = (Ad) java.rmi.Naming.lookup("rmi://" + regHost + ":" + regPort + "/ad");

            System.out.println("Bem vindo ao sistema de oferta e procura de alojamentos!");
            System.out.println("");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
