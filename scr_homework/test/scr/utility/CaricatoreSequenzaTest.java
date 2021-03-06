package scr.utility;

import static org.junit.Assert.*;

import org.junit.Test;

import scr.object.Segnale;

public class CaricatoreSequenzaTest {

	@Test
	public void test() {
		CaricatoreSequenza cs = new CaricatoreSequenza(1000000);
		Segnale s = cs.parseRighe(cs.leggiSequenza("Sequenze/seq1/output_1.dat"),"\t");
		assertEquals(1000000, s.getLength());
		for(int i=0; i<10; i++) {
			System.out.println(s.getReali()[i] + " " + s.getImmaginari()[i] + " i");
		}
	}

}
