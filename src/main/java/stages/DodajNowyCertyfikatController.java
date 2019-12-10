package stages;

import dao.CertyfikatJakosciDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.CertyfikatJakosci;
import model.WartościDopuszczalnePaliwa;

import java.io.IOException;

public class DodajNowyCertyfikatController {

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
    protected void dodajClick(ActionEvent event) throws IOException {

        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        int numer = certyfikatJakosciDao.getNajwyzszyNumerCertyfikatuDao()+1;
       // System.out.println(numer);
        String numerString = Integer.toString(numer);

        String isAktywny;
        if (aktywnyCheckbox.selectedProperty().getValue() == true) {
            isAktywny = "TAK";
        } else {
            isAktywny = "NIE";
        }
        String asortymentValue;
        if(asortymentCombobox.getSelectionModel().isEmpty()){
        asortymentValue="";} else {asortymentValue=asortymentCombobox.getSelectionModel().selectedItemProperty().getValue().toString();}

        CertyfikatJakosci cerytfikatJakosci = new CertyfikatJakosci(numerString, isAktywny, naszaNazwaField.getText(), asortymentValue,
                dataField.getText(), nrCertyfikatuLaboratoriumField.getText(), zawartoscPopioluField.getText(), zawartoscSiarkiField.getText(),
                zawartoscCzesciLotnychField.getText(), wartoscOpalowaField.getText(), spiekalnoscField.getText(), minWymiarziarnaField.getText(),
                maxWymiarziarnaField.getText(), zawartoscPodziarnaField.getText(), zawartoscNadziarnaField.getText(), zawartoscWilgociField.getText(),
                dostawcaField.getText(), nrFvField.getText());
       // System.out.println(cerytfikatJakosci);

        certyfikatJakosciDao.addCertyfikatJakosciToDatabase(cerytfikatJakosci);


    }

    @FXML
    public void initialize() {


        asortymentCombobox.getItems().addAll(
                WartościDopuszczalnePaliwa.WEGIEL_KAMIENNY_KOSTKA,
                WartościDopuszczalnePaliwa.WEGIEL_KAMIENNY_ORZECH,
                WartościDopuszczalnePaliwa.WEGIEL_KAMIENNY_MIAL,
                WartościDopuszczalnePaliwa.WEGIEL_KAMIENNY_EKOGROSZEK,
                WartościDopuszczalnePaliwa.WEGIEL_KAMIENNY_PELLET


        );
    }
}
