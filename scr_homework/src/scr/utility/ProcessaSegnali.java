package scr.utility;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeMap;
import scr.object.*;

public class ProcessaSegnali {

	public Rumore[] generaSequenzeRumorose(int numeroSequenze,int lunghezzaSequenza,double snr) {
		Rumore[] sequenzeRumore = new Rumore[numeroSequenze];	
		for(int i=0;i<numeroSequenze;i++) {
			sequenzeRumore[i] = new Rumore(lunghezzaSequenza, snr);	//Rumore si occupa di creare la sequenza casuale con snr indicato
		}	
		return sequenzeRumore;
	}
	public double[] calcoloSoglie(double[] livelli_snr, int numeroSeq, int numeroSample, double Pfa) {
		double[] valori_soglie = new double[livelli_snr.length];
		for(int snr=0;snr<livelli_snr.length;snr++) {
			//Genero 1000 sequenze di rumore da 1000 campioni ciascuna con SNR=-13
			Rumore[] sequenzeRumore = generaSequenzeRumorose(numeroSeq, numeroSample, livelli_snr[snr]);
			double[] potenzeSequenze = new double[sequenzeRumore.length];		
			//Popolo l'array delle potenze con quelle di ogni sequenza di rumore
			for(int i=0; i<sequenzeRumore.length; i++) {
				potenzeSequenze[i] = sequenzeRumore[i].potenza();
			}
			//Ordino le potenze calcolate in ordine crescente
			Arrays.sort(potenzeSequenze);
			//Array da 0 a 999, solo 10 devono essere sopra soglia, 990..999 -> [989]
			//soglia = potenzeSequenze[989] cambiato per permettere la variazione delle costanti nel main senza avere accoppiamento
			valori_soglie[snr] = potenzeSequenze[(numeroSeq-1)-(int)(numeroSeq*Pfa)];	
		}
		return valori_soglie;
	}

	//Si potrebbe restituire un booleano quando la percentuale è sopra il 70%
	public double calcolaProbabilitaDetection(Segnale[] s_chunks, double soglia) {
		double contaSopraSoglia = 0;	//Conto quante volte la potenza di segnale > soglia (No white space)
		for(int i=0; i<s_chunks.length;i++) {
			double pot = s_chunks[i].potenza();	//Calcolo potenza di ogni singola sequenza dei blocchi
			if(pot > soglia)	//Se la potenza del singolo blocco (o Segnale) è sopra la soglia ho segnale utile
				contaSopraSoglia++;
		}
		return (contaSopraSoglia/s_chunks.length)*100;
	}

	public double potenzaMediaSequenza(Segnale[] sequenza) {
		double potenza = 0;
		for(int i=0;i<sequenza.length;i++) {
			potenza += sequenza[i].potenza();
		}
		return potenza/sequenza.length;
	}

	//Non utilizzato
	public double[] campionaSequenza(double[] campioni, int numeroCampioni) {
		double[] sequence = new double[numeroCampioni];
		int fattoreDecimazione = campioni.length/numeroCampioni;
		for(int i=0;i<numeroCampioni;i++) {
			sequence[i] = campioni[i*fattoreDecimazione];	//Prendo un campione ogni numeroCampioni (0,1000,2000...)
		}
		return sequence;
	}

	public boolean presenzaUtentePrimario(TreeMap<Double,Double> snr2pd, double percentualeMinima) {
		boolean b = true;
		//Ottengo la lista delle Percenduali Detection ordinate per SNR crescente
		LinkedList<Double> listaPd = new LinkedList<>(snr2pd.values());
		Double primoPd = listaPd.removeFirst();
		while(listaPd.size()>0 && b) {
			Double temp = listaPd.removeFirst();
			if(temp < primoPd || temp < percentualeMinima)
				b = false;
			primoPd = temp;
		}
		return b;
	}

}
