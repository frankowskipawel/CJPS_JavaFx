package pl.weglokoks.modelFX;

import javafx.beans.property.SimpleStringProperty;

public class CertyfikatJakosci{
 //   private WartościDopuszczalnePaliwa typ;

    //Zmienne używane na stronie głównej na liście aktywnych certyfikatów
    private final SimpleStringProperty numerCertyfikatuAktywne = new SimpleStringProperty("");
    private final SimpleStringProperty naszaNazwaAktywne = new SimpleStringProperty("");
    private final SimpleStringProperty iloscAktywne = new SimpleStringProperty("");

    //zmienne używane na liście certyfiktów
    private final SimpleStringProperty numerCertyfikatu = new SimpleStringProperty("");
    private final SimpleStringProperty aktywny = new SimpleStringProperty("");
    private final SimpleStringProperty naszaNazwa = new SimpleStringProperty("");
    private final SimpleStringProperty asortyment = new SimpleStringProperty("");
    private final SimpleStringProperty data = new SimpleStringProperty("");
    private final SimpleStringProperty numerCertyfikatuLaboratorium = new SimpleStringProperty("");
    private final SimpleStringProperty zawartoscPopiolu = new SimpleStringProperty("");
    private final SimpleStringProperty zawartoscSiarkiCalkowitej = new SimpleStringProperty("");
    private final SimpleStringProperty zawartoscCzesciLotnych = new SimpleStringProperty("");
    private final SimpleStringProperty wartoscOpalowa = new SimpleStringProperty("");
    private final SimpleStringProperty zdolnoscSpiekania = new SimpleStringProperty("");
    private final SimpleStringProperty minimalnyWymiarZiarna = new SimpleStringProperty("");
    private final SimpleStringProperty maksymalnyWymiarZiarna = new SimpleStringProperty("");
    private final SimpleStringProperty zawartoscPodziarna = new SimpleStringProperty("");
    private final SimpleStringProperty zawartoscNadziarna = new SimpleStringProperty("");
    private final SimpleStringProperty zawartoscWilgociCalkowitej = new SimpleStringProperty("");
    private final SimpleStringProperty dostawca = new SimpleStringProperty("");
    private final SimpleStringProperty nrFV = new SimpleStringProperty("");
    private final SimpleStringProperty ilosc = new SimpleStringProperty("");

    //Konstruktor używany na liście certyfikatów
    public CertyfikatJakosci(String numerCertyfikatu, String aktywny, String naszaNazwa, String asortyment, String data,
                             String numerCertyfikatuLaboratorium, String zawartoscPopiolu, String zawartoscSiarkiCalkowitej,
                             String zawartoscCzesciLotnych, String wartoscOpalowa, String zdolnoscSpiekania,
                             String minimalnyWymiarZiarna, String maksymalnyWymiarZiarna, String zawartoscPodziarna,
                             String zawartoscNadziarna, String zawartoscWilgociCalkowitej, String dostawca, String nrFV, String ilosc) {

        setNumerCertyfikatu(numerCertyfikatu);
        setAktywny(aktywny);
        setNaszaNazwa(naszaNazwa);
        setAsortyment(asortyment);
        setData(data);
        setNumerCertyfikatuLaboratorium(numerCertyfikatuLaboratorium);
        setZawartoscPopiolu(zawartoscPopiolu);
        setZawartoscSiarkiCalkowitej(zawartoscSiarkiCalkowitej);
        setZawartoscCzesciLotnych(zawartoscCzesciLotnych);
        setWartoscOpalowa(wartoscOpalowa);
        setZdolnoscSpiekania(zdolnoscSpiekania);
        setMinimalnyWymiarZiarna(minimalnyWymiarZiarna);
        setMaksymalnyWymiarZiarna(maksymalnyWymiarZiarna);
        setZawartoscPodziarna(zawartoscPodziarna);
        setZawartoscNadziarna(zawartoscNadziarna);
        setZawartoscWilgociCalkowitej(zawartoscWilgociCalkowitej);
        setDostawca(dostawca);
        setNrFV(nrFV);
        setIloscAktywne(ilosc);
//---
    }

    public String getNumerCertyfikatu() {
        return numerCertyfikatu.get();
    }

    public SimpleStringProperty numerCertyfikatuProperty() {
        return numerCertyfikatu;
    }

    public void setNumerCertyfikatu(String numerCertyfikatu) {
        this.numerCertyfikatu.set(numerCertyfikatu);
    }

    //---
    public String getAktywny() {
        return aktywny.get();
    }

