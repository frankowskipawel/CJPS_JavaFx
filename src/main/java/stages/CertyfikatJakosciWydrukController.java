package stages;

import model.Dokument;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Dokument;

import java.io.IOException;

public class CertyfikatJakosciWydrukController {
    @FXML
    private Pane paneWydruk;
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


    public CertyfikatJakosciWydrukController() {
    }


    public void initialize() throws IOException {
//        Dokument dokument = new Dokument();
//        nrWydruk.setText(dokumenty.numerDokumentuStatic);
//        dataDokumentuWydruk.setText(dokumenty.dataDokumentuStatic);
//        asortymentWydruk.setText(dokumenty.asortymentStatic);
//        nrLabWydruk.setText(dokumenty.numerCertyfikatuLaboratoriumStatic);
//        popiolWydruk.setText(dokumenty.zawartoscPopioluStatic);
//        siarkaWydruk.setText(dokumenty.zawartoscSiarkiCalkowitejStatic);
//        czLotneWydruk.setText(dokumenty.zawartoscCzesciLotnychStatic);
//        wartoscOpalowaWydruk.setText(dokumenty.wartoscOpalowaStatic);
//        spiekalnoscWydruk.setText(dokumenty.zdolnoscSpiekaniaStatic);
//        wymiarZiarnaWydruk.setText((dokumenty.minimalnyWymiarZiarnaStatic) + "-" + (dokumenty.maksymalnyWymiarZiarnaStatic));
//        zawPodziarnaWydruk.setText(dokumenty.zawartoscPodziarnaStatic);
//        nadziarnoWydruk.setText(dokumenty.zawartoscNadziarnaStatic);
//        zawWilgociWydruk.setText(dokumenty.zawartoscWilgociCalkowitejStatic);

//     String[] wartosciDopuszczalne = wartosciDopuszczalne(dokument.asortymentStatic);
//
//
//
//
//
//         WDminPopiolWydruk.setText(wartosciDopuszczalne[0]);
//         WDminSiarkaWydruk.setText(wartosciDopuszczalne[1]);
//         WDminCzLotneWydruk.setText(wartosciDopuszczalne[2]);
//         WDminWartoscOpalowaWydruk.setText(wartosciDopuszczalne[3]);
//         WDminSpiekalnoscWydruk.setText(wartosciDopuszczalne[4]);
//         WDminWymiarZiarna.setText(wartosciDopuszczalne[5]);
//         WDminZawPodziarnaWydruk.setText(wartosciDopuszczalne[6]);
//         WDminNadziarnoWydruk.setText(wartosciDopuszczalne[7]);
//         WDminZawWilgociWydruk.setText(wartosciDopuszczalne[8]);
//
//         WDmaxPopiolWydruk.setText(wartosciDopuszczalne[9]);
//         WDmaxSiarkaWydruk.setText(wartosciDopuszczalne[10]);
//         WDmaxCzLotneWydruk.setText(wartosciDopuszczalne[11]);
//         WDmaxWartoscOpalowaWydruk.setText(wartosciDopuszczalne[12]);
//         WDmaxSpiekalnoscWydruk.setText(wartosciDopuszczalne[13]);
//         WDmaxWymiarZiarna.setText(wartosciDopuszczalne[14]);
//         WDmaxZawPodziarnaWydruk.setText(wartosciDopuszczalne[15]);
//         WDmaxNadziarnoWydruk.setText(wartosciDopuszczalne[16]);
//         WDmaxZawWilgociWydruk.setText(wartosciDopuszczalne[17]);

        //print(paneWydruk);
       // print(paneWydruk);
    }
    public void setTextLabel(String text){
        nrWydruk.setText(text);
    }

    private String[] wartosciDopuszczalne(String asortyment) {

        if (asortyment.equals("WĘGIEL KAMIENNY - KOSTKA")) {
            String[] wartosciDopuszczalneTablica;
            wartosciDopuszczalneTablica = new String[]{"-", "-", "-", "22", "-", "63", "0", "0", "-", "12", "1,7", "-", "-", "-", "200", "10", "10", "20"};
            return wartosciDopuszczalneTablica;
        }
        if (asortyment.equals("WĘGIEL KAMIENNY - ORZECH") ) {
            String[] wartosciDopuszczalneTablica;
            wartosciDopuszczalneTablica = new String[]{"-", "-", "-", "22", "-", "25", "0", "0", "-", "12", "1,7", "-", "-", "-", "80", "10", "10", "20"};
            return wartosciDopuszczalneTablica;
        }
        if (asortyment.equals("WĘGIEL KAMIENNY - GROSZEK")) {
            String[] wartosciDopuszczalneTablica;
            wartosciDopuszczalneTablica = new String[]{"-", "-", "-", "21", "-", "5", "0", "0", "-", "14", "1,7", "-", "-", "90", "40", "11", "10", "20"};
            return wartosciDopuszczalneTablica;
        }
        if (asortyment.equals("WĘGIEL KAMIENNY - MIAŁ")) {
            String[] wartosciDopuszczalneTablica;
            wartosciDopuszczalneTablica = new String[]{"-", "-", "-", "18", "-", "1", "-", "0", "-", "28", "1,7", "-", "-", "-", "31,5", "30", "5", "24"};
            return wartosciDopuszczalneTablica;
        }
        if (asortyment.equals("WĘGIEL KAMIENNY - EKOGROSZEK")) {
            String[] wartosciDopuszczalneTablica;
            wartosciDopuszczalneTablica = new String[]{"-", "-", "-", "24", "-", "5", "0", "0", "-", "12", "1,2", "-", "-", "25", "31,5", "10", "10", "15"};
            return wartosciDopuszczalneTablica;
        }
        if (asortyment.equals("WĘGIEL KAMIENNY - PELLET")) {
            String[] wartosciDopuszczalneTablica;
            wartosciDopuszczalneTablica = new String[]{"-", "-", "-", "24", "-", "5", "0", "0", "-", "12", "1,2", "-", "-", "25", "31,5", "10", "5", "15"};
            return wartosciDopuszczalneTablica;
        }

        String[] wartosciDopuszczalneTablica;
        wartosciDopuszczalneTablica = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        return wartosciDopuszczalneTablica;
    }


    @FXML
    private void printClick() throws IOException {
        print(paneWydruk);


    }

    public void print(Node node) {
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
}