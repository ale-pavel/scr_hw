package scr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import scr.object.Signal;

public class CaricatoreSequenza {
	private int sequenceLen;
	
	public CaricatoreSequenza(int length) {
		this.sequenceLen = length;
	}
	
	public int getSequenceLen() {
		return sequenceLen;
	}

	public String[] leggiSequenza(String path) {
		String[] righeSequenza = new String[sequenceLen];
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String rigaCorrente = br.readLine();
			int i=0;
			while(rigaCorrente != null) {
				righeSequenza[i] = rigaCorrente;
				rigaCorrente = br.readLine();
				i++;
			}
			br.close();
		} catch (IOException e){
			System.out.println("Path del file errato");
		}
		return righeSequenza;
	}
	
	public Signal parseRighe(String[] righe) {
		Signal s = new Signal(righe.length);
		double[] reali = s.getReali();
		double[] imm = s.getImmaginari();
		int i=0;
		for(String riga : righe) {
			String[] coppia = riga.split("\t");
			reali[i] = Double.parseDouble(coppia[0]);
			imm[i] = Double.parseDouble(coppia[1]);
			i++;
		}
		return s;
	}

}
