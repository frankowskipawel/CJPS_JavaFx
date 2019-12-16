package stages;

import dao.DokumentDao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Dokument;

import java.io.IOException;
import java.util.Collections;


public class DokumentyStageController {

    MainController mainController;

    @FXML
    private ListView<Dokument> dokumentyListView;

    @FXML
    public void initialize() {
        refreshDokumentyListView();

    }

    private void refreshDokumentyListView() {
        ObservableList<Dokument> data = dokumentyListView.getItems();
        data.removeAll();
        DokumentDao dokumentDao = new DokumentDao();
        data.addAll(dokumentDao.getAllDokumenty());
        Collections.reverse(data);
    }

    @FXML
    public void podgladOnClick() throws IOException {
        mainController.showAndPrintDokument(dokumentyListView.getSelectionModel().getSelectedItem(), false, true);

    }
}
