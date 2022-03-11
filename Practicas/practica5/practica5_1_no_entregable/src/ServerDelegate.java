import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerDelegate extends Thread{
	
	Socket client;
	
	public ServerDelegate(Socket client) {
		
		this.client = client;
	}
	
	
	public void run() {
		
		try {
			
			// Crear flujo de datos hacia el cliente
			PrintWriter out;
			
			out = new PrintWriter(client.getOutputStream(), true);

			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			// Leer el nombre del fichero
			String filename = in.readLine();
			System.out.println("Fichero a enviar al cliente: " + filename);
			
			// Leer el fichero
			BufferedReader buffer = new BufferedReader(new FileReader(filename));
			String line;
			
			line = buffer.readLine();
			
			while(line != null) {
				
				out.println(line);
				line = buffer.readLine();				
			}
			
			buffer.close();
			out.close();
			in.close();
			client.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
