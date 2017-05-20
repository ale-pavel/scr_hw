package scr.utility;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;
import scr.object.*;

public class SignalProcessingTest {

	@Test
	public void test() {
		CaricatoreSequenza cs = new CaricatoreSequenza(1000000);
		Signal s = cs.parseRighe(cs.leggiSequenza("Sequenze/seq1/output_1.dat"));
		SignalProcessing sp = new SignalProcessing();
		
		double[] reali_1000 = sp.campionaSequenza(s.getReali(), 10000);
		double[] imm_1000 = sp.campionaSequenza(s.getImmaginari(), 10000);
		assertEquals(10000, reali_1000.length);
		
		Signal s_1000 = new Signal(reali_1000.length,reali_1000,imm_1000);
		System.out.println("power_100 = " + s_1000.potenza());
		System.out.println("power = " + s.potenza());
	}
	
	@Test
	public void testPotenzaSequenzaRumorosa() {
		int num_sequenze = 1000;
		int num_campioni = 1000;
		SignalProcessing sp = new SignalProcessing();
		Noise[] seq1 = sp.generaSequenzeRumorose(num_sequenze, num_campioni, -13);
		Noise[] seq2 = sp.generaSequenzeRumorose(num_sequenze, num_campioni, -8);
		Noise[] seq3 = sp.generaSequenzeRumorose(num_sequenze, num_campioni, -5);
		Noise[] seq4 = sp.generaSequenzeRumorose(num_sequenze, num_campioni, 2);
		//Valori arrotondati alla seconda cifra decimale calcolati con formula SNR linearizzato
		System.out.println("Pn:19.95 = " + sp.potenzaMediaSequenza(seq1));
		System.out.println("Pn:6.31 = " + sp.potenzaMediaSequenza(seq2));
		System.out.println("Pn:3.16 = " + sp.potenzaMediaSequenza(seq3));
		System.out.println("Pn:0.63 = " + sp.potenzaMediaSequenza(seq4));
	}
	
	@Test
	public void testVarianzaGaussianaAWGN() {
		Random r = new Random();
		int length = 1000;
		double sigma = 0;
		for(int i=0;i<length;i++) {
			double casuale = r.nextGaussian();
			sigma+=casuale*casuale;
		}
		double risultato = sigma/length;
		System.out.println("Varianza:1 = " + risultato); //Circa uguale a 1, OK
	}
	
	@Test
	public void testSeparaSequenzaInBlocchi() {
		SignalProcessing sp = new SignalProcessing();
		double[] n = {1,2,3,4,5,6,7,8,9,10};
		double[][] n_chunks = sp.separaSequenzaInBlocchi(n, 5);
		for(int i=0;i<n_chunks.length;i++) {
			for(int j=0; j<n_chunks[i].length;j++) {
				System.out.print(n_chunks[i][j]+" ");
			}
			System.out.println();
		}
		assertSame(5, n_chunks.length);
		assertSame(2, n_chunks[0].length);
	}

}
