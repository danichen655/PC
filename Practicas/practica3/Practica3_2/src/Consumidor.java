
public class Consumidor extends Thread{
	
	private int id;
	private Almacen almacen;
	private int numIter;
	
	public Consumidor(int id, Almacen almacen, int numIter) {
		this.id = id;
		this.almacen = almacen;
		this.numIter = numIter;
	}
	
	public void run() {
		
		int result;
		
		for(int i = 0; i < numIter; ++i) {
			
			result = almacen.extraer();
			
			//System.out.println("Soy el consumidor:" + id + ". El producto extraido es: " + result);
		}
	}
}

