package scr.main;

import scr.object.Signal;
import scr.utility.CaricatoreSequenza;

public class HomeworkMain {

	public static void main(String[] args) {
		CaricatoreSequenza cs = new CaricatoreSequenza(1000000);
		Signal s = cs.parseRighe(cs.leggiSequenza("Sequenze/seq1/output_1.dat"));
		//A questo punto ho i valori dei campioni in reali e imm convertiti in double a partire dal .dat
		double[] livelli_snr = {-13,-8,-5,2};
		
	}
	
}
