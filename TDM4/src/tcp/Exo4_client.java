package tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Exo4_client {
	static public void main(String[] args) throws IOException
	{
		Exo4_client exo4_client = new Exo4_client();
		if(args.length!=1)
		{
			System.out.println("Usage : <file_name>");
		}else {
			exo4_client.execute(args[0]);
		}
		
	}
	@SuppressWarnings("resource")
	private void execute(String arg) throws IOException
	{
		String[] parts = arg.split("/");
	    String name_file = parts[parts.length - 1];
		
		FileOutputStream fos = new FileOutputStream(name_file);
		Socket socket = new Socket();
		
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 5099);
		socket.connect(adrDest);
		
		byte[] bufE = new String(arg+"\n").getBytes();
		
		OutputStream os = socket.getOutputStream();
        os.write(bufE);
        
        byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		int lenBufR = is.read(bufR);
		while(lenBufR!=-1)
		{
			fos.write(bufR,0,lenBufR);
			lenBufR = is.read(bufR);
		}
		System.out.println("Fin transfert fichier");
		socket.close();
		
	}
}
