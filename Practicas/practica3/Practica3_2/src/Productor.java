
public class Productor extends Thread{
	
	private int id;
	private Almacen almacen;
	private int numIter;
	
	public Productor(int id, Almacen almacen, int numIter) {
		this.id = id;
		this.almacen = almacen;
		this.numIter = numIter;
	}
	
	public void run() {
		
		for(int i = 0; i < numIter; ++i) {
			
			almacen.almacenar(i + 1);
			//System.out.println("Soy el productor:" + id + ". El producto almacenado es: " + (i + 1));
		}
	}
}
