import java.io.Serializable;
import java.net.InetAddress;
import java.util.List;

public class Usuario implements Serializable{
	
	private String id;
	private InetAddress dir_ip;
	private List<String> ficherosCompartidos;
	
	public Usuario() {}
	
	public Usuario(String id, InetAddress dir_ip, List<String> informacionCompartida) {
		
		this.id = id;
		this.dir_ip = dir_ip;
		this.ficherosCompartidos = informacionCompartida;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InetAddress getDir_ip() {
		return dir_ip;
	}

	public void setDir_ip(InetAddress dir_ip) {
		this.dir_ip = dir_ip;
	}

	public List<String> getInformacionCompartida() {
		return ficherosCompartidos;
	}

	public void setInformacionCompartida(List<String> informacionCompartida) {
		this.ficherosCompartidos = informacionCompartida;
	}
}
