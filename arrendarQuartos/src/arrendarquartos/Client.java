/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;

import java.io.*;
import java.sql.Date;

/**
 *
 * @author gui
 */
public class Client {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ConnectionDB bd;
    private static void showMenu() {
        try {
            System.out.println("1 - Registar um novo anúncio");
            System.out.println("2 - Mostrar anúncios (oferta ou procura)");
            System.out.println("3 - Mostrar todos os anúncios de um anunciante");
            System.out.println("4 - Obter detalhes de um anúncio (inserir aid do anúncio)");
            System.out.println("5 - Enviar mensagem para um anúncio (inserir aid do anúncio)");
            System.out.println("6 - Consultar mensagens de um anúncio (inserir aid do anúncio)");
            System.out.println("0 - Sair");

            int option = -1;
            try {
                option = Integer.parseInt(br.readLine());
                if (option < 0 || option > 6) {
                    System.out.println("Insira uma das opções apresentadas!");
                    showMenu();
                }
            } catch(NumberFormatException e) {
                System.out.println("Opção inválida!");
                showMenu();
            }

            chooseOption(option);
        }
        catch (Exception e) {
            System.err.println("Argumentos inválidos!");
            showMenu();
        }
    }

    private static void chooseOption(int option) throws java.rmi.RemoteException, java.io.IOException{
        switch (option) {
            case 0:
                System.out.println("Adeus! Até à próxima!");
                System.exit(1);
                break;
            case 1:
                registerAd();
                break;
            case 2:
                searchAds();
                break;
            case 3:
                searchByAdvertiser();
                break;
            case 4:
                getDetailsByAid();
                break;
            case 5:
                sendMessage();
                break;
            case 6:
                showMessages();
                break;
            default:
                showMenu();
        }
        showMenu();
    }

