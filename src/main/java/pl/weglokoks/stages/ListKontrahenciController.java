package pl.weglokoks.stages;

import pl.weglokoks.dao.KontrahentDao;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.weglokoks.modelFX.Kontrahent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.weglokoks.utils.DialogsUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ListKontrahenciController {

    private AddNewCertyfikatController addNewCertyfikatController;

    @FXML
    private Button anulujButton;
    @FXML
    private TableView<Kontrahent> kontrahenciTableView;
    @FXML
    private Button okButton;
    @FXML
    private TextField szukajTextField;


    @FXML
    public void initialize() {
        setAllKontrahentTableView();
    }

    @FXML
    protected void usunKontrahent() {
        Optional<ButtonType> result = DialogsUtils.confirmationDialog("delete.title", "delete.header");
        if (result.get() == ButtonType.OK) {
            KontrahentDao kontrahentDao = new KontrahentDao();
            kontrahentDao.deleteKontrahentById(kontrahenciTableView.getSelectionModel().getSelectedItem().getIdKontrahent());
            ObservableList<Kontrahent> data = kontrahenciTableView.getItems();
            data.remove(kontrahenciTableView.getSelectionModel().getSelectedItem());
        }
    }

    public boolean addNowyKontrahent(Kontrahent kontrahent) {
        ObservableList<Kontrahent> data = kontrahenciTableView.getItems();

        KontrahentDao kontrahentDao = new KontrahentDao();
        boolean isAddOk = true;
        try {
            kontrahentDao.insertKontrahent(kontrahent);
        } catch (SQLException e) {

            DialogsUtils.errorDialog("errorUniqueId.title", "errorUniqueId.header");
            isAddOk = false;
            return false;
        }
        if (isAddOk) {
            data.add(new Kontrahent(kontrahent.getIdKontrahent(),
                    kontrahent.getNazwaKontrahent(),
                    kontrahent.getAdresKontrahent(),
                    kontrahent.getNipKontrahent(),
                    kontrahent.getRegonKontrahent()
            ));
            return true;
        }
        return isAddOk;
    }

    public void updateKontrahent(Kontrahent kontrahent) {
        KontrahentDao kontrahentDao = new KontrahentDao();
        kontrahentDao.updateKontrahent(kontrahent);
        ObservableList<Kontrahent> data = kontrahenciTableView.getItems();
        data.removeAll();
        setAllKontrahentTableView();
    }

    @FXML
    protected void dodajNowyOnClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/pl/weglokoks/stages/AddNewKontrahent.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Nowy kontrahent");
        stage.initModality(Modality.APPLICATION_MODAL);
        AddNewKontrahentController addNewKontrahentController = loader.getController();
        addNewKontrahentController.listKontrahenciController = ListKontrahenciController.this;
        stage.show();
    }

    @FXML
    protected void setAllKontrahentTableView() {
        KontrahentDao kontrahentDao = new KontrahentDao();
        List<Kontrahent> list = kontrahentDao.findAllKontrahent();
        ObservableList<Kontrahent> data = kontrahenciTableView.getItems();
        data.removeAll(data);
        for (Kontrahent b : list) {
            data.add(new Kontrahent(b.idKontrahentProperty().getValue(),
                    b.nazwaKontrahentProperty().getValue(),
                    b.adresKontrahentProperty().getValue(),
                    b.nipKontrahentProperty().getValue(),
                    b.regonKontrahentProperty().getValue()));
        }
    }

    @FXML
    void okOnClick(ActionEvent event) {
        if (addNewCertyfikatController != null) {
            addNewCertyfikatController.setDostawcaField(getKontrahenciTableView().getSelectionModel().getSelectedItem().getIdKontrahent());
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        } else {
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void anulujOnClick(ActionEvent event) {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void szukaj(KeyEvent event) {
        ObservableList<Kontrahent> data = kontrahenciTableView.getItems();
        data.removeAll(data);
        KontrahentDao kontrachentDao = new KontrahentDao();
        List<Kontrahent> dataFromDB = kontrachentDao.findAllKontrahent();
        dataFromDB.stream()
                .filter(item -> item.toString().matches(("(.*)" + szukajTextField.getText()) + "(.*)"))
                .forEach(data::add);

        Collections.reverse(data);
    }

    public void setAddNewCertyfikatController(AddNewCertyfikatController addNewCertyfikatController) {
        this.addNewCertyfikatController = addNewCertyfikatController;
    }

    public TableView<Kontrahent> getKontrahenciTableView() {
        return kontrahenciTableView;
    }

    public void edytujOnClick() throws IOException {
        if (!kontrahenciTableView.getSelectionModel().isEmpty()) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/pl/weglokoks/stages/AddNewKontrahent.fxml"));
            Pane pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.setTitle("Edycja kontrahenta");
            stage.initModality(Modality.APPLICATION_MODAL);
            AddNewKontrahentController addNewKontrahentController = loader.getController();
            addNewKontrahentController.listKontrahenciController = ListKontrahenciController.this;
            Kontrahent selected = kontrahenciTableView.getSelectionModel().getSelectedItem();

            addNewKontrahentController.setId(selected.getIdKontrahent());
            addNewKontrahentController.getId().setDisable(true);
            addNewKontrahentController.setNazwa(selected.getNazwaKontrahent());
            addNewKontrahentController.setAdres(selected.getAdresKontrahent());
            addNewKontrahentController.setNip(selected.getNipKontrahent());
            addNewKontrahentController.setRegon(selected.getRegonKontrahent());
            stage.show();
        }
    }

    public void dodajNowyContextMenu() throws IOException {
        dodajNowyOnClick();
    }

    public void edycjaContextMenu() throws IOException {
        edytujOnClick();
    }

    public void usunContextMenu() {
        usunKontrahent();
    }
}