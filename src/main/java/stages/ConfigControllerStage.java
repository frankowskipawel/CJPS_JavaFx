package stages;

import config.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfigControllerStage extends Application {
    public static void main(String[] args) throws IOException {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        // Stage stage = new Stage();
        stage.setResizable(false);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/CertyfikatJakosciWydruk.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Podgląd wydruku");

        CertyfikatJakosciWydrukController certyfikatJakosciWydrukController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        stage.show();

//        stage.setResizable(false);
//        stage.setTitle("Ustawienia");
//        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
//                ("Config.fxml"));
//        Scene myScene = new Scene(myPane);
//        stage.setScene(myScene);
//        stage.show();

    }


}
