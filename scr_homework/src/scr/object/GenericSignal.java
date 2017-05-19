package scr.object;

public abstract class GenericSignal {
	private double[] reali;
	private double[] immaginari;
	private int length;
	
	public GenericSignal(int lunghezzaSequenza) {
		this.reali = new double[lunghezzaSequenza];
		this.immaginari = new double[lunghezzaSequenza];
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

}