    public SimpleStringProperty aktywnyProperty() {
        return aktywny;
    }

    public void setAktywny(String aktywny) {
        this.aktywny.set(aktywny);
    }

    //---
    public String getNaszaNazwa() {
        return naszaNazwa.get();
    }

    public SimpleStringProperty naszaNazwaProperty() {
        return naszaNazwa;
    }

    public void setNaszaNazwa(String naszaNazwa) {
        this.naszaNazwa.set(naszaNazwa);
    }

    //---
    public String getAsortyment() {
        return asortyment.get();
    }

    public SimpleStringProperty asortymentProperty() {
        return asortyment;
    }

    public void setAsortyment(String asortyment) {
        this.asortyment.set(asortyment);
    }

    //---
    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    //---
    public String getNumerCertyfikatuLaboratorium() {
        return numerCertyfikatuLaboratorium.get();
    }

    public SimpleStringProperty numerCertyfikatuLaboratoriumProperty() {
        return numerCertyfikatuLaboratorium;
    }

    public void setNumerCertyfikatuLaboratorium(String numerCertyfikatuLaboratorium) {
        this.numerCertyfikatuLaboratorium.set(numerCertyfikatuLaboratorium);
    }

    //---
    public String getZawartoscPopiolu() {
        return zawartoscPopiolu.get();
    }

    public SimpleStringProperty zawartoscPopioluProperty() {
        return zawartoscPopiolu;
    }

    public void setZawartoscPopiolu(String zawartoscPopiolu) {
        this.zawartoscPopiolu.set(zawartoscPopiolu);
    }

    //---
    public String getZawartoscSiarkiCalkowitej() {
        return zawartoscSiarkiCalkowitej.get();
    }

    public SimpleStringProperty zawartoscSiarkiCalkowitejProperty() {
        return zawartoscSiarkiCalkowitej;
    }

    public void setZawartoscSiarkiCalkowitej(String zawartoscSiarkiCalkowitej) {
        this.zawartoscSiarkiCalkowitej.set(zawartoscSiarkiCalkowitej);
    }

    //---
    public String getZawartoscCzesciLotnych() {
        return zawartoscCzesciLotnych.get();
    }

    public SimpleStringProperty zawartoscCzesciLotnychProperty() {
        return zawartoscCzesciLotnych;
    }

    public void setZawartoscCzesciLotnych(String zawartoscCzesciLotnych) {
        this.zawartoscCzesciLotnych.set(zawartoscCzesciLotnych);
    }

    //---
    public String getWartoscOpalowa() {
        return wartoscOpalowa.get();
    }

    public SimpleStringProperty wartoscOpalowaProperty() {
        return wartoscOpalowa;
    }

    public void setWartoscOpalowa(String wartoscOpalowa) {
        this.wartoscOpalowa.set(wartoscOpalowa);
    }

    //---
    public String getZdolnoscSpiekania() {
        return zdolnoscSpiekania.get();
    }

    public SimpleStringProperty zdolnoscSpiekaniaProperty() {
        return zdolnoscSpiekania;
    }

    public void setZdolnoscSpiekania(String zdolnoscSpiekania) {
        this.zdolnoscSpiekania.set(zdolnoscSpiekania);
    }

    //---
    public String getMinimalnyWymiarZiarna() {
        return minimalnyWymiarZiarna.get();
    }

    public SimpleStringProperty minimalnyWymiarZiarnaProperty() {
        return minimalnyWymiarZiarna;
    }

    public void setMinimalnyWymiarZiarna(String minimalnyWymiarZiarna) {
        this.minimalnyWymiarZiarna.set(minimalnyWymiarZiarna);
    }

    //---
    public String getMaksymalnyWymiarZiarna() {
        return maksymalnyWymiarZiarna.get();
    }

    public SimpleStringProperty maksymalnyWymiarZiarnaProperty() {
        return maksymalnyWymiarZiarna;
    }

    public void setMaksymalnyWymiarZiarna(String maksymalnyWymiarZiarna) {
        this.maksymalnyWymiarZiarna.set(maksymalnyWymiarZiarna);
    }

    //---
    public String getZawartoscPodziarna() {
        return zawartoscPodziarna.get();
    }

    public SimpleStringProperty zawartoscPodziarnaProperty() {
        return zawartoscPodziarna;
    }

    public void setZawartoscPodziarna(String zawartoscPodziarna) {
        this.zawartoscPodziarna.set(zawartoscPodziarna);
    }

    //---
    public String getZawartoscNadziarna() {
        return zawartoscNadziarna.get();
    }

