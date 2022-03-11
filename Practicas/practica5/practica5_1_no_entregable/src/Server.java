import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static int PORT = 1234;

	public static void main(String[] args) throws IOException{
		
		ServerSocket server_s = new ServerSocket(PORT);
		
		while(true) {
			
			System.out.println("Esperando cliente.");
			
			// espera a la conexión del cliente y establece un socket con el
			Socket client = server_s.accept(); 
			
			System.out.println("Cliente aceptado.");
			
			// Creamos un hilo para delegar la gestión del cliente
			ServerDelegate delegate = new ServerDelegate(client);
			delegate.start();

		}
	}
}
