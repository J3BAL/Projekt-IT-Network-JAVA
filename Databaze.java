import java.util.ArrayList;

/**
 * Třída vyjadřuje databázi pojištěnců.
 *
 * @author Jakub Valenta
 **/
public class Databaze {

    // Seznam pojištěných osob
    private final ArrayList<Pojistenec> pojistenci = new ArrayList<>();

    /**
     * Metoda přidání nového pojištěnce.
     *
     */
    public void vlozPojistence(String jmeno, String prijmeni, int vek, int telefonniCislo) {
        pojistenci.add(new Pojistenec(jmeno, prijmeni, vek, telefonniCislo));
    }

    /**
     * Metoda výpisu vložených pojištěnců
     *
     * @return Veškeré záznamy pojištěnců
     */
    protected ArrayList<Pojistenec> vypisVlozenychPojistencu() {
        return new ArrayList<>(pojistenci);
    }
}