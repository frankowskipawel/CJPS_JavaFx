import config.Config;
import dao.DaoService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stages.HomeController;
import utils.FxmlUtils;

import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Config config = new Config();
        config.getConfigFromFile();
        DaoService daoService = new DaoService();
        daoService.init();

        VBox vBox = (VBox) FxmlUtils.fxmlLoader("/stages/Home.fxml");
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.show();
        FXMLLoader loader = FxmlUtils.getLoader("/stages/Home.fxml");
        HomeController homeController = loader.getController();

    }
}
