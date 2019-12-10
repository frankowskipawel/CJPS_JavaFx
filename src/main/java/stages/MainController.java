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
import model.WartościDopuszczalnePaliwa;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class MainController {

    @FXML
    private TableView<CertyfikatJakosci> listaAktywnychCertyfikatowTableViewStronaGlowna;
    @FXML
    private ListView<Dokument> listaDokumentowListViewStronaGlowna;
    @FXML
    private VBox vBoxStronaGlowna;


    @FXML
    public void initialize() {

        setListaAktywnychCertyfikatow();
        add5DokumentowToListView();

    }

    protected List<CertyfikatJakosci> getAktywneCertyfikaty() {

        CertyfikatJakosciDao certyfikatJakosciDao = new CertyfikatJakosciDao();
        return certyfikatJakosciDao.getAktywneCertyfikatyJakosci();
    }

    protected void setListaAktywnychCertyfikatow() {

        for (CertyfikatJakosci b : getAktywneCertyfikaty()) {
            ObservableList<CertyfikatJakosci> data = listaAktywnychCertyfikatowTableViewStronaGlowna.getItems();
            data.add(new CertyfikatJakosci(b.numerCertyfikatuProperty().getValue(), b.naszaNazwaProperty().getValue()
            ));
        }
    }

    @FXML
    protected void refreshClick() {

        refreshListaAktywnychCertyfikatow();
        refreshListaDokumentow();
    }

    @FXML
    protected void addAndPrintNowyDokumentClick() throws IOException {
        Dokument dokument;
        dokument = addDokumentToListView();
        refreshListaAktywnychCertyfikatow();
        refreshListaDokumentow();
        showDokument(dokument); //zamienic na print docelowo
    }

    protected void showDokument(Dokument dokument) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/stages/CertyfikatJakosciWydruk.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Podgląd wydruku");
        stage.show();
//-----------------------------------
        CertyfikatJakosciWydrukController certyfikatJakosciWydrukController = loader.getController(); //wyciągnięcie referencji wyświetlanego stage-a
        setWartosciDopuszczalneNaWydruku(certyfikatJakosciWydrukController, WartościDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()));

    }


    public void setWartosciDopuszczalneNaWydruku(CertyfikatJakosciWydrukController certyfikatJakosciWydrukController, WartościDopuszczalnePaliwa asortyment) {
        certyfikatJakosciWydrukController.setWDminPopiolWydruk(asortyment.getMinPopiol());
        certyfikatJakosciWydrukController.setWDmaxPopiolWydruk(asortyment.getMaxPopiol());
        certyfikatJakosciWydrukController.setWDminSiarkaWydruk(asortyment.getMinSiarka());
        certyfikatJakosciWydrukController.setWDmaxSiarkaWydruk(asortyment.getMaxSiarka());
        certyfikatJakosciWydrukController.setWDminCzLotneWydruk(asortyment.getMinCzLotne());
        certyfikatJakosciWydrukController.setWDmaxCzLotneWydruk(asortyment.getMaxCzLotne());
        certyfikatJakosciWydrukController.setWDminWartoscOpalowaWydruk(asortyment.getMinWartoscOpalowa());
        certyfikatJakosciWydrukController.setWDmaxWartoscOpalowaWydruk(asortyment.getMaxWartoscOpalowa());
        certyfikatJakosciWydrukController.setWDminSpiekalnoscWydruk(asortyment.getMinSpiekalnsc());
        certyfikatJakosciWydrukController.setWDmaxSpiekalnoscWydruk(asortyment.getMaxSpiekalnosc());
        certyfikatJakosciWydrukController.setWDminWymiarZiarna(asortyment.getMinWymiarZiarna());
        certyfikatJakosciWydrukController.setWDmaxWymiarZiarna(asortyment.getMaxWymiarZiarna());
        certyfikatJakosciWydrukController.setWDminPodziarnoWydruk(asortyment.getMinPodziarno());
        certyfikatJakosciWydrukController.setWDmaxPodziarnoWydruk(asortyment.getMaxPodziarno());
        certyfikatJakosciWydrukController.setWDminNadziarnoWydruk(asortyment.getMinNadziarno());
        certyfikatJakosciWydrukController.setWDmaxNadziarnoWydruk(asortyment.getMaxNadziarno());
        certyfikatJakosciWydrukController.setWDminWilgotnoscWydruk(asortyment.getMinWilgotnosc());
        certyfikatJakosciWydrukController.setWDmaxWilgotnoscWydruk(asortyment.getMaxWilgotnosc());

    }

    public void refreshListaAktywnychCertyfikatow() {
        ObservableList<CertyfikatJakosci> data = listaAktywnychCertyfikatowTableViewStronaGlowna.getItems();
        data.removeAll(data);
        setListaAktywnychCertyfikatow();
    }

    public void refreshListaDokumentow() {
        ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();
        data.removeAll(data);
        add5DokumentowToListView();
    }

    protected Dokument addDokumentToListView() {

        ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();
        CertyfikatJakosci certyfikatJakosci = new CertyfikatJakosciDao().znajdzCertyfikatPoId(listaAktywnychCertyfikatowTableViewStronaGlowna.getSelectionModel().getSelectedItem().getNumerCertyfikatuAktywne());

        DokumentDao dokumentDao = new DokumentDao();
        System.out.println(dokumentDao.getNajwyzszyNumerDokumentuDao());
        int numer = dokumentDao.getNajwyzszyNumerDokumentuDao() + 1;

        ZonedDateTime dataDzisiejsza = ZonedDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataDzisiejszaString = dataDzisiejsza.format(f);

        Dokument dokumentDodawany = new Dokument(Integer.toString(numer) + "/" + dataDzisiejszaString.substring(0, 4), dataDzisiejszaString, certyfikatJakosci);
        System.out.println("dok dodawany : " + dokumentDodawany);
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


        System.out.println(">>>>" + dokumentDodawany.getCertyfikatJakosci().getNaszaNazwa());
        dokumentDao.addDokumentToDatabase(dokumentDodawany);
        return dokumentDodawany;
    }

    public void przekazanieZmiennychDoWydruku() {

    }

    @FXML
    protected void add5DokumentowToListView() {

        DokumentDao dokumentDao = new DokumentDao();
        List<Dokument> list = dokumentDao.getAllDokumenty();

        Iterator<Dokument> iterator = list.iterator();
        // System.out.println("Dlugosc listy: " + list.size());
        int i = 0;
        // CertyfikatJakosci certyfikatJakosci = new CertyfikatJakosci();
        for (Dokument b : list) {
            if (i > list.size() - 6) {
                ObservableList<Dokument> data = listaDokumentowListViewStronaGlowna.getItems();

                data.add(new Dokument(b.numerDokumentuProperty().getValue(),
                        b.dataDokumentuProperty().getValue(),
                        new CertyfikatJakosci(b.getCertyfikatJakosci().numerCertyfikatuProperty().getValue(),
                                b.getCertyfikatJakosci().aktywnyProperty().getValue(),
                                b.getCertyfikatJakosci().naszaNazwaProperty().getValue(),
                                b.getCertyfikatJakosci().asortymentProperty().getValue(),
                                b.getCertyfikatJakosci().dataProperty().getValue(),
                                b.getCertyfikatJakosci().numerCertyfikatuLaboratoriumProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscPopioluProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscSiarkiCalkowitejProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscCzesciLotnychProperty().getValue(),
                                b.getCertyfikatJakosci().wartoscOpalowaProperty().getValue(),
                                b.getCertyfikatJakosci().zdolnoscSpiekaniaProperty().getValue(),
                                b.getCertyfikatJakosci().minimalnyWymiarZiarnaProperty().getValue(),
                                b.getCertyfikatJakosci().maksymalnyWymiarZiarnaProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscPodziarnaProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscNadziarnaProperty().getValue(),
                                b.getCertyfikatJakosci().zawartoscWilgociCalkowitejProperty().getValue(),
                                b.getCertyfikatJakosci().dostawcaProperty().getValue(),
                                b.getCertyfikatJakosci().nrFVProperty().getValue()))

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
        refreshListaDokumentow();
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
