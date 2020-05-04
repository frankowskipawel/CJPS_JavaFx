package pl.weglokoks.dao;

import pl.weglokoks.config.Config;
import pl.weglokoks.modelFX.Kontrahent;
import pl.weglokoks.utils.DialogsUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class KontrahentDao {
    private Connection connection;
    private final String tableName = "kontrahent";


    public KontrahentDao() {
        init();
    }

    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + Config.HOSTNAME + ":" + Config.PORT + "/" + Config.DATABASENAME + "?useSSL=false", Config.USER, Config.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Kontrahent> findAllKontrahent() {
        List<Kontrahent> kontrahenci = new LinkedList<Kontrahent>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String id_kontrahent = resultSet.getString("id_kontrahent");
                String nazwa_kontrahent = resultSet.getString("nazwa_kontrahent");
                String adres_kontrahent = resultSet.getString("adres_kontrahent");
                String nip_kontrahent = resultSet.getString("nip_kontrahent");
                String regon_kontrahent = resultSet.getString("regon_kontrahent");
                Kontrahent kontrahent = new Kontrahent(id_kontrahent, nazwa_kontrahent, adres_kontrahent, nip_kontrahent, regon_kontrahent);
                kontrahenci.add(kontrahent);
            }
            statement.close();
        } catch (SQLException e) {
            DialogsUtils.errorDialog("Błąd", e.getMessage());
            e.printStackTrace();
        }
        return kontrahenci;
    }

    public void insertKontrahent(Kontrahent kontrahent) throws SQLException {
        Statement statement = null;
            statement = connection.createStatement();
            String query = "insert into " + tableName + "(id_kontrahent, nazwa_kontrahent, adres_kontrahent, nip_kontrahent, regon_kontrahent) values('" + kontrahent.getIdKontrahent() + "', '" + kontrahent.getNazwaKontrahent() + "', '" + kontrahent.getAdresKontrahent() + "', '" + kontrahent.getNipKontrahent() + "', '" + kontrahent.getRegonKontrahent() + "');";
            statement.executeUpdate(query);
    }

    public void deleteKontrahentById(String id) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "delete from " + tableName + " where id_kontrahent = '" + id+"';";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            DialogsUtils.errorDialog("Błąd", e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateKontrahent(Kontrahent kontrahent) {

        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "UPDATE " + tableName + " SET `id_kontrahent` = '" + kontrahent.getIdKontrahent() + "', `nazwa_kontrahent` = '" + kontrahent.getNazwaKontrahent() + "', `adres_kontrahent` = '" + kontrahent.getAdresKontrahent() + "', `nip_kontrahent` = '" + kontrahent.getNipKontrahent() + "', `regon_kontrahent` = '" + kontrahent.getRegonKontrahent() + "' WHERE (`id_kontrahent` = '" + kontrahent.getIdKontrahent() + "')";
            //   System.out.println(query);
            int resultSet = statement.executeUpdate(query);
        } catch (SQLException e) {
            DialogsUtils.errorDialog("Błąd", e.getMessage());
            e.printStackTrace();
        }
    }
}