    static void registerAd() throws java.rmi.RemoteException, java.io.IOException{
        Ad ad = new AdImpl();

        String type = "";
        do {
            System.out.print("Insira o tipo de anúncio: ");
            type = br.readLine();
            type = type.toLowerCase();
        } while(!type.equals("oferta") || !type.equals("procura"));
        ad.setType(type);

        String name = "";
        do {
            System.out.print("Insira o seu nome: ");
            name = br.readLine();
        } while(name.length() == 0);
        ad.setAdvertiser(name);

        String local = "";
        do {
            System.out.print("Insira a localização do alojamento: ");
            local = br.readLine();
        } while(local.length() == 0);
        ad.setLocal(local);

        double price = -1;
        do {
            System.out.println("Insira o preço do alojamento: ");
            price = Double.parseDouble(br.readLine());
        } while(price < 0);
        ad.setPrice(price);

        String gender = "";
        do {
            System.out.print("Insira o género que pretende para potenciais interessados (masculino, feminino ou indiferente)");
            gender = br.readLine();
            gender = gender.toLowerCase();
        } while(!gender.equals("masculino") || !gender.equals("feminino") || !gender.equals("indiferente"));

        String typology = "";
        int n = 0;
        do {
            System.out.print("Insira a tipologia do alojamento (quarto ou T1, T2...");
            typology = br.readLine();
            typology = typology.toLowerCase();
            if(typology.equals("quarto"))
                break;
            else if(typology.startsWith("t")){
                String aux = typology.substring(1, typology.length());
                try {
                    n = Integer.parseInt(aux);
                    if(n < 0) {
                        System.out.println("Formato errado! TN (sendo N um número inteiro positivo");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Formato errado! TN (sendo N um número inteiro positivo");
                    continue;
                }
            }
        } while(true);
        if(typology.startsWith("t"))
            typology = "T" + String.valueOf(n);
        ad.setTypology(typology);

        ad.setDate(new Date(System.currentTimeMillis()));
        ad.setState("inativo");

        bd.insertIntoTableAdvertisement(ad);
    }

    public static void searchAds() throws java.rmi.RemoteException, IOException {
        String typeAd = "";
        String fields = "";
        do {
            System.out.println("Tipo de anúncio:");
            System.out.println("o - oferta");
            System.out.println("p - procura");
            typeAd = br.readLine();
            typeAd = typeAd.toLowerCase();
        } while(!typeAd.equals("o") || !typeAd.equals("p") || !typeAd.equals("oferta") || !typeAd.equals("procura"));
        if(typeAd.equals("o") || typeAd.equals("oferta"))
            fields = "typead=oferta&";
        else
            fields = "typead=procura&";
        fields += "statead=ativo&";

        String chooseFilters = "";
        do {
            System.out.println("Deseja utilizar campos de procura adicionais?");
            System.out.println("s - sim");
            System.out.println("n - não");
            chooseFilters = br.readLine();
            chooseFilters = chooseFilters.toLowerCase();
        } while(!chooseFilters.equals("s") || !chooseFilters.equals("n") || !chooseFilters.equals("sim") || !chooseFilters.equals("não"));

        if(chooseFilters.equals("sim") || chooseFilters.equals("s")) {
            String aux = "";

            System.out.println("Insira a localização desejada");
            aux = br.readLine();
            if(!aux.equals(""))
                fields += "localad=" + aux + "&";
            aux = "";
            do {
                System.out.println("Insira o género desejado");
                aux = br.readLine();
            } while(!aux.equals("masculino") || !aux.equals("feminino") || !aux.equals("indiferente") || !aux.equals(""));
            if(!aux.equals(""))
                fields += "gender=" + aux + "&";
            aux = "";
            do {
                System.out.println("Insira o preço desejado");
                aux = br.readLine();
                if(aux.equals(""))
                    break;
                try {
                    int price = Integer.parseInt(aux);
                    if(price < 0)
                        continue;
                } catch(NumberFormatException e) {
                    System.out.println("Insira um valor válido para o preço!");
                    continue;
                }
            } while(true);
            if(!aux.equals(""))
                fields += "price=" + aux;
            bd.consultTableAdvertisement(fields);
        }
    }

    public static void searchByAdvertiser() throws java.rmi.RemoteException, IOException{
        String advertiser = "";
        do {
            System.out.println("Insira o nome do anunciante desejado:");
            advertiser = br.readLine();
        } while(advertiser.equals(""));
        advertiser = "anunciante=" + advertiser;
        bd.consultTableAdvertisement(advertiser);
    }

    public static void getDetailsByAid() throws java.rmi.RemoteException, IOException{
        int aux;
        do {
            System.out.println("Insira o aid do anúncio desejado:");
            try {
                aux = Integer.parseInt(br.readLine());
                if(aux > 0)
                    break;
            } catch(NumberFormatException e) {
                System.out.println("Aid inválido!");
                continue;
            }
        } while(true);
        String aid = "aid=" + String.valueOf(aux);
        bd.consultTableAdvertisement(aid);
    }

    public static void sendMessage() throws java.rmi.RemoteException, IOException{
        Message msg = new MessageImpl();
        String aux = "";

        do {
            System.out.println("Digite o aid do anúncio que pretende contactar:");
            aux = br.readLine();
            try {
                int n = Integer.parseInt(aux);
                if(n > 0)
                    break;
            } catch(NumberFormatException e) {
                System.out.println("Formato de aid inválido! Por favor insira apenas números inteiros positivos!");
                continue;
            }
        } while(true);
        msg.setAid(Integer.parseInt(aux));

        aux = "";
        do {
            System.out.println("Insira o seu nome:");
            aux = br.readLine();
        } while(aux.equals(""));
        msg.setSender(aux);

        aux = "";
        do {
            System.out.println("Escreva o conteúdo da sua mensagem:");
            aux = br.readLine();
        } while(aux.equals(""));
        msg.setContent(aux);

        msg.setDate(new Date(System.currentTimeMillis()));

        bd.insertIntoTableMessages(msg);
    }

    public static void showMessages() throws IOException {
        int aid;
        do {
            try {
                aid = Integer.parseInt(br.readLine());
                if(aid > 0)
                    break;
            } catch(NumberFormatException e) {
                System.err.println("Formato de aid inválido! Por favor insira um número inteiro positivo");
            }
        } while(true);
        String fields = "aid=" + String.valueOf(aid);
        bd.consultTableMessages(fields);
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
            bd = new ConnectionDBImpl();

            System.out.println("Bem vindo ao sistema de oferta e procura de alojamentos!");
            System.out.println("");

            bd.connectDb();

            showMenu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
