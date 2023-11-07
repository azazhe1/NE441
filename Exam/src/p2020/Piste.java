package p2020;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Piste {

	public static void main(String[] args) throws IOException {
		Piste piste = new Piste();
		piste.execute();
	}

	private void execute() throws IOException {
		DatagramSocket socket = new DatagramSocket();
		
		getKey(socket);
		
	}

	private void getKey(DatagramSocket socket) throws IOException {
		
		int port = 9200;
		while(true)
		{
			String buff = getMSG(port,socket);
			if(buff.contains("clef"))
			{
				System.out.println(buff);
				break;
			}
			port = getPort(buff);
		}
		
	}

	private int getPort(String buff) {
		int p = buff.indexOf("port");
		buff = buff.substring(p+5,buff.length());
		return Integer.parseInt(buff);
	}

	private String getMSG(int port, DatagramSocket socket) throws IOException {
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", port);
		byte[] bufE = new String("JOUER").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE);
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        socket.receive(dpR);
        
		return new String(bufR, dpR.getOffset(), dpR.getLength());
	}
}

