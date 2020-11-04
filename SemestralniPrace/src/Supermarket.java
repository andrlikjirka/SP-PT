import java.util.Arrays;

/**
 * 
 */

/**
 * Trida reprezentujici supermarket
 * @author jandrlik
 */
public class Supermarket {
	/** Atribut - ID supermarketu (identifikator) */
	private int ID;
	/** Atribut - matice poptavky (po vsech druzich zbozi ve vsechny dny) */
	public int[][] poptavka;
	/** Atribut - pole skladu (na zacatku simulace obsahuje hodnoty pocatecnich zasob vsech druhu zbozi) */
	public int[] sklad;
	/** Atribut - hodnota kolik ks druhu vyrobku potrebuje supermarket za mesic koupit (celk popt - poc zasoby) */
	public int[] potrebujeKoupitMesic;
	
	/**
	 * Konstruktor vytvari instance tridy supermarket
	 * @param iD ID supermarket
	 * @param poptavka matice poptavek
	 * @param sklad pole ks druhu zbozi na sklade
	 */
	public Supermarket(int iD, int[][] poptavka, int[] sklad) {
		ID = iD;
		this.poptavka = poptavka;
		this.sklad = sklad;
		this.potrebujeKoupitMesic = potrebujeKoupitM();
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
	 * Getr atributu poptavky	
	 * @param den Den
	 * @param druhZbozi Druh zbozi
	 * @return hodnota poptavky po druhu zbozi v den
	 */
	public int getPoptavka(int den, int druhZbozi) {
		return poptavka[den][druhZbozi];
	}
	
	/** Metoda vypise atributy instance tridy Supermarket */
	@Override
	public String toString() {
		return "Supermarket [ID=S" + ID + ", poptavka=" + Arrays.deepToString(poptavka) + ", sklad=" + Arrays.toString(sklad) + "]";
	}
	
}
