import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

// Exemple: java Exo5 4000 4001 0  java Exo5 4001 4002 0  java Exo5 4002 4003 0  java Exo5 4001 4000 1 (il faut faire ça sur 4 terminal)
public class Exo5 {
	public static void main(String[] args) throws Exception
	{
		Exo5 exo5 = new Exo5();
		if(args.length!= 3) {
			System.out.println("Usage: <port_src> <port_dest> <start>");
		}else {
			exo5.execute(args[0],args[1],args[2]);
		}
	}
	private void execute(String Port_src_str,String Port_dest_str, String Start ) throws IOException, InterruptedException
	{
		 try {
		        int Port_src = Integer.parseInt(Port_src_str);
		        int Port_dest = Integer.parseInt(Port_dest_str);
		        
		        DatagramSocket socket = new DatagramSocket(null);
		        socket.bind(new InetSocketAddress(Port_src));
		        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", Port_dest);
		        
		        
		        if(Start.equals("0")) {
		        	Wait_Chenillard(socket, adrDest);
		        }else {
		        	Start_Chenillard(socket, adrDest);
		        }
		        
		    } catch (final NumberFormatException e) {
		        System.out.println(e);
		    }
	}
	
	private void Start_Chenillard(DatagramSocket socket, InetSocketAddress adrDest) throws InterruptedException, IOException
	{
		 byte[] bufR = new byte[2048];
	     byte[] bufE = new String("red\n").getBytes();
	     DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
	     
	     JFrame frame = new JFrame("Chenillard");
	     frame.setSize(300,300);
	     
	     while(true) {
	        	set_red(frame);
	        	Thread.sleep(1000);
	        	set_green(frame);
	            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
	            socket.send(dpE);
	            socket.receive(dpR);
	            String message = new String(bufR, dpR.getOffset(), dpR.getLength());
	        	if(!message.equals("red\n")) {
	            	socket.close();
	            	return;
	            }
	    }
	}
	
	private void Wait_Chenillard(DatagramSocket socket, InetSocketAddress adrDest) throws IOException, InterruptedException
	{
		byte[] bufR = new byte[2048];
	    byte[] bufE = new String("red\n").getBytes();
	    DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
	     
	    JFrame frame = new JFrame("Chenillard");
	    frame.setSize(300,300);
	    set_green(frame);
	    
	    while(true) {
        	socket.receive(dpR);
        	String message = new String(bufR, dpR.getOffset(), dpR.getLength());
        	if(!message.equals("red\n")) {
            	socket.close();
            	return;
            }
        	set_red(frame);
        	Thread.sleep(1000);
        	set_green(frame);
            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);
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
