package modelFXML;

import javafx.beans.property.SimpleStringProperty;

public class Dokument {

        private CertyfikatJakosci certyfikatJakosci;

        private final SimpleStringProperty numerDokumentu = new SimpleStringProperty("");
        private final SimpleStringProperty dataDokumentu = new SimpleStringProperty("");

        public Dokument(String numerDokumentu, String dataDokumentu, CertyfikatJakosci certyfikatJakosci) {
            setNumerDokumentu(numerDokumentu);
            setDataDokumentu(dataDokumentu);
            this.certyfikatJakosci = certyfikatJakosci;
        }

    public String getNumerDokumentu() {
        return numerDokumentu.get();
    }

    public SimpleStringProperty numerDokumentuProperty() {
        return numerDokumentu;
    }

    public void setNumerDokumentu(String numerDokumentu) {
        this.numerDokumentu.set(numerDokumentu);
    }

    public String getDataDokumentu() {
        return dataDokumentu.get();
    }

    public SimpleStringProperty dataDokumentuProperty() {
        return dataDokumentu;
    }

    public void setDataDokumentu(String dataDokumentu) {
        this.dataDokumentu.set(dataDokumentu);
    }

    public CertyfikatJakosci getCertyfikatJakosci() {
        return certyfikatJakosci;
    }

    public void setCertyfikatJakosci(CertyfikatJakosci certyfikatJakosci) {
        this.certyfikatJakosci = certyfikatJakosci;
    }

    @Override
    public String toString() {
        return
                getNumerDokumentu() + "    " + certyfikatJakosci.getNaszaNazwa()+" {data: "+getDataDokumentu()+ "}-{nr certyfikatu: " +
                        certyfikatJakosci.getNumerCertyfikatu()+"}-{popiół: "+
                        certyfikatJakosci.getZawartoscPopiolu()+"%}-{siarka: "+certyfikatJakosci.getZawartoscSiarkiCalkowitej()+"%}-{cz.lotne: "+
                        certyfikatJakosci.getZawartoscCzesciLotnych()+"%}-{wart.opał.: "+certyfikatJakosci.getWartoscOpalowa()+"MJ/kg}-{RI: "+
                        certyfikatJakosci.getZdolnoscSpiekania()+"}-{wym.ziar.: "+certyfikatJakosci.getMinimalnyWymiarZiarna()+"-"+
                        certyfikatJakosci.getMaksymalnyWymiarZiarna()+"mm}-{podziarno: "+certyfikatJakosci.getZawartoscPodziarna()+"%}-{nadziarno: "+
                        certyfikatJakosci.getZawartoscNadziarna()+"%}-{wilgotność: "+certyfikatJakosci.getZawartoscWilgociCalkowitej()+"%}";
    }
}
