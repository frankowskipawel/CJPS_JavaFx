package stages;

import config.Config;
import dao.CertyfikatJakosciDao;
import dao.DokumentDao;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.CertyfikatJakosci;
import model.Dokument;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.WartosciDopuszczalnePaliwa;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainController {

    @FXML
    private TableView<CertyfikatJakosci> listaAktywnychCertyfikatowTableViewStronaGlowna;
    @FXML
    private ListView<Dokument> listaDokumentowListViewStronaGlowna;
    @FXML
    private Label messageLabelMain;

    @FXML
    public void initialize() {

        setListaAktywnychCertyfikatow();
        add5DokumentowToListView();
    }

    protected List<CertyfikatJakosci> getAktywneCertyfikaty() throws SQLException {

        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        return certyfikatJakosciDao.getAktywneCertyfikatyJakosci();
    }

    protected void setListaAktywnychCertyfikatow() {

        try {
            for (CertyfikatJakosci b : getAktywneCertyfikaty()) {
                ObservableList<CertyfikatJakosci> data = listaAktywnychCertyfikatowTableViewStronaGlowna.getItems();
                data.add(new CertyfikatJakosci(b.numerCertyfikatuProperty().getValue(), b.naszaNazwaProperty().getValue()
                ));
            }

        } catch (SQLException e) {
            // System.out.println("fsfafsd");
            e.printStackTrace();
        }
    }

    @FXML
    protected void refreshClick() {

        refreshListaAktywnychCertyfikatow();
        refreshListaDokumentow();
    }

    @FXML
    protected void addAndPrintNowyDokumentClick() throws IOException {
        ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();
        if (!listaAktywnychCertyfikatowTableViewStronaGlowna.getSelectionModel().isEmpty()) {
            Dokument dokument;
            dokument = addDokumentToListView();
            refreshListaAktywnychCertyfikatow();
            refreshListaDokumentow();

            showAndPrintDokument(dokument, true, false); //zamienic na print docelowo}
            messageLabelMain.setText("");
        } else {
            messageLabelMain.setText("Błąd - nie zaznaczono certyfikatu");
            //   System.out.println("Zaznacz certyfikat do wydrukowania");
        }
    }

    @FXML
    public void podgladClick() throws IOException {

        if (!listaDokumentowListViewStronaGlowna.getSelectionModel().isEmpty()) {
            messageLabelMain.setText("");
           // System.out.println(listaDokumentowListViewStronaGlowna.getSelectionModel().getSelectedItem());
            showAndPrintDokument(listaDokumentowListViewStronaGlowna.getSelectionModel().getSelectedItem(), false, true);
        }else{
            messageLabelMain.setText("Błąd - zaznacz najpierw dokument");
        }
    }

    protected void showAndPrintDokument(Dokument dokument, boolean print, boolean show) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/CertyfikatJakosciWydruk.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Podgląd wydruku");
        if (show) {
            stage.show();
        }
        CertyfikatJakosciWydrukController certyfikatJakosciWydrukController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        certyfikatJakosciWydrukController.certyfikatJakosciWydrukController = certyfikatJakosciWydrukController; //
        setWartosciDopuszczalneNaWydruku(certyfikatJakosciWydrukController, WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()));
        setWartosciNaWydruku(certyfikatJakosciWydrukController, dokument);
        if (print) {
            certyfikatJakosciWydrukController.print(2);
        }
    }

    public void setWartosciNaWydruku(CertyfikatJakosciWydrukController certyfikatJakosciWydrukController, Dokument dokument) {

        certyfikatJakosciWydrukController.setNazwaAdresPodmiotuWydruk(Config.NAZWA_PODMIOTU + ", " + Config.ULICA_I_NUMER_DOMU_PODMIOTU+", "+Config.KOD_POCZTOWY_PODMIOTU+" "+Config.MIASTO_PODMIOTU);
        certyfikatJakosciWydrukController.setNipRegonPodmiotuWydruk("NIP " + Config.NIP_PODMIOTU + " / REGON " + Config.REGON_PODMIOTU);
        certyfikatJakosciWydrukController.setNrWydruk(dokument.getNumerDokumentu());
        certyfikatJakosciWydrukController.setDataDokumentuWydruk(dokument.getDataDokumentu());
        certyfikatJakosciWydrukController.setAsortymentWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getNazwa());
        certyfikatJakosciWydrukController.setNrLabWydruk(dokument.getCertyfikatJakosci().getNumerCertyfikatuLaboratorium());
        certyfikatJakosciWydrukController.setPopiolWydruk(dokument.getCertyfikatJakosci().getZawartoscPopiolu());
        certyfikatJakosciWydrukController.setSiarkaWydruk(dokument.getCertyfikatJakosci().getZawartoscSiarkiCalkowitej());
        certyfikatJakosciWydrukController.setCzLotneWydruk(dokument.getCertyfikatJakosci().getZawartoscCzesciLotnych());
        certyfikatJakosciWydrukController.setWartoscOpalowaWydruk(dokument.getCertyfikatJakosci().getWartoscOpalowa());
        certyfikatJakosciWydrukController.setSpiekalnoscWydruk(dokument.getCertyfikatJakosci().getZdolnoscSpiekania());
        certyfikatJakosciWydrukController.setWymiarZiarnaWydruk(dokument.getCertyfikatJakosci().getMinimalnyWymiarZiarna() + "-" + dokument.getCertyfikatJakosci().getMaksymalnyWymiarZiarna());
        certyfikatJakosciWydrukController.setZawPodziarnaWydruk(dokument.getCertyfikatJakosci().getZawartoscPodziarna());
        certyfikatJakosciWydrukController.setZawNadziarnaWydruk(dokument.getCertyfikatJakosci().getZawartoscNadziarna());
        certyfikatJakosciWydrukController.setZawWilgociWydruk(dokument.getCertyfikatJakosci().getZawartoscWilgociCalkowitej());

    }

    public void setWartosciDopuszczalneNaWydruku(CertyfikatJakosciWydrukController certyfikatJakosciWydrukController, WartosciDopuszczalnePaliwa asortyment) {
        certyfikatJakosciWydrukController.setWDminPopiolWydruk(asortyment.getMinPopiol());
        certyfikatJakosciWydrukController.setWDmaxPopiolWydruk(asortyment.getMaxPopiol());
        certyfikatJakosciWydrukController.setWDminSiarkaWydruk(asortyment.getMinSiarka());
        certyfikatJakosciWydrukController.setWDmaxSiarkaWydruk(asortyment.getMaxSiarka());
        certyfikatJakosciWydrukController.setWDminCzLotneWydruk(asortyment.getMinCzLotne());
        certyfikatJakosciWydrukController.setWDmaxCzLotneWydruk(asortyment.getMaxCzLotne());
        certyfikatJakosciWydrukController.setWDminWartoscOpalowaWydruk(asortyment.getMinWartoscOpalowa());
        certyfikatJakosciWydrukController.setWDmaxWartoscOpalowaWydruk(asortyment.getMaxWartoscOpalowa());
        certyfikatJakosciWydrukController.setWDminSpiekalnoscWydruk(asortyment.getMinSpiekalnsc());
        certyfikatJakosciWydrukController.setWDmaxSpiekalnoscWydruk(asortyment.getMaxSpiekalnosc());
        certyfikatJakosciWydrukController.setWDminWymiarZiarna(asortyment.getMinWymiarZiarna());
        certyfikatJakosciWydrukController.setWDmaxWymiarZiarna(asortyment.getMaxWymiarZiarna());
        certyfikatJakosciWydrukController.setWDminPodziarnoWydruk(asortyment.getMinPodziarno());
        certyfikatJakosciWydrukController.setWDmaxPodziarnoWydruk(asortyment.getMaxPodziarno());
        certyfikatJakosciWydrukController.setWDminNadziarnoWydruk(asortyment.getMinNadziarno());
        certyfikatJakosciWydrukController.setWDmaxNadziarnoWydruk(asortyment.getMaxNadziarno());
        certyfikatJakosciWydrukController.setWDminWilgotnoscWydruk(asortyment.getMinWilgotnosc());
        certyfikatJakosciWydrukController.setWDmaxWilgotnoscWydruk(asortyment.getMaxWilgotnosc());

    }

    public void refreshListaAktywnychCertyfikatow() {
        ObservableList<CertyfikatJakosci> data = listaAktywnychCertyfikatowTableViewStronaGlowna.getItems();
        data.removeAll(data);
        setListaAktywnychCertyfikatow();
    }

    public void refreshListaDokumentow() {
        ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();
        data.removeAll(data);
        add5DokumentowToListView();
    }

    protected Dokument addDokumentToListView() {

        ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();

        CertyfikatJakosci certyfikatJakosci = new CertyfikatJakosciDao().znajdzCertyfikatPoNr(listaAktywnychCertyfikatowTableViewStronaGlowna.getSelectionModel().getSelectedItem().getNumerCertyfikatuAktywne());

        DokumentDao dokumentDao = new DokumentDao();

        int numer = dokumentDao.getNajwyzszyNumerDokumentuDao() + 1;

        ZonedDateTime dataDzisiejsza = ZonedDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataDzisiejszaString = dataDzisiejsza.format(f);

        Dokument dokumentDodawany = new Dokument(Integer.toString(numer) + "/" + dataDzisiejszaString.substring(0, 4), dataDzisiejszaString, certyfikatJakosci);

        data.add(dokumentDodawany);


        dokumentDao.addDokumentToDatabase(dokumentDodawany);
        return dokumentDodawany;
    }

    @FXML
    void menuDanePodmiotuClick(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/PodmiotConfig.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Ustawienia podmiotu");

        ConfigPodmiotController podmiotConfigController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        podmiotConfigController.setNazwaTextFieldPodmiotConfig(Config.NAZWA_PODMIOTU);
        podmiotConfigController.setUlicaTextFieldPodmiotConfig(Config.ULICA_I_NUMER_DOMU_PODMIOTU);
        podmiotConfigController.setKodPocztowyTextFieldPodmiotConfig(Config.KOD_POCZTOWY_PODMIOTU);
        podmiotConfigController.setMiastoTextFieldPodmiotConfig(Config.MIASTO_PODMIOTU);
        podmiotConfigController.setNipTextFieldPodmiotConfig(Config.NIP_PODMIOTU);
        podmiotConfigController.setRegonTextFieldPodmiotConfig(Config.REGON_PODMIOTU);
        stage.show();
    }

    @FXML
    protected void add5DokumentowToListView() {

        DokumentDao dokumentDao = new DokumentDao();
        List<Dokument> list = dokumentDao.getAllDokumenty();

        //   Iterator<Dokument> iterator = list.iterator();
        int i = 0;
        for (Dokument b : list) {
            if (i > list.size() - 6) {
                ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();

                data.add(new Dokument(b.numerDokumentuProperty().getValue(),
                        b.dataDokumentuProperty().getValue(),
                        new CertyfikatJakosci(b.getCertyfikatJakosci().numerCertyfikatuProperty().getValue(),
                                b.getCertyfikatJakosci().aktywnyProperty().getValue(),
                                b.getCertyfikatJakosci().naszaNazwaProperty().getValue(),
                                b.getCertyfikatJakosci().asortymentProperty().getValue(),
                                b.getCertyfikatJakosci().dataProperty().getValue(),
                                b.getCertyfikatJakosci().numerCertyfikatuLaboratoriumProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscPopioluProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscSiarkiCalkowitejProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscCzesciLotnychProperty().getValue(),
                                b.getCertyfikatJakosci().wartoscOpalowaProperty().getValue(),
                                b.getCertyfikatJakosci().zdolnoscSpiekaniaProperty().getValue(),
                                b.getCertyfikatJakosci().minimalnyWymiarZiarnaProperty().getValue(),
                                b.getCertyfikatJakosci().maksymalnyWymiarZiarnaProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscPodziarnaProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscNadziarnaProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscWilgociCalkowitejProperty().getValue(),
                                b.getCertyfikatJakosci().dostawcaProperty().getValue(),
                                b.getCertyfikatJakosci().nrFVProperty().getValue()))
                );
            }
            i++;
        }
      //  Collections.reverse(listaDokumentowListViewStronaGlowna.getItems());

    }

    private void print(Node node) {
        // Define the Job Status Message
//        jobStatus.textProperty().unbind();
//        jobStatus.setText("Creating a printer job...");

        // Create a printer job for the default printer
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null) {
            // Show the printer job status
//            jobStatus.textProperty().bind(job.jobStatusProperty().asString());

            // Print the node
            boolean printed = job.printPage(node);

            if (printed) {
                // End the printer job
                job.endJob();
            } else {
                // Write Error Message
//                jobStatus.textProperty().unbind();
//                jobStatus.setText("Printing failed.");
            }
        } else {
            // Write Error Message
//            jobStatus.setText("Could not create a printer job.");
        }
    }

    @FXML
    public void MenuListaCertyfikatowClick() throws IOException {
        Stage stage = new Stage();
//        stage.setTitle("Lista Certyfikatow");
//        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
//                ("ListaCertyfikatowTableView.fxml"));
//        Scene myScene = new Scene(myPane);
//        stage.setScene(myScene);
//        stage.show();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/ListaCertyfikatowTableView.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Lista Certyfikatów");
        stage.show();

    }

    @FXML
    public void usunOstatniMenuClick() throws IOException {
        DokumentDao dokumentDao = new DokumentDao();
        dokumentDao.usunOstatniDokument();
        refreshListaDokumentow();
    }



    @FXML
    void menuUstawieniaClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Ustawienia");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("Config.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();
    }

    @FXML
    void menuKontrahenciClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Kontrahenci");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("KontrahentTableView.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();
    }

    @FXML
    void menuDokumentyClick(ActionEvent event) throws IOException {
//        Stage stage = new Stage();
//        stage.setTitle("Lista Dokumentow");
//        VBox myPane = (VBox) FXMLLoader.load(getClass().getResource
//                ("Dokumenty.fxml"));
//        Scene myScene = new Scene(myPane);
//        stage.setScene(myScene);
//        stage.show();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/Dokumenty.fxml"));
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Lista Dokumentów");

        DokumentyStageController dokumentyStageController = loader.getController(); //
        dokumentyStageController.mainController = MainController.this;

        stage.show();
    }
}

