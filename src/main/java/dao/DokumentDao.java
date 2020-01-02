package dao;

import config.Config;
import model.CertyfikatJakosci;
import model.Dokument;

import java.sql.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

                Dokument dokumenty = new Dokument(id_dokumenty, data_dokumenty, new CertyfikatJakosci(nr_certyfikaty, aktywny_certyfikaty, nasza_nazwa_certyfikaty,
                        asortyment_certyfikaty, data_certyfikaty, nr_lab_certyfikaty, popiol_certyfikaty,
                        siarka_certyfikaty, cz_lotne_certyfikaty, wartosc_opalowa_certyfikaty, spiekalnosc_certyfikaty,
                        min_ziarno_certyfikaty, max_ziarno_certyfikaty, podziarno_certyfikaty, nadziarno_certyfikaty,
                        wilgoc_certyfikaty, dostawca_certyfikaty, nr_fv_certyfikaty,""));

                dokumentyLista.add(dokumenty);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dokumentyLista;
    }

    public Dokument znajdzDokumentPoId(String id) {
        Dokument dokument = new Dokument("", "", new CertyfikatJakosci("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",""));
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

                Dokument dokumenty = new Dokument(id_dokumenty, data_dokumenty, new CertyfikatJakosci(nr_certyfikaty, aktywny_certyfikaty, nasza_nazwa_certyfikaty,
                        asortyment_certyfikaty, data_certyfikaty, nr_lab_certyfikaty, popiol_certyfikaty,
                        siarka_certyfikaty, cz_lotne_certyfikaty, wartosc_opalowa_certyfikaty, spiekalnosc_certyfikaty,
                        min_ziarno_certyfikaty, max_ziarno_certyfikaty, podziarno_certyfikaty, nadziarno_certyfikaty,
                        wilgoc_certyfikaty, dostawca_certyfikaty, nr_fv_certyfikaty,""));
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
            String query = "insert into " + tableName + "(id_dokumenty, data_dokumenty, nr_certyfikaty, aktywny_certyfikaty, nasza_nazwa_certyfikaty, asortyment_certyfikaty, data_certyfikaty, nr_lab_certyfikaty, popiol_certyfikaty, siarka_certyfikaty, cz_lotne_certyfikaty, wartosc_opalowa_certyfikaty, spiekalnosc_certyfikaty, min_ziarno_certyfikaty, max_ziarno_certyfikaty, podziarno_certyfikaty, nadziarno_certyfikaty, wilgoc_certyfikaty, dostawca_certyfikaty, nr_fv_certyfikaty) values('" + dokument.getNumerDokumentu() + "', '" + dokument.getDataDokumentu() + "', '" + dokument.getCertyfikatJakosci().getNumerCertyfikatu() + "', '" + dokument.getCertyfikatJakosci().getAktywny() + "', '" + dokument.getCertyfikatJakosci().getNaszaNazwa() + "', '" + dokument.getCertyfikatJakosci().getAsortyment() + "', '" + dokument.getCertyfikatJakosci().getData() + "', '" + dokument.getCertyfikatJakosci().getNumerCertyfikatuLaboratorium() + "', '" + dokument.getCertyfikatJakosci().getZawartoscPopiolu() + "', '" + dokument.getCertyfikatJakosci().getZawartoscSiarkiCalkowitej() + "', '" + dokument.getCertyfikatJakosci().getZawartoscCzesciLotnych() + "', '" + dokument.getCertyfikatJakosci().getWartoscOpalowa() + "', '" + dokument.getCertyfikatJakosci().getZdolnoscSpiekania() + "', '" + dokument.getCertyfikatJakosci().getMinimalnyWymiarZiarna() + "', '" + dokument.getCertyfikatJakosci().getMaksymalnyWymiarZiarna() + "', '" + dokument.getCertyfikatJakosci().getZawartoscPodziarna() + "', '" + dokument.getCertyfikatJakosci().getZawartoscNadziarna() + "', '" + dokument.getCertyfikatJakosci().getZawartoscWilgociCalkowitej() + "', '" + dokument.getCertyfikatJakosci().getDostawca() + "', '" + dokument.getCertyfikatJakosci().getNrFV() + "');";

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
        int autoIncrement = 0;
        int nrNajwyzszy = 0;
        try {
            statement = connection.createStatement();
            String query = "SELECT max(auto_numeracja) FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                autoIncrement = resultSet.getInt("max(auto_numeracja)");
            }

            query = "SELECT id_dokumenty FROM " + tableName + " WHERE auto_numeracja=" + autoIncrement;

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

    public int getKolejnyNumerDokumentuDao() {
        Statement statement;
        int autoIncrement = 0;
        int nrNajwyzszy = 0;
        int nrKolejny=0;
        String dataNajwyzszy = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT max(auto_numeracja) FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                autoIncrement = resultSet.getInt("max(auto_numeracja)");
            }

            query = "SELECT id_dokumenty, data_Dokumenty FROM " + tableName + " WHERE auto_numeracja=" + autoIncrement;

            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                nrNajwyzszy = resultSet.getInt("id_dokumenty");
                dataNajwyzszy = resultSet.getString("data_dokumenty");
            }
            statement.close();

            ZonedDateTime dataDzisiejsza = ZonedDateTime.now();
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dataDzisiejszaString = dataDzisiejsza.format(f);
            if (dataDzisiejszaString.substring(0, 4).equals(dataNajwyzszy.substring(0, 4))) {
                nrKolejny = nrNajwyzszy + 1;
            } else {
                nrKolejny = 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nrKolejny;
    }


    public String getAILastDokument() {
        Statement statement = null;
        String nrNajwyzszy = "";
        try {
            statement = connection.createStatement();
            String query = "SELECT max(auto_numeracja) FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                nrNajwyzszy = resultSet.getString("max(auto_numeracja)");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nrNajwyzszy;
    }

    public void deleteDokumentDatabase(String autoNumeracja) {
        Statement statement = null;
        try {
            statement = connection.createStatement();

            String query = "delete from " + tableName + " where auto_numeracja = '" + autoNumeracja + "'";
            ///  System.out.println(query);1
            //   String query = "select * from " + tableName;
            int resultSet = statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void usunOstatniDokument() {
        DokumentDao dokumentDao = new DokumentDao();
        dokumentDao.deleteDokumentDatabase(dokumentDao.getAILastDokument());

    }

    public void updateDokument(Dokument dokument) {
        Statement statement = null;
        try {
            statement = connection.createStatement();

            String query = "UPDATE " + tableName + " SET `nr_certyfikaty` = '" + dokument.getCertyfikatJakosci().getNumerCertyfikatu() + "', `aktywny_certyfikaty` = '" + dokument.getCertyfikatJakosci().getAktywny() + "', `nasza_nazwa_certyfikaty` = '" + dokument.getCertyfikatJakosci().getNaszaNazwa() + "', `asortyment_certyfikaty` = '" + dokument.getCertyfikatJakosci().getAsortyment() + "', `data_certyfikaty` = '" + dokument.getCertyfikatJakosci().getData() + "', `nr_lab_certyfikaty` = '" + dokument.getCertyfikatJakosci().getNumerCertyfikatuLaboratorium() + "', `popiol_certyfikaty` = '" + dokument.getCertyfikatJakosci().getZawartoscPopiolu() + "', `siarka_certyfikaty` = '" + dokument.getCertyfikatJakosci().getZawartoscSiarkiCalkowitej() + "', `cz_lotne_certyfikaty` = '" + dokument.getCertyfikatJakosci().getZawartoscCzesciLotnych() + "', `wartosc_opalowa_certyfikaty` = '" + dokument.getCertyfikatJakosci().getWartoscOpalowa() + "', `spiekalnosc_certyfikaty` = '" + dokument.getCertyfikatJakosci().getZdolnoscSpiekania() + "', `min_ziarno_certyfikaty` = '" + dokument.getCertyfikatJakosci().getMinimalnyWymiarZiarna() + "', `max_ziarno_certyfikaty` = '" + dokument.getCertyfikatJakosci().getMaksymalnyWymiarZiarna() + "', `podziarno_certyfikaty` = '" + dokument.getCertyfikatJakosci().getZawartoscPodziarna() + "', `nadziarno_certyfikaty` = '" + dokument.getCertyfikatJakosci().getZawartoscNadziarna() + "', `wilgoc_certyfikaty` = '" + dokument.getCertyfikatJakosci().getZawartoscWilgociCalkowitej() + "', `dostawca_certyfikaty` = '" + dokument.getCertyfikatJakosci().getDostawca() + "', `nr_fv_certyfikaty` = '" + dokument.getCertyfikatJakosci().getNrFV() + "' WHERE (`id_dokumenty` = '" + dokument.getNumerDokumentu() + "');";
            //   System.out.println(query);

            int resultSet = statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int countDokument(String numerCertyfikatu) {
        //  SELECT * FROM certyfikaty_database.dokumenty WHERE nr_certyfikaty="30";
        int count = 0;
        Statement statement = null;
        try {
            statement = connection.createStatement();

            String query = "SELECT * FROM " + tableName + " WHERE nr_certyfikaty='" + numerCertyfikatu + "';";            //   System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                count++;
            }
            // System.out.println(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
