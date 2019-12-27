package stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Temp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/OknoDialogowe.fxml"));
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Komunikat");

        OknoDialogoweController oknoDialogoweController = loader.getController();
        oknoDialogoweController.setMessage("Komunikat pierwszy");
        stage.show();
        while (!oknoDialogoweController.okClick()){}
        System.out.println("koniec komunikatu");
    }
}
