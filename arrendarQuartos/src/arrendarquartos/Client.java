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
public class Client {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static void showMenu() {
        try {
            System.out.println("1 - Registar um novo anúncio");
            System.out.println("2 - Mostrar anúncios (oferta)");
            System.out.println("3 - Mostrar anúncios (procura)");
            System.out.println("4 - Mostrar todos os anúncios de um anunciante");
            System.out.println("5 - Obter detalhes de um anúncio (inserir aid do anúncio)");
            System.out.println("6 - Enviar mensagem para um anúncio (inserir aid do anúncio)");
            System.out.println("7 - Consultar mensagens de um anúncio (inserir aid do anúncio)");
            System.out.println("0 - Sair");

            int option = Integer.parseInt(br.readLine());
            if(option < 0 || option > 7) {
                System.out.println("Selecione uma das opções válidas!");
                System.out.println("");
                showMenu();
            }
            chooseOption(option);
        }
        catch (Exception e) {
            System.err.println("Argumentos inválidos!");
            showMenu();
        }
    }

    static void chooseOption(int option) throws java.rmi.RemoteException, java.io.IOException{
        switch (option) {
            case 0:
                System.out.println("Adeus! Até à próxima!");
                System.exit(1);
            case 1:
                registerAd();
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

        ad.setDate(java.time.LocalDate.now());
        ad.setState("inativo");
    }

    public void showAds() throws java.rmi.RemoteException, IOException {
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
            fields = "tipo=oferta&";
        else
            fields = "tipo=procura&";
        fields += "estado=ativo&";

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
                fields += "localizacao=" + aux + "&";
            aux = "";
            do {
                System.out.println("Insira o género desejado");
                aux = br.readLine();
            } while(!aux.equals("masculino") || !aux.equals("feminino") || !aux.equals("indiferente") || !aux.equals(""));
            if(!aux.equals(""))
                fields += "genero=" + aux + "&";
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
                fields += "preco=" + aux;
        }
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

            showMenu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
