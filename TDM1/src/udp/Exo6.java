package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Exo6 {
	public static void main(String[] args) throws Exception
	{
		Exo6 exo6 =new Exo6();
		exo6.execute();
				
		
	}
	private void execute() throws IOException
	{
		System.out.println("Connexion au serv");
		DatagramSocket socket = new DatagramSocket();
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 29000);
		byte[] bufR = new byte[2048];
		int NbGame = 1;
		int InGame = 0;
		while(NbGame<=10) {
			if(InGame==0)
			{
				System.out.println("===================================\nDébut de la partie "+ Integer.toString(NbGame));
				InGame = 1;
				byte[] bufE = new String("JOUER").getBytes();
				DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
				System.out.println("Envoi d'un paquet UDP avec JOUER");
				socket.send(dpE);
			}else{
				DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		        socket.receive(dpR);
		        String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
		        System.out.println("Le serveur a répondu "+reponse);
		        if(reponse.equals("PING")) {
		        	byte[] bufE = new String("PONG").getBytes();
					System.out.println("Envoi d'un paquet UDP avec PONG");
					DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
				    socket.send(dpE);
		        }else if(reponse.equals("PONG")) {
		        	byte[] bufE = new String("PING").getBytes();
					System.out.println("Envoi d'un paquet UDP avec PING");
					DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
				    socket.send(dpE);
		        }else if(reponse.equals("GAGNE"))
		        {
		        	System.out.println("Fin de la partie " + Integer.toString(NbGame));
		        	NbGame = NbGame +1;
		        	InGame = 0;
		        }
			}        
		}
		socket.close();
	}
}
