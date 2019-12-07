import config.Config;
import config.DefaultConnection;
import dao.DaoService;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Config config = new Config();
        config.getConfigFromFile();
        config.setConfig("10.0.0.6","3306","certyfikaty_database","pawel","admin");
        System.out.println(config.HOSTNAME);
        System.out.println(config.DATABASENAME);
        System.out.println(config.USER);
        System.out.println(config.PASSWORD);

        DaoService daoService = new DaoService();
        daoService.init();

        System.out.println(DefaultConnection.CONNECTION.getDatabaseName());


    }

}
