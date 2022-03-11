
public class Main {

	private static int N = 30000; //num de decrementos o incrementos que hara cada hilo

	public static void main(String[] args) {
		
		Entero n = new Entero();
		Entero last = new Entero();
		n.value = 0;
		last.value = -1;
		
		Booleano in1 = new Booleano();
		Booleano in2 = new Booleano();
		in1.value = false;
		in2.value = false;
		
		System.out.println("Empieza la creación de los hilos");
		
		Aumentador h1 = new Aumentador(1,N,n,last,in1,in2);
		Decrementador h2 = new Decrementador(2,N,n,last,in1,in2);
		
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
		System.out.println("El valor final de la variable compartida es: " + n.value);		
	}
}
