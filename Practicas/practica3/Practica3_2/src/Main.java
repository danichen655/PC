
public class Main {

	private static int N = 10; // num prodouctores 
	private static int M = 30; // num consumidores
	private static int numIterProd = 30; // num iteraciones de cada hilo
	private static int numIterCons = 10; // num iteraciones de cada hilo
	
	public static void main(String[] args) {

		System.out.println("Empieza la creación de los hilos");
		
		Almacen almacen = new Almacen();
		
		Productor[] productores = new Productor[N];
		Consumidor[] consumidores = new Consumidor[M];
		
		for (int i = 0; i < N; i++) {
			productores[i] = new Productor(i+1, almacen, numIterProd);
			productores[i].start();
		}
		
		for (int i = 0; i < M; i++) {
			consumidores[i] = new Consumidor(i+1+M, almacen, numIterCons);
			consumidores[i].start();
		}
		
		try {
			for (int i = 0; i< consumidores.length; i++)
				consumidores[i].join();
			
			for (int i = 0; i< productores.length; i++)
				productores[i].join();
		} 
		catch (Exception e) {
			System.out.println("Error esperando a la finalización de los hijos");
		}
		
		System.out.println("Se han terminado todos los hilos");
	}
}
