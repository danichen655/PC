import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class OyenteServidor extends Thread{
	
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public OyenteServidor(Socket socket, ObjectOutputStream out, ObjectInputStream in) throws IOException {
		
		this.socket = socket;
		this.in  = in;
		this.out  = out;
	}
	
	
	public void run() {
		
		Mensaje m;
		
		while(true) {
			
			m = leerMensaje();
			
			try {
				procesarMensaje(m);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
	
	
	public Mensaje leerMensaje() {
		
		Object m = null;
		
		try {
			m = in.readObject();
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		Mensaje ret = (Mensaje) m;
		
		return ret;
	}
	
	
	// TODO hacer toda la parte de procesar mensaje 
	private void procesarMensaje(Mensaje m) throws IOException {
		
		switch(m.getTipo()) {
		
		case 1:
			
			/*- MENSAJE_CONFIRMACION_CONEXION:*/
			
			System.out.println(m.getDestino() + ": conexión establecida");
			
			break;
	
		case 5:
			
		    /*- MENSAJE_CONFIRMACION_LISTA_USUARIOS
	        imprimir lista usuarios por standard output*/	
			
			MensajeConfirmarInformacionUsuarios mensaje = (MensajeConfirmarInformacionUsuarios) m;
			
			System.out.println(mensaje.getInformacion().toString());
			
			//imprimirInformacionUsuarios(mensaje.getInformacion());
			
			break;
			
		case 45:
			
			/*- MENSAJE_EMITIR_FICHERO
			(nos llega nombre de cliente C1 e informacion pedida 3)
			enviar MENSAJE_PREPARADO_CLIENTESERVIDOR a mi oyente
			crear proceso EMISOR y esperar en accept la conexion*/	
			
			
			
			break;
			
		case 65:
			
			/*- MENSAJE_PREPARADO_SERVIDORCLIENTE
			(llega direccion Ip y puerto del propietario de fichero)
			crear proceso RECEPTOR*/	
			
			
			
			break;
			
		case 3:

			   /*- MENSAJE_CONFIRMACION_CERRAR_CONEXION
			   imprimir adios por standard output*/
			
			System.out.println("Adios!");
			socket.close();
			System.exit(1);
			
			break;
			
		default:
			
			System.out.println("Oyente de servidor: Mensaje no valido (0 - ). " + m.getTipo());
		}
	}

	
	private void imprimirInformacionUsuarios(ConcurrentHashMap<String, List<String>> informacion) {

        System.out.println("Información usuarios:");

        for (String key : informacion.keySet()) {
            List<String> item = informacion.get(key);
            System.out.println(key + ":");
            for (String s : item) {
                System.out.println('\t'+s);

            }
            System.out.println();
        }
    }
}
