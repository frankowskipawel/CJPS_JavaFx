package pl.weglokoks;

import pl.weglokoks.config.Config;
import pl.weglokoks.dao.DaoService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.weglokoks.utils.FxmlUtils;

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

        VBox vBox = (VBox) FxmlUtils.fxmlLoader("/pl/weglokoks/stages/Home.fxml");
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle(new String(FxmlUtils.getResourceBundle().getString("title.application").getBytes("ISO-8859-1"), "UTF-8"));
        primaryStage.show();
//        FXMLLoader loader = FxmlUtils.getLoader("/pl/weglokoks/stages/Home.fxml");
//        HomeController homeController = loader.getController();

    }
}
