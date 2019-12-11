package stages;

import config.Config;
import dao.KontrahentDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Kontrahent;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DokumentyStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lista Dokumentow");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("Dokumenty.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
    public static void main(String[] args) throws IOException {
        Config config = new Config();
        config.getConfigFromFile();

        launch(args);
    }
}
