
/**
 * Třída vyjadřuje pojištěnce
 *
 * @author Jakub Valenta
 **/
public class Pojistenec {
    private String jmeno;
    private String prijmeni;
    private int vek;
    private int telefonniCislo;


    /**
     * Konstruktor pojištěnce
     *
     * @param jmeno           Křestní jméno konkrétního pojištěnce
     * @param prijmeni        Příjmení konkrétního pojištěnce
     * @param vek             Věk v letech konkrétního pojištěnce
     * @param telefonniCislo  Telefonní číslo konkrétního pojištěnce ve formátu ČČČČČČČČČ
     */
    public Pojistenec(String jmeno, String prijmeni, int vek, int telefonniCislo) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
        this.telefonniCislo = telefonniCislo;
    }

    // Gettery a Settery pro všechny atributy (včetně přípravy dosud nevyužitých pro budoucí verze)

    public String ziskejJmeno() {
        return jmeno;
    }

    public void nastavJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String ziskejPrijmeni() {
        return prijmeni;
    }

    public void nastavPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public int ziskejVek() {
        return vek;
    }

    public void nastavVek(int vek) {
        this.vek = vek;
    }

    public int ziskejTelefonniCislo() {
        return telefonniCislo;
    }

    public void nastavTelefonniCislo(int telefonniCislo) {
        this.telefonniCislo = telefonniCislo;
    }


    // Metoda vypisující konkrétního pojištěnce
    @Override
    public String toString() {
        return String.format("%-18s%-18s%-6s%-9s", jmeno, prijmeni, vek, telefonniCislo);
    }
}