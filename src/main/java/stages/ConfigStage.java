package stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfigStage extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Ustawienia");
       // stage.setResizable(false); //Brak możliwości zmiany rozmiaru
       // stage.isFullScreen(); //pełny ekran
       // stage.setX(0); // współrzędne uruchomienia
       // stage.setY(0);
        //stage.initStyle(StageStyle.UNDECORATED); //np. bez ramki
      //  stage.setOpacity(0.5);
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource("Config.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();

    }
}
