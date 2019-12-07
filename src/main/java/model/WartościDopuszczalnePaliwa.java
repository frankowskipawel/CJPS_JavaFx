package model;

public enum WartościDopuszczalnePaliwa {
    WEGIEL_KAMIENNY_KOSTKA("-", "12", "-", "1,7", "-", "-", "22", "-", "-", "-", "63", "200", "0", "10", "0", "10", "-", "20"),
    WEGIEL_KAMIENNY_ORZECH("-", "12", "-", "1,7", "-", "-", "22", "-", "-", "-", "25", "80", "0", "10", "0", "10", "-", "20");


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

    WartościDopuszczalnePaliwa(String minPopiol, String maxPopiol, String minSiarka, String maxSiarka, String minCzLotne, String maxCzLotne, String minWartoscOpalowa, String maxWartoscOpalowa, String minSpiekalnsc, String maxSpiekalnosc, String minWymiarZiarna, String maxWymiarZiarna, String minPodziarno, String maxPadziarno, String minNadziarno, String maxNadziarno, String minWolgotnosc, String maxWilgotnosc) {
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

    public String getMinPopiol() {
        return minPopiol;
    }

    public void setMinPopiol(String minPopiol) {
        this.minPopiol = minPopiol;
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

    public String getMaxPadziarno() {
        return maxPadziarno;
    }

    public String getMinNadziarno() {
        return minNadziarno;
    }

    public String getMaxNadziarno() {
        return maxNadziarno;
    }

    public String getMinWolgotnosc() {
        return minWolgotnosc;
    }

    public String getMaxWilgotnosc() {
        return maxWilgotnosc;
    }
}



