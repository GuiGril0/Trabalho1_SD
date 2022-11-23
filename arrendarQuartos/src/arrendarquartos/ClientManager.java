/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;

import java.io.*;

/**
 *
 * @author gui
 */
public class ClientManager {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static void showMenu() throws IOException{
        System.out.println("1 - Mostrar anúncios (inserir estado ativo ou inativo)");
        System.out.println("2 - Obter detalhes de um anúncio (inserir aid do anúncio)");
        System.out.println("3 - Aprovar um anúncio (inserir aid do anúncio)");
        System.out.println("4 - Alterar o estado de um anúncio (inserir aid do anúncio)");
        System.out.println("0 - Sair");

        int option = -1;
        try {
            option = Integer.parseInt(br.readLine());
            if(option < 0 || option > 4) {
                System.out.println("Insira uma das opções apresentadas!");
                showMenu();
            }
        } catch(NumberFormatException e) {
            System.out.println("Opção inválida!");
            showMenu();
        }
    }

    private static void chooseOption(int option) {
        switch(option) {
            case 0:
                System.out.println("Adeus admin! Até à próxima!");
                System.exit(1);
            case 1:
                showAdsByState();
            case 2:
                getDetails();
            case 3:
                approveAd();
            case 4:
                changeState();
        }
    }

    public static void showAdsByState() {

    }

    public static void getDetails() {

    }

    public static void approveAd() {

    }

    public static void changeState() {

    }
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
