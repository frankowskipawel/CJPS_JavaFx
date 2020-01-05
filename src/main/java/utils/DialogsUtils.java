package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Modality;
import stages.ConfigDataBaseController;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {

    private static final ResourceBundle bundle = FxmlUtils.getResourceBundle();

    public static Optional<ButtonType> confirmationDialog(String bundleTitle, String BundleHeader) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(bundle.getString(bundleTitle));
        confirmationDialog.setHeaderText(bundle.getString(BundleHeader));
        confirmationDialog.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> result = confirmationDialog.showAndWait();

        return result;
    }


    public static void errorDialog(String errorTitle, String errorHeader) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString(errorTitle));
        errorAlert.setHeaderText(bundle.getString(errorHeader));
        errorAlert.initModality(Modality.APPLICATION_MODAL);
        //TextArea textArea = new TextArea(error);
      //  errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }

    public static Optional<ButtonType> configDialog(String bundleTitle, String BundleHeader) throws IOException {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(bundle.getString(bundleTitle));
        confirmationDialog.setHeaderText(bundle.getString(BundleHeader));
       // confirmationDialog.initModality(Modality.NONE);
        confirmationDialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.<ConfigDataBaseController>setController(FxmlUtils.fxmlLoader("/stages/ConfigDataBase.fxml"));
//        ConfigController configController = (ConfigController)loader.getController();

        confirmationDialog.<ConfigDataBaseController>getDialogPane().setContent(loader.getController());
        Optional<ButtonType> result = confirmationDialog.showAndWait();

        return result;
    }

    public static void infoDialog(String title, String header, String bundleText) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle(bundle.getString(title));
        infoAlert.setHeaderText(bundle.getString(header));
        infoAlert.initModality(Modality.APPLICATION_MODAL);
        TextArea textArea = new TextArea(bundle.getString(bundleText));
        infoAlert.getDialogPane().setContent(textArea);
        infoAlert.showAndWait();
    }
}
