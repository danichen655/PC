
public class Main {

	private static int N = 30000; //num de decrementos o incrementos que hara cada hilo

	public static void main(String[] args) {
		
		Monitor m = new Monitor();
		
		System.out.println("Empieza la creación de los hilos");
		
		Aumentador h1 = new Aumentador(1,N,m);
		Decrementador h2 = new Decrementador(2,N,m);
		
		h1.start();
		h2.start();
		
		try {
			h1.join();
			h2.join();
		} 
		catch (Exception e) {
			System.out.println("Error esperando a la finalización de los hijos");
		}
		
		System.out.println("Se han terminado los dos hilos");
		System.out.println("El valor final de la variable compartida es: " + m.getValor());		
	}
}
