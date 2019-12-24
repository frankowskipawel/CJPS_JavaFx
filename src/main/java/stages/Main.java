package stages;

import config.Config;
import dao.DaoService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        Config config = new Config();
        config.getConfigFromFile();
        DaoService daoService = new DaoService();
        daoService.init();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lista Certyfikatów Jakości");
        VBox vbox = (VBox) FXMLLoader.load(getClass().getResource
                ("StronaGlowna.fxml"));
        primaryStage.setX(-7);
        primaryStage.setY(307);
        Scene myScene = new Scene(vbox);
        primaryStage.setScene(myScene);
        primaryStage.show();

    }
}
