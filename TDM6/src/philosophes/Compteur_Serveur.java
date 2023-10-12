package philosophes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Compteur_Serveur extends Thread{
	int port;
	private Compteur compteur;
	Object mutex = new Object();
	public Compteur_Serveur(int port,Compteur compteur) {
		this.port = port;
		this.compteur = compteur;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Compteur_Serveur> list = new ArrayList<>();
		Compteur compteur = new Compteur();
	
		for(int i=21_000;i<23_000;i++)
		{
			list.add(new Compteur_Serveur(i,compteur));
		}
		
		for(Compteur_Serveur compteur_server: list)
		{
			compteur_server.start();
		}
	}
	
	public void run()
	{
		
		try {
			
			ServerSocket socketEcoute = new ServerSocket();
			socketEcoute.bind(new InetSocketAddress(this.port));
			Socket socketConnexion = socketEcoute.accept();
			int somme = compteur.getsomme();
			System.out.println(port);
			//synchronized (mutex) {
				byte[] bufR = new byte[2048];
		        InputStream is = socketConnexion.getInputStream();
		        String reponse = new String("");
		        int lenBufR;
		        
		        while(reponse.contains("?")==false)
		        {
		        	lenBufR = is.read(bufR);
		        	reponse += new String(bufR, 0 , lenBufR );
		        }
	        	
	        	
	        	byte[] bufE = new String("NUMERO="+String.valueOf(somme)).getBytes();
	        	OutputStream os = socketConnexion.getOutputStream();
	        	os.write(bufE);
	        	
	        	socketConnexion.close();
	        
	        	
	        //}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	static class Compteur
	{
		int somme =1;
		Object mutex = new Object();
		
		public int getsomme() 
		{
			
			synchronized (mutex) {
				int somme;
				somme=this.somme;
				this.somme +=1;
				return somme;
			}
		}
	}
}
