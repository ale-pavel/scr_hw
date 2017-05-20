package scr.main;

import java.lang.reflect.Array;
import java.util.Arrays;

import scr.object.*;
import scr.utility.*;

public class HomeworkMain {

	public static void main(String[] args) {
		CaricatoreSequenza cs = new CaricatoreSequenza(1000000);
		SignalProcessing sp = new SignalProcessing();	

		int[] livelli_snr = {-13,-8,-5,2};
		double[] valori_soglie = new double[4];

		for(int soglia=0;soglia<valori_soglie.length;soglia++) {
			//Genero 1000 sequenze di rumore da 1000 campioni ciascuna con SNR=-13
			Noise[] sequenzeRumore = sp.generaSequenzeRumorose(1000, 1000, livelli_snr[soglia]);
			double[] potenzeSequenze = new double[sequenzeRumore.length];		
			//Popolo l'array delle potenze con quella di ogni sequenza di rumore
			for(int i=0; i<sequenzeRumore.length; i++) {
				potenzeSequenze[i] = sequenzeRumore[i].potenza();
			}
			//Ordino le potenze calcolate in ordine crescente
			Arrays.sort(potenzeSequenze);
			valori_soglie[soglia] = potenzeSequenze[989];	//Array da 0 a 999, solo 10 devono essere sopra soglia, 990..999
			System.out.println("Soglia="+valori_soglie[soglia]);
		}

		for(int seq=1;seq<=3;seq++) {
			for(int snr=0;snr<livelli_snr.length;snr++) {
				//Carico il file contenente parte reale e imm. dentro un Signal
				Signal s = cs.parseRighe(cs.leggiSequenza("Sequenze/seq"+seq+"/output_"+(snr+1)+".dat"));

				//Devo dividere il segnale in 100 o 1000 blocchi da 10.000 o 1000 campioni rispettivamente
				Signal[] s_chunks = sp.separaSegnaleInBlocchi(s, 1000);
				int percentualeSopraSoglia = 0;
				for(int i=0; i<s_chunks.length;i++) {
					double pot = s_chunks[i].potenza();
					if(pot > valori_soglie[snr])
						percentualeSopraSoglia++;
				}
				System.out.print("Seq"+seq+"."+(snr+1)+"="+percentualeSopraSoglia/10 + "% ");
			}
			System.out.println();
		}
	}

}
