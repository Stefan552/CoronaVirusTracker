package io.javabrains.coronavirustracker.models;

public class StatisticeTari {
    private String judet;
    private String tara;
    private int latestTotalCazuri;
    private int difFromPrevDay;

    public int getDifFromPrevDay() {
        return difFromPrevDay;
    }

    public void setDifFromPrevDay(int difFromPrevDay) {
        this.difFromPrevDay = difFromPrevDay;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public int getLatestTotalCazuri() {
        return latestTotalCazuri;
    }

    public void setLatestTotalCazuri(int latestTotalCazuri) {
        this.latestTotalCazuri = latestTotalCazuri;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "judet='" + judet + '\'' +
                ", tara='" + tara + '\'' +
                ", latestTotalCazuri=" + latestTotalCazuri +
                '}';
    }
}
