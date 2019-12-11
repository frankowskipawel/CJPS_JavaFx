package stages;


import config.Config;
import dao.KontrahentDao;
import model.Kontrahent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class KontrahentStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Lista Kontrahentów");
        Pane myPane = (Pane)FXMLLoader.load(getClass().getResource
                ("KontrahentTableView.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();

    }

    public static void main(String[] args) throws IOException {
        Config config = new Config();
        config.getConfigFromFile();
        KontrahentDao kontrahentDao = new KontrahentDao();
        List<Kontrahent> list = kontrahentDao.getAllKontrahent();
        Iterator<Kontrahent> iterator=list.iterator();
        launch(args);
    }
}
