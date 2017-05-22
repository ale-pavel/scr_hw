package scr.main;

import scr.object.*;
import scr.utility.*;

public class HwMain {
	public static final int NUMERO_CAMPIONI = 1000000;
	public static final int NUMERO_SEQUENZE = 3;
	public static final int NUMERO_OSSERVAZIONE = 4;
	public static final int NUMERO_BLOCCHI = 1000;
	public static final double PROB_FALSO_ALLARME = 0.01;
	public static final int NUMERO_SEQ_RUMORE = 1000;
	public static final int NUMERO_SAMPLE_RUMORE = 1000;
	public static final double[] LIVELLI_SNR = {-3,2,-8,-13};

	public static void main(String[] args) {
		CaricatoreSequenza cs = new CaricatoreSequenza(NUMERO_CAMPIONI);
		ProcessaSegnali ps = new ProcessaSegnali();	

		//Calcolo delle soglie generando 1000 sequenze di rumore per ogni SNR		
		double[] valori_soglie = ps.calcoloSoglie(LIVELLI_SNR);

		for(int i=0;i<valori_soglie.length;i++) {
			System.out.println("SNR = "+LIVELLI_SNR[i]+" Soglia = "+valori_soglie[i]);
		}

		for(int seq=1;seq<=NUMERO_SEQUENZE;seq++) {	//Itero per controllare le 3 sequenze
			System.out.print("Sequenza "+seq+" [ ");
			for(int soglia=0;soglia<NUMERO_OSSERVAZIONE;soglia++) {	//Itero per leggere le 4 osservazioni di ogni sequenza e confrontarle con le 4 soglie
				//Carico il file contenente parte reale e imm. dentro un Segnale e separato da un tab
				Segnale s = cs.parseRighe(cs.leggiSequenza("Sequenze/seq"+seq+"/output_"+(soglia+1)+".dat"),"\t");

				//Devo dividere il Segnale in 100 o 1000 blocchi da 10.000 o 1000 campioni rispettivamente
				Segnale[] s_chunks = s.separaSegnaleInBlocchi(NUMERO_BLOCCHI);

				//Probabilità di detection di segnale utile immerso in rumore gaussiano
				double probabilitaDetection = ps.calcolaProbabilitaDetection(s_chunks, valori_soglie[soglia]);
				System.out.print("Osservazione "+(soglia+1)+": Pd="+probabilitaDetection + "% | ");
			}
			System.out.println(" ]");
		}
	}

}
