package pl.weglokoks.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Modality;
import pl.weglokoks.stages.ConfigDataBaseController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {

    private static final ResourceBundle bundle = FxmlUtils.getResourceBundle();

    public static Optional<ButtonType> confirmationDialog(String bundleTitle, String BundleHeader) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        try {
            confirmationDialog.setTitle(new String(bundle.getString(bundleTitle).getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            DialogsUtils.errorDialog("Błąd", e.getMessage());
            e.printStackTrace();
        }
        try {
            confirmationDialog.setHeaderText(new String(bundle.getString(BundleHeader).getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            DialogsUtils.errorDialog("Błąd", e.getMessage());
            e.printStackTrace();
        }
        confirmationDialog.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> result = confirmationDialog.showAndWait();

        return result;
    }


    public static void errorDialog(String errorTitle, String errorHeader) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        try {
            errorAlert.setTitle(new String(bundle.getString(errorTitle).getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            DialogsUtils.errorDialog("Błąd", e.getMessage());
            e.printStackTrace();
        }
        try {
            errorAlert.setHeaderText(new String(bundle.getString(errorHeader).getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            DialogsUtils.errorDialog("Błąd", e.getMessage());
            e.printStackTrace();
        }
        errorAlert.initModality(Modality.APPLICATION_MODAL);
        errorAlert.showAndWait();
    }

    public static Optional<ButtonType> configDialog(String bundleTitle, String BundleHeader) throws IOException {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(new String(bundle.getString(bundleTitle).getBytes("ISO-8859-1"), "UTF-8"));
        confirmationDialog.setHeaderText(new String(bundle.getString(BundleHeader).getBytes("ISO-8859-1"), "UTF-8"));
        confirmationDialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.<ConfigDataBaseController>setController(FxmlUtils.fxmlLoader("/pl/weglokoks/stages/ConfigDataBase.fxml"));
        confirmationDialog.<ConfigDataBaseController>getDialogPane().setContent(loader.getController());
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }

    public static void infoDialog(String title, String header, String bundleText) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        try {
            infoAlert.setTitle(new String(bundle.getString(title).getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            DialogsUtils.errorDialog("Błąd", e.getMessage());
            e.printStackTrace();
        }
        try {
            infoAlert.setHeaderText(new String(bundle.getString(header).getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            DialogsUtils.errorDialog("Błąd", e.getMessage());
            e.printStackTrace();
        }
        infoAlert.initModality(Modality.APPLICATION_MODAL);
        TextArea textArea = null;
        try {
            textArea = new TextArea(new String(bundle.getString(bundleText).getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        infoAlert.getDialogPane().setContent(textArea);
        infoAlert.showAndWait();
    }
}
