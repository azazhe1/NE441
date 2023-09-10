package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Exo3 {
	
	public static void main(String[] args) throws Exception
	{
		Exo3 exo3 = new Exo3();
		exo3.execute();
	}
	private void execute() throws IOException
	{
		System.out.println("Demarage du client");
		DatagramSocket socket = new DatagramSocket();
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 2000);
		byte[] bufE = new String("Test").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE);
        System.out.println("Message envoyé");
	}
}
