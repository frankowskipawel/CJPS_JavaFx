package stages;

import dao.DokumentDao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CertyfikatJakosci;
import model.Dokument;


import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class DokumentyStageController {

    MainController mainController;
    @FXML
    private Button anulujButton;
    @FXML
    private TextField szukajTextField;

    @FXML
    private ListView<Dokument> dokumentyListView;

    @FXML
    public void initialize() {
        refreshDokumentyListView();
        refreshDokumentyListView();

    }

    public void refreshDokumentyListView() {
        ObservableList<Dokument> data = dokumentyListView.getItems();
        data.removeAll(data);
        DokumentDao dokumentDao = new DokumentDao();
        data.addAll(dokumentDao.getAllDokumenty());
        Collections.reverse(data);
    }

    @FXML
    public void podgladOnClick() throws IOException {
        mainController.showAndPrintDokument(dokumentyListView.getSelectionModel().getSelectedItem(), false, true);

    }

    @FXML
    void anulujOnClick() {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void edytujOnClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/ListaCertyfikatowTableView.fxml"));
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Wybierz nowy certyfikat dla dokumentu");
        ListaCertyfikatowController listaCertyfikatowController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        listaCertyfikatowController.setDokumentEdytowany(dokumentyListView.getSelectionModel().getSelectedItem());
        listaCertyfikatowController.setDokumentyStageController(DokumentyStageController.this);
        listaCertyfikatowController.getZmienButton().setDisable(false);

        Iterator iterator = listaCertyfikatowController.getLista().iterator();

        //Zazanaczenie na liście certyfikatów - certyfikatu z dokumentu
        int row = 0;
        int counter = 0;
        while (iterator.hasNext()) {
            CertyfikatJakosci element = (CertyfikatJakosci) iterator.next();
            if (dokumentyListView.getSelectionModel().getSelectedItem().getCertyfikatJakosci().getNumerCertyfikatu().equals(element.getNumerCertyfikatu())) {
                row = counter;
            }
            counter++;
        }
        listaCertyfikatowController.getListaCertyfikatowTableView().getSelectionModel().select(row);
        //^^^^^^^^^^

        stage.show();
    }

    @FXML
    void szukaj(KeyEvent event) {

        ObservableList<Dokument> data = dokumentyListView.getItems();
        data.removeAll(data);
        DokumentDao dokumentDao = new DokumentDao();
        List<Dokument> dataFromDB = dokumentDao.getAllDokumenty();

        for (Dokument item : dataFromDB) {
            if (item.toString().matches(("(.*)" + szukajTextField.getText()) + "(.*)")) {
                data.add(item);

            }
        }
        Collections.reverse(data);
    }
}