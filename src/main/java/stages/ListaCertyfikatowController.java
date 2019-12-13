package stages;

import dao.CertyfikatJakosciDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.CertyfikatJakosci;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ListaCertyfikatowController {


    @FXML
    private Button zamknijButton;
    @FXML
    private TableView<CertyfikatJakosci> listaCertyfikatowTableView;
    @FXML
    private Button dodajNowyButton;

    @FXML
    public void initialize() {

        addAllCertyfikatyListFromDatabaseToTableView();


    }

    @FXML
    protected void addAllCertyfikatyListFromDatabaseToTableView() {

        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        List<CertyfikatJakosci> list = certyfikatJakosciDao.getAllCertyfikatJakosci();



        Iterator<CertyfikatJakosci> iterator = list.iterator();

        for (CertyfikatJakosci b : list) {

            ObservableList<CertyfikatJakosci> data = listaCertyfikatowTableView.getItems();

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

}
