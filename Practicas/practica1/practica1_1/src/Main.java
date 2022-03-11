

public class Main {
	
	private static int N = 10; //hilos A EJECUTAR
	private static int T = 30; //milisegundos que debe dormir cada hilo

	public static void main(String[] args) {
		
		System.out.println("Empieza la creación de los hilos");
		
		Hilos[] vecHilos = new Hilos[N];
		
		for (int i = 0; i< vecHilos.length; i++) {
			vecHilos[i] = new Hilos(i+1, T);
			vecHilos[i].start();
		}
		
		try {
			for (int i = 0; i< vecHilos.length; i++)
				vecHilos[i].join();
			
		} catch (Exception e) {
			System.out.println("Error esperando a la finalización de los hijos");
		}
		
		System.out.println("Se han terminado todos los hilos");
	}
}
