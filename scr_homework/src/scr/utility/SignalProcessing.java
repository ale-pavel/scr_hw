package scr.utility;

import scr.object.*;

public class SignalProcessing {
	
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
	
	public Noise[] generaSequenzeRumorose(int numeroSequenze,int lunghezzaSequenza, double snr) {
		Noise[] sequenzeRumore = new Noise[numeroSequenze];	
		for(int i=0;i<numeroSequenze;i++) {
			sequenzeRumore[i] = new Noise(lunghezzaSequenza, snr);
		}	
		return sequenzeRumore;
	}
	
}
