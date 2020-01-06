package stages;

import dao.CertyfikatJakosciDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import modelFX.CertyfikatJakosci;
import modelFX.WartosciDopuszczalnePaliwa;
import utils.DialogsUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewCertyfikatController {

    private ListCertyfikatyController listCertyfikatyController;
    private HomeController homeController;

    @FXML
    private Label numerLabel;
    @FXML
    private TextField naszaNazwaField;
    @FXML
    private CheckBox aktywnyCheckbox;
    @FXML
    private ComboBox asortymentCombobox;
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
    private DatePicker datePicker;
    @FXML
    private Label minPopiolLabel;
    @FXML
    private Label minSiarkaLabel;
    @FXML
    private Label minZawCzLotnychLabel;
    @FXML
    private Label minWartoscOpalowaLabel;
    @FXML
    private Label maxWartoscOpalowaLabel;
    @FXML
    private Label minSpiekalnoscLabel;
    @FXML
    private Label minWymiarZiarnaLabel;
    @FXML
    private Label minPodziarnoLabel;
    @FXML
    private Label minNadziarnoLabel;
    @FXML
    private Label minZawartoscWilgociLabel;
    @FXML
    private Label maxPopiolLabel;
    @FXML
    private Label maxSiarkaLabel;
    @FXML
    private Label maxZawCzLotnychLabel;
    @FXML
    private Label maxSpiekalnoscLabel;
    @FXML
    private Label maxWymiarZiarnaLabel;
    @FXML
    private Label maxPodziarnoLabel;
    @FXML
    private Label maxNadziarnoLabel;
    @FXML
    private Label maxZawartoscWilgociLabel;
    @FXML
    private CheckBox niestandardoweCheckBox;


    @FXML
    protected void okOnClick() throws IOException {


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
        boolean validate = true;
       // System.out.println(asortymentCombobox.getValue());


        if (!asortymentCombobox.getValue().toString().equals("")) {
            asortymentValue = asortymentCombobox.getValue().toString();
        } else {
            DialogsUtils.errorDialog("errorAsortyment.title", "errorAsortyment.header");
            validate = false;
            asortymentValue = "";
        }
        //  System.out.println("asot: "+asortymentCombobox.getValue().toString());
        if (validate) {
            CertyfikatJakosci cerytfikatJakosci = new CertyfikatJakosci(numerString, isAktywny, naszaNazwaField.getText(), asortymentValue,
                    datePicker.getValue().toString(), nrCertyfikatuLaboratoriumField.getText(), zawartoscPopioluField.getText(), zawartoscSiarkiField.getText(),
                    zawartoscCzesciLotnychField.getText(), wartoscOpalowaField.getText(), spiekalnoscField.getText(), minWymiarziarnaField.getText(),
                    maxWymiarziarnaField.getText(), zawartoscPodziarnaField.getText(), zawartoscNadziarnaField.getText(), zawartoscWilgociField.getText(),
                    dostawcaField.getText(), nrFvField.getText(), "");

            if (numerLabel.getText().equals("(auto)")) {
                certyfikatJakosciDao.addCertyfikatJakosciToDatabase(cerytfikatJakosci);
            } else {
                certyfikatJakosciDao.replaceCertyfikatJakosci(cerytfikatJakosci);
            }

            Stage stage = (Stage) anulujButton.getScene().getWindow();
            stage.close();
            if (this.homeController != null) {
                this.homeController.refreshClick();
            }
            if (this.listCertyfikatyController != null) {
                this.listCertyfikatyController.odswiezClick();
            }
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
        loader.setLocation(this.getClass().getResource("/stages/ListKontrahenci.fxml"));
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Kontrahenci");
        stage.show();
        ListKontrahenciController listKontrahenciController = loader.getController();
        listKontrahenciController.setAddNewCertyfikatController(AddNewCertyfikatController.this);

    }

    @FXML
    public void initialize() {

        asortymentCombobox.getItems().addAll(WartosciDopuszczalnePaliwa.values());


        String pattern = "yyyy-MM-dd";
        // datePicker.setPromptText(pattern);
        try {
            datePicker.setConverter(new StringConverter<LocalDate>() {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);

                @Override
                public String toString(LocalDate object) {
                    if (object == null) {
                        return null;
                    }
                    return dtf.format(object);
                }

                @Override
                public LocalDate fromString(String string) {
                    if (string != null & !string.isEmpty()) {
                        return LocalDate.parse(string, dtf);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


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

    public void setDatePicker(String datePicker) {

        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(datePicker, f);

        this.datePicker.setValue(data);
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

    public ListCertyfikatyController getListCertyfikatyController() {
        return listCertyfikatyController;
    }

    public void setListCertyfikatyController(ListCertyfikatyController listCertyfikatyController) {
        this.listCertyfikatyController = listCertyfikatyController;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public ComboBox getAsortymentCombobox() {
        return asortymentCombobox;
    }

    public void dataOnClick(ActionEvent actionEvent) {

    }

    public void asortymentSelectOnClick(ActionEvent actionEvent) {

        WartosciDopuszczalnePaliwa wartosciDopuszczalnePaliwa = WartosciDopuszczalnePaliwa.valueOf(getAsortymentCombobox().getSelectionModel().getSelectedItem().toString());
        setMinPopiolLabel(wartosciDopuszczalnePaliwa.getMinPopiol());
        setMaxPopiolLabel(wartosciDopuszczalnePaliwa.getMaxPopiol());
        setMinSiarkaLabel(wartosciDopuszczalnePaliwa.getMinSiarka());
        setMaxSiarkaLabel(wartosciDopuszczalnePaliwa.getMaxSiarka());
        setMinZawCzLotnychLabel(wartosciDopuszczalnePaliwa.getMinCzLotne());
        setMaxZawCzLotnychLabel(wartosciDopuszczalnePaliwa.getMaxCzLotne());
        setMinWartoscOpalowaLabel(wartosciDopuszczalnePaliwa.getMinWartoscOpalowa());
        setMaxWartoscOpalowaLabel(wartosciDopuszczalnePaliwa.getMaxWartoscOpalowa());
        setMinSpiekalnoscLabel(wartosciDopuszczalnePaliwa.getMinSpiekalnsc());
        setMaxSpiekalnoscLabel(wartosciDopuszczalnePaliwa.getMaxSpiekalnosc());
        setMinWymiarZiarnaLabel(wartosciDopuszczalnePaliwa.getMinWymiarZiarna());
        setMaxWymiarZiarnaLabel(wartosciDopuszczalnePaliwa.getMaxWymiarZiarna());
        setMinPodziarnoLabel(wartosciDopuszczalnePaliwa.getMinPodziarno());
        setMaxPodziarnoLabel(wartosciDopuszczalnePaliwa.getMaxPodziarno());
        setMinNadziarnoLabel(wartosciDopuszczalnePaliwa.getMinNadziarno());
        setMaxNadziarnoLabel(wartosciDopuszczalnePaliwa.getMaxNadziarno());
        setMinZawartoscWilgociLabel(wartosciDopuszczalnePaliwa.getMinWilgotnosc());
        setMaxZawartoscWilgociLabel(wartosciDopuszczalnePaliwa.getMaxWilgotnosc());
        checkAllValidationField(false);
    }


    public Label getMinPopiolLabel() {
        return minPopiolLabel;
    }

    public void setMinPopiolLabel(String minPopiolLabel) {
        this.minPopiolLabel.setText(minPopiolLabel);
    }

    public Label getMinSiarkaLabel() {
        return minSiarkaLabel;
    }

    public void setMinSiarkaLabel(String minSiarkaLabel) {
        this.minSiarkaLabel.setText(minSiarkaLabel);
    }

    public Label getMinZawCzLotnychLabel() {
        return minZawCzLotnychLabel;
    }

    public void setMinZawCzLotnychLabel(String minZawCzLotnychLabel) {
        this.minZawCzLotnychLabel.setText(minZawCzLotnychLabel);
    }

    public Label getMinWartoscOpalowaLabel() {
        return minWartoscOpalowaLabel;
    }

    public void setMinWartoscOpalowaLabel(String minWartoscOpalowaLabel) {
        this.minWartoscOpalowaLabel.setText(minWartoscOpalowaLabel);
    }

    public Label getMinSpiekalnoscLabel() {
        return minSpiekalnoscLabel;
    }

    public void setMinSpiekalnoscLabel(String minSpiekalnoscLabel) {
        this.minSpiekalnoscLabel.setText(minSpiekalnoscLabel);
    }

    public Label getMinWymiarZiarnaLabel() {
        return minWymiarZiarnaLabel;
    }

    public void setMinWymiarZiarnaLabel(String minWymiarZiarnaLabel) {
        this.minWymiarZiarnaLabel.setText(minWymiarZiarnaLabel);
    }

    public Label getMinPodziarnoLabel() {
        return minPodziarnoLabel;
    }

    public void setMinPodziarnoLabel(String minPodziarnoLabel) {
        this.minPodziarnoLabel.setText(minPodziarnoLabel);
    }

    public Label getMinNadziarnoLabel() {
        return minNadziarnoLabel;
    }

    public void setMinNadziarnoLabel(String minNadziarnoLabel) {
        this.minNadziarnoLabel.setText(minNadziarnoLabel);
    }

    public Label getMinZawartoscWilgociLabel() {
        return minZawartoscWilgociLabel;
    }

    public void setMinZawartoscWilgociLabel(String minZawartoscWilgociLabel) {
        this.minZawartoscWilgociLabel.setText(minZawartoscWilgociLabel);
    }

    public Label getMaxPopiolLabel() {
        return maxPopiolLabel;
    }

    public void setMaxPopiolLabel(String maxPopiolLabel) {
        this.maxPopiolLabel.setText(maxPopiolLabel);
    }

    public Label getMaxSiarkaLabel() {
        return maxSiarkaLabel;
    }

    public void setMaxSiarkaLabel(String maxSiarkaLabel) {
        this.maxSiarkaLabel.setText(maxSiarkaLabel);
    }

    public Label getMaxZawCzLotnychLabel() {
        return maxZawCzLotnychLabel;
    }

    public void setMaxZawCzLotnychLabel(String maxZawCzLotnychLabel) {
        this.maxZawCzLotnychLabel.setText(maxZawCzLotnychLabel);
    }

    public Label getMaxWartoscOpalowaLabel() {
        return maxWartoscOpalowaLabel;
    }

    public void setMaxWartoscOpalowaLabel(String maxWartoscOpalowaLabel) {
        this.maxWartoscOpalowaLabel.setText(maxWartoscOpalowaLabel);
    }

    public Label getMaxSpiekalnoscLabel() {
        return maxSpiekalnoscLabel;
    }

    public void setMaxSpiekalnoscLabel(String maxSpiekalnoscLabel) {
        this.maxSpiekalnoscLabel.setText(maxSpiekalnoscLabel);
    }

    public Label getMaxWymiarZiarnaLabel() {
        return maxWymiarZiarnaLabel;
    }

    public void setMaxWymiarZiarnaLabel(String maxWymiarZiarnaLabel) {
        this.maxWymiarZiarnaLabel.setText(maxWymiarZiarnaLabel);
    }

    public Label getMaxPodziarnoLabel() {
        return maxPodziarnoLabel;
    }

    public void setMaxPodziarnoLabel(String maxPodziarnoLabel) {
        this.maxPodziarnoLabel.setText(maxPodziarnoLabel);
    }

    public Label getMaxNadziarnoLabel() {
        return maxNadziarnoLabel;
    }

    public void setMaxNadziarnoLabel(String maxNadziarnoLabel) {
        this.maxNadziarnoLabel.setText(maxNadziarnoLabel);
    }

    public Label getMaxZawartoscWilgociLabel() {
        return maxZawartoscWilgociLabel;
    }

    public void setMaxZawartoscWilgociLabel(String maxZawartoscWilgociLabel) {
        this.maxZawartoscWilgociLabel.setText(maxZawartoscWilgociLabel);
    }

    public TextField getNaszaNazwaField() {
        return naszaNazwaField;
    }

    public void setNaszaNazwaField(TextField naszaNazwaField) {
        this.naszaNazwaField = naszaNazwaField;
    }

    public CheckBox getAktywnyCheckbox() {
        return aktywnyCheckbox;
    }

    public void setAktywnyCheckbox(CheckBox aktywnyCheckbox) {
        this.aktywnyCheckbox = aktywnyCheckbox;
    }

    public void setAsortymentCombobox(ComboBox asortymentCombobox) {
        this.asortymentCombobox = asortymentCombobox;
    }

    public TextField getNrCertyfikatuLaboratoriumField() {
        return nrCertyfikatuLaboratoriumField;
    }

    public void setNrCertyfikatuLaboratoriumField(TextField nrCertyfikatuLaboratoriumField) {
        this.nrCertyfikatuLaboratoriumField = nrCertyfikatuLaboratoriumField;
    }

    public TextField getZawartoscPopioluField() {
        return zawartoscPopioluField;
    }

    public void setZawartoscPopioluField(TextField zawartoscPopioluField) {
        this.zawartoscPopioluField = zawartoscPopioluField;
    }

    public TextField getZawartoscSiarkiField() {
        return zawartoscSiarkiField;
    }

    public void setZawartoscSiarkiField(TextField zawartoscSiarkiField) {
        this.zawartoscSiarkiField = zawartoscSiarkiField;
    }

    public TextField getZawartoscCzesciLotnychField() {
        return zawartoscCzesciLotnychField;
    }

    public void setZawartoscCzesciLotnychField(TextField zawartoscCzesciLotnychField) {
        this.zawartoscCzesciLotnychField = zawartoscCzesciLotnychField;
    }

    public TextField getWartoscOpalowaField() {
        return wartoscOpalowaField;
    }

    public void setWartoscOpalowaField(TextField wartoscOpalowaField) {
        this.wartoscOpalowaField = wartoscOpalowaField;
    }

    public TextField getSpiekalnoscField() {
        return spiekalnoscField;
    }

    public void setSpiekalnoscField(TextField spiekalnoscField) {
        this.spiekalnoscField = spiekalnoscField;
    }

    public TextField getMinWymiarziarnaField() {
        return minWymiarziarnaField;
    }

    public void setMinWymiarziarnaField(TextField minWymiarziarnaField) {
        this.minWymiarziarnaField = minWymiarziarnaField;
    }

    public TextField getMaxWymiarziarnaField() {
        return maxWymiarziarnaField;
    }

    public void setMaxWymiarziarnaField(TextField maxWymiarziarnaField) {
        this.maxWymiarziarnaField = maxWymiarziarnaField;
    }

    public TextField getZawartoscPodziarnaField() {
        return zawartoscPodziarnaField;
    }

    public void setZawartoscPodziarnaField(TextField zawartoscPodziarnaField) {
        this.zawartoscPodziarnaField = zawartoscPodziarnaField;
    }

    public TextField getZawartoscNadziarnaField() {
        return zawartoscNadziarnaField;
    }

    public void setZawartoscNadziarnaField(TextField zawartoscNadziarnaField) {
        this.zawartoscNadziarnaField = zawartoscNadziarnaField;
    }

    public TextField getZawartoscWilgociField() {
        return zawartoscWilgociField;
    }

    public void setZawartoscWilgociField(TextField zawartoscWilgociField) {
        this.zawartoscWilgociField = zawartoscWilgociField;
    }

    public TextField getDostawcaField() {
        return dostawcaField;
    }

    public void setDostawcaField(TextField dostawcaField) {
        this.dostawcaField = dostawcaField;
    }

    public TextField getNrFvField() {
        return nrFvField;
    }

    public void setNrFvField(TextField nrFvField) {
        this.nrFvField = nrFvField;
    }


    public CheckBox getNiestandardoweCheckBox() {
        return niestandardoweCheckBox;
    }

    public void setNiestandardoweCheckBox(CheckBox niestandardoweCheckBox) {
        this.niestandardoweCheckBox = niestandardoweCheckBox;
    }

    public void zawartoscPopioluOnKeyReleased() {

        isNumberValidation(getZawartoscPopioluField(), minPopiolLabel, maxPopiolLabel, true);
    }

    public void zawartoscSiarkiOnKeyReleased() {
        isNumberValidation(getZawartoscSiarkiField(), minSiarkaLabel, maxSiarkaLabel, true);
    }

    public void zawartoscCzesiLotnychOnKeyReleased() {
        isNumberValidation(getZawartoscCzesciLotnychField(), minZawCzLotnychLabel, maxZawCzLotnychLabel, true);
    }

    public void wartoscOpalowaOnKeyReleased() {
        isNumberValidation(getWartoscOpalowaField(), minWartoscOpalowaLabel, maxWartoscOpalowaLabel, true);
    }

    public void spiekalnoscOnKeyReleased() {
        isNumberValidation(getSpiekalnoscField(), minSpiekalnoscLabel, maxSpiekalnoscLabel, true);
    }

    public void minWymiarZiarnaOnKeyReleased() {
        isNumberValidation(getMinWymiarziarnaField(), minWymiarZiarnaLabel, maxWymiarZiarnaLabel, true);
    }

    public void maxWymiarZiarnaOnKeyReleased() {
        isNumberValidation(getMaxWymiarziarnaField(), minWymiarZiarnaLabel, maxWymiarZiarnaLabel, true);
    }

    public void zawartoscPodziarnaOnKeyReleased() {
        isNumberValidation(getZawartoscPodziarnaField(), minPodziarnoLabel, maxPodziarnoLabel, true);
    }

    public void zawartoscNadziarnaOnKeyReleased() {
        isNumberValidation(getZawartoscNadziarnaField(), minNadziarnoLabel, maxNadziarnoLabel, true);
    }

    public void zawartoscWilgociOnKeyReleased() {
        isNumberValidation(getZawartoscWilgociField(), minZawartoscWilgociLabel, maxZawartoscWilgociLabel, true);

    }

    private void isNumberValidation(TextField textField, Label wartoscMinTemp, Label wartoscMaxTemp, boolean repair) {
        String input = textField.getText();
        String wartoscMin;
        String wartoscMax;
        if (wartoscMinTemp.getText().equals("-")) {
            wartoscMin = "0";
        } else {
            wartoscMin = wartoscMinTemp.getText();
        }
        if (wartoscMaxTemp.getText().equals("-")) {
            wartoscMax = "9999";
        } else {
            wartoscMax = wartoscMaxTemp.getText();
        }
        if (!niestandardoweCheckBox.isSelected()) {
            boolean find = false;
            Pattern pattern = Pattern.compile("\\d+\\,\\d*|\\,?\\d+");
            Matcher matcher = pattern.matcher(input);
            Double value;
            while (matcher.find()) {

                if (repair) {
                    textField.setText(matcher.group());
                }
                value = Double.parseDouble(matcher.group().replace(",", "."));
                if (value > Double.parseDouble(wartoscMax.replace(",", ".")) || value < Double.parseDouble(wartoscMin.replace(",", "."))) {
                    textField.setStyle("-fx-background-color: violet");
                } else {
                    textField.setStyle("");
                }
                find = true;
            }
            if (!find) {
                textField.setText("");
            }
            textField.positionCaret(textField.getText().length());
        }
    }


    public void checkAllValidationField(boolean repair) {

        isNumberValidation(getZawartoscPopioluField(), minPopiolLabel, maxPopiolLabel, repair);
        isNumberValidation(getZawartoscSiarkiField(), minSiarkaLabel, maxSiarkaLabel, repair);
        isNumberValidation(getZawartoscCzesciLotnychField(), minZawCzLotnychLabel, maxZawCzLotnychLabel, repair);
        isNumberValidation(getWartoscOpalowaField(), minWartoscOpalowaLabel, maxWartoscOpalowaLabel, repair);
        isNumberValidation(getSpiekalnoscField(), minSpiekalnoscLabel, maxSpiekalnoscLabel, repair);
        isNumberValidation(getMinWymiarziarnaField(), minWymiarZiarnaLabel, maxWymiarZiarnaLabel, repair);
        isNumberValidation(getMaxWymiarziarnaField(), minWymiarZiarnaLabel, maxWymiarZiarnaLabel, repair);
        isNumberValidation(getZawartoscPodziarnaField(), minPodziarnoLabel, maxPodziarnoLabel, repair);
        isNumberValidation(getZawartoscNadziarnaField(), minNadziarnoLabel, maxNadziarnoLabel, repair);
        isNumberValidation(getZawartoscWilgociField(), minZawartoscWilgociLabel, maxZawartoscWilgociLabel, repair);
    }

    @FXML
    private void niestandardoweCheckButtonOnClick() {
        if (!niestandardoweCheckBox.isSelected()) {
            checkAllValidationField(false);
        } else {
            setAllFieldNoColour();
        }
    }

    private void setAllFieldNoColour() {
        getZawartoscPopioluField().setStyle("");
        getZawartoscSiarkiField().setStyle("");
        getZawartoscCzesciLotnychField().setStyle("");
        getWartoscOpalowaField().setStyle("");
        getSpiekalnoscField().setStyle("");
        getMinWymiarziarnaField().setStyle("");
        getMaxWymiarziarnaField().setStyle("");
        getZawartoscPodziarnaField().setStyle("");
        getZawartoscNadziarnaField().setStyle("");
        getZawartoscWilgociField().setStyle("");
    }

}

