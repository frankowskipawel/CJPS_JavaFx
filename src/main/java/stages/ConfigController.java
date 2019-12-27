package stages;

import config.Config;
import config.DefaultConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class ConfigController {

    @FXML
    private TextField hostTextFieldConfig;

    @FXML
    private TextField portTextFieldConfig;

    @FXML
    private TextField databaseNameTextFieldConfig;

    @FXML
    private TextField loginTextFieldConfig;

    @FXML
    private TextField passwordTextfieldConfig;

    @FXML
    private Button anulujButton;

    @FXML
    private Label messageLabel;


    public ConfigController() {
    }

    //Najpierw wywoływany jest konstruktor dopiero metoda initialize i to w niej mozna dopiero ustawiać kontrolki
    @FXML
    public void initialize() {
        //Chwilowo wczytanie ustawień z pliku
        Config config = new Config();
        try {
            config.getConfigFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        hostTextFieldConfig.setText(Config.HOSTNAME);
        portTextFieldConfig.setText(Config.PORT);
        databaseNameTextFieldConfig.setText(Config.DATABASENAME);
        loginTextFieldConfig.setText(Config.USER);
        passwordTextfieldConfig.setText(Config.PASSWORD);

    }

    @FXML
    void okClick(ActionEvent event) throws IOException {
        Config config = new Config();
        config.setConfig(hostTextFieldConfig.getText(), portTextFieldConfig.getText(), databaseNameTextFieldConfig.getText(), loginTextFieldConfig.getText(), passwordTextfieldConfig.getText());
        config.getConfigFromFile();
        messageLabel.setText("Zapisano zmiany!");
//        closeStage();
    }

    @FXML
    void domyslneClick(ActionEvent event) {
        hostTextFieldConfig.setText(DefaultConnection.CONNECTION.getHost());
        portTextFieldConfig.setText(DefaultConnection.CONNECTION.getPort());
        databaseNameTextFieldConfig.setText(DefaultConnection.CONNECTION.getDatabaseName());
        loginTextFieldConfig.setText(DefaultConnection.CONNECTION.getUser());
        passwordTextfieldConfig.setText(DefaultConnection.CONNECTION.getPassword());

    }

    @FXML
    void anulujClick(ActionEvent event) {
        closeStage();
    }

    void closeStage() {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        stage.close();
    }

    public void setMessageLabel(String text){
        messageLabel.setText(text);
    }

    public TextField getHostTextFieldConfig() {
        return hostTextFieldConfig;
    }

    public void setHostTextFieldConfig(TextField hostTextFieldConfig) {
        this.hostTextFieldConfig = hostTextFieldConfig;
    }

    public TextField getPortTextFieldConfig() {
        return portTextFieldConfig;
    }

    public void setPortTextFieldConfig(TextField portTextFieldConfig) {
        this.portTextFieldConfig = portTextFieldConfig;
    }

    public TextField getDatabaseNameTextFieldConfig() {
        return databaseNameTextFieldConfig;
    }

    public void setDatabaseNameTextFieldConfig(TextField databaseNameTextFieldConfig) {
        this.databaseNameTextFieldConfig = databaseNameTextFieldConfig;
    }

    public TextField getLoginTextFieldConfig() {
        return loginTextFieldConfig;
    }

    public void setLoginTextFieldConfig(TextField loginTextFieldConfig) {
        this.loginTextFieldConfig = loginTextFieldConfig;
    }

    public TextField getPasswordTextfieldConfig() {
        return passwordTextfieldConfig;
    }

    public void setPasswordTextfieldConfig(TextField passwordTextfieldConfig) {
        this.passwordTextfieldConfig = passwordTextfieldConfig;
    }

    public Button getAnulujButton() {
        return anulujButton;
    }

    public void setAnulujButton(Button anulujButton) {
        this.anulujButton = anulujButton;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(Label messageLabel) {
        this.messageLabel = messageLabel;
    }
}
