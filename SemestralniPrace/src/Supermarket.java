import java.util.Arrays;

/**
 * 
 */

/**
 * Trida reprezentujici supermarket
 * @author jandrlik
 * @author kmotycko
 */
public class Supermarket {
	/** Atribut - ID supermarketu (identifikator) */
	private int ID;
	/** Atribut - matice poptavky (po vsech druzich zbozi ve vsechny dny) */
	private int[][] poptavka;
	/** Atribut - pole skladu (na zacatku simulace obsahuje hodnoty pocatecnich zasob vsech druhu zbozi) */
	private int[] sklad;
	/** Atribut - hodnota kolik ks druhu vyrobku potrebuje supermarket za mesic koupit (celk popt - poc zasoby) */
	private int[] potrebujeKoupitObdobi;
	
	/**
	 * Konstruktor vytvari instance tridy supermarket
	 * @param iD ID supermarket
	 * @param poptavka matice poptavek
	 * @param sklad pole ks druhu zbozi na sklade
	 */
	public Supermarket(int iD, int[][] poptavka, int[] sklad) {
		if (iD != 0 && poptavka != null && sklad != null) {
			ID = iD;
			this.poptavka = poptavka;
			this.sklad = sklad;
			this.potrebujeKoupitObdobi = potrebujeKoupitM();
		}	
	}
	
	/**
	 * Metoda vypocte a vrati pole hodnot ks, ktere celkem potrebuje supermarket koupit (z tovaren)
	 * @return pole hodnot
	 */
	private int[] potrebujeKoupitM() {
		int pocetZ = poptavka[0].length;
		int[] pole = new int[pocetZ];
		
		for (int i = 0; i < pole.length; i++) { //pocetZ - sloupce
			for (int j = 0; j < poptavka.length; j++) { //pocetT - radky
				pole[i] += poptavka[j][i];
			}
			pole[i] -= sklad[i];
		}
		return pole;
	}
	
	/**
	 * Getr atributu ID supermarketu
	 * @return ID supermarketu
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Getr vrati hodnotu atributu poptavky po druhu zbozi v den 	
	 * @param den Den
	 * @param druhZbozi Druh zbozi
	 * @return hodnota poptavky po druhu zbozi v den
	 */
	public int getPoptavka(int den, int druhZbozi) {
		return poptavka[den][druhZbozi];
	}
	
	/**
	 * Setr snizi hodnotu poptavky po druhu zbozi v den 
	 * @param den Den
	 * @param druhZbozi Druh zbozi
	 * @param zmena Hodnota o kterou se snizi poptavka
	 */
	public void setSnizeniPoptavky(int den, int druhZbozi, int zmena) {
		poptavka[den][druhZbozi] -= zmena;
	}
	
	/**
	 * Getr vrati hodnotu skladovych zasob druhu zbozi
	 * @param druhZbozi Druh Zbozi
	 * @return hodnota skladovych zasob druhu zbozi
	 */
	public int getSkladoveZasoby(int druhZbozi) {
		return sklad[druhZbozi];
	}
	
	/**
	 * Setr snizi hodnotu skladovych zasob druhu zbozi 
	 * @param druhZbozi Druh zbozi
	 * @param zmena Hodnota o kterou se snizi skladove zasoby
	 */
	public void setSnizeniSkladZasob(int druhZbozi, int zmena) {
		sklad[druhZbozi] -= zmena;
	}

	/**
	 * Getr vrati hodnotu mnozstvi ks ktere potrebuje supermarket koupit od D za cele obdobi
	 * @param druhZbozi Druh zbozi
	 * @return Hodnota mnozstvi ks ktere potrebuje supermarket koupit od D za cele obdobi
	 */
	public int getPotrebujeKoupitZaObdobi(int druhZbozi) {
		return potrebujeKoupitObdobi[druhZbozi];
	}
	
	/**
	 * Setr snizi hodnotu kolik ks druhu zbozi potrebuje supermarket koupit od tovaren za cele obdobi
	 * @param druhZbozi Druh zbozi
	 * @param zmena Hodnota o kterou se snizi kolik sup potrebuje koupit za obdobi
	 */
	public void setSnizeniPotrebujeKoupitZaObdobi(int druhZbozi, int zmena) {
		potrebujeKoupitObdobi[druhZbozi] -= zmena;
	}
	
	/**
	 * Metoda vypise skladove zasoby druhu zbozi jako retezec
	 * @return Retezec ve kterem jsou vypsany skladove zasoby
	 */
	public String skladToString() {
		return (Arrays.toString(sklad));
	}

	/** Metoda vypise atributy instance tridy Supermarket */
	@Override
	public String toString() {
		return "Supermarket [ID=S" + ID + ", poptavka=" + Arrays.deepToString(poptavka) + ", sklad=" + Arrays.toString(sklad) + "]";
	}
	
}