    public SimpleStringProperty zawartoscNadziarnaProperty() {
        return zawartoscNadziarna;
    }

    public void setZawartoscNadziarna(String zawartoscNadziarna) {
        this.zawartoscNadziarna.set(zawartoscNadziarna);
    }

    //---
    public String getZawartoscWilgociCalkowitej() {
        return zawartoscWilgociCalkowitej.get();
    }

    public SimpleStringProperty zawartoscWilgociCalkowitejProperty() {
        return zawartoscWilgociCalkowitej;
    }

    public void setZawartoscWilgociCalkowitej(String zawartoscWilgociCalkowitej) {
        this.zawartoscWilgociCalkowitej.set(zawartoscWilgociCalkowitej);
    }

    //---
    public String getDostawca() {
        return dostawca.get();
    }

    public SimpleStringProperty dostawcaProperty() {
        return dostawca;
    }

    public void setDostawca(String dostawca) {
        this.dostawca.set(dostawca);
    }

    //---
    public String getNrFV() {
        return nrFV.get();
    }

    public SimpleStringProperty nrFVProperty() {
        return nrFV;
    }

    public void setNrFV(String nrFV) {
        this.nrFV.set(nrFV);
    }

    public String getIlosc() {
        return ilosc.get();
    }

    public SimpleStringProperty iloscProperty() {
        return iloscAktywne;
    }

    public void setIlosc(String ilosc) {
        //  DokumentDao dokumentDao = new DokumentDao();

        this.iloscAktywne.set(ilosc);
    }
//-------------------------------------------------------------------------------------------------------

    //Konstruktor na potrzeby wyświetlenia listy Aktywnych certyfikatów na Stronie Głównej
    public CertyfikatJakosci(String numerCertyfikatuAktywne, String naszaNazwaAktywne, String iloscAktywne) {
        setNumerCertyfikatuAktywne(numerCertyfikatuAktywne);
        setNaszaNazwaAktywne(naszaNazwaAktywne);
        setIloscAktywne(iloscAktywne);
    }

    public String getNumerCertyfikatuAktywne() {
        return numerCertyfikatuAktywne.get();
    } //Tu sprawdzic

    public SimpleStringProperty numerCertyfikatuAktywneProperty() {
        return numerCertyfikatuAktywne;
    }

    public void setNumerCertyfikatuAktywne(String numerCertyfikatuAktywne) {
        this.numerCertyfikatuAktywne.set(numerCertyfikatuAktywne);
    }

    //---
    public String getNaszaNazwaAktywne() {
        return naszaNazwaAktywne.get();
    }

    public SimpleStringProperty naszaNazwaAktywneProperty() {
        return naszaNazwaAktywne;
    }

    public void setNaszaNazwaAktywne(String naszaNazwaAktywne) {
        this.naszaNazwaAktywne.set(naszaNazwaAktywne);
    }

    //---
    public String getIloscAktywne() {
        return iloscAktywne.get();
    }

    public SimpleStringProperty iloscStronaGlownaProperty() {
        return iloscAktywne;
    }

    public void setIloscAktywne(String iloscAktywne) {
      //  DokumentDao dokumentDao = new DokumentDao();

        this.iloscAktywne.set(iloscAktywne);
    }
//----------------------------------------------------------------------------------------------------------


    @Override
    public String toString() {
        return "CertyfikatJakosci{" +
                "numerCertyfikatu=" + numerCertyfikatu +
                ", aktywny=" + aktywny +
                ", naszaNazwa=" + naszaNazwa +
                ", asortyment=" + asortyment +
                ", data=" + data +
                ", numerCertyfikatuLaboratorium=" + numerCertyfikatuLaboratorium +
                ", zawartoscPopiolu=" + zawartoscPopiolu +
                ", zawartoscSiarkiCalkowitej=" + zawartoscSiarkiCalkowitej +
                ", zawartoscCzesciLotnych=" + zawartoscCzesciLotnych +
                ", wartoscOpalowa=" + wartoscOpalowa +
                ", zdolnoscSpiekania=" + zdolnoscSpiekania +
                ", minimalnyWymiarZiarna=" + minimalnyWymiarZiarna +
                ", maksymalnyWymiarZiarna=" + maksymalnyWymiarZiarna +
                ", zawartoscPodziarna=" + zawartoscPodziarna +
                ", zawartoscNadziarna=" + zawartoscNadziarna +
                ", zawartoscWilgociCalkowitej=" + zawartoscWilgociCalkowitej +
                ", dostawca=" + dostawca +
                ", nrFV=" + nrFV +
                '}';
    }


}