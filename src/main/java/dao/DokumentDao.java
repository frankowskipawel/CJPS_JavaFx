package dao;

import config.Config;
import model.Dokument;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DokumentDao {

    private Connection connection;
    private final String tableName = "dokumenty";


    public DokumentDao() {
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

    public List<Dokument> getAllDokumenty() {
        List<Dokument> dokumentyLista = new LinkedList<Dokument>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String id_dokumenty = resultSet.getString("id_dokumenty");
                String data_dokumenty = resultSet.getString("data_dokumenty");
                String nr_certyfikaty = resultSet.getString("nr_certyfikaty");
                String aktywny_certyfikaty = resultSet.getString("aktywny_certyfikaty");
                String nasza_nazwa_certyfikaty = resultSet.getString("nasza_nazwa_certyfikaty");
                String asortyment_certyfikaty = resultSet.getString("asortyment_certyfikaty");
                String data_certyfikaty = resultSet.getString("data_certyfikaty");
                String nr_lab_certyfikaty = resultSet.getString("nr_lab_certyfikaty");
                String popiol_certyfikaty = resultSet.getString("popiol_certyfikaty");
                String siarka_certyfikaty = resultSet.getString("siarka_certyfikaty");
                String cz_lotne_certyfikaty = resultSet.getString("cz_lotne_certyfikaty");
                String wartosc_opalowa_certyfikaty = resultSet.getString("wartosc_opalowa_certyfikaty");
                String spiekalnosc_certyfikaty = resultSet.getString("spiekalnosc_certyfikaty");
                String min_ziarno_certyfikaty = resultSet.getString("min_ziarno_certyfikaty");
                String max_ziarno_certyfikaty = resultSet.getString("max_ziarno_certyfikaty");
                String podziarno_certyfikaty = resultSet.getString("podziarno_certyfikaty");
                String nadziarno_certyfikaty = resultSet.getString("nadziarno_certyfikaty");
                String wilgoc_certyfikaty = resultSet.getString("wilgoc_certyfikaty");
                String dostawca_certyfikaty = resultSet.getString("dostawca_certyfikaty");
                String nr_fv_certyfikaty = resultSet.getString("nr_fv_certyfikaty");

                Dokument dokumenty = new Dokument(id_dokumenty, data_dokumenty, nr_certyfikaty, aktywny_certyfikaty, nasza_nazwa_certyfikaty,
                        asortyment_certyfikaty, data_certyfikaty, nr_lab_certyfikaty, popiol_certyfikaty,
                        siarka_certyfikaty, cz_lotne_certyfikaty, wartosc_opalowa_certyfikaty, spiekalnosc_certyfikaty,
                        min_ziarno_certyfikaty, max_ziarno_certyfikaty, podziarno_certyfikaty, nadziarno_certyfikaty,
                        wilgoc_certyfikaty, dostawca_certyfikaty, nr_fv_certyfikaty);

                dokumentyLista.add(dokumenty);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dokumentyLista;
    }

    public Dokument znajdzDokumentPoId(String id) {
        Dokument dokument = new Dokument("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName + " WHERE id_dokumenty=" + id;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String id_dokumenty = resultSet.getString("id_dokumenty");
                String data_dokumenty = resultSet.getString("data_dokumenty");
                String nr_certyfikaty = resultSet.getString("nr_certyfikaty");
                String aktywny_certyfikaty = resultSet.getString("aktywny_certyfikaty");
                String nasza_nazwa_certyfikaty = resultSet.getString("nasza_nazwa_certyfikaty");
                String asortyment_certyfikaty = resultSet.getString("asortyment_certyfikaty");
                String data_certyfikaty = resultSet.getString("data_certyfikaty");
                String nr_lab_certyfikaty = resultSet.getString("nr_lab_certyfikaty");
                String popiol_certyfikaty = resultSet.getString("popiol_certyfikaty");
                String siarka_certyfikaty = resultSet.getString("siarka_certyfikaty");
                String cz_lotne_certyfikaty = resultSet.getString("cz_lotne_certyfikaty");
                String wartosc_opalowa_certyfikaty = resultSet.getString("wartosc_opalowa_certyfikaty");
                String spiekalnosc_certyfikaty = resultSet.getString("spiekalnosc_certyfikaty");
                String min_ziarno_certyfikaty = resultSet.getString("min_ziarno_certyfikaty");
                String max_ziarno_certyfikaty = resultSet.getString("max_ziarno_certyfikaty");
                String podziarno_certyfikaty = resultSet.getString("podziarno_certyfikaty");
                String nadziarno_certyfikaty = resultSet.getString("nadziarno_certyfikaty");
                String wilgoc_certyfikaty = resultSet.getString("wilgoc_certyfikaty");
                String dostawca_certyfikaty = resultSet.getString("dostawca_certyfikaty");
                String nr_fv_certyfikaty = resultSet.getString("nr_fv_certyfikaty");

                Dokument dokumenty = new Dokument(id_dokumenty, data_dokumenty, nr_certyfikaty, aktywny_certyfikaty, nasza_nazwa_certyfikaty,
                        asortyment_certyfikaty, data_certyfikaty, nr_lab_certyfikaty, popiol_certyfikaty,
                        siarka_certyfikaty, cz_lotne_certyfikaty, wartosc_opalowa_certyfikaty, spiekalnosc_certyfikaty,
                        min_ziarno_certyfikaty, max_ziarno_certyfikaty, podziarno_certyfikaty, nadziarno_certyfikaty,
                        wilgoc_certyfikaty, dostawca_certyfikaty, nr_fv_certyfikaty);
                dokument = dokumenty;

            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dokument;
    }

    public void addDokumentToDatabase(Dokument dokument) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            //  insert into employees(name, lastname, age) values('Jan', 'Kowalski', 22);
            String query = "insert into " + tableName + "(id_dokumenty, data_dokumenty, nr_certyfikaty, aktywny_certyfikaty, nasza_nazwa_certyfikaty, asortyment_certyfikaty, data_certyfikaty, nr_lab_certyfikaty, popiol_certyfikaty, siarka_certyfikaty, cz_lotne_certyfikaty, wartosc_opalowa_certyfikaty, spiekalnosc_certyfikaty, min_ziarno_certyfikaty, max_ziarno_certyfikaty, podziarno_certyfikaty, nadziarno_certyfikaty, wilgoc_certyfikaty, dostawca_certyfikaty, nr_fv_certyfikaty) values('" + dokument.getNumerDokumentu() + "', '" + dokument.getDataDokumentu() + "', '" + dokument.getNumerCertyfikatu() + "', '" + dokument.getAktywny() + "', '" + dokument.getNaszaNazwa() + "', '" + dokument.getAsortyment() + "', '" + dokument.getData() + "', '" + dokument.getNumerCertyfikatuLaboratorium() + "', '" + dokument.getZawartoscPopiolu() + "', '" + dokument.getZawartoscSiarkiCalkowitej() + "', '" + dokument.getZawartoscCzesciLotnych() + "', '" + dokument.getWartoscOpalowa() + "', '" + dokument.getZdolnoscSpiekania() + "', '" + dokument.getMinimalnyWymiarZiarna() + "', '" + dokument.getMaksymalnyWymiarZiarna() + "', '" + dokument.getZawartoscPodziarna() + "', '" + dokument.getZawartoscNadziarna() + "', '" + dokument.getZawartoscWilgociCalkowitej() + "', '" + dokument.getDostawca() + "', '" + dokument.getNrFV() + "');";
            System.out.println(query);
            //   String query = "select * from " + tableName;
            int resultSet = statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public int getNajwyzszyNumerDokumentuDao() {
//        Statement statement = null;
//        int nrNajwyzszy = 0;
//        int nrId = 0;
//        int najwyzszyNumerAutonumeracji = 0;
//        int autonumeracja = 0;
//        String rok = null;
//        try {
//            statement = connection.createStatement();
//            String query = "SELECT * FROM " + tableName;
//            // System.out.println(query);
//            ResultSet resultSet = statement.executeQuery(query);
//
//            ZonedDateTime dataDzisiejsza = ZonedDateTime.now();
//            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String rokAktualny = dataDzisiejsza.format(f).substring(0, 4);
//            while (resultSet.next()) {
//
//                autonumeracja = resultSet.getInt("auto_numeracja");
//                nrId = resultSet.getInt("id_dokumenty");
//                rok = resultSet.getString("data_dokumenty").substring(0, 4);
//
//
//                if (najwyzszyNumerAutonumeracji < autonumeracja) {
//                    najwyzszyNumerAutonumeracji = autonumeracja;
//                    nrNajwyzszy = nrId;
//                }
//            }
//            //  System.out.println("Rok="+rok);
//            // System.out.println("Rok="+rokAktualny);
//            if (!rokAktualny.equals(rok)) {
//                nrNajwyzszy = 0;
//            }
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        //  System.out.println("Numer najwyższy dokumenty " + nrNajwyzszy);
//
//        return nrNajwyzszy;
//    }

    public int getNajwyzszyNumerDokumentuDao() {
        Statement statement;
        int autoIncrement=0;
        int nrNajwyzszy = 0;
        try {
            statement = connection.createStatement();
            String query = "SELECT max(auto_numeracja) FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                autoIncrement = resultSet.getInt("max(auto_numeracja)");
            }
            System.out.println(autoIncrement);
            query = "SELECT id_dokumenty FROM " + tableName+" WHERE auto_numeracja="+autoIncrement;
            System.out.println(query);
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                nrNajwyzszy = resultSet.getInt("id_dokumenty");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nrNajwyzszy;
    }

    public String getNajwyzszyNumerDokumentuDaoString() {
        Statement statement = null;
        String nrNajwyzszy = "";
        try {
            statement = connection.createStatement();
            String query = "SELECT max(id_dokumenty) FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                nrNajwyzszy = resultSet.getString("max(id_dokumenty)");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nrNajwyzszy;
    }

    public void deleteDokumentDatabase(String id) {
        Statement statement = null;
        try {
            statement = connection.createStatement();

            String query = "delete from " + tableName + " where id_dokumenty = '" + id + "'";
            ///  System.out.println(query);1
            //   String query = "select * from " + tableName;
            int resultSet = statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
