
public class Aumentador extends Thread{
	
	private int id;
	private int N;
	private Entero n;
	private Entero last;
	private Booleano in1;
	private Booleano in2;
	
	public Aumentador(int id,int N, Entero n, Entero last, Booleano in1, Booleano in2) {
		
		this.id = id;
		this.N = N;
		this.n = n;
		this.last = last;
		this.in1 = in1;
		this.in2 = in2;
	}
	
	public void run() {
		
		for(int i = 0; i < N; ++i) {
			
			//mecanismo de entrada a la SC
			in1.value = true;
			last.value = 1;
			while(in2.value && last.value == 1);
			
			//secci�n cr�tica
			n.value += 1; 
			
			//mecanismo de salida de la SC
			in1.value = false;
			
			//System.out.println(i +" Soy el hilo " + id + ". El valor de n es: " + n.value);
		}
		
		System.out.println("Soy el hilo " + id + ". El valor de n es: " + n.value);
	}
}