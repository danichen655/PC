
public class Monitor {

	private int valor;
	
	public Monitor() {
		
		valor = 0;
	}
	
	public synchronized void aumentar() {
		
		++valor;
	}
	public synchronized void decrementar() {
		
		--valor;
	}
	public synchronized int getValor() {
		
		return valor;
	}
}
