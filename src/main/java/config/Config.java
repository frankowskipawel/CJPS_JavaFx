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

    public void getConfigFromFile() throws IOException {
        try {
            getConfig();

        } catch (FileNotFoundException e) {
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
    }

    private void setDefaultConfig() throws IOException {
        File file = new File(PATH);
        file.createNewFile();
        PrintWriter printWriter = new PrintWriter(PATH);
        //Wartości domyślnie wpisywane do pliku konfiguracyjnego
        printWriter.println(DefaultConnection.CONNECTION.getHost());
        printWriter.println(DefaultConnection.CONNECTION.getPort());
        printWriter.println(DefaultConnection.CONNECTION.getDatabaseName());
        printWriter.println(DefaultConnection.CONNECTION.getUser());
        printWriter.println(DefaultConnection.CONNECTION.getPassword());
        printWriter.close();
    }

    public void setConfig(String host, String port, String database, String login, String password) {
        try {
            File file = new File(PATH);
            if (!file.delete()) {
                PrintWriter printWriter = new PrintWriter(PATH);
                printWriter.println(host);
                printWriter.println(port);
                printWriter.println(database);
                printWriter.println(login);
                printWriter.println(password);
                printWriter.close();
                getConfig();
            } else {
                file.createNewFile();
                setConfig(host, port, database, login, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



