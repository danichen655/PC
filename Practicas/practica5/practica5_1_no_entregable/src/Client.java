import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {

	private static int PORT = 1234; // puerto donde esta el servidor
	private static String HOST = "localhost";

	public static void main(String[] args) throws IOException{
		
		//Leer el nombre del fichero
		String fileName;
		
		System.out.print("- Introduzca el nombre del fichero: ");
		Scanner teclado = new Scanner(System.in);
		fileName = teclado.nextLine();
		teclado.close();
		
		// Crear socket
		Socket socket = new Socket(HOST, PORT);
		
		// Crear flujo de datos hacia el servidor
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		// Mandamos al servidor el nombre del fichero que queremos
		out.println(fileName);
		
		// Esperamos a recibir todas las líneas del fichero
		String line, file = "";
		
		line = in.readLine();
		
		while(line != null) {
			
			file += line + '\n';
			line = in.readLine();
		}
		
		System.out.println("Fichero recibido: ");
		System.out.println(file);
		
		out.close();
		in.close();
		socket.close();
	}
}
