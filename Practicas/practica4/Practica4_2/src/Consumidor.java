
public class Consumidor extends Thread{
	
	private int id;
	private MonitorAlmacen almacen;
	private int numIter;
	
	public Consumidor(int id, MonitorAlmacen almacen, int numIter) {
		this.id = id;
		this.almacen = almacen;
		this.numIter = numIter;
	}
	
	public void run() {
		
		int result = -1;
		
		for(int i = 0; i < numIter; ++i) {
			
			try {
				result = almacen.extraer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Soy el consumidor:" + id + ". El producto extraido es: " + result);
		}
	}
}

