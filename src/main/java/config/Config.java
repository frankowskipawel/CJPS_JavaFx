package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Config {
    private final String PATH = "src\\main\\java\\config\\config.txt";

    public static String HOSTNAME;
    public static String DATABASE;
    public static String LOGIN;
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
        DATABASE = wejscie.nextLine();
        LOGIN = wejscie.nextLine();
        PASSWORD = wejscie.nextLine();
    }

    private void setDefaultConfig() throws IOException {
        File file = new File(PATH);
        file.createNewFile();
        PrintWriter tekstowy = new PrintWriter(PATH);
        //Wartości domyślnie wpisywane do pliku konfiguracyjnego
        tekstowy.println("localhost");
        tekstowy.println("certyfikaty_database");
        tekstowy.println("pawel");
        tekstowy.println("admin");
        tekstowy.close();
    }


}



