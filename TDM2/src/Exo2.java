import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;


public class Exo2 {
	public static void main(String[] args) throws Exception
	{
		Exo2 exo2 =  new Exo2();
		exo2.execute();
	}
	
	private void execute() throws IOException
	{
		System.out.println("Demarrage du serveur ...");
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(7050));
        JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);
        
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        while(true) {
        	
            
            socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("Message recu = "+message);
            if(message.equals("green\n"))
            {
            	frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);

            }else if(message.equals("red\n"))
            {
            	frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
            }
        }
	}
}
