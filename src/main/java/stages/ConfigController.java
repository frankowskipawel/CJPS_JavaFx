package stages;

import config.Config;
import config.Default;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    }

    @FXML
    void domyslneClick(ActionEvent event) {
        hostTextFieldConfig.setText(Default.CONNECTION.getHost());
        portTextFieldConfig.setText(Default.CONNECTION.getPort());
        databaseNameTextFieldConfig.setText(Default.CONNECTION.getDatabaseName());
        loginTextFieldConfig.setText(Default.CONNECTION.getUser());
        passwordTextfieldConfig.setText(Default.CONNECTION.getPassword());

    }


}
