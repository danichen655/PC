

public class Main {
	
	private static int N = 4; //tamaño de las matrices es NxN

	public static void main(String[] args) {
		
		Matriz A = new Matriz(N);
		Matriz B = new Matriz(N);
		Matriz C = new Matriz(N);
		
		int aux[][] = {{2,3,4,7},{8,4,1,4},{6,7,4,9},{4,6,1,1}};
		int aux2[][] = {{3,4,5,1},{7,8,9,10},{1,3,6,8},{9,8,5,3}};
		
		A.inicializar(aux);
		B.inicializar(aux2);
		
		Hilos[] vecHilos = new Hilos[N];
		
		for(int i = 0; i < N; ++i) {
			
			vecHilos[i] = new Hilos(i, A.getFila(i), B, N, C);
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
		C.printMatriz();
	}
}
