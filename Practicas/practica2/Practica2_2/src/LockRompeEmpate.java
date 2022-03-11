

public class LockRompeEmpate {
	
	private Entero in[];
	private Entero last[];
	private int N; //numero de procesos (y por lo tanto de etapas)
	
	public LockRompeEmpate(int N) {
		
		this.N = N;
		this.in = new Entero[N + 1];
		this.last = new Entero[N + 1];
		
		for(int i = 0; i <= N; ++i) {
			in[i] = new Entero();
			last[i] = new Entero();
			
			in[i].valor = 0;
			last[i].valor = 0;
		}
	}
	
	public void takeLock(int id) {
		
		for(int j = 1; j <= N; ++j) {
			
			in[id].valor = j;
			last[j].valor = id;
			
			for(int k = 1; k <= N; ++k) 
				if(k != id)
					while((in[k].valor >= in[id].valor) && (last[j].valor == id));
		}
	}
	
	public void releaseLock(int id) {
		
		in[id].valor = 0;
	}
}
