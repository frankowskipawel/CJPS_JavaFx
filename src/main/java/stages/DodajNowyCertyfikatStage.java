package stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DodajNowyCertyfikatStage extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Dodaj nowy certyfikat jakości");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("DodajNowyCertyfikat.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();

    }
}
