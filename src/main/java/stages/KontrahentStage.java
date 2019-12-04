package stages;


import dao.KontrahentDao;
import model.Kontrahent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.List;

public class KontrahentStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Certyfikaty Jakości Paliw Stałych 1.0");
        Pane myPane = (Pane)FXMLLoader.load(getClass().getResource
                ("KontrahentTableView.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();

    }

    public static void main(String[] args) {



        KontrahentDao kontrahentDao = new KontrahentDao();
        System.out.println(kontrahentDao.getAllKontrahent());
        List<Kontrahent> list = kontrahentDao.getAllKontrahent();

        Iterator<Kontrahent> iterator=list.iterator();
       /* while(iterator.hasNext()){
            System.out.println(iterator.next());

            }
        }*/
        for(Kontrahent b:list) {

            System.out.print(b.idKontrahentProperty().getValue()+"/");
            System.out.print(b.nazwaKontrahentProperty().getValue()+"/");
            System.out.print(b.adresKontrahentProperty().getValue()+"/");
            System.out.print(b.nipKontrahentProperty().getValue()+"/");
            System.out.println(b.regonKontrahentProperty().getValue());
        }
       // kontrahentDao.getNazwa();


       // kontrahentDao.addKontrahentDatabase("4", "JSW", "Slask", "234342", "999876");

        launch(args);
    }
}
