import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 
 */

/**
 * Trida zajistujici generovani vstupnich datasetu
 * @author jandrlik
 * @author kmotycko
 */
public class Generator {
	/**	Pocet tovaren */
	private int pocetD = 0;
	/**	Pocet supermarketu */
	private int pocetS = 0;
	/**	Pocet druhu zbozi */
	private int pocetZ = 0;
	/**	Pocet dnu v obdobi simulace */
	private int pocetT = 0;
	
	/**	Stredni hodnota ceny */
	private final int C_MEAN = 1;
	/**	Rozptyl hodnot cen s nejvetsi pravdepodobnosti vygenerovani */
	private final int C_DEVIATION = 5;
	
	/**	Stredni hodnota poc. zasob na sklade */
	private final int Q_MEAN = 60;
	/**	Rozptyl hodnot poc. zas. s nej. pravd. vygenerovani */
	private final int Q_DEVIATION = 25;
	
	/**	Stredni hodnota produkce tovarny */
	private final int P_MEAN = 20;
	/**	Rozptyl hodnot produkce s nej. pravd. vygenerovani */
	private final int P_DEVIATION = 10;
	
	/**	Stredni hodnota poptavky supermarketu */
	private final int R_MEAN = 20;
	/**	Rozptyl hodnot poptavky sup. pravd. vygenerovani */
	private final int R_DEVIATION = 10;
	
	/**	Textovy retezec generovanych dat */
	private StringBuilder data;

	
	/**
	 * Konstruktor priradi hodnoty poctu D, S, Z, T zadane uzivatelem 
	 * @param pocetD Predany pocet tovarny
	 * @param pocetS Predany pocet supermarketu
	 * @param pocetZ Predany pocet druhu zbozi
	 * @param pocetT Predany pocet dnu
	 */
	public Generator(int pocetD, int pocetS, int pocetZ, int pocetT) {
		if (pocetD != 0 && pocetS != 0 && pocetZ != 0 && pocetT != 0) {
			this.pocetD = pocetD;
			this.pocetS = pocetS;
			this.pocetZ = pocetZ;
			this.pocetT = pocetT;
		}
	}
	
	/**
	 * Hlavni metoda generovani 
	 * @param nazevSouboru Nazev souboru do ktereho se ulozi vygenerovany dataset
	 */
	public void generovani(String nazevSouboru) {
		Random random = new Random();
		data = new StringBuilder();
		PrintTo vystup = new PrintTo(nazevSouboru);
		generovaniUvod();
		generovaniC(random);
		generovaniQ(random);
		generovaniP(random);
		generovaniR(random);
		vystup.zapisDoSouboru(data.toString());
	}
	
