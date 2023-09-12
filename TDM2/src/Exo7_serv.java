import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;


public class Exo7_serv {
	public static void main(String[] args) throws Exception
	{
		Exo7_serv exo7_serv= new Exo7_serv();
		if(args.length!= 1) {
			System.out.println("Usage: <port_src>");
		}else {
			exo7_serv.execute(args[0]);
		}
		
	}
	
	private void execute(String Port_src_str) throws IOException, InterruptedException {
		
		try {
			int Port_src = Integer.parseInt(Port_src_str);
			DatagramSocket socket = new DatagramSocket(null);
	        socket.bind(new InetSocketAddress(Port_src));
	        List<AdresseIPPort> ListeIPPort = wait_connexion(socket);
	        Send_order(ListeIPPort, socket);
		}catch (final NumberFormatException e) {
	        System.out.println(e);
	    }
	}
	
	private List<AdresseIPPort> wait_connexion(DatagramSocket socket) throws IOException
	{
		List<AdresseIPPort> ListeIPPort = new ArrayList<>();
		byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        int leave = 0;
        while(leave==0) {
        	socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());
            ListeIPPort.add(new AdresseIPPort(dpR.getAddress(), dpR.getPort()));
            if(message.equals("1")) {
            	leave = 1;
            }
        }
		return ListeIPPort;
	}
	
	private void Send_order(List<AdresseIPPort> ListeIPPort,DatagramSocket socket) throws InterruptedException, IOException
	{
		byte[] bufE_r = new String("red\n").getBytes();
		byte[] bufE_g = new String("green\n").getBytes();
		while(true) {
			for (AdresseIPPort client : ListeIPPort) {
		        DatagramPacket dpS_1 = new DatagramPacket(bufE_r, bufE_r.length, client.getAdresseIP(), client.getPort());
		        socket.send(dpS_1);
		        Thread.sleep(1000);
		        DatagramPacket dpS_2 = new DatagramPacket(bufE_g, bufE_g.length, client.getAdresseIP(), client.getPort());
		        socket.send(dpS_2);
		    }
		}
	}
	
}
