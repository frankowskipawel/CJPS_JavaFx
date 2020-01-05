package stages;

import config.Config;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import modelFX.Dokument;
import modelFX.WartosciDopuszczalnePaliwa;

import java.io.IOException;

public class WydrukController {

    WydrukController wydrukController;
    HomeController homeController;

    @FXML
    private Pane paneWydruk;
    @FXML
    private Label nazwaAdresPodmiotuWydruk;
    @FXML
    private Label NipRegonPodmiotuWydruk;
    @FXML
    private Label nrWydruk;
    @FXML
    private Label dataDokumentuWydruk;
    @FXML
    private Label asortymentWydruk;
    @FXML
    private Label nrLabWydruk;
    @FXML
    private Label popiolWydruk;
    @FXML
    private Label siarkaWydruk;
    @FXML
    private Label czLotneWydruk;
    @FXML
    private Label wartoscOpalowaWydruk;
    @FXML
    private Label spiekalnoscWydruk;
    @FXML
    private Label wymiarZiarnaWydruk;
    @FXML
    private Label zawPodziarnaWydruk;
    @FXML
    private Label nadziarnoWydruk;
    @FXML
    private Label zawWilgociWydruk;
    @FXML
    private Label WDminPopiolWydruk;
    @FXML
    private Label WDminSiarkaWydruk;
    @FXML
    private Label WDminCzLotneWydruk;
    @FXML
    private Label WDminWartoscOpalowaWydruk;
    @FXML
    private Label WDminSpiekalnoscWydruk;
    @FXML
    private Label WDminWymiarZiarna;
    @FXML
    private Label WDminZawPodziarnaWydruk;
    @FXML
    private Label WDminNadziarnoWydruk;
    @FXML
    private Label WDminZawWilgociWydruk;
    @FXML
    private Label WDmaxPopiolWydruk;
    @FXML
    private Label WDmaxSiarkaWydruk;
    @FXML
    private Label WDmaxCzLotneWydruk;
    @FXML
    private Label WDmaxWartoscOpalowaWydruk;
    @FXML
    private Label WDmaxSpiekalnoscWydruk;
    @FXML
    private Label WDmaxWymiarZiarna;
    @FXML
    private Label WDmaxZawPodziarnaWydruk;
    @FXML
    private Label WDmaxNadziarnoWydruk;
    @FXML
    private Label WDmaxZawWilgociWydruk;
    @FXML
    private Button printButton;
    @FXML
    private CheckBox jednaKopiaCheckBox;


    public WydrukController() {
    }

    public void initialize() {
    }

