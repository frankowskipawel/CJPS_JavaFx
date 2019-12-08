package stages;

import dao.CertyfikatJakosciDao;
import dao.DokumentDao;
import javafx.event.ActionEvent;
import model.CertyfikatJakosci;
import model.Dokument;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class StronaGlownaController {

    @FXML
    private TableView<CertyfikatJakosci> listaAktywnychCertyfikatowTableViewStronaGlowna;
    @FXML
    private ListView<Dokument> listaDokumentowListViewStronaGlowna;
    @FXML
    private VBox vBoxStronaGlowna;



    @FXML
    public void initialize() {

        dodajAktywneCertyfikatyDoTableView();
        dodajOstatniePiecDokumentowDoListView();

    }

    @FXML
    protected void dodajAktywneCertyfikatyDoTableView() {

        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        List<CertyfikatJakosci> list = certyfikatJakosciDao.getAllCertyfikatJakosciTylkoAktywne();


        Iterator<CertyfikatJakosci> iterator = list.iterator();
        for (CertyfikatJakosci b : list) {
            ObservableList<CertyfikatJakosci> data = listaAktywnychCertyfikatowTableViewStronaGlowna.getItems();
            data.add(new CertyfikatJakosci(b.numerCertyfikatuProperty().getValue(), b.naszaNazwaProperty().getValue()
            ));

        }

    }

    @FXML
    protected void odswiezClick() throws IOException {

        odswiezListeAktywnychCertyfikatow();
        odswiezListedokumentow();


    }

    @FXML
    protected void dodajWydrukujClick() throws IOException {


        dodajDokumentDoListView();

        odswiezListeAktywnychCertyfikatow();

        odswiezListedokumentow();
        pokazDokument();

        //print(vBoxStronaGlowna); //do zmiany

    }

    protected void pokazDokument() throws IOException {



        Stage stage = new Stage();
        stage.setTitle("Dodaj nowy certyfikat jakości");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("CertyfikatJakosciWydruk.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();

    }

    public void odswiezListeAktywnychCertyfikatow() {
        ObservableList<CertyfikatJakosci> data = listaAktywnychCertyfikatowTableViewStronaGlowna.getItems();
        data.removeAll(data);
        dodajAktywneCertyfikatyDoTableView();
    }

    public void odswiezListedokumentow() {
        ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();
        data.removeAll(data);
        dodajOstatniePiecDokumentowDoListView();
    }


    @FXML
    protected void dodajDokumentDoListView() {

        ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();

        System.out.println("zaznaczenia nr "+listaAktywnychCertyfikatowTableViewStronaGlowna.getSelectionModel().getSelectedItem());

        CertyfikatJakosci certyfikatJakoscipoId = new CertyfikatJakosciDao().znajdzCertyfikatPoId(listaAktywnychCertyfikatowTableViewStronaGlowna.getSelectionModel().getSelectedItem().getNumerCertyfikatuAktywne());

        DokumentDao dokumentDao = new DokumentDao();
        int numer = dokumentDao.getNajwyzszyNumerDokumentuDao() + 1;

      //  System.out.println("Najwyzszy numer to: "+numer);
        ZonedDateTime dataDzisiejsza = ZonedDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataDzisiejszaString = dataDzisiejsza.format(f);


        Dokument dokumentDodawany = new Dokument(Integer.toString(numer)+"/"+dataDzisiejszaString.substring(0, 4), dataDzisiejszaString, certyfikatJakoscipoId.getNumerCertyfikatu(), certyfikatJakoscipoId.getAktywny(), certyfikatJakoscipoId.getNaszaNazwa(), certyfikatJakoscipoId.getAsortyment(), certyfikatJakoscipoId.getData(), certyfikatJakoscipoId.getNumerCertyfikatuLaboratorium(), certyfikatJakoscipoId.getZawartoscPopiolu(), certyfikatJakoscipoId.getZawartoscSiarkiCalkowitej(), certyfikatJakoscipoId.getZawartoscCzesciLotnych(), certyfikatJakoscipoId.getWartoscOpalowa(), certyfikatJakoscipoId.getZdolnoscSpiekania(), certyfikatJakoscipoId.getMinimalnyWymiarZiarna(), certyfikatJakoscipoId.getMaksymalnyWymiarZiarna(), certyfikatJakoscipoId.getZawartoscPodziarna(), certyfikatJakoscipoId.getZawartoscNadziarna(), certyfikatJakoscipoId.getZawartoscWilgociCalkowitej(), certyfikatJakoscipoId.getDostawca(), certyfikatJakoscipoId.getNrFV());
        System.out.println("dok dodawany : "+dokumentDodawany);
        data.add(dokumentDodawany);


//        dokumentDodawany.numerDokumentuStatic = dokumentDodawany.getNumerDokumentu();
//        dokumentDodawany.dataDokumentuStatic = dokumentDodawany.getDataDokumentu();
//        dokumentDodawany.asortymentStatic= dokumentDodawany.getAsortyment();
//        dokumentDodawany.dataStatic= dokumentDodawany.getData();
//        dokumentDodawany.numerCertyfikatuLaboratoriumStatic= dokumentDodawany.getNumerCertyfikatuLaboratorium();
//        dokumentDodawany.zawartoscPopioluStatic= dokumentDodawany.getZawartoscPopiolu();
//        dokumentDodawany.zawartoscSiarkiCalkowitejStatic= dokumentDodawany.getZawartoscSiarkiCalkowitej();
//        dokumentDodawany.zawartoscCzesciLotnychStatic= dokumentDodawany.getZawartoscCzesciLotnych();
//        dokumentDodawany.wartoscOpalowaStatic= dokumentDodawany.getWartoscOpalowa();
//        dokumentDodawany.zdolnoscSpiekaniaStatic= dokumentDodawany.getZdolnoscSpiekania();
//        dokumentDodawany.minimalnyWymiarZiarnaStatic= dokumentDodawany.getMinimalnyWymiarZiarna();
//        dokumentDodawany.maksymalnyWymiarZiarnaStatic= dokumentDodawany.getMaksymalnyWymiarZiarna();
//
//        dokumentDodawany.zawartoscPodziarnaStatic = dokumentDodawany.getZawartoscPodziarna();
//        dokumentDodawany.zawartoscNadziarnaStatic = dokumentDodawany.getZawartoscNadziarna();
//        dokumentDodawany.zawartoscWilgociCalkowitejStatic = dokumentDodawany.getZawartoscWilgociCalkowitej();


        System.out.println(">>>>"+dokumentDodawany.getNaszaNazwa());
        dokumentDao.addDokumentToDatabase(dokumentDodawany);

    }

    public void przekazanieZmiennychDoWydruku(){

    }

    @FXML
    protected void dodajOstatniePiecDokumentowDoListView() {

        DokumentDao dokumentDao = new DokumentDao();
        List<Dokument> list = dokumentDao.getAllDokumenty();
       // System.out.println(list);


        Iterator<Dokument> iterator = list.iterator();
       // System.out.println("Dlugosc listy: " + list.size());
        int i = 0;
        for (Dokument b : list) {
            if (i > list.size() - 6) {
                ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();

                data.add(new Dokument(b.numerDokumentuProperty().getValue(),
                        b.dataDokumentuProperty().getValue(),
                        b.numerCertyfikatuProperty().getValue(),
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
            i++;
        }

    }

    private void print(Node node) {
        // Define the Job Status Message
//        jobStatus.textProperty().unbind();
//        jobStatus.setText("Creating a printer job...");

        // Create a printer job for the default printer
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null) {
            // Show the printer job status
//            jobStatus.textProperty().bind(job.jobStatusProperty().asString());

            // Print the node
            boolean printed = job.printPage(node);

            if (printed) {
                // End the printer job
                job.endJob();
            } else {
                // Write Error Message
//                jobStatus.textProperty().unbind();
//                jobStatus.setText("Printing failed.");
            }
        } else {
            // Write Error Message
//            jobStatus.setText("Could not create a printer job.");
        }
    }
    @FXML
    public void MenuListaCertyfikatowClick() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Lista Certyfikatow");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("ListaCertyfikatowTableView.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();
    }

    @FXML
    public void usunOstatniMenuClick() throws IOException {
       DokumentDao dokumentDao = new DokumentDao();
       dokumentDao.getNajwyzszyNumerDokumentuDaoString();
       dokumentDao.deleteDokumentDatabase(dokumentDao.getNajwyzszyNumerDokumentuDaoString());
       odswiezListedokumentow();
    }
    @FXML
    public void podgladClick() throws IOException {

    }

    @FXML
    void menuUstawieniaClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Ustawienia");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource
                ("Config.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();
    }

}
