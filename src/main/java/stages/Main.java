package stages;

import config.Config;
import dao.DaoService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.FxmlUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        StartMain.startMain(primaryStage);
//        Config config = new Config();
//        config.getConfigFromFile();
//        DaoService daoService = new DaoService();
//        daoService.init();
//
//        VBox vBox = (VBox) FxmlUtils.fxmlLoader("/stages/StronaGlowna.fxml");
//        Scene scene = new Scene(vBox);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
//        primaryStage.show();
//        FXMLLoader loader = FxmlUtils.getLoader("/stages/StronaGlowna.fxml");
//        MainController mainController = loader.getController();


//        Locale.setDefault(new Locale("pl"));
//        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(this.getClass().getResource("/stages/StronaGlowna.fxml"));
//        VBox vBox = loader.load();
//        loader.setResources(bundle);
//        Scene scene = new Scene(vBox);
//        stage.setScene(scene);
//        stage.setTitle(bundle.getString("title.application"));
//        stage.show();


    }
}
