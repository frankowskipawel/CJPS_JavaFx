package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Config {
    private final String PATH = "src\\main\\java\\config\\config.txt";

    public static String HOSTNAME;
    public static String PORT;
    public static String DATABASENAME;
    public static String USER;
    public static String PASSWORD;
    public static String NAZWA_PODMIOTU;
    public static String ULICA_I_NUMER_DOMU_PODMIOTU;
    public static String KOD_POCZTOWY_PODMIOTU;
    public static String MIASTO_PODMIOTU;
    public static String NIP_PODMIOTU;
    public static String REGON_PODMIOTU;


    public void getConfigFromFile() throws IOException {
        try {
            getConfig();

        } catch (FileNotFoundException e) {
            System.out.println("brak pliku");
            setDefaultConfig();
            getConfig();
        }

    }

    private void getConfig() throws FileNotFoundException {
        File plik = new File(PATH);
        Scanner wejscie = new Scanner(plik);
        HOSTNAME = wejscie.nextLine();
        PORT = wejscie.nextLine();
        DATABASENAME = wejscie.nextLine();
        USER = wejscie.nextLine();
        PASSWORD = wejscie.nextLine();
        NAZWA_PODMIOTU = wejscie.nextLine();
        ULICA_I_NUMER_DOMU_PODMIOTU = wejscie.nextLine();
        KOD_POCZTOWY_PODMIOTU= wejscie.nextLine();
        MIASTO_PODMIOTU= wejscie.nextLine();
        NIP_PODMIOTU= wejscie.nextLine();
        REGON_PODMIOTU= wejscie.nextLine();
        wejscie.close();

    }

    private void setDefaultConfig() throws IOException {
        File file = new File(PATH);
       // file.delete();
        file.createNewFile();

        PrintWriter printWriter = new PrintWriter(PATH);
        //Wartości domyślnie wpisywane do pliku konfiguracyjnego
        printWriter.println(DefaultConnection.CONNECTION.getHost());
        printWriter.println(DefaultConnection.CONNECTION.getPort());
        printWriter.println(DefaultConnection.CONNECTION.getDatabaseName());
        printWriter.println(DefaultConnection.CONNECTION.getUser());
        printWriter.println(DefaultConnection.CONNECTION.getPassword());
        printWriter.println("");
        printWriter.println("");
        printWriter.println("");
        printWriter.println("");
        printWriter.println("");
        printWriter.println("");

        printWriter.close();
        getConfig();
    }

    public void setConfig(String host, String port, String database, String login, String password) {
        try {
            File file = new File(PATH);
            if (file.exists()) {
                PrintWriter printWriter = new PrintWriter(PATH);
                printWriter.println(host);
                printWriter.println(port);
                printWriter.println(database);
                printWriter.println(login);
                printWriter.println(password);
                printWriter.println(Config.NAZWA_PODMIOTU);
                printWriter.println(Config.ULICA_I_NUMER_DOMU_PODMIOTU);
                printWriter.println(Config.KOD_POCZTOWY_PODMIOTU);
                printWriter.println(Config.MIASTO_PODMIOTU);
                printWriter.println(Config.NIP_PODMIOTU);
                printWriter.println(Config.REGON_PODMIOTU);
                printWriter.close();
                Config config = new Config();
                config.getConfigFromFile();
                getConfig();
            } else {
                file.createNewFile();
                setConfig(host, port, database, login, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDanePodmiotu(String nazwa, String ulica, String kodPocztowy, String miasto, String nip, String regon){
        try {
            File file = new File(PATH);
//            file.delete();
//            file.createNewFile();
            if (file.exists()) {

                PrintWriter printWriter = new PrintWriter(PATH);
                printWriter.println(Config.HOSTNAME);
                printWriter.println(Config.PORT);
                printWriter.println(Config.DATABASENAME);
                printWriter.println(Config.USER);
                printWriter.println(Config.PASSWORD);
                printWriter.println(nazwa);
                printWriter.println(ulica);
                printWriter.println(kodPocztowy);
                printWriter.println(miasto);
                printWriter.println(nip);
                printWriter.println(regon);
                printWriter.close();
                getConfig();

            }else{
                file.createNewFile();
                setDanePodmiotu(nazwa, ulica, kodPocztowy, miasto, nip, regon);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }


}



