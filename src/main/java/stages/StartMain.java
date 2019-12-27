package stages;

import config.Config;
import dao.DaoService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.FxmlUtils;

import java.io.IOException;

public class StartMain {

    public static void startMain(Stage stage) throws Exception {
        //Stage stage = new Stage();
        Config config = new Config();
        config.getConfigFromFile();
        DaoService daoService = new DaoService();
        daoService.init();

        VBox vBox = (VBox) FxmlUtils.fxmlLoader("/stages/StronaGlowna.fxml");
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        stage.show();
        FXMLLoader loader = FxmlUtils.getLoader("/stages/StronaGlowna.fxml");
        MainController mainController = loader.getController();
    }
}
