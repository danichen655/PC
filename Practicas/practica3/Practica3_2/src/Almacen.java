import java.util.concurrent.Semaphore;

public class Almacen {
	
	private Semaphore empty;
	private Semaphore full;
	private int buffer;
	
	public Almacen() {
		
		this.empty = new Semaphore(1); // ver inicialización
		this.full = new Semaphore(0); // ver inicialización
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
		
		System.out.println("- El producto insertado es: " + producto);
		
		
		buffer = producto;
		
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
		
		int ret = buffer;
		
		System.out.println("- El producto sacado es: " + ret);
			
		empty.release();	
		
		return ret;
	}
}