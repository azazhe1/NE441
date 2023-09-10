package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Exo4 {
	public static void main(String[] args) throws Exception
	{
		Exo4 exo4 =  new Exo4();
		exo4.execute();
	}
	private void execute() throws IOException
	{
		System.out.println("Demarrage du serveur ...");
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(10000));
        
        while(true) {
        	byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("Message recu = "+message);
            System.out.println("IP = "+dpR.getAddress());
            System.out.println("Port = "+dpR.getPort());
        }
	}
}
