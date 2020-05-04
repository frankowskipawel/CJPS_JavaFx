package pl.weglokoks.stages;

import pl.weglokoks.config.Config;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfigPodmiotController {

    @FXML
    private Button okButton;
    @FXML
    private TextField nazwaTextFieldPodmiotConfig;
    @FXML
    private TextField ulicaTextFieldPodmiotConfig;
    @FXML
    private TextField kodPocztowyTextFieldPodmiotConfig;
    @FXML
    private TextField miastoTextFieldPodmiotConfig;
    @FXML
    private TextField nipTextFieldPodmiotConfig;
    @FXML
    private TextField regonTextFieldPodmiotConfig;
    @FXML
    private Button anulujButton;


    @FXML
    void cancelOnClick() {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void okOnClick() throws IOException {
        Config config = new Config();
        config.setDanePodmiotu(nazwaTextFieldPodmiotConfig.getText(), ulicaTextFieldPodmiotConfig.getText(), kodPocztowyTextFieldPodmiotConfig.getText(),
                miastoTextFieldPodmiotConfig.getText(), nipTextFieldPodmiotConfig.getText(), regonTextFieldPodmiotConfig.getText());

        config.getConfigFromFile();
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();

    }

    public void setNazwaTextFieldPodmiotConfig(String nazwaTextFieldPodmiotConfig) {
        this.nazwaTextFieldPodmiotConfig.setText(nazwaTextFieldPodmiotConfig);
    }

    public void setUlicaTextFieldPodmiotConfig(String ulicaTextFieldPodmiotConfig) {
        this.ulicaTextFieldPodmiotConfig.setText(ulicaTextFieldPodmiotConfig);
    }

    public void setKodPocztowyTextFieldPodmiotConfig(String kodPocztowyTextFieldPodmiotConfig) {
        this.kodPocztowyTextFieldPodmiotConfig.setText(kodPocztowyTextFieldPodmiotConfig);
    }

    public void setMiastoTextFieldPodmiotConfig(String miastoTextFieldPodmiotConfig) {
        this.miastoTextFieldPodmiotConfig.setText(miastoTextFieldPodmiotConfig);
    }

    public void setNipTextFieldPodmiotConfig(String nipTextFieldPodmiotConfig) {
        this.nipTextFieldPodmiotConfig.setText(nipTextFieldPodmiotConfig);
    }

    public void setRegonTextFieldPodmiotConfig(String regonTextFieldPodmiotConfig) {
        this.regonTextFieldPodmiotConfig.setText(regonTextFieldPodmiotConfig);
    }
}
