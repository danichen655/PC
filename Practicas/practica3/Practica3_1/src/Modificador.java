import java.util.concurrent.Semaphore;

public class Modificador extends Thread{
	
	private int id;
	private boolean tipo; //true decrementa, false aumenta
	private int N;
	private Entero n;
	private Semaphore sem;
	
	public Modificador(int id, boolean tipo, int N, Entero n, Semaphore sem) {
		this.id = id;
		this.tipo = tipo;	
		this.N = N;
		this.n = n;
		this.sem = sem;
	}
	
	public void run() {
		
		int aux = 1;
		
		if (tipo) 
			aux = -1;
		
		for(int i = 0; i < N; ++i) {
			
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			n.valor += aux; // sección crítica
			
			sem.release();
		}
		
	//	System.out.println("Soy el hilo:" + id + ". El valor de n es: " + n.valor);
	}
}
