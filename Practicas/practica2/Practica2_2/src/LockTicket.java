import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket {
	
	private volatile AtomicInteger number;
	private volatile int next;
	private int turn[];
	
	public LockTicket(int N) {

		this.turn = new int[N + 1];
		this.number = new AtomicInteger(0);
		next = 1;
		
		for(int i = 0; i <= N; ++i) 
			turn[i] = 0;
	}
	
	public void takeLock(int id) {

		turn[id] = number.addAndGet(1);
		
		while(turn[id] != next);
	}
	
	public void releaseLock() {
		
		next = next + 1;
	}
}