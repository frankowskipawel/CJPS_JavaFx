package stages;

import dao.DokumentDao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Dokument;


public class DokumentyStageController {

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
    }

}
