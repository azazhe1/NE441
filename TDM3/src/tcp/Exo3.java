package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Exo3 {
	public static void main(String[] args) throws IOException
	{
		Exo3 exo3 = new Exo3();
		exo3.exectute();
	}
	
	private void exectute() throws IOException
	{
		 @SuppressWarnings("resource")
		 ServerSocket socketEcoute = new ServerSocket();
	     socketEcoute.bind(new InetSocketAddress(4000));
	     Socket socketConnexion = socketEcoute.accept();
	     System.out.println("Un client est connecté");
	     System.out.println("IP:"+socketConnexion.getInetAddress());
	     System.out.println("Port:"+socketConnexion.getPort());
	     
	     byte[] bufR = new byte[2048];
	     int lenBufR =0;
	     InputStream is = socketConnexion.getInputStream();
	     while(lenBufR!=-1)
	     {
	    	 lenBufR = is.read(bufR);
	    	 if(lenBufR!=-1)
	    	 {
	    		 String message = new String(bufR, 0 , lenBufR);
		         System.out.println("Message recu = "+message); 
	    	 }
	         
	     }
	     socketEcoute.close();
	}
}
