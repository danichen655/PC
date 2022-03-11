

public class Main {

	private static int N = 30; //num de decrementos o incrementos que hara cada hilo
	private static int M = 500; //numero de procesos de cada tipo
	
	public static void main(String[] args) {
		
		Entero n = new Entero();
		n.valor = 0;
		
		System.out.println("Empieza la creación de los hilos");
		
		Modificador[] vecHilos = new Modificador[M*2];
		
		for (int i = 0; i < M; i++) {
			vecHilos[i] = new Modificador(i+1, true, N, n);
			vecHilos[i + M] = new Modificador(i+M+1, false, N, n);
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
		
		System.out.println("Se han terminado todos los hilos");
		System.out.println("El valor final de la variable compartida es: " + n.valor);		
	}
	
}