	/**
	 * Metoda vraci aktualni datum dle urciteho formatu
	 * @return datum
	 */
	private String datum() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String datum = date.format(formatter);
		return datum;
	}
	
	/**	Metoda generuje uvodni informace */
	private void generovaniUvod() {
		data.append("# Data pro semestralni praci KIV/PT 2020/2021");
		data.append("\n# Vytvoreno systemem jandrlik & kmotycko, ");
		data.append(datum());
		data.append("."); 
		data.append("\n#");
		data.append("\n# prazdne radky nebo radky zacinajici znakem # se ingoruji");
		data.append("\n# v opacnem pripade jsou na kazde radce ciselne hodnoty oddelene mezerou");
		data.append("\n# data jsou popsana vzdy nad prislusnym blokem dat, bloky jsou oddelene");
		data.append("\n# prazdnym radkem nasledujici radkem viditelne oznacenym vyrazem BLOK:\n");
		data.append("\n# BLOK: pocet tovaren D, pocet supermarketu S, pocet druhu zbozi Z, pocet dni T\n");
		data.append(pocetD);
		data.append(" ");
		data.append(pocetS);
		data.append(" ");
		data.append(pocetZ);
		data.append(" ");
		data.append(pocetT);
		data.append("\n");
	}
	
	/**
	 * Metoda generuje ceny cest
	 *  @param random Generator (gaussian method)
	 */
	private void generovaniC(Random random) { 
		data.append("\n");
		data.append("# BLOK: Cena prevozu jednoho zbozi c_{s,d}");
		data.append("\n#");
		data.append("\n# c_{1,1} c_{2,1} ... c_{S,1}");
		data.append("\n# c_{1,2} c_{2,2} ... c_{S,2}");
		data.append("\n#    .      .    .      .");
		data.append("\n#    .      .    .      .");
		data.append("\n#    .      .    .      .");
		data.append("\n# c_{1,D} c_{2,D} ... c_{S,D}\n");
		
		for(int i = 0; i<pocetD; i++){
			for(int j = 0; j<pocetS; j++){
				int cena = 0;
				while (cena == 0) {
					double c = random.nextGaussian()*C_DEVIATION + C_MEAN;
					cena = (int) Math.abs(Math.round(c));
				} 
				data.append(Integer.toString(cena));
				if (j != pocetS-1) {
					data.append(" ");
				}
			}
			data.append("\n");
		}
	}
	
	/**
	 * Metoda generuje pocatecni zasoby skladu
	 * @param random Generator (gaussian method)
	 */
	private void generovaniQ(Random random) {
		data.append("\n");
		data.append("# BLOK: Pocatecni skladove zasoby q_{z,s}");
		data.append("\n#");
		data.append("\n# q_{1,1} q_{1,2} ... q_{1,S}");
		data.append("\n# q_{2,1} q_{2,2} ... q_{2,S}");
		data.append("\n#    .      .    .      .");
		data.append("\n#    .      .    .      .");
		data.append("\n#    .      .    .      .");
		data.append("\n# q_{Z,1} q_{Z,2} ... q_{Z,S}\n");
		
		for(int i = 0; i<pocetZ; i++){
			for(int j = 0; j<pocetS; j++){
				double q = random.nextGaussian()*Q_DEVIATION + Q_MEAN;
				int pocZas = (int) Math.abs(Math.round(q));
				data.append(Integer.toString(pocZas));
				if (j != pocetS-1) {
					data.append(" ");	
				}
			}
			data.append("\n");
		}
	}
	
	/**
	 * Metoda generuje poptavky tovaren
	 * @param random Generator (gaussian method)
	 */
	private void generovaniP(Random random) {
		data.append("\n");
		data.append("# BLOK: Produkce tovaren p_{d,z,t}");
		data.append("\n# p_{1,1,1} p_{2,1,1} .... p_{D,1,1}");
		data.append("\n# p_{1,1,2} p_{2,1,2} .... p_{D,1,2}");
		data.append("\n#      .         .    .         .");
		data.append("\n#      .         .     .        .");
		data.append("\n#      .         .      .       .");
		data.append("\n# p_{1,1,T} p_{2,1,T} .... p_{D,1,T}");
		data.append("\n# p_{1,2,1} p_{2,2,1} .... p_{D,2,1}");
		data.append("\n# p_{1,2,2} p_{2,2,2} .... p_{D,2,2}");
		data.append("\n#      .         .    .         .");
		data.append("\n#      .         .     .        .");
		data.append("\n#      .         .      .       .");
		data.append("\n#      .         .       .      .");
		data.append("\n# p_{1,Z,T} p_{2,Z,T} .... p_{D,Z,T}\n");
	
		for(int i = 0; i<pocetZ*pocetT; i++){
			for(int j = 0; j<pocetD; j++){
				double p = random.nextGaussian()*P_DEVIATION + P_MEAN;
				int produkce = (int) Math.abs(Math.round(p));
				data.append(Integer.toString(produkce));
				if (j != pocetD-1) {
					data.append(" ");
				}
			}
			data.append("\n");
		}

	}
	
	/**
	 * Metoda generuje poptavek supermarketu 
	 * @param random Generator (gaussian method)
	 */
	private void generovaniR(Random random) {
		data.append("\n");
		data.append("# BLOK: Poptavka zbozi r_{s,z,t}");
		data.append("\n# r_{1,1,1} r_{2,1,1} .... r_{S,1,1}");
		data.append("\n# r_{1,1,2} r_{2,1,2} .... r_{S,1,2}");
		data.append("\n#      .         .    .         .");
		data.append("\n#      .         .     .        .");
		data.append("\n#      .         .      .       .");
		data.append("\n# r_{1,1,T} r_{2,1,T} .... r_{S,1,T}");
		data.append("\n# r_{1,2,1} r_{2,2,1} .... r_{S,2,1}");
		data.append("\n# r_{1,2,2} r_{2,2,2} .... r_{S,2,2}");
		data.append("\n#      .         .    .         .");
		data.append("\n#      .         .     .        .");
		data.append("\n#      .         .      .       .");
		data.append("\n#      .         .       .      .");
		data.append("\n# r_{1,Z,T} r_{2,Z,T} .... r_{S,Z,T}\n");
		
		for(int i = 0; i<pocetZ*pocetT; i++){
			for(int j = 0; j<pocetS; j++){
				double r = random.nextGaussian()*R_DEVIATION + R_MEAN;
				int poptavka = (int) Math.abs(Math.round(r));
				data.append(Integer.toString(poptavka));
				if (j != pocetS-1) {
					data.append(" ");
				}
			}
			data.append("\n");
		}
	}
	
}
