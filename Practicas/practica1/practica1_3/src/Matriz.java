

public class Matriz {
	
	private int N;
	private int m[][];
	
	public Matriz(int N) {
		
		this.N = N;
		m = new int[N][N];
	}
	
	public void inicializar(int valores[][]) {
		
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < N; ++j)
				m[i][j] = valores[i][j];
	}
	
	public void cambiarValor(int x, int y, int val) {
		
		m[x][y] = val;
	}
	
	public int[] getFila(int x) {
		
		return m[x];
	}
	
	public int[] getColumna(int x) {
		
		int res[] = new int[N];
		
		for(int i = 0; i < N; ++i)
			res[i] = m[i][x];
		
		return res;
	}
	
	public void printMatriz() {

        for (int i = 0; i< N; i++){
            for (int j = 0; j<N; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
	}
}
