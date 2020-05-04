package pl.weglokoks.stages;

import pl.weglokoks.config.Config;
import pl.weglokoks.config.DefaultConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class ConfigDataBaseController {

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

    @FXML
    public void initialize() {
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
    void okOnClick(ActionEvent event) throws IOException {
        Config config = new Config();
        config.setConfig(hostTextFieldConfig.getText(), portTextFieldConfig.getText(), databaseNameTextFieldConfig.getText(), loginTextFieldConfig.getText(), passwordTextfieldConfig.getText());
        config.getConfigFromFile();
        messageLabel.setText("Zapisano zmiany!");
    }

    @FXML
    void defaultClick(ActionEvent event) {
        hostTextFieldConfig.setText(DefaultConnection.CONNECTION.getHost());
        portTextFieldConfig.setText(DefaultConnection.CONNECTION.getPort());
        databaseNameTextFieldConfig.setText(DefaultConnection.CONNECTION.getDatabaseName());
        loginTextFieldConfig.setText(DefaultConnection.CONNECTION.getUser());
        passwordTextfieldConfig.setText(DefaultConnection.CONNECTION.getPassword());
    }

    @FXML
    void cancelOnClick(ActionEvent event) {
        closeStage();
    }

    void closeStage() {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        stage.close();
    }
}
