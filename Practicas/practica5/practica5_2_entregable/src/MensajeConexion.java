
public class MensajeConexion extends Mensaje{

	private Usuario usuario;
	
	public MensajeConexion(String origen, String destino, Usuario usuario) {
		
		super(0, origen, destino);
		this.setUsuario(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
