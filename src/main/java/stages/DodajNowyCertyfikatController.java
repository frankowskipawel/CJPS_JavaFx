package stages;

import dao.CertyfikatJakosciDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CertyfikatJakosci;
import model.WartosciDopuszczalnePaliwa;

import java.io.IOException;

public class DodajNowyCertyfikatController {

    private ListaCertyfikatowController listaCertyfikatowController;
    private MainController mainController;

    @FXML
    private Label numerLabel;
    @FXML
    private TextField naszaNazwaField;
    @FXML
    private CheckBox aktywnyCheckbox;
    @FXML
    private ComboBox asortymentCombobox;
    @FXML
    private TextField dataField;
    @FXML
    private TextField nrCertyfikatuLaboratoriumField;
    @FXML
    private TextField zawartoscPopioluField;
    @FXML
    private TextField zawartoscSiarkiField;
    @FXML
    private TextField zawartoscCzesciLotnychField;
    @FXML
    private TextField wartoscOpalowaField;
    @FXML
    private TextField spiekalnoscField;
    @FXML
    private TextField minWymiarziarnaField;
    @FXML
    private TextField maxWymiarziarnaField;
    @FXML
    private TextField zawartoscPodziarnaField;
    @FXML
    private TextField zawartoscNadziarnaField;
    @FXML
    private TextField zawartoscWilgociField;
    @FXML
    private TextField dostawcaField;
    @FXML
    private TextField nrFvField;
    @FXML
    private Button anulujButton;


    @FXML
    protected void okOnClick() {

        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        String numerString;
        if (numerLabel.getText().equals("(auto)")) {
            numerString = Integer.toString(certyfikatJakosciDao.getNajwyzszyNumerCertyfikatuDao() + 1);
        } else {
            numerString = numerLabel.getText();
        }
        String isAktywny;
        if (aktywnyCheckbox.selectedProperty().getValue()) {
            isAktywny = "TAK";
        } else {
            isAktywny = "NIE";
        }
        String asortymentValue;

        asortymentValue = asortymentCombobox.getValue().toString();


        CertyfikatJakosci cerytfikatJakosci = new CertyfikatJakosci(numerString, isAktywny, naszaNazwaField.getText(), asortymentValue,
                dataField.getText(), nrCertyfikatuLaboratoriumField.getText(), zawartoscPopioluField.getText(), zawartoscSiarkiField.getText(),
                zawartoscCzesciLotnychField.getText(), wartoscOpalowaField.getText(), spiekalnoscField.getText(), minWymiarziarnaField.getText(),
                maxWymiarziarnaField.getText(), zawartoscPodziarnaField.getText(), zawartoscNadziarnaField.getText(), zawartoscWilgociField.getText(),
                dostawcaField.getText(), nrFvField.getText(),"");

        if (numerLabel.getText().equals("(auto)")) {
            certyfikatJakosciDao.addCertyfikatJakosciToDatabase(cerytfikatJakosci);
        } else {
            certyfikatJakosciDao.replaceCertyfikatJakosci(cerytfikatJakosci);
        }

        Stage stage = (Stage) anulujButton.getScene().getWindow();
        stage.close();
        if (this.mainController != null) {
            this.mainController.refreshClick();
        }
        if (this.listaCertyfikatowController != null) {
            this.listaCertyfikatowController.odswiezClick();
        }
    }

    @FXML
    void anulujOnClick(ActionEvent event) {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void kontrahentOnClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/KontrahentTableView.fxml"));
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Kontrahenci");
        stage.show();
        KontrahentController kontrahentController = loader.getController();
        kontrahentController.setDodajNowyCertyfikatController(DodajNowyCertyfikatController.this);

    }

    @FXML
    public void initialize() {

        asortymentCombobox.getItems().addAll(WartosciDopuszczalnePaliwa.values());
    }

    public void setNumerLabel(String numerLabel) {
        this.numerLabel.setText(numerLabel);
    }

    public void setNaszaNazwaField(String naszaNazwaField) {
        this.naszaNazwaField.setText(naszaNazwaField);
    }

    public void setAktywnyCheckbox(String aktywnyCheckbox) {
        if (aktywnyCheckbox.equals("TAK")) {
            this.aktywnyCheckbox.setSelected(true);
        } else {
            this.aktywnyCheckbox.setSelected(false);
        }
    }

    public void setAsortymentCombobox(String asortymentCombobox) {

        this.asortymentCombobox.setPromptText(asortymentCombobox);
    }

    public void setDataField(String dataField) {
        this.dataField.setText(dataField);
    }

    public void setNrCertyfikatuLaboratoriumField(String nrCertyfikatuLaboratoriumField) {
        this.nrCertyfikatuLaboratoriumField.setText(nrCertyfikatuLaboratoriumField);
    }

    public void setZawartoscPopioluField(String zawartoscPopioluField) {
        this.zawartoscPopioluField.setText(zawartoscPopioluField);
    }

    public void setZawartoscSiarkiField(String zawartoscSiarkiField) {
        this.zawartoscSiarkiField.setText(zawartoscSiarkiField);
    }

    public void setZawartoscCzesciLotnychField(String zawartoscCzesciLotnychField) {
        this.zawartoscCzesciLotnychField.setText(zawartoscCzesciLotnychField);
    }

    public void setWartoscOpalowaField(String wartoscOpalowaField) {
        this.wartoscOpalowaField.setText(wartoscOpalowaField);
    }

    public void setSpiekalnoscField(String spiekalnoscField) {
        this.spiekalnoscField.setText(spiekalnoscField);
    }

    public void setMinWymiarziarnaField(String minWymiarziarnaField) {
        this.minWymiarziarnaField.setText(minWymiarziarnaField);
    }

    public void setMaxWymiarziarnaField(String maxWymiarziarnaField) {
        this.maxWymiarziarnaField.setText(maxWymiarziarnaField);
    }

    public void setZawartoscPodziarnaField(String zawartoscPodziarnaField) {
        this.zawartoscPodziarnaField.setText(zawartoscPodziarnaField);
    }

    public void setZawartoscNadziarnaField(String zawartoscNadziarnaField) {
        this.zawartoscNadziarnaField.setText(zawartoscNadziarnaField);
    }

    public void setZawartoscWilgociField(String zawartoscWilgociField) {
        this.zawartoscWilgociField.setText(zawartoscWilgociField);
    }

    public void setDostawcaField(String dostawcaField) {
        this.dostawcaField.setText(dostawcaField);
    }

    public void setNrFvField(String nrFvField) {
        this.nrFvField.setText(nrFvField);
    }

    public ListaCertyfikatowController getListaCertyfikatowController() {
        return listaCertyfikatowController;
    }

    public void setListaCertyfikatowController(ListaCertyfikatowController listaCertyfikatowController) {
        this.listaCertyfikatowController = listaCertyfikatowController;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public ComboBox getAsortymentCombobox() {
        return asortymentCombobox;
    }
}

