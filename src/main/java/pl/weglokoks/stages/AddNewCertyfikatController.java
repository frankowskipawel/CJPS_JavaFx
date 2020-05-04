package pl.weglokoks.stages;

import pl.weglokoks.dao.CertyfikatJakosciDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import pl.weglokoks.modelFX.CertyfikatJakosci;
import pl.weglokoks.modelFX.WartosciDopuszczalnePaliwa;
import pl.weglokoks.utils.DialogsUtils;

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
            numerString = Integer.toString(certyfikatJakosciDao.findLastNumber() + 1);
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
        asortymentValue = "";
        boolean temp = true;
        if (!asortymentCombobox.getSelectionModel().isEmpty()) {
            asortymentValue = asortymentCombobox.getValue().toString();
            temp = false;
        }
        if (temp) {
            try {
                if (!asortymentCombobox.getValue().toString().equals("")) {
                    asortymentValue = asortymentCombobox.getValue().toString();
                }
            } catch (Exception e) {
                DialogsUtils.errorDialog("errorAsortyment.title", "errorAsortyment.header");
                validate = false;
                asortymentValue = "";
            }
        }
        if (validate) {
            CertyfikatJakosci cerytfikatJakosci = new CertyfikatJakosci(numerString, isAktywny, naszaNazwaField.getText(), asortymentValue,
                    datePicker.getValue().toString(), nrCertyfikatuLaboratoriumField.getText(), zawartoscPopioluField.getText(), zawartoscSiarkiField.getText(),
                    zawartoscCzesciLotnychField.getText(), wartoscOpalowaField.getText(), spiekalnoscField.getText(), minWymiarziarnaField.getText(),
                    maxWymiarziarnaField.getText(), zawartoscPodziarnaField.getText(), zawartoscNadziarnaField.getText(), zawartoscWilgociField.getText(),
                    dostawcaField.getText(), nrFvField.getText(), "");

            if (numerLabel.getText().equals("(auto)")) {
                certyfikatJakosciDao.insertCertyfikatJakosci(cerytfikatJakosci);
            } else {
                certyfikatJakosciDao.updateCertyfikatJakosci(cerytfikatJakosci);
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
        loader.setLocation(this.getClass().getResource("/pl/weglokoks/stages/ListKontrahenci.fxml"));
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

    public void setListCertyfikatyController(ListCertyfikatyController listCertyfikatyController) {
        this.listCertyfikatyController = listCertyfikatyController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public ComboBox getAsortymentCombobox() {
        return asortymentCombobox;
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


    public void setMinPopiolLabel(String minPopiolLabel) {
        this.minPopiolLabel.setText(minPopiolLabel);
    }

    public void setMinSiarkaLabel(String minSiarkaLabel) {
        this.minSiarkaLabel.setText(minSiarkaLabel);
    }

    public void setMinZawCzLotnychLabel(String minZawCzLotnychLabel) {
        this.minZawCzLotnychLabel.setText(minZawCzLotnychLabel);
    }

    public void setMinWartoscOpalowaLabel(String minWartoscOpalowaLabel) {
        this.minWartoscOpalowaLabel.setText(minWartoscOpalowaLabel);
    }

    public void setMinSpiekalnoscLabel(String minSpiekalnoscLabel) {
        this.minSpiekalnoscLabel.setText(minSpiekalnoscLabel);
    }

    public void setMinWymiarZiarnaLabel(String minWymiarZiarnaLabel) {
        this.minWymiarZiarnaLabel.setText(minWymiarZiarnaLabel);
    }

    public void setMinPodziarnoLabel(String minPodziarnoLabel) {
        this.minPodziarnoLabel.setText(minPodziarnoLabel);
    }

    public void setMinNadziarnoLabel(String minNadziarnoLabel) {
        this.minNadziarnoLabel.setText(minNadziarnoLabel);
    }

    public void setMinZawartoscWilgociLabel(String minZawartoscWilgociLabel) {
        this.minZawartoscWilgociLabel.setText(minZawartoscWilgociLabel);
    }

    public void setMaxPopiolLabel(String maxPopiolLabel) {
        this.maxPopiolLabel.setText(maxPopiolLabel);
    }

    public void setMaxSiarkaLabel(String maxSiarkaLabel) {
        this.maxSiarkaLabel.setText(maxSiarkaLabel);
    }

    public void setMaxZawCzLotnychLabel(String maxZawCzLotnychLabel) {
        this.maxZawCzLotnychLabel.setText(maxZawCzLotnychLabel);
    }

    public void setMaxWartoscOpalowaLabel(String maxWartoscOpalowaLabel) {
        this.maxWartoscOpalowaLabel.setText(maxWartoscOpalowaLabel);
    }

    public void setMaxSpiekalnoscLabel(String maxSpiekalnoscLabel) {
        this.maxSpiekalnoscLabel.setText(maxSpiekalnoscLabel);
    }

    public void setMaxWymiarZiarnaLabel(String maxWymiarZiarnaLabel) {
        this.maxWymiarZiarnaLabel.setText(maxWymiarZiarnaLabel);
    }

    public void setMaxPodziarnoLabel(String maxPodziarnoLabel) {
        this.maxPodziarnoLabel.setText(maxPodziarnoLabel);
    }

    public void setMaxNadziarnoLabel(String maxNadziarnoLabel) {
        this.maxNadziarnoLabel.setText(maxNadziarnoLabel);
    }

    public void setMaxZawartoscWilgociLabel(String maxZawartoscWilgociLabel) {
        this.maxZawartoscWilgociLabel.setText(maxZawartoscWilgociLabel);
    }

    public TextField getZawartoscPopioluField() {
        return zawartoscPopioluField;
    }

    public TextField getZawartoscSiarkiField() {
        return zawartoscSiarkiField;
    }

    public TextField getZawartoscCzesciLotnychField() {
        return zawartoscCzesciLotnychField;
    }

    public TextField getWartoscOpalowaField() {
        return wartoscOpalowaField;
    }

    public TextField getSpiekalnoscField() {
        return spiekalnoscField;
    }

    public TextField getMinWymiarziarnaField() {
        return minWymiarziarnaField;
    }

    public TextField getMaxWymiarziarnaField() {
        return maxWymiarziarnaField;
    }

    public TextField getZawartoscPodziarnaField() {
        return zawartoscPodziarnaField;
    }

    public TextField getZawartoscNadziarnaField() {
        return zawartoscNadziarnaField;
    }

    public TextField getZawartoscWilgociField() {
        return zawartoscWilgociField;
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

