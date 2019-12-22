package stages;

import dao.CertyfikatJakosciDao;
import dao.DokumentDao;
import dao.KontrahentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import model.CertyfikatJakosci;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Dokument;
import model.Kontrahent;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListaCertyfikatowController {


    private DokumentyStageController dokumentyStageController;
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

        addAllCertyfikatyListFromDatabaseToTableView();


    }

    @FXML
    protected void addAllCertyfikatyListFromDatabaseToTableView() {

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
                    b.nrFVProperty().getValue())

            );

        }
        this.lista = data;

    }

    @FXML
    protected void dodajNowyCertyfikatClick() throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/DodajNowyCertyfikat.fxml"));
        VBox vbox = loader.load();
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Dodaj Nowy");
        stage.show();


        DodajNowyCertyfikatController editedCertyfikat = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a

        editedCertyfikat.listaCertyfikatowController=ListaCertyfikatowController.this;

    }

    @FXML
    protected void odswiezClick()  {

       odswiezListeCertyfikatow();

    }

    public TableView<CertyfikatJakosci> getListaCertyfikatowTableView() {
        return listaCertyfikatowTableView;
    }

    @FXML
    void edycjaOnClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/DodajNowyCertyfikat.fxml"));
        VBox vbox = loader.load();
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Edycja");
        stage.show();


        DodajNowyCertyfikatController editedCertyfikat = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        CertyfikatJakosci selected = listaCertyfikatowTableView.getSelectionModel().getSelectedItem();
        editedCertyfikat.setNumerLabel(selected.getNumerCertyfikatu());
        editedCertyfikat.setNaszaNazwaField(selected.getNaszaNazwa());
        editedCertyfikat.setDataField(selected.getData());
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
     //   editedCertyfikat.setAsortymentCombobox(selected.getAsortyment());
        editedCertyfikat.setAsortymentCombobox(selected.getAsortyment());

        editedCertyfikat.listaCertyfikatowController=ListaCertyfikatowController.this;

    }


    public void odswiezListeCertyfikatow(){
        ObservableList<CertyfikatJakosci> data = listaCertyfikatowTableView.getItems();
        data.removeAll(data);
        addAllCertyfikatyListFromDatabaseToTableView();
    }
    @FXML
    public void usunCertyfikat(){
        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        certyfikatJakosciDao.deleteCertyfikatJakosci(listaCertyfikatowTableView.getSelectionModel().getSelectedItem());
        odswiezClick();
    }
    @FXML
    private void zamknijOnClick(){
        Stage stage = (Stage) zamknijButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    void zmienOnClick(ActionEvent event) {
        Dokument dokument = new Dokument(dokumentEdytowany.getNumerDokumentu(),dokumentEdytowany.getDataDokumentu(),listaCertyfikatowTableView.getSelectionModel().getSelectedItem());
        DokumentDao dokumentDao = new DokumentDao();
        dokumentDao.updateDokument(dokument);
        dokumentyStageController.refreshDokumentyListView();
        zamknijOnClick();
    }
    @FXML
    void tylkoAktywneCheck(ActionEvent event) {

        if (tylkoAktywne.isSelected()){
        ObservableList<CertyfikatJakosci> data = listaCertyfikatowTableView.getItems();
        data.removeAll(data);
        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        List<CertyfikatJakosci> dataFromDB = certyfikatJakosciDao.getAllCertyfikatJakosci();

        dataFromDB.stream()
                .filter(item -> item.getAktywny().equals("TAK"))
                .forEach(data::add);}
        else{
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

    public DokumentyStageController getDokumentyStageController() {
        return dokumentyStageController;
    }

    public void setDokumentyStageController(DokumentyStageController dokumentyStageController) {
        this.dokumentyStageController = dokumentyStageController;
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
}
