package stages;

import config.Config;
import dao.CertyfikatJakosciDao;
import dao.DaoService;
import dao.DokumentDao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import modelFXML.CertyfikatJakosci;
import modelFXML.Dokument;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.DialogsUtils;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class HomeController {

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
    private MenuItem edytujContextItem;
    @FXML
    private CheckBox jednaKopiaCheckBox;
    @FXML
    private CheckBox podgladChechBox;
    @FXML
    private CheckBox nieDrukuj;

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
        jednaKopiaCheckBox.setSelected(false);
        podgladChechBox.setSelected(false);
        nieDrukuj.setSelected(false);
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

            showAndPrintDokument(dokument, !nieDrukuj.isSelected(), podgladChechBox.isSelected()); //zamienic na print docelowo}
            jednaKopiaCheckBox.setSelected(false);
            podgladChechBox.setSelected(false);
            nieDrukuj.setSelected(false);
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
        loader.setLocation(this.getClass().getResource("/stages/Wydruk.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Podgląd wydruku");
        stage.initModality(Modality.APPLICATION_MODAL);
        if (show) {

            stage.show();
        }
        WydrukController wydrukController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        wydrukController.setFieldInWydruk(dokument);
        wydrukController.homeController = HomeController.this;
        if (print) {
            wydrukController.getJednaKopiaCheckBox().setVisible(false);
            wydrukController.getPrintButton().setVisible(false);
            if (jednaKopiaCheckBox.isSelected()) {
                wydrukController.print(1);
            } else {
                wydrukController.print(2);
            }
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
        CertyfikatJakosci certyfikatJakosci = new CertyfikatJakosciDao().findCertyfikat(listaAktywnychCertyfikatowTableViewStronaGlowna.getSelectionModel().getSelectedItem().getNumerCertyfikatuAktywne());
        DokumentDao dokumentDao = new DokumentDao();

        int numer = dokumentDao.getNextNumberDokumentDao();
        //System.out.println(numer);

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
        loader.setLocation(this.getClass().getResource("/stages/ConfigPodmiot.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Ustawienia podmiotu");
        stage.initModality(Modality.APPLICATION_MODAL);

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
        Optional<ButtonType> result = DialogsUtils.confirmationDialog("exit.title", "exit.header");
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }


        // System.exit(0);
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
                                b.getCertyfikatJakosci().nrFVProperty().getValue(),
                                ""))
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
        loader.setLocation(this.getClass().getResource("/stages/ListCertyfikaty.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Lista Certyfikatów");
        stage.initModality(Modality.APPLICATION_MODAL);


        ListCertyfikatyController listCertyfikatyController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        listCertyfikatyController.setHomeController(this);

        stage.showAndWait();
    }

    @FXML
    public void usunOstatniMenuClick() throws IOException {

        Optional<ButtonType> result = DialogsUtils.confirmationDialog("delete.title", "delete.header");
        if (result.get() == ButtonType.OK) {


            DokumentDao dokumentDao = new DokumentDao();
            dokumentDao.deleteLastDokument();
            refreshListaDokumentow();
            refreshListaAktywnychCertyfikatow();
        }
    }


    @FXML
    void menuUstawieniaClick(ActionEvent event) throws Exception {
        Optional<ButtonType> result = DialogsUtils.configDialog("config.title", "config.header");
        if (result.get() == ButtonType.OK) {
            Config config = new Config();
            config.getConfigFromFile();
            DaoService daoService = new DaoService();
            daoService.init();
            refreshClick();
        }
    }

    @FXML
    void menuKontrahenciClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Kontrahenci");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("ListKontrahenci.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void menuDokumentyClick(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/ListDokumenty.fxml"));
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Lista Dokumentów");
        stage.initModality(Modality.APPLICATION_MODAL);

        ListDokumentyController listDokumentyController = loader.getController(); //
        listDokumentyController.homeController = HomeController.this;

        stage.show();
    }

    @FXML
    public void menuOprogramieOnClick() {
        DialogsUtils.infoDialog("info.title", "info.header", "info.text");
    }

    public void setMessageLabelMain(String messageLabelMain) {
        this.messageLabelMain.setText(messageLabelMain);
    }


    public void edytujContextMenu(ActionEvent actionEvent) throws IOException {

        if (listaAktywnychCertyfikatowTableViewStronaGlowna.getSelectionModel().getSelectedItem() != null) {


            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/stages/AddNewCertyfikat.fxml"));
            VBox vbox = loader.load();
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            stage.setTitle("Edycja");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();


            AddNewCertyfikatController editedCertyfikat = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
            editedCertyfikat.setHomeController(HomeController.this);
            CertyfikatJakosci selected = listaAktywnychCertyfikatowTableViewStronaGlowna.getSelectionModel().getSelectedItem();
            CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
            CertyfikatJakosci selectedCertyfikat = certyfikatJakosciDao.findCertyfikat(selected.getNumerCertyfikatuAktywne());


            editedCertyfikat.setNumerLabel(selectedCertyfikat.getNumerCertyfikatu());
            editedCertyfikat.setNaszaNazwaField(selectedCertyfikat.getNaszaNazwa());

            editedCertyfikat.setDatePicker(selectedCertyfikat.getData());
            editedCertyfikat.setNrCertyfikatuLaboratoriumField(selectedCertyfikat.getNumerCertyfikatuLaboratorium());
            editedCertyfikat.setZawartoscPopioluField(selectedCertyfikat.getZawartoscPopiolu());
            editedCertyfikat.setZawartoscSiarkiField(selectedCertyfikat.getZawartoscSiarkiCalkowitej());
            editedCertyfikat.setZawartoscCzesciLotnychField(selectedCertyfikat.getZawartoscCzesciLotnych());
            editedCertyfikat.setWartoscOpalowaField(selectedCertyfikat.getWartoscOpalowa());
            editedCertyfikat.setSpiekalnoscField(selectedCertyfikat.getZdolnoscSpiekania());
            editedCertyfikat.setMinWymiarziarnaField(selectedCertyfikat.getMinimalnyWymiarZiarna());
            editedCertyfikat.setMaxWymiarziarnaField(selectedCertyfikat.getMaksymalnyWymiarZiarna());
            editedCertyfikat.setZawartoscPodziarnaField(selectedCertyfikat.getZawartoscPodziarna());
            editedCertyfikat.setZawartoscNadziarnaField(selectedCertyfikat.getZawartoscNadziarna());
            editedCertyfikat.setZawartoscWilgociField(selectedCertyfikat.getZawartoscWilgociCalkowitej());
            editedCertyfikat.setDostawcaField(selectedCertyfikat.getDostawca());
            editedCertyfikat.setNrFvField(selectedCertyfikat.getNrFV());
            editedCertyfikat.setAktywnyCheckbox(selectedCertyfikat.getAktywny());
            editedCertyfikat.getAsortymentCombobox().getSelectionModel().select(selectedCertyfikat.getAsortyment());
            editedCertyfikat.setHomeController(HomeController.this);

        } else {
            messageLabelMain.setText("Zaznacz najpierw certyfikat");
        }
    }

    @FXML
    void usunOstatniMenuContextClick(ActionEvent event) throws IOException {
        usunOstatniMenuClick();
    }

    @FXML
    void podgladMenuContextClick() throws IOException {
        showAndPrintDokument(listaDokumentowListViewStronaGlowna.getSelectionModel().getSelectedItem(), false, true);
    }
}

