package stages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelFXML.Kontrahent;

public class AddNewKontrahentController {

    ListKontrahenciController listKontrahenciController;

    @FXML
    private TextField id;
    @FXML
    private TextField nazwa;
    @FXML
    private TextField adres;
    @FXML
    private TextField nip;
    @FXML
    private TextField regon;
    @FXML
    private Button okButton;
    @FXML
    void anulujOnClick() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void okOnClick() {

        Kontrahent kontrahent = new Kontrahent(id.getText(), nazwa.getText(), adres.getText(), nip.getText(), regon.getText());
        listKontrahenciController.addNowyKontrahent(kontrahent);
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
