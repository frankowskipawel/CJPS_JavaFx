package pl.weglokoks.modelFX;

import javafx.beans.property.SimpleStringProperty;

public class Kontrahent {

    private final SimpleStringProperty idKontrahent = new SimpleStringProperty("");
    private final SimpleStringProperty nazwaKontrahent = new SimpleStringProperty("");
    private final SimpleStringProperty adresKontrahent = new SimpleStringProperty("");
    private final SimpleStringProperty nipKontrahent = new SimpleStringProperty("");
    private final SimpleStringProperty regonKontrahent = new SimpleStringProperty("");


    public Kontrahent(String id, String nazwa, String adres, String nip, String regon) {
        setIdKontrahent(id);
        setNazwaKontrahent(nazwa);
        setAdresKontrahent(adres);
        setNipKontrahent(nip);
        setRegonKontrahent(regon);

    }

    public String getIdKontrahent() {
        return idKontrahent.get();
    }
    public SimpleStringProperty idKontrahentProperty() {return idKontrahent; }
    public void setIdKontrahent(String idKontrahent) {
        this.idKontrahent.set(idKontrahent);
    }

    public String getNazwaKontrahent() {
        return nazwaKontrahent.get();
    }
    public SimpleStringProperty nazwaKontrahentProperty() {
        return nazwaKontrahent;
    }
    public void setNazwaKontrahent(String nazwaKontrahent) {
        this.nazwaKontrahent.set(nazwaKontrahent);
    }

    public String getAdresKontrahent() {
        return adresKontrahent.get();
    }
    public SimpleStringProperty adresKontrahentProperty() {
        return adresKontrahent;
    }
    public void setAdresKontrahent(String adresKontrahent) {
        this.adresKontrahent.set(adresKontrahent);
    }

    public String getNipKontrahent() {
        return nipKontrahent.get();
    }
    public SimpleStringProperty nipKontrahentProperty() {
        return nipKontrahent;
    }
    public void setNipKontrahent(String nipKontrahent) {
        this.nipKontrahent.set(nipKontrahent);
    }

    public String getRegonKontrahent() { return regonKontrahent.get(); }
    public SimpleStringProperty regonKontrahentProperty() {
        return regonKontrahent;
    }
    public void setRegonKontrahent(String regonKontrahent) {
        this.regonKontrahent.set(regonKontrahent);
    }

    @Override
    public String toString() {
        return "Kontrahent{" +
                "id=" + idKontrahent +
                ", nazwa='" + nazwaKontrahent + '\'' +
                ", adres='" + adresKontrahent + '\'' +
                ", nip='" + nipKontrahent + '\'' +
                ", regon='" + regonKontrahent + '\'' +
                '}';
    }
}
