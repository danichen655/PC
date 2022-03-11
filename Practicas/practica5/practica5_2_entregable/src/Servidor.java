import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class Servidor {

	private static int PORT = 1234;
	private static InetAddress HOST = null;
	private static ServerSocket serverSocket;
	private static volatile ConcurrentHashMap<String, DataFlows> usuarios;
	private static ConcurrentHashMap<String, List<String>> informacion;
	//private static MonitorInformacion monitorInfo;
	private static Semaphore semUsuarios;
	
	public static void main(String[] args) throws IOException {
		
		semUsuarios = new Semaphore(1); //aqui ponemos el lock que queramos usar
		usuarios = new ConcurrentHashMap<>();
		informacion = new ConcurrentHashMap();
		
		HOST = InetAddress.getLocalHost();
		
		serverSocket = new ServerSocket(PORT);
			
		while(true) {
			
			System.out.println("Servidor: Esperando cliente.");
			Socket client = serverSocket.accept(); 
			System.out.println("Servidor: Cliente aceptado.");
			
			// Creamos un hilo para delegar la gestión del cliente
			OyenteCliente oyente = new OyenteCliente(client, usuarios, informacion, semUsuarios);
			oyente.start();
		}
	}
}