    public void setFieldInWydruk(Dokument dokument) {

        //Wartości paliwa
        setNazwaAdresPodmiotuWydruk(Config.NAZWA_PODMIOTU + ", " + Config.ULICA_I_NUMER_DOMU_PODMIOTU + ", " + Config.KOD_POCZTOWY_PODMIOTU + " " + Config.MIASTO_PODMIOTU);
        setNipRegonPodmiotuWydruk("NIP " + Config.NIP_PODMIOTU + " / REGON " + Config.REGON_PODMIOTU);
        setNrWydruk(dokument.getNumerDokumentu());
        setDataDokumentuWydruk(dokument.getDataDokumentu());
        setAsortymentWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getNazwa());
        setNrLabWydruk(dokument.getCertyfikatJakosci().getNumerCertyfikatuLaboratorium());
        setPopiolWydruk(dokument.getCertyfikatJakosci().getZawartoscPopiolu());
        setSiarkaWydruk(dokument.getCertyfikatJakosci().getZawartoscSiarkiCalkowitej());
        setCzLotneWydruk(dokument.getCertyfikatJakosci().getZawartoscCzesciLotnych());
        setWartoscOpalowaWydruk(dokument.getCertyfikatJakosci().getWartoscOpalowa());
        setSpiekalnoscWydruk(dokument.getCertyfikatJakosci().getZdolnoscSpiekania());
        setWymiarZiarnaWydruk(dokument.getCertyfikatJakosci().getMinimalnyWymiarZiarna() + "-" + dokument.getCertyfikatJakosci().getMaksymalnyWymiarZiarna());
        setZawPodziarnaWydruk(dokument.getCertyfikatJakosci().getZawartoscPodziarna());
        setZawNadziarnaWydruk(dokument.getCertyfikatJakosci().getZawartoscNadziarna());
        setZawWilgociWydruk(dokument.getCertyfikatJakosci().getZawartoscWilgociCalkowitej());
        //wartości dopuszczalne
        setWDminPopiolWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMinPopiol());
        setWDmaxPopiolWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMaxPopiol());
        setWDminSiarkaWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMinSiarka());
        setWDmaxSiarkaWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMaxSiarka());
        setWDminCzLotneWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMinCzLotne());
        setWDmaxCzLotneWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMaxCzLotne());
        setWDminWartoscOpalowaWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMinWartoscOpalowa());
        setWDmaxWartoscOpalowaWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMaxWartoscOpalowa());
        setWDminSpiekalnoscWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMinSpiekalnsc());
        setWDmaxSpiekalnoscWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMaxSpiekalnosc());
        setWDminWymiarZiarna(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMinWymiarZiarna());
        setWDmaxWymiarZiarna(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMaxWymiarZiarna());
        setWDminPodziarnoWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMinPodziarno());
        setWDmaxPodziarnoWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMaxPodziarno());
        setWDminNadziarnoWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMinNadziarno());
        setWDmaxNadziarnoWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMaxNadziarno());
        setWDminWilgotnoscWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMinWilgotnosc());
        setWDmaxWilgotnoscWydruk(WartosciDopuszczalnePaliwa.valueOf(dokument.getCertyfikatJakosci().getAsortyment()).getMaxWilgotnosc());
    }

    @FXML
    private void printOnClick() throws IOException {
        getJednaKopiaCheckBox().setVisible(false);
        getPrintButton().setVisible(false);

        if (getJednaKopiaCheckBox().isSelected()) {

            print(1);
        } else {
            print(2);
        }
        getJednaKopiaCheckBox().setVisible(true);
        getPrintButton().setVisible(true);

    }

    public void print(int a) {
        for (int i = 1; i <= a; i++) {
            print(paneWydruk);
        }
    }

    public void print(Node node) {

        homeController.setMessageLabelMain("Przygotowuje wydruk");
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null) {
            homeController.setMessageLabelMain("Trwa Drukowanie");
            boolean printed = job.printPage(node);

            if (printed) {
                homeController.setMessageLabelMain("Wydrukowano pomyślnie");
                job.endJob();
            } else {
                homeController.setMessageLabelMain("Błąd wydruku");
            }
        } else {
            homeController.setMessageLabelMain("Błąd wydruku");
        }
    }


    //getters and sertters
    public void setNazwaAdresPodmiotuWydruk(String nazwaAdresPodmiotuWydruk) {
        this.nazwaAdresPodmiotuWydruk.setText(nazwaAdresPodmiotuWydruk);
    }

    public void setNipRegonPodmiotuWydruk(String nipRegonPodmiotuWydruk) {
        NipRegonPodmiotuWydruk.setText(nipRegonPodmiotuWydruk);
    }

    public void setNrWydruk(String nrWydruk) {
        this.nrWydruk.setText(nrWydruk);
    }

    public void setDataDokumentuWydruk(String dataDokumentuWydruk) {
        this.dataDokumentuWydruk.setText(dataDokumentuWydruk);
    }

    public void setAsortymentWydruk(String asortymentWydruk) {
        this.asortymentWydruk.setText(asortymentWydruk);
    }

    public void setNrLabWydruk(String nrLabWydruk) {
        this.nrLabWydruk.setText(nrLabWydruk);
    }

    public void setPopiolWydruk(String popiolWydruk) {
        this.popiolWydruk.setText(popiolWydruk);
    }

    public void setSiarkaWydruk(String siarkaWydruk) {
        this.siarkaWydruk.setText(siarkaWydruk);
    }

    public void setCzLotneWydruk(String czLotneWydruk) {
        this.czLotneWydruk.setText(czLotneWydruk);
    }

    public void setWartoscOpalowaWydruk(String wartoscOpalowaWydruk) {
        this.wartoscOpalowaWydruk.setText(wartoscOpalowaWydruk);
    }

    public void setSpiekalnoscWydruk(String spiekalnoscWydruk) {
        this.spiekalnoscWydruk.setText(spiekalnoscWydruk);
    }

    public void setWymiarZiarnaWydruk(String wymiarZiarnaWydruk) {
        this.wymiarZiarnaWydruk.setText(wymiarZiarnaWydruk);
    }

    public void setZawPodziarnaWydruk(String zawPodziarnaWydruk) {
        this.zawPodziarnaWydruk.setText(zawPodziarnaWydruk);
    }

    public void setZawNadziarnaWydruk(String nadziarnoWydruk) {
        this.nadziarnoWydruk.setText(nadziarnoWydruk);
    }

    public void setZawWilgociWydruk(String zawWilgociWydruk) {
        this.zawWilgociWydruk.setText(zawWilgociWydruk);
    }

    public void setWDminPopiolWydruk(String WDminPopiolWydruk) {
        this.WDminPopiolWydruk.setText(WDminPopiolWydruk);
    }

    public void setWDminSiarkaWydruk(String WDminSiarkaWydruk) {
        this.WDminSiarkaWydruk.setText(WDminSiarkaWydruk);
    }

    public void setWDminCzLotneWydruk(String WDminCzLotneWydruk) {
        this.WDminCzLotneWydruk.setText(WDminCzLotneWydruk);
    }

    public void setWDminWartoscOpalowaWydruk(String WDminWartoscOpalowaWydruk) {
        this.WDminWartoscOpalowaWydruk.setText(WDminWartoscOpalowaWydruk);
    }

    public void setWDminSpiekalnoscWydruk(String WDminSpiekalnoscWydruk) {
        this.WDminSpiekalnoscWydruk.setText(WDminSpiekalnoscWydruk);
    }

    public void setWDminWymiarZiarna(String WDminWymiarZiarna) {
        this.WDminWymiarZiarna.setText(WDminWymiarZiarna);
    }

    public void setWDminPodziarnoWydruk(String WDminZawPodziarnaWydruk) {
        this.WDminZawPodziarnaWydruk.setText(WDminZawPodziarnaWydruk);
    }

    public void setWDminNadziarnoWydruk(String WDminNadziarnoWydruk) {
        this.WDminNadziarnoWydruk.setText(WDminNadziarnoWydruk);
    }

    public void setWDminWilgotnoscWydruk(String WDminZawWilgociWydruk) {
        this.WDminZawWilgociWydruk.setText(WDminZawWilgociWydruk);
    }

    public void setWDmaxPopiolWydruk(String WDmaxPopiolWydruk) {
        this.WDmaxPopiolWydruk.setText(WDmaxPopiolWydruk);
    }

    public void setWDmaxSiarkaWydruk(String WDmaxSiarkaWydruk) {
        this.WDmaxSiarkaWydruk.setText(WDmaxSiarkaWydruk);
    }

    public void setWDmaxCzLotneWydruk(String WDmaxCzLotneWydruk) {
        this.WDmaxCzLotneWydruk.setText(WDmaxCzLotneWydruk);
    }

    public void setWDmaxWartoscOpalowaWydruk(String WDmaxWartoscOpalowaWydruk) {
        this.WDmaxWartoscOpalowaWydruk.setText(WDmaxWartoscOpalowaWydruk);
    }

    public void setWDmaxSpiekalnoscWydruk(String WDmaxSpiekalnoscWydruk) {
        this.WDmaxSpiekalnoscWydruk.setText(WDmaxSpiekalnoscWydruk);
    }

    public void setWDmaxWymiarZiarna(String WDmaxWymiarZiarna) {
        this.WDmaxWymiarZiarna.setText(WDmaxWymiarZiarna);
    }

    public void setWDmaxPodziarnoWydruk(String WDmaxZawPodziarnaWydruk) {
        this.WDmaxZawPodziarnaWydruk.setText(WDmaxZawPodziarnaWydruk);
    }

    public void setWDmaxNadziarnoWydruk(String WDmaxNadziarnoWydruk) {
        this.WDmaxNadziarnoWydruk.setText(WDmaxNadziarnoWydruk);
    }

    public void setWDmaxWilgotnoscWydruk(String WDmaxZawWilgociWydruk) {
        this.WDmaxZawWilgociWydruk.setText(WDmaxZawWilgociWydruk);
    }

    public WydrukController getWydrukController() {
        return wydrukController;
    }

    public void setWydrukController(WydrukController wydrukController) {
        this.wydrukController = wydrukController;
    }

    public Button getPrintButton() {
        return printButton;
    }

    public void setPrintButton(Button printButton) {
        this.printButton = printButton;
    }

    public CheckBox getJednaKopiaCheckBox() {
        return jednaKopiaCheckBox;
    }

    public void setJednaKopiaCheckBox(CheckBox jednaKopiaCheckBox) {
        this.jednaKopiaCheckBox = jednaKopiaCheckBox;
    }
}
