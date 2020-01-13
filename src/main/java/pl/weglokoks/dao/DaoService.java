package pl.weglokoks.dao;

import pl.weglokoks.config.Config;
import javafx.scene.control.ButtonType;
import pl.weglokoks.utils.DialogsUtils;

import java.sql.*;
import java.util.Optional;

public class DaoService {

    private Connection connection;

    public void init() throws Exception {
        boolean isOk = false;

        while (!isOk) {
            try {
                isOk = true;
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + Config.HOSTNAME + ":" + Config.PORT + "/" + Config.DATABASENAME + "?useSSL=false", Config.USER, Config.PASSWORD);
            } catch (SQLException e) {
                try {
                    isOk = false;
                    createDatabase();

                } catch (SQLException ex) {
                    isOk = false;
                    Optional<ButtonType> result = DialogsUtils.configDialog("errorConnection.title", "errorConnection.header");
                    if (result.get() == ButtonType.OK) {
                    }
                }
            }
        }
    }


    public void createDatabase() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + Config.HOSTNAME + "/" + "?useSSL=false", Config.USER, Config.PASSWORD);

        Statement statement = null;

        String query;
        statement = connection.createStatement();
        query = "create database " + Config.DATABASENAME + ";";
        statement.executeUpdate(query);
        query = "use " + Config.DATABASENAME;
        statement.executeUpdate(query);
        query = "CREATE TABLE `certyfikaty` (\n" +
                "  `nr_certyfikaty` int(11) NOT NULL,\n" +
                "  `aktywny_certyfikaty` varchar(10) DEFAULT NULL,\n" +
                "  `nasza_nazwa_certyfikaty` varchar(250) DEFAULT NULL,\n" +
                "  `asortyment_certyfikaty` varchar(250) DEFAULT NULL,\n" +
                "  `data_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `nr_lab_certyfikaty` varchar(100) DEFAULT NULL,\n" +
                "  `popiol_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `siarka_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `cz_lotne_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `wartosc_opalowa_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `spiekalnosc_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `min_ziarno_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `max_ziarno_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `podziarno_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `nadziarno_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `wilgoc_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `dostawca_certyfikaty` varchar(250) DEFAULT NULL,\n" +
                "  `nr_fv_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`nr_certyfikaty`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n";
        statement.executeUpdate(query);
        query = "CREATE TABLE `dokumenty` (\n" +
                "  `auto_numeracja` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `id_dokumenty` varchar(20) NOT NULL,\n" +
                "  `data_Dokumenty` varchar(45) DEFAULT NULL,\n" +
                "  `nr_certyfikaty` int(11) DEFAULT NULL,\n" +
                "  `aktywny_certyfikaty` varchar(10) DEFAULT NULL,\n" +
                "  `nasza_nazwa_certyfikaty` varchar(250) DEFAULT NULL,\n" +
                "  `asortyment_certyfikaty` varchar(250) DEFAULT NULL,\n" +
                "  `data_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `nr_lab_certyfikaty` varchar(100) DEFAULT NULL,\n" +
                "  `popiol_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `siarka_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `cz_lotne_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `wartosc_opalowa_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `spiekalnosc_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `min_ziarno_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `max_ziarno_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `podziarno_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `nadziarno_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `wilgoc_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  `dostawca_certyfikaty` varchar(250) DEFAULT NULL,\n" +
                "  `nr_fv_certyfikaty` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`auto_numeracja`,`id_dokumenty`),\n" +
                "  UNIQUE KEY `auto_numeracja_UNIQUE` (`auto_numeracja`),\n" +
                "  UNIQUE KEY `id_dokumenty_UNIQUE` (`id_dokumenty`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n";
        statement.executeUpdate(query);
        query = "CREATE TABLE `kontrahent` (\n" +
                "  `id_kontrahent` varchar(15) NOT NULL,\n" +
                "  `nazwa_kontrahent` varchar(100) DEFAULT NULL,\n" +
                "  `adres_kontrahent` varchar(100) DEFAULT NULL,\n" +
                "  `nip_kontrahent` varchar(15) DEFAULT NULL,\n" +
                "  `regon_kontrahent` varchar(15) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id_kontrahent`),\n" +
                "  UNIQUE KEY `id_kontrahent_UNIQUE` (`id_kontrahent`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n";
        statement.executeUpdate(query);
    }
}
