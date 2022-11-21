/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arrendaquartos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author gui
 */

public class Client {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static void showMenu() {
        try {
            System.out.println("1 - Registar um novo anúncio");
            System.out.println("3 - Mostrar anúncios (procura)");
            System.out.println("2 - Mostrar anúncios (oferta)");
            System.out.println("5 - Obter detalhes de um anúncio (inserir aid do anúncio)");
            System.out.println("6 - Enviar mensagem para um anúncio (inserir aid do anúncio)");
            System.out.println("4 - Mostrar todos os anúncios de um anunciante");
            System.out.println("7 - Consultar mensagens de um anúncio (inserir aid do anúncio)");

            int option = Integer.parseInt(br.readLine());
            if(option < 1 || option > 7) {
                System.out.println("Selecione uma das opções!");
                System.out.println("");
                showMenu();
            }
            chooseOption(option);
        }
        catch (Exception e) {
            System.err.println("Argumentos inválidos");
            showMenu();
        }
    }

    static void chooseOption(int option) {
        switch (option) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
        }
    }

    static void registerAd() throws java.rmi.RemoteException, java.io.IOException{
        Ad ad = new AdImpl();

        System.out.print("Insira o tipo de anúncio: ");
        String type = "";
        do {
            type = br.readLine();
            type = type.toLowerCase();
        } while(!type.equals("oferta") || !type.equals("procura"));
        ad.setType(type);

        System.out.print("Insira o seu nome: ");
        String name = "";
        do {
            name = br.readLine();
        } while(name.length() == 0);
        ad.setAdvertiser(name);

        System.out.print("Insira a localização do alojamento: ");
        String local = "";
        do {
            local = br.readLine();
        } while(local.length() == 0);
        ad.setLocal(local);

        System.out.println("Insira o preço do alojamento: ");
        double price = -1;
        do {
            price = Double.parseDouble(br.readLine());
        } while(price < 0);
        ad.setPrice(price);

        System.out.print("Insira o género que pretende para potenciais interessados (masculino, feminino ou indiferente)");
        String gender = "";
        do {
            gender = br.readLine();
            gender = gender.toLowerCase();
        } while(!gender.equals("masculino") || !gender.equals("feminino") || !gender.equals("indiferente"));

        System.out.print("Insira a tipologia do alojamento (quarto ou T1, T2...");
        String typology = "";
        int n = 0;
        do {
            typology = br.readLine();
            typology = typology.toLowerCase();
            if(typology.equals("quarto"))
                break;
            String aux = typology.substring(1, typology.length());
            try {
                n = Integer.parseInt(aux);
            }
            catch (NumberFormatException e) {
                continue;
            }
        } while(!typology.startsWith("t"));
        typology = "T" + String.valueOf(n);
        ad.se

    }

    public static void main(String[] args) {
        String regHost = "localhost";
        String regPort = "9000";

        args[0] = regHost;
        args[1] = regPort;

        if(args.length != 1) {
            System.out.println("Faltam argumentos!");
            System.exit(1);
        }

        try {
            Ad ad = (Ad) java.rmi.Naming.lookup("rmi://" + regHost + ":" + regPort + "/ad");

            System.out.println("Bem vindo ao sistema de oferta e procura de alojamentos!");
            System.out.println("");

            showMenu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
