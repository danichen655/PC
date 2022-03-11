
public class MonitorAlmacen {
	
	private int[] buffer;
	int ini;
	int fin;
	int count;
	int tam;
	
	public MonitorAlmacen(int tam) {
		
		this.tam = tam;
		buffer = new int[tam];
		ini = 0;
		fin = 0;
		count = 0;
	}
	
	/*
	* Almacena (como ultimo) un producto en el almac´en. Si no hay
	* hueco el proceso que ejecute el m´etodo bloquear´a hasta que lo
	* haya.
	*/
	public synchronized void almacenar(int producto) throws InterruptedException {
		
		while(count == tam) wait();
		
		System.out.println("- El producto insertado es: " + producto);
		buffer[fin] = producto;
		fin = (fin + 1) % tam;
		count++;
		
		notifyAll();
	}
	
	/*
	* Extrae el primer producto disponible. Si no hay productos el
	* proceso que ejecute el m´etodo bloquear´a hasta que se almacene un
	* dato.
	*/
	public synchronized int extraer() throws InterruptedException {

		while(count == 0) wait();
		
		int ret = buffer[ini];
		ini = (ini +  1) % tam;
		count--;
		
		System.out.println("- El producto sacado es: " + ret);

		notifyAll();
		
		return ret;
	}
}