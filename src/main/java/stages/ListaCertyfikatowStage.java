package stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ListaCertyfikatowStage extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lista Certyfikatów Jakości");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("ListaCertyfikatowTableView.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();

    }
}
