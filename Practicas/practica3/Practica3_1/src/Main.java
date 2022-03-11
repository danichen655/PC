import java.util.concurrent.Semaphore;

public class Main {

	private static int N = 30; //num de decrementos o incrementos que hara cada hilo
	private static int M = 20; //numero de procesos de cada tipo
	
	public static void main(String[] args) {
		
		int aux;
		int numCeros = 0;
		
		for(int i = 0; i < 1000; ++i) {
			
			aux = casoDePrueba();
			
			if(aux == 0)
				numCeros++;
			
		}
		System.out.println("Ha sacado " + numCeros + " ceros.");	
	}
	
	private static int  casoDePrueba() {
		
		Entero n = new Entero();
		n.valor = 0;
		
		Semaphore sem = new Semaphore(1); //aqui ponemos el lock que queramos usar
		
		//System.out.println("Empieza la creación de los hilos");
		
		Modificador[] vecHilos = new Modificador[M*2];
		
		for (int i = 0; i < M; i++) {
			vecHilos[i] = new Modificador(i+1, true, N, n, sem);
			vecHilos[i + M] = new Modificador(i+M+1, false, N, n, sem);
			vecHilos[i + M].start();
			vecHilos[i].start();
		}
		
		try {
			for (int i = 0; i< vecHilos.length; i++)
				vecHilos[i].join();
		} 
		catch (Exception e) {
			System.out.println("Error esperando a la finalización de los hijos");
		}
		
		//System.out.println("Se han terminado todos los hilos");
		//System.out.println("El valor final de la variable compartida es: " + n.valor);		
		
		return n.valor;
	}
}
