

public class Modificador extends Thread{
	
	private int id;
	private boolean tipo; //true decrementa, false aumenta
	private int N;
	private Entero n;
	private LockBakery lock;
	
	public Modificador(int id, boolean tipo, int N, Entero n, LockBakery lock) {
		this.id = id;
		this.tipo = tipo;	
		this.N = N;
		this.n = n;
		this.lock = lock;
	}
	
	public void run() {
		
		int aux = 1;
		
		if (tipo) 
			aux = -1;
		
		for(int i = 0; i < N; ++i) {
			
			lock.takeLock(id);
			
			n.valor += aux; // sección crítica
			
			lock.releaseLock(id);
		}
		
		System.out.println("Soy el hilo:" + id + ". El valor de n es: " + n.valor);
	}
}
