package model;

import javafx.beans.property.SimpleStringProperty;

public class Dokument extends CertyfikatJakosci{

    private final SimpleStringProperty numerDokumentu = new SimpleStringProperty("");
    private final SimpleStringProperty dataDokumentu = new SimpleStringProperty("");

    public Dokument(String numerDokumentu, String dataDokumentu, String numerCertyfikatu, String aktywny, String naszaNazwa, String asortyment, String data, String numerCertyfikatuLaboratorium, String zawartoscPopiolu, String zawartoscSiarkiCalkowitej, String zawartoscCzesciLotnych, String wartoscOpalowa, String zdolnoscSpiekania, String minimalnyWymiarZiarna, String maksymalnyWymiarZiarna, String zawartoscPodziarna, String zawartoscNadziarna, String zawartoscWilgociCalkowitej, String dostawca, String nrFV) {
        super(numerCertyfikatu, aktywny, naszaNazwa, asortyment, data, numerCertyfikatuLaboratorium, zawartoscPopiolu, zawartoscSiarkiCalkowitej, zawartoscCzesciLotnych, wartoscOpalowa, zdolnoscSpiekania, minimalnyWymiarZiarna, maksymalnyWymiarZiarna, zawartoscPodziarna, zawartoscNadziarna, zawartoscWilgociCalkowitej, dostawca, nrFV);
        setNumerDokumentu(numerDokumentu);
        setDataDokumentu(dataDokumentu);
    }

    public String getNumerDokumentu() {return numerDokumentu.get(); }
    public SimpleStringProperty numerDokumentuProperty() {return numerDokumentu; }
    public void setNumerDokumentu(String numerDokumentu) {this.numerDokumentu.set(numerDokumentu); }

    public String getDataDokumentu() {return dataDokumentu.get(); }
    public SimpleStringProperty dataDokumentuProperty() {return dataDokumentu; }
    public void setDataDokumentu(String dataDokumentu) {this.dataDokumentu.set(dataDokumentu); }

    @Override
    public String toString() {
        return
                 numerDokumentu.getValue() +

                ", " +
        ", " + dataDokumentu.getValue() ;


    }

}
