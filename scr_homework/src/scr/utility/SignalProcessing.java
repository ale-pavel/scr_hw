package scr.utility;

import scr.object.*;

public class SignalProcessing {
	
	public double potenzaMediaSequenza(Signal[] sequenza) {
		double potenza = 0;
		for(int i=0;i<sequenza.length;i++) {
			potenza += sequenza[i].potenza();
		}
		return potenza/sequenza.length;
	}

	public double[] campionaSequenza(double[] campioni, int numeroCampioni) {
		double[] sequence = new double[numeroCampioni];
		int fattoreDecimazione = campioni.length/numeroCampioni;
		for(int i=0;i<numeroCampioni;i++) {
			sequence[i] = campioni[i*fattoreDecimazione];
		}
		return sequence;
	}
	
	public double[][] separaSequenzaInBlocchi(double[] campioni, int numeroBlocchi) {	//partendo da 10^6 campioni
		double[][] sequenzaSeparata = new double[numeroBlocchi][campioni.length/numeroBlocchi];	//[100][10.000]	
		for(int i=0;i<sequenzaSeparata.length;i++) {	//length = 100 = numero blocchi
			for(int j=0;j<sequenzaSeparata[i].length;j++) {	//length = 10.000 = numero campioni per blocco
				sequenzaSeparata[i][j] = campioni[j+i*sequenzaSeparata[i].length];	//j[0 a 9.999]+i[0 a 99]*10.000																					//
			}																		//per prelevare campione corretto
		}		
		return sequenzaSeparata;
	}
	
	public Signal[] separaSegnaleInBlocchi(Signal s, int numeroBlocchi) {
		Signal[] signalChunks = new Signal[numeroBlocchi];
		double[][] realiChunks = this.separaSequenzaInBlocchi(s.getReali(), numeroBlocchi);
		double[][] immChunks = this.separaSequenzaInBlocchi(s.getImmaginari(), numeroBlocchi);
		int length = realiChunks.length;
		for(int i=0;i<numeroBlocchi;i++) {
			signalChunks[i] = new Signal(length);
			signalChunks[i].setReali(realiChunks[i]);
			signalChunks[i].setImmaginari(immChunks[i]);
		}
		return signalChunks;
	}
	
	public Noise[] generaSequenzeRumorose(int numeroSequenze,int lunghezzaSequenza, double snr) {
		Noise[] sequenzeRumore = new Noise[numeroSequenze];	
		for(int i=0;i<numeroSequenze;i++) {
			sequenzeRumore[i] = new Noise(lunghezzaSequenza, snr);
		}	
		return sequenzeRumore;
	}
	
}
