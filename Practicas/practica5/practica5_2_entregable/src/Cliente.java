import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente extends Thread{
	
	private  int PORT = 1234;
	private  InetAddress HOST;
	private  ObjectOutputStream out;
	private  ObjectInputStream in;
	private  Socket socket;
	private  Usuario usuario = new Usuario();
	private  Scanner teclado;
	
	public Cliente() {
		
	}
	
	public void run() {
		
		teclado = new Scanner(System.in);
		
		preguntarNombreYFicheros();

		try {
			
			crearSocketConServidor();
		
			crearOyenteServidor();
			
			enviarConexion();
			
			while(menuPrincipal());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        teclado.close();
    }
	
	private void preguntarNombreYFicheros() {
		
		System.out.print("- Nombre de Usuario: ");
		usuario.setId(teclado.nextLine());
		
        System.out.print("- Introduce el numero de ficheros: ");
        String n = teclado.nextLine();
        int numero = Integer.parseInt(n);

        System.out.println("Introduce tus fichero: ");
        List<String> ficheros = new ArrayList<String>();

        for(int i = 0; i< numero; ++i) {
        	
        	System.out.print("("+(i+1)+") ");
            String fichero = teclado.nextLine();
            ficheros.add(fichero);
        }
		
		usuario.setInformacionCompartida(ficheros);
	}
	
	private void crearSocketConServidor() throws IOException {
		
		HOST = InetAddress.getLocalHost();

		socket = new Socket(HOST, PORT);
		
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	private void crearOyenteServidor() throws IOException {
		
		OyenteServidor oyente = new OyenteServidor(socket,out,in); // TODO pasarle los atributos necesarios
		oyente.start();

	}
	
	private void enviarConexion() throws IOException {
		
		out.writeObject(new MensajeConexion(usuario.getId(), "Servidor", usuario));
		
		//TODO es
	}
	
	private boolean menuPrincipal() throws IOException {
		
		// TODO abrir un menú con 3 opciones: 1. consultar lista de usuarios 2. pedir ficher 3. salir
		boolean ret = true;
	     
		System.out.println("\n\nMENÚ PRINCIPAL.\n\n (1) Lista de usuarios.\n (2) Pedir fichero. \n (0) Salir.");

        System.out.print("- Introduce la opción: ");
        
        int opcion = Integer.parseInt(teclado.nextLine());
        
        while(opcion < 0 || opcion > 2) {
        	
        	System.out.println("Opción incorrecta (0 - 2)");
            System.out.print("- Introduce la opción: ");
            opcion = Integer.parseInt(teclado.nextLine());
        }
        
        switch(opcion) {
        
        case 1:
        	
        	System.out.println("Pidiendo lista de usuarios.");
        	out.writeObject(new MensajeListaUsuarios(usuario.getId(), "Servidor"));
        	
        	//TODO esperara que se reciba la lista de usuarios (y no se imprima a la vez que el menú)
        	
        	
        	break;
        	
        case 2:
        	
        	// pedir el usuario y el fichero y mandar el mensaje
        	
        	break;
        	
        case 0:
        	
        	System.out.println("Saliendo...");
        	out.writeObject(new MensajeCerrarConexion(usuario.getId(), "Servidor"));

        	ret = false;
        	break;
        
        default:
        	System.out.println("Opción incorrecta (0 - 2) - no deberia llegar aqui -");
        }
        
		return ret;
	}
}
