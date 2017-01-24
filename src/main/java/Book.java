import java.util.Arrays;

/**
 * Created by phonik on 2017-01-04.
 */
public class Book {
    private String[] autor;
    private String tytulOrg;
    private String tytulPl;
    private String jezykOrg;
    private String[] tlumacz;
    private String kategoria;
    private String[] gatunek;
    private String[] forma;
    private int rokWydPierwsze;
    private int rokWydPl;
    private String alternVer;
    private String[] dodatkoweInfo;
    private Double ocena;
    private int glosy;
    private String id;

    @Override
    public String toString() {
        return "Book{" +
                "autor=" + Arrays.toString(autor) +
                ", tytulOrg='" + tytulOrg + '\'' +
                ", tytulPl='" + tytulPl + '\'' +
                ", jezykOrg='" + jezykOrg + '\'' +
                ", tlumacz=" + Arrays.toString(tlumacz) +
                ", kategoria='" + kategoria + '\'' +
                ", gatunek=" + Arrays.toString(gatunek) +
                ", forma=" + Arrays.toString(forma) +
                ", rokWydPierwsze=" + rokWydPierwsze +
                ", rokWydPl=" + rokWydPl +
                ", alternVer='" + alternVer + '\'' +
                ", dodatkoweInfo=" + Arrays.toString(dodatkoweInfo) +
                ", ocena=" + ocena +
                ", glosy=" + glosy +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTytulPl() {
        return tytulPl;
    }

    public void setTytulPl(String tytulPl) {
        this.tytulPl = tytulPl;
    }

    public String[] getAutor() {
        return autor;
    }

    public void setAutor(String[] autor) {
        this.autor = autor;
    }

    public String getTytulOrg() {
        return tytulOrg;
    }

    public void setTytulOrg(String tytulOrg) {
        this.tytulOrg = tytulOrg;
    }

    public String getJezykOrg() {
        return jezykOrg;
    }

    public void setJezykOrg(String jezykOrg) {
        this.jezykOrg = jezykOrg;
    }

    public String[] getTlumacz() {
        return tlumacz;
    }

    public void setTlumacz(String[] tlumacz) {
        this.tlumacz = tlumacz;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String[] getGatunek() {
        return gatunek;
    }

    public void setGatunek(String[] gatunek) {
        this.gatunek = gatunek;
    }

    public String[] getForma() {
        return forma;
    }

    public void setForma(String[] forma) {
        this.forma = forma;
    }

    public int getRokWydPierwsze() {
        return rokWydPierwsze;
    }

    public void setRokWydPierwsze(int rokWydPierwsze) {
        this.rokWydPierwsze = rokWydPierwsze;
    }

    public int getRokWydPl() {
        return rokWydPl;
    }

    public void setRokWydPl(int rokWydPl) {
        this.rokWydPl = rokWydPl;
    }

    public String getAlternVer() {
        return alternVer;
    }

    public void setAlternVer(String alternVer) {
        this.alternVer = alternVer;
    }

    public String[] getDodatkoweInfo() {
        return dodatkoweInfo;
    }

    public void setDodatkoweInfo(String[] dodatkoweInfo) {
        this.dodatkoweInfo = dodatkoweInfo;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public int getGlosy() {
        return glosy;
    }

    public void setGlosy(int glosy) {
        this.glosy = glosy;
    }
}
