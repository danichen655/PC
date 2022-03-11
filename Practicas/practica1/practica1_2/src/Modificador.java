

public class Modificador extends Thread{
	
	private int id;
	private boolean tipo; //true decrementa, false aumenta
	private int N;
	private Entero n;
	
	public Modificador(int id, boolean tipo, int N, Entero n) {
		this.id = id;
		this.tipo = tipo;	
		this.N = N;
		this.n = n;
	}
	
	public void run() {
		
		int aux = 1;
		
		if (tipo) 
			aux = -1;
		
		for(int i = 0; i < N; ++i) 
			n.valor += aux;
		
		System.out.println("Soy el hilo:" + id + ". El valor de n es: " + n.valor);
	}
}
