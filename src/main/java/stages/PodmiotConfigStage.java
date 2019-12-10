package stages;

import config.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PodmiotConfigStage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Config config = new Config();
        config.getConfigFromFile();
        //  Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/PodmiotConfig.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Ustawienia podmiotu");
        stage.show();
//-----------------------------------
        PodmiotConfigController podmiotConfigController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        podmiotConfigController.setNazwaTextFieldPodmiotConfig(Config.NAZWA_PODMIOTU);
        podmiotConfigController.setUlicaTextFieldPodmiotConfig(Config.ULICA_I_NUMER_DOMU_PODMIOTU);
        podmiotConfigController.setKodPocztowyTextFieldPodmiotConfig(Config.KOD_POCZTOWY_PODMIOTU);
        podmiotConfigController.setMiastoTextFieldPodmiotConfig(Config.MIASTO_PODMIOTU);
        podmiotConfigController.setNipTextFieldPodmiotConfig(Config.NIP_PODMIOTU);
        podmiotConfigController.setRegonTextFieldPodmiotConfig(Config.REGON_PODMIOTU);
    }
}
