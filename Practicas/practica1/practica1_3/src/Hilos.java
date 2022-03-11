

public class Hilos extends Thread{
	
	int id;
	int filaDeA[];
	Matriz B;
	Matriz C;
	int N;
	
	public Hilos(int id, int filaDeA[], Matriz B, int N, Matriz C) {
		
		this.id = id;
		this.filaDeA = filaDeA;
		this.B = B;
		this.N = N;
		this.C = C;
	}
	
	public void run() {
		
		int aux = 0;
		int[] auxColumna;

		for(int i = 0; i < filaDeA.length; i++) {
			
			aux = 0;
			auxColumna = B.getColumna(i);
			
            for (int j = 0; j<N; j++)
                aux += filaDeA[j] * auxColumna[j];

            C.cambiarValor(id, i, aux);
        }
		
		System.out.println("el hilo "+id+" ha acabado de calcular su fila");
	}

}
