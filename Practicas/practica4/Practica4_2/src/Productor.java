
public class Productor extends Thread{
	
	private int id;
	private MonitorAlmacen almacen;
	private int numIter;
	
	public Productor(int id, MonitorAlmacen almacen, int numIter) {
		this.id = id;
		this.almacen = almacen;
		this.numIter = numIter;
	}
	
	public void run() {
		
		for(int i = 0; i < numIter; ++i) {
			
			try {
				almacen.almacenar(i + 1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Soy el productor:" + id + ". El producto almacenado es: " + (i + 1));
		}
	}
}
