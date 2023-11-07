package p2023;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PingPongTCP {

	public static void main(String[] args) throws IOException {
		PingPongTCP pingpongTCP = new PingPongTCP();
		pingpongTCP.execute();
			

	}

	private void execute() throws IOException {
		Socket socket_1 = new Socket();
        InetSocketAddress adrDest_1 = new InetSocketAddress("127.0.0.1", 8000);
        socket_1.connect(adrDest_1); 
        InputStream is_1 = socket_1.getInputStream();
        
        Socket socket_2 = new Socket();
        InetSocketAddress adrDest_2 = new InetSocketAddress("127.0.0.1", 8200);
        socket_2.connect(adrDest_2); 
        InputStream is_2 = socket_2.getInputStream();
        OutputStream os = socket_2.getOutputStream();
        maketransfertFile(is_1,is_2,os);
        
        socket_1.close();
        socket_2.close();
		
	}

	private void maketransfertFile(InputStream is_1, InputStream is_2, OutputStream os) throws IOException {
		
		dotranfert(is_1,os);
		getReponse(is_2);
		
	}

	private void dotranfert(InputStream is_1, OutputStream os) throws IOException {
		byte[] bufR = new byte[1_000_000_000];
		int len = is_1.read(bufR);
		long modulo =0;
		while (len!=-1)
		{
			byte[] bufE = new byte[(len/2)+1];
			int lenE =0;
			for(int i=0;i<len;i++)
			{
				if(modulo%2==0)
				{
					bufE[i/2] = bufR[i];
					lenE++;
				}
				modulo++;
			}
			os.write(bufE,0,lenE);
			len = is_1.read(bufR);
			
		}
	}

	private void getReponse(InputStream is_2) throws IOException {
		byte[] bufR = new byte[1];
		int len = 0;
		while(len!=-1)
		{
			len = is_2.read(bufR);
			String s  = new String(bufR);
			System.out.print(s);
		}
		
	}

}
