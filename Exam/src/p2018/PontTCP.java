package p2018;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PontTCP {
	public static void main(String[] args) throws IOException
	{
		PontTCP client = new PontTCP();
		client.execute();
	}

	private void execute() throws IOException {
		Socket socket1 = new Socket();
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 8000);
        socket1.connect(adrDest);
        Socket socket2 = new Socket();
        InetSocketAddress adrDest2 = new InetSocketAddress("127.0.0.1", 8200);
        socket2.connect(adrDest2);
        OutputStream os = socket2.getOutputStream();
        InputStream is = socket1.getInputStream();
        InputStream is2 = socket2.getInputStream();
        
        makePont(is,os,is2);
        
        socket1.close();
        socket2.close();
	}

	private void makePont(InputStream is, OutputStream os, InputStream is2) throws IOException {
		transfereFile(is,os);
		getReponse(is2);
		
	}

	private void getReponse(InputStream is2) throws IOException {
		byte[] buffR = new byte[1];
		while( is2.read(buffR)!=-1)
		{
			String s = new String(buffR);
			if(s.equals("U"))
			{
				break;
			}
			System.out.print(s);
		}
		
	}

	private void transfereFile(InputStream is, OutputStream os) throws IOException {
		byte[] buff = new byte[1];
		while(is.read(buff)!=-1)
		{
			os.write(buff);
		}
		System.out.println("fin envoi");
	}
}
