package tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Exo3_client {
	static public void main(String[] args) throws IOException
	{
		Exo3_client exo3_client = new Exo3_client();
		exo3_client.execute();
	}
	@SuppressWarnings("resource")
	private void execute() throws IOException
	{
		FileOutputStream fos = new FileOutputStream("/home/userir/file_client");
		Socket socket = new Socket();
		
		long start = System.currentTimeMillis();
		
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 5099);
		socket.connect(adrDest);
		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		int lenBufR = is.read(bufR);
		while(lenBufR!=-1)
		{
			fos.write(bufR,0,lenBufR);
			lenBufR = is.read(bufR);
		}
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stop-start)+" ms");
		System.out.println("Fin transfert fichier");
		socket.close();
	}
}
