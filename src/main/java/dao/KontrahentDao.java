package dao;

import model.Kontrahent;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class KontrahentDao {
    private Connection connection;
    private final String databaseName = "certyfikaty_database";
    private final String tableName = "kontrahent";
    private final String user = "pawel";
    private final String password = "admin";

    public KontrahentDao() {
        init();
    }
    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseName+"?useSSL=false", user, password);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public  List<Kontrahent> getAllKontrahent() {
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
            e.printStackTrace();
        }

        return kontrahenci;
    }

    public void addKontrahentDatabase(String id, String nazwa, String adres, String nip, String regon) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
          //  insert into employees(name, lastname, age) values('Jan', 'Kowalski', 22);
            String query  = "insert into "+ tableName+"(id_kontrahent, nazwa_kontrahent, adres_kontrahent, nip_kontrahent, regon_kontrahent) values('"+id+"', '"+nazwa+"', '"+adres+"', '"+nip+"', '"+regon+"');";
           // System.out.println(query);
            //   String query = "select * from " + tableName;
            int resultSet = statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteKontrahentDatabase(String id) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            //  insert into employees(name, lastname, age) values('Jan', 'Kowalski', 22);
            String query  = "delete from "+ tableName+" where id_dokumenty = "+id;
          ///  System.out.println(query);
            //   String query = "select * from " + tableName;
            int resultSet = statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}