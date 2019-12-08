package model;

public enum WartościDopuszczalnePaliwa {

    WEGIEL_KAMIENNY_KOSTKA("WĘGIEL KAMIENNY - KOSTKA","-", "12", "-", "1,7", "-", "-", "22", "-", "-", "-", "63", "200", "0", "10", "0", "10", "-", "20"),
    WEGIEL_KAMIENNY_ORZECH("WĘGIEL KAMIENNY - ORZECH","-", "12", "-", "1,7", "-", "-", "22", "-", "-", "-", "25", "80", "0", "10", "0", "10", "-", "20"),
    WEGIEL_KAMIENNY_GROSZEK("WĘGIEL KAMIENNY - GROSZEK","-", "14", "-", "1,7", "-", "-", "21", "-", "-", "90", "5", "40", "0", "11", "0", "10", "-", "20"),
    WEGIEL_KAMIENNY_MIAL("WĘGIEL KAMIENNY - MIAŁ","-", "28", "-", "1,7", "-", "-", "18", "-", "-", "-", "1", "31,5", "-", "30", "0", "5", "-", "24"),
    WEGIEL_KAMIENNY_EKOGROSZEK("WĘGIEL KAMIENNY - EKOGROSZEK","-", "12", "-", "1,2", "-", "-", "24", "-", "-", "25", "5", "31,5", "0", "10", "0", "10", "-", "15"),
    WEGIEL_KAMIENNY_PELLET("WĘGIEL KAMIENNY - EKOGROSZEK","-", "12", "-", "1,2", "-", "-", "24", "-", "-", "25", "5", "31,5", "0", "10", "0", "5", "-", "15");


    private String nazwa;
    private String minPopiol;
    private String maxPopiol;
    private String minSiarka;
    private String maxSiarka;
    private String minCzLotne;
    private String maxCzLotne;
    private String minWartoscOpalowa;
    private String maxWartoscOpalowa;
    private String minSpiekalnsc;
    private String maxSpiekalnosc;
    private String minWymiarZiarna;
    private String maxWymiarZiarna;
    private String minPodziarno;
    private String maxPadziarno;
    private String minNadziarno;
    private String maxNadziarno;
    private String minWolgotnosc;
    private String maxWilgotnosc;

    WartościDopuszczalnePaliwa(String nazwa, String minPopiol, String maxPopiol, String minSiarka, String maxSiarka, String minCzLotne, String maxCzLotne, String minWartoscOpalowa, String maxWartoscOpalowa, String minSpiekalnsc, String maxSpiekalnosc, String minWymiarZiarna, String maxWymiarZiarna, String minPodziarno, String maxPadziarno, String minNadziarno, String maxNadziarno, String minWolgotnosc, String maxWilgotnosc) {
        this.nazwa = nazwa;
        this.minPopiol = minPopiol;
        this.maxPopiol = maxPopiol;
        this.minSiarka = minSiarka;
        this.maxSiarka = maxSiarka;
        this.minCzLotne = minCzLotne;
        this.maxCzLotne = maxCzLotne;
        this.minWartoscOpalowa = minWartoscOpalowa;
        this.maxWartoscOpalowa = maxWartoscOpalowa;
        this.minSpiekalnsc = minSpiekalnsc;
        this.maxSpiekalnosc = maxSpiekalnosc;
        this.minWymiarZiarna = minWymiarZiarna;
        this.maxWymiarZiarna = maxWymiarZiarna;
        this.minPodziarno = minPodziarno;
        this.maxPadziarno = maxPadziarno;
        this.minNadziarno = minNadziarno;
        this.maxNadziarno = maxNadziarno;
        this.minWolgotnosc = minWolgotnosc;
        this.maxWilgotnosc = maxWilgotnosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getMinPopiol() {
        return minPopiol;
    }

    public void setMinPopiol(String minPopiol) {
        this.minPopiol = minPopiol;
    }

    public String getMaxPopiol() {
        return maxPopiol;
    }

    public void setMaxPopiol(String maxPopiol) {
        this.maxPopiol = maxPopiol;
    }

    public String getMinSiarka() {
        return minSiarka;
    }

    public void setMinSiarka(String minSiarka) {
        this.minSiarka = minSiarka;
    }

    public String getMaxSiarka() {
        return maxSiarka;
    }

    public void setMaxSiarka(String maxSiarka) {
        this.maxSiarka = maxSiarka;
    }

    public String getMinCzLotne() {
        return minCzLotne;
    }

    public void setMinCzLotne(String minCzLotne) {
        this.minCzLotne = minCzLotne;
    }

    public String getMaxCzLotne() {
        return maxCzLotne;
    }

    public void setMaxCzLotne(String maxCzLotne) {
        this.maxCzLotne = maxCzLotne;
    }

    public String getMinWartoscOpalowa() {
        return minWartoscOpalowa;
    }

    public void setMinWartoscOpalowa(String minWartoscOpalowa) {
        this.minWartoscOpalowa = minWartoscOpalowa;
    }

    public String getMaxWartoscOpalowa() {
        return maxWartoscOpalowa;
    }

    public void setMaxWartoscOpalowa(String maxWartoscOpalowa) {
        this.maxWartoscOpalowa = maxWartoscOpalowa;
    }

    public String getMinSpiekalnsc() {
        return minSpiekalnsc;
    }

    public void setMinSpiekalnsc(String minSpiekalnsc) {
        this.minSpiekalnsc = minSpiekalnsc;
    }

    public String getMaxSpiekalnosc() {
        return maxSpiekalnosc;
    }

    public void setMaxSpiekalnosc(String maxSpiekalnosc) {
        this.maxSpiekalnosc = maxSpiekalnosc;
    }

    public String getMinWymiarZiarna() {
        return minWymiarZiarna;
    }

    public void setMinWymiarZiarna(String minWymiarZiarna) {
        this.minWymiarZiarna = minWymiarZiarna;
    }

    public String getMaxWymiarZiarna() {
        return maxWymiarZiarna;
    }

    public void setMaxWymiarZiarna(String maxWymiarZiarna) {
        this.maxWymiarZiarna = maxWymiarZiarna;
    }

    public String getMinPodziarno() {
        return minPodziarno;
    }

    public void setMinPodziarno(String minPodziarno) {
        this.minPodziarno = minPodziarno;
    }

    public String getMaxPodziarno() {
        return maxPadziarno;
    }

    public void setMaxPadziarno(String maxPadziarno) {
        this.maxPadziarno = maxPadziarno;
    }

    public String getMinNadziarno() {
        return minNadziarno;
    }

    public void setMinNadziarno(String minNadziarno) {
        this.minNadziarno = minNadziarno;
    }

    public String getMaxNadziarno() {
        return maxNadziarno;
    }

    public void setMaxNadziarno(String maxNadziarno) {
        this.maxNadziarno = maxNadziarno;
    }

    public String getMinWilgotnosc() {
        return minWolgotnosc;
    }

    public void setMinWolgotnosc(String minWolgotnosc) {
        this.minWolgotnosc = minWolgotnosc;
    }

    public String getMaxWilgotnosc() {
        return maxWilgotnosc;
    }

    public void setMaxWilgotnosc(String maxWilgotnosc) {
        this.maxWilgotnosc = maxWilgotnosc;
    }
}



