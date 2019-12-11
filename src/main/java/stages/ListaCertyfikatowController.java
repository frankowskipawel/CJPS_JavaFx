package stages;

import dao.CertyfikatJakosciDao;
import javafx.fxml.FXML;
import model.CertyfikatJakosci;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ListaCertyfikatowController {

    @FXML
    private TableView<CertyfikatJakosci> listaCertyfikatowTableView;
    @FXML
    private Button dodajNowyButton;

    @FXML
    public void initialize() {

        addAllCertyfikatyListFromDatabaseToTableView();


    }

    @FXML
    protected void addAllCertyfikatyListFromDatabaseToTableView() {

        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        List<CertyfikatJakosci> list = certyfikatJakosciDao.getAllCertyfikatJakosci();



        Iterator<CertyfikatJakosci> iterator = list.iterator();

        for (CertyfikatJakosci b : list) {

            ObservableList<CertyfikatJakosci> data = listaCertyfikatowTableView.getItems();

            data.add(new CertyfikatJakosci(b.numerCertyfikatuProperty().getValue(),

                    b.aktywnyProperty().getValue(),
                    b.naszaNazwaProperty().getValue(),
                    b.asortymentProperty().getValue(),
                    b.dataProperty().getValue(),
                    b.numerCertyfikatuLaboratoriumProperty().getValue(),
                    b.zawartoscPopioluProperty().getValue(),
                    b.zawartoscSiarkiCalkowitejProperty().getValue(),
                    b.zawartoscCzesciLotnychProperty().getValue(),
                    b.wartoscOpalowaProperty().getValue(),
                    b.zdolnoscSpiekaniaProperty().getValue(),
                    b.minimalnyWymiarZiarnaProperty().getValue(),
                    b.maksymalnyWymiarZiarnaProperty().getValue(),
                    b.zawartoscPodziarnaProperty().getValue(),
                    b.zawartoscNadziarnaProperty().getValue(),
                    b.zawartoscWilgociCalkowitejProperty().getValue(),
                    b.dostawcaProperty().getValue(),
                    b.nrFVProperty().getValue())

            );

        }
    }

    @FXML
    protected void dodajNowyCertyfikatClick() throws IOException {

        Stage stage = new Stage();
        stage.setTitle("Dodaj nowy certyfikat jakości");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("DodajNowyCertyfikat.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();

    }

    @FXML
    protected void odswiezClick()  {

       odswiezListeCertyfikatow();

    }

    public void odswiezListeCertyfikatow(){
        ObservableList<CertyfikatJakosci> data = listaCertyfikatowTableView.getItems();
        data.removeAll(data);
        addAllCertyfikatyListFromDatabaseToTableView();
    }

}
