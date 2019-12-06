package stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Testowy extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/Config.fxml"));


      VBox vBox = loader.load();

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Ustawienia");
        primaryStage.show();

        ConfigController configController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        configController.setMessageLabel("fsxafs");




    }
}
