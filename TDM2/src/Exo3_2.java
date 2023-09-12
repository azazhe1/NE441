import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;
import java.lang.Thread;
public class Exo3_2 {
	public static void main(String[] args) throws Exception
	{
		Exo3_2 exo3_2 =  new Exo3_2();
		exo3_2.execute();
	}
	
	private void execute() throws IOException, InterruptedException
	{
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4002));
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4001);
        
        JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);
        
        set_green(frame);
        byte[] bufR = new byte[2048];
        byte[] bufE = new String("red").getBytes();
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        while(true) {
        	socket.receive(dpR);
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

