package tcp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Exo4_serveur {
	public static void main(String[] args) throws IOException
	{
		Exo4_serveur exo4_Serveur = new Exo4_serveur();
		exo4_Serveur.execute();
	}
	
	@SuppressWarnings("resource")
	private void execute() throws IOException
	{
		//FileInputStream fis = new FileInputStream("/home/userir/file_serveur");
	
		
		
		ServerSocket socketEcoute = new ServerSocket();
		socketEcoute.bind(new InetSocketAddress(5099));
		Socket socketConnexion = socketEcoute.accept();
		
		byte[] bufR = new byte[2048];
		InputStream is = socketConnexion.getInputStream();
		String msg = new String("");
		int lenBufR=0;
		while(!msg.contains("\n"))
		{
			lenBufR = is.read(bufR);
			msg = msg + new String(bufR, 0 , lenBufR);
			//System.out.println(msg);
		}
		System.out.println("Message recu = "+msg);
		FileInputStream fis = new FileInputStream(msg.replace("\n",""));
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
