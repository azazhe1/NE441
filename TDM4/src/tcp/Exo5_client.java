package tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Exo5_client {
	static public void main(String[] args) throws IOException
	{
		Exo5_client exo5_client = new Exo5_client();
		if(args.length!=1)
		{
			System.out.println("Usage : <file_name>");
		}else {
			exo5_client.execute(args[0]);
		}
		
	}
	@SuppressWarnings({ "resource", "removal" })
	private void execute(String arg) throws IOException
	{
		String[] parts = arg.split("/");
	    String name_file = parts[parts.length - 1];
		
		FileOutputStream fos = new FileOutputStream(name_file);
		Socket socket = new Socket();
		
		long Max_len;
		long Curent_len;
		long Older_value;
		long Curent_value;
		
		
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 5099);
		socket.connect(adrDest);
		
		byte[] bufE = new String(arg+"\n").getBytes();
		
		OutputStream os = socket.getOutputStream();
        os.write(bufE);
        
        byte[] bufR = new byte[2048];
        InputStream is = socket.getInputStream();
        
        // Récupération de la taille
        String msg = new String("");
		int lenBufR=0;
		while(!msg.contains("\n"))
		{
			lenBufR = is.read(bufR);
			msg = msg + new String(bufR, 0 , lenBufR);
		}
		msg = msg.replace("\n","");
		Max_len = Long.parseLong(msg);
		
		//Récupération de la taille
		lenBufR = is.read(bufR);
		Curent_len = lenBufR;
		Curent_value = (Curent_len*100)/Max_len;
		Older_value = Curent_value;
		print_pourcentage(Curent_value);
		while(lenBufR!=-1)
		{
			fos.write(bufR,0,lenBufR);
			lenBufR = is.read(bufR);
			Curent_len+= lenBufR;
			Curent_value = (Curent_len*100)/Max_len;
			if(Older_value<Curent_value)
			{
				
				print_pourcentage(Curent_value);
				Older_value = Curent_value;
			}
			
		}
		System.out.println("Fin transfert fichier");
		socket.close();	
	}
	
	private void print_pourcentage(long value) {
	    System.out.print("[");
	    int i = 0;
	    for (; i <= (int)(value/2); i++) {
	        System.out.print("=");
	    }
	    for (; i <= 50; i++) { // Remplissez le reste avec des espaces
	        System.out.print(".");
	    }
	    System.out.println("] "+value+"%"); // Nouvelle ligne pour une sortie propre
	}
	
}
