package p2023;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Pong {

	public static void main(String[] args) throws IOException {
		Pong client = new Pong();
		client.execute();

	}

	private void execute() throws IOException {
		Socket socket_2 = new Socket();
		Socket socket_1 = new Socket();
		
		socket_1.connect(new InetSocketAddress("127.0.0.1", 8000));
		socket_2.connect(new InetSocketAddress("127.0.0.1", 8200));
		
		InputStream is_1 = socket_1.getInputStream();
		InputStream is_2 = socket_2.getInputStream();
		OutputStream os = socket_2.getOutputStream();
		
		makeTCPPont(is_1,is_2,os);
	}

	private void makeTCPPont(InputStream is_1, InputStream is_2, OutputStream os) throws IOException {
		doTransfer(is_1,os);
		
	}

	private void doTransfer(InputStream is, OutputStream os) throws IOException {
		// TODO Auto-generated method stub
		byte[] bufR = new byte[1_000_000_000];
		int lenBufR = is.read(bufR);
		int modulo = 0;
		
		while(lenBufR!=-1)
		{
			byte[] bufE = new byte[(lenBufR/2)+1];
			int lenBufE=0;
			for(int i=0;i<lenBufR;i++)
			{
				if(modulo%2==0)
				{
					bufE[i/2] = bufR[i];
					lenBufE++;
				}
				modulo++;
			}
			os.write(bufE,0,lenBufE);
			lenBufR =  is.read(bufR);
			
		}
	}

}
