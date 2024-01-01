import java.util.ArrayList;
import java.util.Scanner;

/**
 * Třída vyjadřuje evidenci pojištěnců
 *
 * @author Jakub Valenta
 **/

public class EvidencePojistencu {
    Databaze databazePojistencu = new Databaze();
    Scanner sc = new Scanner(System.in, "Windows-1250");

    /**
     * Metoda vypisující menu aplikace s možnostmi uživatele.
     */
    public void vypisMoznostiUzivatele() {
        String vybranaMoznost = "";
        while (!vybranaMoznost.equals("4")) { // Vykonávání cyklu, dokud není vybrána volba 4 - konec.
            System.out.println("""
                    -----------------------------------------
                    Evidence pojištěných
                    -----------------------------------------
                    Vyberte si akci:
                    1 - Přidat nového pojištěného
                    2 - Vypsat všechny pojištěné
                    3 - Vyhledat pojištěného
                    4 - Konec
                    """);
            vybranaMoznost = sc.nextLine();

            switch (vybranaMoznost) {
                case "1":
                    vlozPojistence();
                    break;
                case "2":
                    vypisVlozenychPojistencu();
                    break;
                case "3":
                    najdiVlozenePojistence();
                    break;
                case "4":
                    System.out.print("Aplikace Evidence pojištěných je ukončena.");
                    break;
                default:
                    System.out.print("Zadaná možnost neexistuje, prosím vyberte jinou možnost.\n");
            }
        }
    }

    /**
     * Metoda přidávající nového pojištěnce.
     */
    public void vlozPojistence() {
        String jmeno = vlozJmeno(); //za účelem zadání jména pojištěnce uživatelem
        String prijmeni = vlozPrijmeni(); //za účelem zadání příjmení pojištěnce uživatelem

        //zadání věku
        int vek;
        do {
            System.out.println("Zadejte věk: ");
            try {
                vek = Integer.parseInt(sc.nextLine()); //input zadání věku
                if (vek < 0 || vek > 120) {
                    throw new IllegalArgumentException("Osoba tohoto věku neexistuje. Zadejte prosím věk v rozmezí 0 až 120 let"); //Verifikace počtu let
                }
            } catch (NumberFormatException e) { //Verifikace inputu věku, jestli je celočíselný
                System.out.println("Byla zadána špatná hodnota. Zadejte prosím věk celým číslem.");
                vek = -1;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                vek = -1;
            }
        } while (vek < 0);

        int telefonniCislo;
        do {
            System.out.println("Zadejte telefonní číslo: ");
            try {
                telefonniCislo = Integer.parseInt(sc.nextLine()); //input zadání telefonního čísla
                if (String.valueOf(telefonniCislo).length() != 9) { //verifikace 9 čísel telefonního čísla
                    System.out.println("Telefonní číslo musí mít právě 9 číslic.");
                    telefonniCislo = -1;
                } else if (String.valueOf(telefonniCislo).startsWith("-")) { //verifikace, že telefonní číslo nezačíná záporným číslem
                    System.out.println("Telefonní číslo nesmí začínat pomlčkou.");
                    telefonniCislo = -1;
                }
            } catch (NumberFormatException e) { //Verifikace formátu tel. čísla
                System.out.println("Nesprávný formát telefonního čísla. Prosím zadejte tel. číslo pouze číslicemi bez mezer a předvolby");
                telefonniCislo = -1;
            }
        } while (telefonniCislo < 0);
        databazePojistencu.vlozPojistence(jmeno, prijmeni, vek, telefonniCislo); //Nahrání zadaného pojištěnce do databáze
        System.out.println("-----------------------------------------");
        System.out.printf("Pojištěnec %s %s byl úspěšně přidán.\n", jmeno, prijmeni);
    }

    /**
     * Metoda přidání jména a příjmení pojištěnce
     *
     * @param vyzvaUzivatele Výzva k zadání inputu jména či příjmení
     * @param chybovaHlaska Hláška o typu konkrétní chyby
     * @return text pro jméno i příjmení pojištěnce
     */
    private String vlozText(String vyzvaUzivatele, String chybovaHlaska) {
        String text;
        do {
            System.out.println(vyzvaUzivatele);
            text = sc.nextLine().trim();
            if (text.isEmpty()) {
                System.out.println(chybovaHlaska);
            } else if (text.matches(".*\\d.*")) {
                System.out.println("Zadaný text neodpovídá formátu. Prosím zadejte platný text.");
            }
        } while (text.isEmpty() || text.matches(".*\\d.*"));
        return text;
    }

    public String vlozJmeno() {
        return vlozText("Zadejte jméno: ", "Nelze zadat pojištěnce beze jména. Prosím zadejte jméno pojištěnce.");
    }

    public String vlozPrijmeni() {
        return vlozText("Zadejte příjmení: ", "Nelze zadat pojištěnce bez příjmení. Prosím zadejte příjmení pojištěnce.");
    }

    /**
     * Metoda výpisu všech pojištěnců.
     */
    public void vypisVlozenychPojistencu() {
        ArrayList<Pojistenec> pojistenci = databazePojistencu.vypisVlozenychPojistencu(); //Veškeří pojištěnci v databázi
        System.out.println("-----------------------------------------");
        System.out.println("Seznam pojištěných osob:");
        System.out.format("%-18s%-18s%-6s%-9s","Jméno","Příjmení","Věk","Telefonní číslo");
        System.out.println();
        for (Pojistenec pojistenec : pojistenci) { //výpis informací o každé pojištěné osobě
            System.out.println(pojistenec);
        }
    }

    /**
     * Metoda vyhledávající pojištěnce dle jména, příjmení
     */
    public void najdiVlozenePojistence() {
        ArrayList<Pojistenec> pojistenci = databazePojistencu.vypisVlozenychPojistencu(); //Veškeří pojištěnci v databázi
        String celeVyhledavaneJmeno = vlozJmeno() + " " + vlozPrijmeni();
        boolean vyskytujeSe = false;

        for (Pojistenec pojistenec : pojistenci) {
            String celeJmenoPojistence = pojistenec.ziskejJmeno() + " " + pojistenec.ziskejPrijmeni();

            if (celeVyhledavaneJmeno.equalsIgnoreCase(celeJmenoPojistence)) { //Porovnání zadaného celého jména vůči databázi bez ohledu na velikost písmen
                if (!vyskytujeSe) {
                    System.out.println("Byli nalezeni níže uvedení pojištěnci:");
                    System.out.format("%-18s%-18s%-6s%-9s","Jméno","Příjmení","Věk","Telefonní číslo");
                    System.out.println();
                    vyskytujeSe = true;
                }
                System.out.println(pojistenec); //Vyhledaní pojištěnci
            }
        }

        if (!vyskytujeSe) {
            System.out.println("Tato osoba v evidenci pojištěnců dosud neexistuje");
        }
    }
}