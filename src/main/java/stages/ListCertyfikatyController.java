package stages;

import dao.CertyfikatJakosciDao;
import dao.DokumentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import modelFX.CertyfikatJakosci;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import modelFX.Dokument;
import modelFX.WartosciDopuszczalnePaliwa;
import utils.DialogsUtils;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ListCertyfikatyController {

    private HomeController homeController;
    private ListDokumentyController listDokumentyController;
    private ObservableList<CertyfikatJakosci> lista;
    private Dokument dokumentEdytowany;

    @FXML
    private Button zamknijButton;
    @FXML
    private TableView<CertyfikatJakosci> listaCertyfikatowTableView;
    @FXML
    private Button dodajNowyButton;
    @FXML
    private Button zmienButton;
    @FXML
    private CheckBox tylkoAktywne;

    @FXML
    public void initialize() {

        setAllCertyfikatyTableView();
    }

    @FXML
    protected void setAllCertyfikatyTableView() {

        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        List<CertyfikatJakosci> list = certyfikatJakosciDao.getAllCertyfikatJakosci();

        Iterator<CertyfikatJakosci> iterator = list.iterator();
        ObservableList<CertyfikatJakosci> data = listaCertyfikatowTableView.getItems();

        for (CertyfikatJakosci b : list) {
            data.add(new CertyfikatJakosci(b.numerCertyfikatuProperty().getValue(),

                    b.aktywnyProperty().getValue(),
                    b.naszaNazwaProperty().getValue(),
                    b.asortymentProperty().getValue(),
                    b.dataProperty().getValue(),
                    b.numerCertyfikatuLaboratoriumProperty().getValue(),
                    b.zawartoscPopioluProperty().getValue(),
                    b.zawartoscSiarkiCalkowitejProperty().getValue(),
                    b.zawartoscCzesciLotnychProperty().getValue(),
                    b.wartoscOpalowaProperty().getValue(),
                    b.zdolnoscSpiekaniaProperty().getValue(),
                    b.minimalnyWymiarZiarnaProperty().getValue(),
                    b.maksymalnyWymiarZiarnaProperty().getValue(),
                    b.zawartoscPodziarnaProperty().getValue(),
                    b.zawartoscNadziarnaProperty().getValue(),
                    b.zawartoscWilgociCalkowitejProperty().getValue(),
                    b.dostawcaProperty().getValue(),
                    b.nrFVProperty().getValue(), b.iloscProperty().getValue())
            );
        }
        this.lista = data;

    }

    @FXML
    protected void addNowyCertyfikatOnClick() throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/AddNewCertyfikat.fxml"));
        VBox vbox = loader.load();
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Dodaj Nowy");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        AddNewCertyfikatController editedCertyfikat = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a

        editedCertyfikat.setListCertyfikatyController(ListCertyfikatyController.this);
        ZonedDateTime dataDzisiejsza = ZonedDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataDzisiejszaString = dataDzisiejsza.format(f);
        editedCertyfikat.setDatePicker(dataDzisiejszaString);
    }

    @FXML
    protected void odswiezClick() {

        odswiezListeCertyfikatow();
    }


    @FXML
    void edycjaOnClick() throws IOException {
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

        CertyfikatJakosci selected = listaCertyfikatowTableView.getSelectionModel().getSelectedItem();


        editedCertyfikat.setNumerLabel(selected.getNumerCertyfikatu());
        editedCertyfikat.setNaszaNazwaField(selected.getNaszaNazwa());
        editedCertyfikat.setDatePicker(selected.getData());
        editedCertyfikat.setNrCertyfikatuLaboratoriumField(selected.getNumerCertyfikatuLaboratorium());
        editedCertyfikat.setZawartoscPopioluField(selected.getZawartoscPopiolu());
        editedCertyfikat.setZawartoscSiarkiField(selected.getZawartoscSiarkiCalkowitej());
        editedCertyfikat.setZawartoscCzesciLotnychField(selected.getZawartoscCzesciLotnych());
        editedCertyfikat.setWartoscOpalowaField(selected.getWartoscOpalowa());
        editedCertyfikat.setSpiekalnoscField(selected.getZdolnoscSpiekania());
        editedCertyfikat.setMinWymiarziarnaField(selected.getMinimalnyWymiarZiarna());
        editedCertyfikat.setMaxWymiarziarnaField(selected.getMaksymalnyWymiarZiarna());
        editedCertyfikat.setZawartoscPodziarnaField(selected.getZawartoscPodziarna());
        editedCertyfikat.setZawartoscNadziarnaField(selected.getZawartoscNadziarna());
        editedCertyfikat.setZawartoscWilgociField(selected.getZawartoscWilgociCalkowitej());
        editedCertyfikat.setDostawcaField(selected.getDostawca());
        editedCertyfikat.setNrFvField(selected.getNrFV());
        editedCertyfikat.setAktywnyCheckbox(selected.getAktywny());
       // editedCertyfikat.setAsortymentCombobox(selected.getAsortyment());
        editedCertyfikat.getAsortymentCombobox().getSelectionModel().select(selected.getAsortyment());


        editedCertyfikat.setListCertyfikatyController(ListCertyfikatyController.this);
        editedCertyfikat.checkAllValidationField(false);


    }

    public void odswiezListeCertyfikatow() {
        ObservableList<CertyfikatJakosci> data = listaCertyfikatowTableView.getItems();
        data.removeAll(data);
        setAllCertyfikatyTableView();
    }

    @FXML
    public void usunCertyfikat() {
        Optional<ButtonType> result = DialogsUtils.confirmationDialog("delete.title", "delete.header");
        if (result.get() == ButtonType.OK) {
            CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
            certyfikatJakosciDao.deleteCertyfikatJakosci(listaCertyfikatowTableView.getSelectionModel().getSelectedItem());
            odswiezClick();
        }
    }

    @FXML
    private void okOnClick() {
        getHomeController().refreshClick();
        Stage stage = (Stage) zamknijButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void zmienOnClick(ActionEvent event) {
        Optional<ButtonType> result = DialogsUtils.confirmationDialog("replace.title", "replace.header");
        if (result.get() == ButtonType.OK) {
            Dokument dokument = new Dokument(dokumentEdytowany.getNumerDokumentu(), dokumentEdytowany.getDataDokumentu(), listaCertyfikatowTableView.getSelectionModel().getSelectedItem());
            DokumentDao dokumentDao = new DokumentDao();
            dokumentDao.updateDokument(dokument);
            listDokumentyController.odswiezDokumentyListView();
            Stage stage = (Stage) zamknijButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void dodajNowyContextMenu(ActionEvent event) throws IOException {
        addNowyCertyfikatOnClick();
    }


    @FXML
    void edytujContextMenu(ActionEvent event) throws IOException {
        edycjaOnClick();
    }


    @FXML
    void odswiezContextMenu(ActionEvent event) {
        odswiezClick();
    }

    @FXML
    public void usunContextMenu(ActionEvent actionEvent) {
        usunCertyfikat();
    }

    @FXML
    void tylkoAktywneCheck(ActionEvent event) {


        if (tylkoAktywne.isSelected()) {
            ObservableList<CertyfikatJakosci> data = listaCertyfikatowTableView.getItems();
            data.removeAll(data);
            CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
            List<CertyfikatJakosci> dataFromDB = certyfikatJakosciDao.getAllCertyfikatJakosci();

            dataFromDB.stream()
                    .filter(item -> item.getAktywny().equals("TAK"))
                    .forEach(data::add);
        } else {
            ObservableList<CertyfikatJakosci> data = listaCertyfikatowTableView.getItems();
            data.removeAll(data);
            CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
            List<CertyfikatJakosci> dataFromDB = certyfikatJakosciDao.getAllCertyfikatJakosci();
            data.addAll(dataFromDB);
        }

    }


    public void setListaCertyfikatowTableView(TableView<CertyfikatJakosci> listaCertyfikatowTableView) {
        this.listaCertyfikatowTableView = listaCertyfikatowTableView;
    }

    public ListDokumentyController getListDokumentyController() {
        return listDokumentyController;
    }

    public void setListDokumentyController(ListDokumentyController listDokumentyController) {
        this.listDokumentyController = listDokumentyController;
    }

    public ObservableList<CertyfikatJakosci> getLista() {
        return lista;
    }

    public void setLista(ObservableList<CertyfikatJakosci> lista) {
        this.lista = lista;
    }

    public Dokument getDokumentEdytowany() {
        return dokumentEdytowany;
    }

    public void setDokumentEdytowany(Dokument dokumentEdytowany) {
        this.dokumentEdytowany = dokumentEdytowany;
    }

    public void setZmienButton(Button zmienButton) {
        this.zmienButton = zmienButton;
    }

    public Button getZmienButton() {
        return zmienButton;
    }

    public TableView<CertyfikatJakosci> getListaCertyfikatowTableView() {
        return listaCertyfikatowTableView;
    }

    public Button getZamknijButton() {
        return zamknijButton;
    }

    public void setZamknijButton(Button zamknijButton) {
        this.zamknijButton = zamknijButton;
    }

    public void anulujOnClick(ActionEvent actionEvent) {
        okOnClick();
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
