package tcp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Exo3_Serveur {
	public static void main(String[] args) throws IOException
	{
		Exo3_Serveur exo3_Serveur = new Exo3_Serveur();
		exo3_Serveur.execute();
	}
	
	@SuppressWarnings("resource")
	private void execute() throws IOException
	{
		FileInputStream fis = new FileInputStream("/home/userir/file_serveur");
	
		ServerSocket socketEcoute = new ServerSocket();
		socketEcoute.bind(new InetSocketAddress(5099));
		
		
		Socket socketConnexion = socketEcoute.accept();
		byte[] buf = new byte[100_000];
		OutputStream os = socketConnexion.getOutputStream();
		int len = fis.read(buf);
		while(len!=-1)
		{
			os.write(buf,0,len);
			len = fis.read(buf);
		}
		System.out.println("Fin écriture Socket");
		socketEcoute.close();
		socketConnexion.close();
	}
}
