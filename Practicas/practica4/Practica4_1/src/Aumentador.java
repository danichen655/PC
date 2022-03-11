
public class Aumentador extends Thread{
	
	private int id;
	private int N;
	private Monitor m;
	
	public Aumentador(int id, int N, Monitor m) {
		
		this.id = id;
		this.N = N;
		this.m = m;

	}
	
	public void run() {
		
		for(int i = 0; i < N; ++i)
			m.aumentar(); 
		
		System.out.println("Soy el hilo " + id + ". El valor de n es: " + m.getValor());
	}
}