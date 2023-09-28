package thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class SommeTCP extends Thread {
	
	int port;
	private Info info;
	
	public SommeTCP(int port,Info info) {
		this.port = port;
		this.info = info;
	}

	public static void main(String[] args) throws InterruptedException {
		ArrayList<SommeTCP> list = new ArrayList<>();
		Info info =new Info();
		
		for(int i=0;i<=2000;i++)
		{
			list.add(new SommeTCP(21_000+i,info));
		}
		System.out.println("Début de la recherche ...");
		for(SommeTCP sommeTCP: list)
		{
			sommeTCP.start();
		}
		
		for(SommeTCP sommeTCP: list)
		{
			sommeTCP.join();
		}
		System.out.println("Le montant maximum est "+info.Max+" euros");
		System.out.println("Le port d'écoute correspondant à ce maximum est "+info.port);
		System.out.println("La somme des montants retournés par tous les ports est "+info.somme);
		System.out.println("Fin du programme");
	}
	
	public void run()
	{
		Socket socket = new Socket();
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", this.port);
		
		try {
			socket.connect(adrDest); 
			byte[] bufE = new String("COMBIEN").getBytes();
			OutputStream os;
			os = socket.getOutputStream();
			os.write(bufE);
			byte[] bufR = new byte[2048];
	        InputStream is = socket.getInputStream();
	        int lenBufR = is.read(bufR);
	        String reponse = new String("");
	        while (lenBufR!=-1)
	        {
	            reponse += new String(bufR, 0 , lenBufR );
	            lenBufR = is.read(bufR);
	            
	        }
            int indice = reponse.indexOf('E');
            String montant = reponse.substring(8, indice);
            info.update_info(Integer.parseInt(montant), this.port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static class Info
	{
		int somme = 0;
		int Max =0;
		int port;
		Object mutex = new Object();
		
		public void update_info(int value, int port)
		{
			synchronized (mutex) {
				if(value > this.Max)
				{
					this.Max  = value;
					this.port = port;
				}
				somme+= value;
			}
		}
	}

}
