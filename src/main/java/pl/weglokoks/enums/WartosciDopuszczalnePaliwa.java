package pl.weglokoks.enums;

public enum WartosciDopuszczalnePaliwa {

    WEGIEL_KAMIENNY_KOSTKA("WĘGIEL KAMIENNY - KOSTKA", "-", "12", "-", "1,7", "-", "-", "22", "-", "-", "-", "63", "200", "0", "10", "0", "10", "-", "20"),
    WEGIEL_KAMIENNY_ORZECH("WĘGIEL KAMIENNY - ORZECH", "-", "12", "-", "1,7", "-", "-", "22", "-", "-", "-", "25", "80", "0", "10", "0", "10", "-", "20"),
    WEGIEL_KAMIENNY_GROSZEK("WĘGIEL KAMIENNY - GROSZEK", "-", "14", "-", "1,7", "-", "-", "21", "-", "-", "90", "5", "40", "0", "10", "0", "10", "-", "20"),
    WEGIEL_KAMIENNY_MIAL("WĘGIEL KAMIENNY - MIAŁ", "-", "28", "-", "1,7", "-", "-", "18", "-", "-", "-", "1", "31,5", "-", "30", "0", "5", "-", "24"),
    WEGIEL_KAMIENNY_EKOMIAL("WĘGIEL KAMIENNY - EKOMIAŁ", "-", "28", "-", "1,7", "-", "-", "18", "-", "-", "-", "3", "31,5", "-", "15", "0", "5", "-", "20"),
    WEGIEL_KAMIENNY_EKOGROSZEK("WĘGIEL KAMIENNY - EKOGROSZEK", "-", "12", "-", "1,2", "-", "-", "24", "-", "-", "25", "5", "31,5", "0", "10", "0", "5", "-", "15"),
    WEGIEL_KAMIENNY_PELLET("WĘGIEL KAMIENNY - PELLET", "-", "12", "-", "1,2", "-", "-", "24", "-", "-", "25", "5", "31,5", "0", "10", "0", "5", "-", "15");

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

    WartosciDopuszczalnePaliwa(String nazwa, String minPopiol, String maxPopiol, String minSiarka, String maxSiarka, String minCzLotne, String maxCzLotne, String minWartoscOpalowa, String maxWartoscOpalowa, String minSpiekalnsc, String maxSpiekalnosc, String minWymiarZiarna, String maxWymiarZiarna, String minPodziarno, String maxPadziarno, String minNadziarno, String maxNadziarno, String minWolgotnosc, String maxWilgotnosc) {
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

    public String getMinPopiol() {
        return minPopiol;
    }

    public String getMaxPopiol() {
        return maxPopiol;
    }

    public String getMinSiarka() {
        return minSiarka;
    }

    public String getMaxSiarka() {
        return maxSiarka;
    }

    public String getMinCzLotne() {
        return minCzLotne;
    }

    public String getMaxCzLotne() {
        return maxCzLotne;
    }

    public String getMinWartoscOpalowa() {
        return minWartoscOpalowa;
    }

    public String getMaxWartoscOpalowa() {
        return maxWartoscOpalowa;
    }

    public String getMinSpiekalnsc() {
        return minSpiekalnsc;
    }

    public String getMaxSpiekalnosc() {
        return maxSpiekalnosc;
    }

    public String getMinWymiarZiarna() {
        return minWymiarZiarna;
    }

    public String getMaxWymiarZiarna() {
        return maxWymiarZiarna;
    }

    public String getMinPodziarno() {
        return minPodziarno;
    }

    public String getMaxPodziarno() {
        return maxPadziarno;
    }

    public String getMinNadziarno() {
        return minNadziarno;
    }

    public String getMaxNadziarno() {
        return maxNadziarno;
    }

    public String getMinWilgotnosc() {
        return minWolgotnosc;
    }

    public String getMaxWilgotnosc() {
        return maxWilgotnosc;
    }
}



