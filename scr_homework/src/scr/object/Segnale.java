package scr.object;

public class Segnale {
	private double[] reali;
	private double[] immaginari;
	private int length;
	
	public Segnale(int lunghezzaSequenza) {
		this.reali = new double[lunghezzaSequenza];
		this.immaginari = new double[lunghezzaSequenza];
		this.length = lunghezzaSequenza;
	}
	
	public Segnale(int lunghezzaSequenza, double[] reali, double[] imm) {
		this.reali = reali;
		this.immaginari = imm;
		this.length = lunghezzaSequenza;
	}

	public double[] getReali() {
		return reali;
	}

	public void setReali(double[] reali) {
		this.reali = reali;
	}

	public double[] getImmaginari() {
		return immaginari;
	}

	public void setImmaginari(double[] immaginari) {
		this.immaginari = immaginari;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public double potenza() {
		double potenza=0;		
		for(int i=0; i<this.getLength(); i++) {
			potenza+=(Math.pow(this.reali[i],2) + Math.pow(this.immaginari[i],2));
		}	
		return potenza/this.getLength();
	}
	
	private double[][] separaSequenzaInBlocchi(double[] campioni, int numeroBlocchi) {	//partendo da 10^6 campioni
		int numeroCampioni = campioni.length/numeroBlocchi;
		double[][] sequenzaSeparata = new double[numeroBlocchi][numeroCampioni];	//[100][10.000]	
		for(int i=0;i<numeroBlocchi;i++) {	//length = 100 = numero blocchi
			for(int j=0;j<numeroCampioni;j++) {	//length = 10.000 = numero campioni per blocco
				sequenzaSeparata[i][j] = campioni[j+i*numeroCampioni];	//j[0 a 9.999]+i[0 a 99]*10.000																					//
			}																		//per prelevare campione corretto
		}		
		return sequenzaSeparata;
	}
	
	public Segnale[] separaSegnaleInBlocchi(int numeroBlocchi) {
		Segnale[] signalChunks = new Segnale[numeroBlocchi];
		double[][] realiChunks = this.separaSequenzaInBlocchi(this.getReali(), numeroBlocchi);
		double[][] immChunks = this.separaSequenzaInBlocchi(this.getImmaginari(), numeroBlocchi);
		//Ho separato singolarmente la parte reale ed immaginaria
		for(int i=0;i<numeroBlocchi;i++) {
			signalChunks[i] = new Segnale(this.length/numeroBlocchi);
			signalChunks[i].setReali(realiChunks[i]);
			signalChunks[i].setImmaginari(immChunks[i]);
		}
		return signalChunks;
	}

}
