
public class LockBakery {
	
	private int turn[];
	private int N;
	
	public LockBakery(int N) {

		this.turn = new int[N + 1];
		this.N = N;
		
		for(int i = 1; i <= N; ++i) 
			turn[i] = 0;
	}
	
	public void takeLock(int id) {

		turn[id] = 1;
		turn[id] = max(turn) + 1;
		
		for(int i = 1; i <= N; ++i) 
			if(i != id) 
				while((turn[i] != 0) && mayorMayor(turn[id], id, turn[i], i));
	}
	
	public void releaseLock(int id) {
		
		turn[id] = 0;
	}

	private int max(int[] array) {
		
		int ret = 0;
		
		for(int i = 1; i <= N; ++i)
			if(array[i] > ret)
				ret = array[i];
		
		return ret;
	}
	
	private boolean mayorMayor(int a, int b, int c, int d) {
		
		return (a > c) || ((a == c) && (b > d));
	}
} 