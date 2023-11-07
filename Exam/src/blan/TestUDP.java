package blan;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TestUDP {

	public static void main(String[] args) throws IOException {
		TestUDP test = new TestUDP();
		test.execute();
	}

	private void execute() throws IOException {
		DatagramSocket socket = new DatagramSocket();

        // Creation et envoi du message
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 5000);
        byte[] bufR = new byte[1];
        String buff = new String("");
        while(true)
        {
        	DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String s = new String(bufR,dpR.getOffset(), dpR.getLength());
            if(s.contains(";"))
            {
            	break;
            }
            buff+=s;
        }
        System.out.println(buff);
	}

}
