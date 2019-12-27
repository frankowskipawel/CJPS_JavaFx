package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import stages.ConfigController;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {

    private static final ResourceBundle bundle = FxmlUtils.getResourceBundle();

    public static Optional<ButtonType> confirmationDialog(String bundleTitle, String BundleHeader) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(bundle.getString(bundleTitle));
        confirmationDialog.setHeaderText(bundle.getString(BundleHeader));
        confirmationDialog.initModality(Modality.NONE);
        Optional<ButtonType> result = confirmationDialog.showAndWait();

        return result;
    }


    public static void errorDialog(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));

        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }

    public static Optional<ButtonType> configDialog(String bundleTitle, String BundleHeader) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(bundle.getString(bundleTitle));
        confirmationDialog.setHeaderText(bundle.getString(BundleHeader));
        confirmationDialog.initModality(Modality.NONE);
        confirmationDialog.getDialogPane().setContent(FxmlUtils.fxmlLoader("/stages/Config.fxml"));

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        return result;
    }
}
