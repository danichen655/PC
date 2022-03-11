import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MonitorInformacion {
	
	private ConcurrentHashMap<String, List<String>> informacion;
	
	public MonitorInformacion() {
		
		informacion = new ConcurrentHashMap<>();
	}
	
	public synchronized void addUsuario(Usuario usuario) {
		
		informacion.put(usuario.getId(),usuario.getInformacionCompartida());
	}
	
	public synchronized void  deleteUsuario(String id){
		
		informacion.remove(id);
	}
	
	public synchronized ConcurrentHashMap<String, List<String>> getInformacion(){
		
		return informacion;
	}
}
