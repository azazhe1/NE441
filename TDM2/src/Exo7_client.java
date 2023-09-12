import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import javax.swing.JFrame;

public class Exo7_client {
	public static void main(String[] args) throws Exception
	{
		Exo7_client exo7_client= new Exo7_client();
		if(args.length!= 3) {
			System.out.println("Usage: <port_serv> <ip_dest> <last_client(0/1)>");
		}else {
			exo7_client.execute(args[0],args[1],args[2]);
		}
	}
	
	private void execute(String Port_srv_str, String Ip_dest,String Last) throws IOException
	{
		try {
			int Port_srv = Integer.parseInt(Port_srv_str);
			
			DatagramSocket socket = new DatagramSocket(null);
	        InetSocketAddress adrDest = new InetSocketAddress(Ip_dest, Port_srv);
	        
	        byte[] bufR = new byte[2048];
		    byte[] bufE = new String(Last).getBytes();
		    DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		    DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);
            
		    JFrame frame = new JFrame("Chenillard");
		    frame.setSize(300,300);
		    
		    while(true) {
		    	socket.receive(dpR);
	        	String message = new String(bufR, dpR.getOffset(), dpR.getLength());
	        	
	        	if(message.equals("red\n")) {
	        		set_red(frame);
	        	}else if(message.equals("green\n")) {
	        		set_green(frame);
	        	}else {
	        		return;
	        	}
		    }
		    
	        
		}catch (final NumberFormatException e) {
	        System.out.println(e);
	    }
	}
	
	private void set_green(JFrame frame)
	{
		frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
	}
	private void set_red(JFrame frame)
	{
		frame.getContentPane().setBackground(Color.RED);
        frame.setVisible(true);
	}
}
