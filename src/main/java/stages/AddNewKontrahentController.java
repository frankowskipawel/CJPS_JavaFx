package stages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelFX.Kontrahent;
import utils.DialogsUtils;

import java.sql.SQLException;

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
        if (!id.isDisable()) {
            Kontrahent kontrahent = new Kontrahent(id.getText(), nazwa.getText(), adres.getText(), nip.getText(), regon.getText());

               boolean isAddOk = listKontrahenciController.addNowyKontrahent(kontrahent);

            if (isAddOk){
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();}

        } else {

            Kontrahent kontrahent = new Kontrahent(id.getText(), nazwa.getText(), adres.getText(), nip.getText(), regon.getText());
            listKontrahenciController.updateKontrahent(kontrahent);
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();


        }

    }


    public ListKontrahenciController getListKontrahenciController() {
        return listKontrahenciController;
    }

    public void setListKontrahenciController(ListKontrahenciController listKontrahenciController) {
        this.listKontrahenciController = listKontrahenciController;
    }

    public TextField getId() {
        return id;
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public TextField getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.setText(nazwa);
    }

    public TextField getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres.setText(adres);
    }

    public TextField getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip.setText(nip);
    }

    public TextField getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon.setText(regon);
    }

    public Button getOkButton() {
        return okButton;
    }

    public void setOkButton(Button okButton) {
        this.okButton = okButton;
    }
}
