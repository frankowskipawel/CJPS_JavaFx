package stages;

import dao.KontrahentDao;
import model.Kontrahent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Iterator;
import java.util.List;

public class KontrahentController {
    @FXML
    private TableView<Kontrahent> KontrahenciTableView;
    @FXML private TextField idKontrahentField;
    @FXML private TextField nazwaKontrahentField;
    @FXML private TextField adresKontrahentField;
    @FXML private TextField nipKontrahentField;
    @FXML private TextField regonKontrahentField;


    @FXML
    public void initialize() {

       addAllKontrahentListFromDatabaseToTableView();
     //  addNewKontrahentToTableViewTest();

    }
    @FXML //Pobierz dane zaznaczone w TableView
    protected void deleteRowFromTableViewAndDatabase(ActionEvent event){

        KontrahentDao kontrahentDao = new KontrahentDao();
        kontrahentDao.deleteKontrahentDatabase(KontrahenciTableView.getSelectionModel().getSelectedItem().getIdKontrahent());

        System.out.println(KontrahenciTableView.getSelectionModel().getSelectedItem());
        System.out.println("xxxx"+ KontrahenciTableView.getSelectionModel().getSelectedItem().getIdKontrahent());

        ObservableList<Kontrahent> data = KontrahenciTableView.getItems();
       data.remove(KontrahenciTableView.getSelectionModel().getSelectedItem());


    }


    @FXML
    protected void addNewKontrahentToTableViewAndDatabase(ActionEvent event) {
        ObservableList<Kontrahent> data = KontrahenciTableView.getItems();
        data.add(new Kontrahent(idKontrahentField.getText(),
                nazwaKontrahentField.getText(),
                adresKontrahentField.getText(),
                nipKontrahentField.getText(),
                regonKontrahentField.getText()

        ));
        KontrahentDao kontrahentDao = new KontrahentDao();
        kontrahentDao.addKontrahentDatabase(idKontrahentField.getText(), nazwaKontrahentField.getText(), adresKontrahentField.getText(), nipKontrahentField.getText(), regonKontrahentField.getText() );

        idKontrahentField.setText("");
        nazwaKontrahentField.setText("");
        adresKontrahentField.setText("");
        nipKontrahentField.setText("");
        regonKontrahentField.setText("");
    }

    @FXML
    protected void addNewKontrahentToTableViewTest() {
        ObservableList<Kontrahent> data = KontrahenciTableView.getItems();
        data.add(new Kontrahent("idKontrahentField.getText()",
                "nazwaKontrahentField.getText()",
                "adresKontrahentField.getText()",
                "nipKontrahentField.getText()",
                "regonKontrahentField.getText()" ));

        //data.removeAll(data); //usuwa wszystko z table View
       // data.remove(2); //usuwa pozycje z numerem a


    }


    @FXML
    protected void addAllKontrahentListFromDatabaseToTableView() {

        KontrahentDao kontrahentDao = new KontrahentDao();
        List<Kontrahent> list = kontrahentDao.getAllKontrahent();

        Iterator<Kontrahent> iterator=list.iterator();

        for(Kontrahent b:list) {

            ObservableList<Kontrahent> data = KontrahenciTableView.getItems();
            data.add(new Kontrahent(b.idKontrahentProperty().getValue(),
                    b.nazwaKontrahentProperty().getValue(),
                    b.adresKontrahentProperty().getValue(),
                    b.nipKontrahentProperty().getValue(),
                    b.regonKontrahentProperty().getValue()));

        }
    }
}