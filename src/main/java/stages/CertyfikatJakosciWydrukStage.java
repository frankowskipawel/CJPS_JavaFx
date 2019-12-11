package stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CertyfikatJakosciWydrukStage extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/CertyfikatJakosciWydruk.fxml"));

        Pane pane = loader.load();
       // VBox vBox = loader.load();

        Scene scene = new Scene(pane);
        stage.setScene(scene);

        stage.setTitle("Podgląd wydruku");
        stage.show();
        CertyfikatJakosciWydrukController controller = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
//        ConfigController configController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
//        configController.setMessageLabel("fsxafs");
     //   controller.setTextLabel("3000");



//        stage.setTitle("Dodaj nowy certyfikat jakości");
//        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
//                ("CertyfikatJakosciWydruk.fxml"));
//        Scene myScene = new Scene(myPane);
//        stage.setScene(myScene);
//        stage.show();
//
//        ConfigController configController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
//        configController.setMessageLabel("fsxafs");

//        System.out.println(WartościDopuszczalnePaliwa.WEGIEL_KAMIENNY_KOSTKA.getMinPopiol());
//        WartościDopuszczalnePaliwa.WEGIEL_KAMIENNY_KOSTKA.setMinPopiol("20");
//        System.out.println(WartościDopuszczalnePaliwa.WEGIEL_KAMIENNY_KOSTKA.getMinPopiol());


    }
}