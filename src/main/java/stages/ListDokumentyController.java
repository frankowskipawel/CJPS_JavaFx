package stages;

import dao.DokumentDao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelFXML.CertyfikatJakosci;
import modelFXML.Dokument;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ListDokumentyController {

    HomeController homeController;
    @FXML
    private Button anulujButton;
    @FXML
    private TextField szukajTextField;

    @FXML
    private ListView<Dokument> dokumentyListView;

    @FXML
    public void initialize() {
        odswiezDokumentyListView();
        odswiezDokumentyListView();
    }

    public void odswiezDokumentyListView() {
        ObservableList<Dokument> data = dokumentyListView.getItems();
        data.removeAll(data);
        DokumentDao dokumentDao = new DokumentDao();
        data.addAll(dokumentDao.getAllDokumenty());
        Collections.reverse(data);
    }

    @FXML
    public void podgladOnClick() throws IOException {
        homeController.showAndPrintDokument(dokumentyListView.getSelectionModel().getSelectedItem(), false, true);

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
        loader.setLocation(this.getClass().getResource("/stages/ListCertyfikaty.fxml"));
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Wybierz nowy certyfikat dla dokumentu");
        ListCertyfikatyController listCertyfikatyController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        listCertyfikatyController.setDokumentEdytowany(dokumentyListView.getSelectionModel().getSelectedItem());
        listCertyfikatyController.setListDokumentyController(ListDokumentyController.this);
        listCertyfikatyController.getZmienButton().setDisable(false);

        Iterator iterator = listCertyfikatyController.getLista().iterator();

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
        listCertyfikatyController.getListaCertyfikatowTableView().getSelectionModel().select(row);
        //^^^^^^^^^^

        stage.show();
    }

    @FXML
    void szukaj(KeyEvent event) {

        ObservableList<Dokument> data = dokumentyListView.getItems();
        data.removeAll(data);
        DokumentDao dokumentDao = new DokumentDao();
        List<Dokument> dataFromDB = dokumentDao.getAllDokumenty();

        dataFromDB.stream()
                .filter(item -> item.toString().matches(("(.*)" + szukajTextField.getText()) + "(.*)"))
                .forEach(data::add);

        Collections.reverse(data);
    }
}