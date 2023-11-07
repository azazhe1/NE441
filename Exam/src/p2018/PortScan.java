package p2018;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class PortScan extends Thread{
	int port;
	
	public PortScan(int i) {
		port = i;
	}

	public static void main(String[] args) {
		ArrayList<PortScan> list = new ArrayList<>();
		for(int i =30000;i<=32000;i++)
		{
			list.add(new PortScan(i));
		}
		for(PortScan prt:list)
		{
			prt.start();
		}
		
	}
	
	public void run()
	{
		try {
			DatagramSocket socket = new DatagramSocket();
			InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", this.port);
			byte[] bufE = new String("hello").getBytes();
			DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
			socket.send(dpE);
			byte[] bufR = new byte[2048];
			DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
	        socket.receive(dpR);
	        String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
	        System.out.println("Reponse recue = "+reponse + "port " + port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
