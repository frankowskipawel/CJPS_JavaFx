package stages;

import config.Config;
import dao.CertyfikatJakosciDao;
import dao.DokumentDao;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.CertyfikatJakosci;
import model.Dokument;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
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

    private ObservableList<CertyfikatJakosci> lista;
    @FXML
    private TableView<CertyfikatJakosci> listaAktywnychCertyfikatowTableViewStronaGlowna;
    @FXML
    private ListView<Dokument> listaDokumentowListViewStronaGlowna;
    @FXML
    private Label messageLabelMain;
    @FXML
    private MenuItem menuQuit;
    @FXML
    private TableColumn naszaNazwaAktywne;
    @FXML
    private TableColumn nrCertyfikatuTableViewStronaGlowna;
    @FXML
    private TableColumn iloscTableViewStronaGlowna;

    @FXML
    public void initialize() {

        setListaAktywnychCertyfikatow();
        add5DokumentowToListView();
        listaAktywnychCertyfikatowTableViewStronaGlowna.getSortOrder().add(naszaNazwaAktywne);


    }

    protected void setListaAktywnychCertyfikatow() {
        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        List<CertyfikatJakosci> list = certyfikatJakosciDao.getAktywneCertyfikatyJakosci();

        ObservableList<CertyfikatJakosci> data = listaAktywnychCertyfikatowTableViewStronaGlowna.getItems();

        for (CertyfikatJakosci b : list) {

            data.add(new CertyfikatJakosci(b.numerCertyfikatuAktywneProperty().getValue(), b.naszaNazwaAktywneProperty().getValue(), b.iloscStronaGlownaProperty().getValue()
            ));
        }
        this.lista = data;

    }

    @FXML
    protected void refreshClick() {
        messageLabelMain.setText("");
        refreshListaAktywnychCertyfikatow();
        refreshListaDokumentow();
        listaAktywnychCertyfikatowTableViewStronaGlowna.getSortOrder().add(naszaNazwaAktywne);
    }

    @FXML
    protected void addAndPrintNowyDokumentClick() throws IOException {
        messageLabelMain.setText("");
        ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();
        if (!listaAktywnychCertyfikatowTableViewStronaGlowna.getSelectionModel().isEmpty()) {
            Dokument dokument;
            dokument = addDokumentToListView();
            refreshListaAktywnychCertyfikatow();
            refreshListaDokumentow();

            showAndPrintDokument(dokument, true, false); //zamienic na print docelowo}
        } else {
            messageLabelMain.setText("Błąd - nie zaznaczono certyfikatu");
        }
    }

    @FXML
    public void podgladClick() throws IOException {

        if (!listaDokumentowListViewStronaGlowna.getSelectionModel().isEmpty()) {
            messageLabelMain.setText("");
            showAndPrintDokument(listaDokumentowListViewStronaGlowna.getSelectionModel().getSelectedItem(), false, true);
        } else {
            messageLabelMain.setText("Błąd - zaznacz najpierw dokument");
        }
    }

    protected void showAndPrintDokument(Dokument dokument, boolean print, boolean show) throws IOException {

        Stage stage = new Stage();
        stage.setResizable(false);
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
        certyfikatJakosciWydrukController.setPolaNaWydruku(dokument);
        certyfikatJakosciWydrukController.mainController = MainController.this;
        if (print) {
            certyfikatJakosciWydrukController.print(2);
        }
    }


    public void refreshListaAktywnychCertyfikatow() {
        ObservableList<CertyfikatJakosci> data = listaAktywnychCertyfikatowTableViewStronaGlowna.getItems();
        data.removeAll(data);
        setListaAktywnychCertyfikatow();
        listaAktywnychCertyfikatowTableViewStronaGlowna.getSortOrder().add(naszaNazwaAktywne);
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
        stage.setResizable(false);
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
    void menuQuitOnClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void add5DokumentowToListView() {

        DokumentDao dokumentDao = new DokumentDao();
        List<Dokument> list = dokumentDao.getAllDokumenty();

        int i = 0;
        for (Dokument b : list) {
            if (i > list.size() - 7) {
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
    }

    private void print(Node node) {

        messageLabelMain.setText("Przygotowuje wydruk");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            messageLabelMain.setText(job.jobStatusProperty().toString());
            boolean printed = job.printPage(node);
            if (printed) {
                job.endJob();
            } else {
                messageLabelMain.setText("Błąd wydruku");
            }
        } else {
            messageLabelMain.setText("Błąd wydruku");
        }
    }

    @FXML
    public void MenuListaCertyfikatowClick() throws IOException {
        Stage stage = new Stage();
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
        refreshListaAktywnychCertyfikatow();
    }


    @FXML
    void menuUstawieniaClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setResizable(false);
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

    public void setMessageLabelMain(String messageLabelMain) {
        this.messageLabelMain.setText(messageLabelMain);
    }
}

