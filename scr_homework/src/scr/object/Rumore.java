package scr.object;

import java.util.Random;

public class Rumore extends Segnale{
	private double snr;

	public Rumore(int lunghezzaSequenza, double snr) {
		super(lunghezzaSequenza);
		this.snr = snr;
		this.setReali(this.generaUnaSequenzaRumorosa(this.getLength(), snr));
		this.setImmaginari(this.generaUnaSequenzaRumorosa(this.getLength(), snr));
	}
	
	public Rumore(int lunghezzaSequenza) {
		super(lunghezzaSequenza);
	}
	
	public double getSnr() {
		return this.snr;
	}

	private double[] generaUnaSequenzaRumorosa(int length, double snr) {
		double[] sequenza = new double[length];
		Random r = new Random();
		double snr_lineare = Math.pow(10, snr/10);
		double pot_n = 1/snr_lineare;	
		for(int i=0;i<length;i++) {
			sequenza[i] = r.nextGaussian()*Math.sqrt(pot_n/2);
		}		
		return sequenza;
	}
	
	
}
