import config.Config;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Config config = new Config();
        config.getConfigFromFile();
        System.out.println(config.HOSTNAME);
        System.out.println(config.DATABASE);
        System.out.println(config.LOGIN);
        System.out.println(config.PASSWORD);


    }

}
