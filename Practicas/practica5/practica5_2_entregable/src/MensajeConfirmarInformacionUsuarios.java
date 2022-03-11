import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MensajeConfirmarInformacionUsuarios extends Mensaje{

	private ConcurrentHashMap<String, List<String>> informacion;
	
	public MensajeConfirmarInformacionUsuarios(String origen, String destino, ConcurrentHashMap<String, List<String>> informacion) {
		super(5, origen, destino);
		this.setInformacion(informacion);
	}

	public ConcurrentHashMap<String, List<String>> getInformacion() {
		return informacion;
	}

	public void setInformacion(ConcurrentHashMap<String, List<String>> informacion) {
		this.informacion = informacion;
	}
}
