import java.util.concurrent.Semaphore;

public class Almacen {
	
	int tamBuffer;
	private Semaphore empty;
	private Semaphore full;
	private Semaphore mutexP;
	private Semaphore mutexC;
	private int ini; // primera posición lista para ser leida
	private int fin; // primera posición libre
	private int[] buffer;
	
	public Almacen(int tamBuffer) {
		
		this.empty = new Semaphore(tamBuffer); 
		this.full = new Semaphore(0); 
		this.mutexP = new Semaphore(1);
		this.mutexC = new Semaphore(1); 
		this.buffer = new int[tamBuffer];
		this.tamBuffer = tamBuffer;
		ini = 0;
		fin = 0;
	}
	
	/**
	* Almacena (como ultimo) un producto en el almac´en. Si no hay
	* hueco el proceso que ejecute el m´etodo bloquear´a hasta que lo
	* haya.
	*/
	public void almacenar(int producto) {
		
		try {
			empty.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			mutexP.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("- El producto insertado es: " + producto);
		buffer[fin] = producto;
		fin = (fin + 1) % tamBuffer;
		
		mutexP.release();
		
		full.release();		
	}
	
	/**
	* Extrae el primer producto disponible. Si no hay productos el
	* proceso que ejecute el m´etodo bloquear´a hasta que se almacene un
	* dato.
	*/
	public int extraer() {
		
		try {
			full.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			mutexC.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int ret = buffer[ini];
		ini = (ini + 1) % tamBuffer;
		System.out.println("- El producto sacado es: " + ret);
			
		mutexC.release();
		
		empty.release();	
		
		return ret;
	}
}