import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;
import java.lang.Thread;
public class Exo3_1 {
	public static void main(String[] args) throws Exception
	{
		Exo3_1 exo3_1 =  new Exo3_1();
		exo3_1.execute();
	}
	
	private void execute() throws IOException, InterruptedException
	{
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4001));
        InetSocketAddress adrDest = new InetSocketAddress("192.168.130.167", 4002);
        
        
        JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);
        
        byte[] bufR = new byte[2048];
        byte[] bufE = new String("red").getBytes();
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        while(true) {
        	set_red(frame);
        	Thread.sleep(1000);
        	set_green(frame);
            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
            socket.send(dpE);
            socket.receive(dpR);
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
