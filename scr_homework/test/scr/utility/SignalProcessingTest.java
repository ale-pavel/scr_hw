package scr.utility;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import scr.object.Signal;

public class SignalProcessingTest {

	@Test
	public void test() {
		CaricatoreSequenza cs = new CaricatoreSequenza(1000000);
		Signal s = cs.parseRighe(cs.leggiSequenza("Sequenze/seq1/output_1.dat"));
		SignalProcessing sp = new SignalProcessing();
		double[] reali_1000 = sp.campionaSequenza(s.getReali(), 10000);
		double[] imm_1000 = sp.campionaSequenza(s.getImmaginari(), 10000);
		assertEquals(10000, reali_1000.length);
		Signal s_1000 = new Signal(reali_1000.length);
		s_1000.setReali(reali_1000);
		s_1000.setImmaginari(imm_1000);
		
		/*for(int i=0;i<reali_1000.length;i++) {
			System.out.println(reali_1000[i] + " " + imm_1000[i] + " i");
		}*/
		
		double power_1000 = s_1000.potenza();
		double power = s.potenza();
		System.out.println(power_1000);
		System.out.println(power);
		double[] gaussiani = new double[10];
		Random generator=new Random();
		for(int i=0;i<10;i++) {
			gaussiani[i] = generator.nextGaussian();
			System.out.println(gaussiani[i]);
		}
		
		//Test per check sommatoria nextGaussian, molto poco efficiente scusate...
		double[] gauss = new double[100000];
		Random r = new Random();
		for(int i=0;i<gauss.length;i++) {
			gauss[i] = r.nextGaussian();
		}
		double sigma = 0;
		for(int i=0;i<gauss.length;i++) {
			sigma+=gauss[i]*gauss[i];
		}
		System.out.println(sigma/gauss.length);	//Circa uguale a 1, OK
	}

}
