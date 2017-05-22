package scr.utility;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;
import scr.object.*;

public class ProcessaSegnaliTest {

	@Test
	public void test() {
		CaricatoreSequenza cs = new CaricatoreSequenza(1000000);
		Segnale s = cs.parseRighe(cs.leggiSequenza("Sequenze/seq1/output_1.dat"),"\t");
		ProcessaSegnali sp = new ProcessaSegnali();
		
		double[] reali_1000 = sp.campionaSequenza(s.getReali(), 10000);
		double[] imm_1000 = sp.campionaSequenza(s.getImmaginari(), 10000);
		assertEquals(10000, reali_1000.length);
		
		Segnale s_1000 = new Segnale(reali_1000.length,reali_1000,imm_1000);
		System.out.println("power_100 = " + s_1000.potenza());
		System.out.println("power = " + s.potenza());
	}
	
	@Test
	public void testPotenzaSequenzaRumorosa() {
		int num_sequenze = 1000;
		int num_campioni = 1000;
		ProcessaSegnali sp = new ProcessaSegnali();
		Rumore[] seq1 = sp.generaSequenzeRumorose(num_sequenze, num_campioni, -13);
		Rumore[] seq2 = sp.generaSequenzeRumorose(num_sequenze, num_campioni, -8);
		Rumore[] seq3 = sp.generaSequenzeRumorose(num_sequenze, num_campioni, -5);
		Rumore[] seq4 = sp.generaSequenzeRumorose(num_sequenze, num_campioni, 2);
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
		double[] rea = {1,2,3,4,5,6,7,8,9,10};
		double[] imm = {1,2,3,4,5,6,7,8,9,10};
		Segnale s = new Segnale(rea.length, rea, imm);
		Segnale[] s_chunks = s.separaSegnaleInBlocchi(2);
		for(int i=0;i<s_chunks.length;i++) {
			for(int j=0; j<s_chunks[i].getLength();j++) {
				System.out.print(s_chunks[i].getReali()[j]+" J"+s_chunks[i].getImmaginari()[j]+" | ");
			}
			System.out.println();
		}
		assertSame(5, s_chunks.length);
		assertSame(5, s_chunks[0].getLength());
	}

}
