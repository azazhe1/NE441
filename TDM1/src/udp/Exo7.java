package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Exo7 {
	public static void main(String[] args) throws Exception
	{
		Exo7 exo7 =new Exo7();
		exo7.execute();
				
		
	}
	private void execute() throws IOException
	{
		System.out.println("Connexion au serv");
		DatagramSocket socket = new DatagramSocket();
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 11000);
		int NbGame = 1;
		int InGame = 0;
		int Value1;
		int Value2;
		while(NbGame<=10) {
			if(InGame==0)
			{
				System.out.println("===================================\nDébut de la partie "+ Integer.toString(NbGame));
				InGame = 1;
			}
			
			Scanner myObj = new Scanner(System.in);
			String Game = myObj.nextLine();
			if(!Game.equals("JOUER"))
			{
				Game = Game + ';';
			}
			byte[] bufE = Game.getBytes();
	        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
	        socket.send(dpE);
	        System.out.println("Envoi d'un paquet UDP avec "+Game);
	        
	        byte[] bufR = new byte[2048];
	        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
	        socket.receive(dpR);
	        String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
	        
	        if(reponse.equals("GAGNE"))
	        {
	        	System.out.println("Fin de la partie " + Integer.toString(NbGame));
	        	NbGame = NbGame +1;
	        	InGame = 0;
	        }else if (reponse.equals("PERDU"))
	        {
	        	System.out.println("PERDU, You lose! Bye");
	        	NbGame = 11;
	        	
	        }else {
	        	Value1 = Integer.parseInt(reponse.replace(";", ""));
	        	socket.receive(dpR);
	 	        reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
	 	        Value2 = Integer.parseInt(reponse.replace(";", ""));
		        System.out.println("Le serveur a répondu "+Integer.toString(Value1) + " et " + Integer.toString(Value2));
	        }
	        
	        
		}
	}
}
