import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class OyenteCliente extends Thread{

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ConcurrentHashMap<String, DataFlows> usuarios;
	private ConcurrentHashMap<String, List<String>> informacion;
	//private MonitorInformacion monitorInfo;
	private Semaphore semUsuarios;
	
	public OyenteCliente(Socket socket, ConcurrentHashMap<String, DataFlows> usuarios, ConcurrentHashMap<String, List<String>> informacion, Semaphore semUsuarios) throws IOException {
		
		this.socket = socket;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		this.usuarios = usuarios;
		this.informacion = informacion;
		this.semUsuarios = semUsuarios;
	}
	
	public void run() {
		
		Mensaje m;
		boolean exit = true;
		
		while(exit) {
			
			m = leerMensaje();
			try {
				exit = procesarMensaje(m);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Mensaje leerMensaje() {
		
		Mensaje m = null;
		
		try {
			m = (Mensaje) in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("no lee el mensaje" + m);
			e.printStackTrace();
		}
		
		return m;
	}
	
	
	// TODO hacer toda la parte de procesar mensaje 
	private boolean procesarMensaje(Mensaje m) throws IOException, InterruptedException {
		
		boolean ret = true;
		
		switch(m.getTipo()) {
		
		case 0:
		    /*- MENSAJE_CONEXION:
		    * guardar informacion del usuario (en las tablas)
		 	* envio mensaje confirmacion conexion fout	*/
			
			MensajeConexion mensaje = (MensajeConexion) m;
			
			addUsuario(mensaje.getUsuario(), out, in);
			
			
			System.out.println("Mandando confirmacion conexion");
			out.writeObject(new MensajeConfirmarConexion("Servidor", m.getOrigen()));
			
			break;
		
		case 2:
		    /* - MENSAJE_CERRAR_CONEXION:
		     * eliminar informacion del usuario (en las tablas)
		     * envio mensaje confirmacion cerrar conexion fout*/
			
			deleteUsuario(m.getOrigen());
			
			out.writeObject(new MensajeConfirmarCerrarConexion("Servidor", m.getOrigen()));
			ret = false;
			break;
			
		case 4:
		    /*- MENSAJE_LISTA_USARIOS:
		    * crear un mensaje con la informacion de usuarios en sistema
			* envio mensaje confirmacion lista usuarios fout*/
		
			ConcurrentHashMap<String, List<String>> aux = getInformacionUsuarios();

			MensajeConfirmarInformacionUsuarios msg = new MensajeConfirmarInformacionUsuarios("Servidor", m.getOrigen(), aux);
			
			System.out.println("1 " + msg.getInformacion().toString());
					
			out.writeObject(msg);
			
			out.reset();
			
			break;
			

		default:
			
			System.out.println("Oyente de cliente: Mensaje no valido (0 - ). " + m.getTipo());
		}
		
		return ret;
	}
	
	private void deleteUsuario(String usuario) throws InterruptedException {
		
		semUsuarios.acquire();
		usuarios.remove(usuario);
		semUsuarios.release();
		
		informacion.remove(usuario);
	}

	public void addUsuario(Usuario usuario, ObjectOutputStream out, ObjectInputStream in) throws InterruptedException {
		
		semUsuarios.acquire();
		
		usuarios.put(usuario.getId(), new DataFlows(out,in));
		
		semUsuarios.release();
		
		informacion.put(usuario.getId(), usuario.getInformacionCompartida());
		
	}
	
	public ConcurrentHashMap<String, List<String>> getInformacionUsuarios() {
		
		ConcurrentHashMap<String, List<String>> ret = informacion;
		 
		 return ret;
	}
}
