package p2023;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PasswordFinder {

	public static void main(String[] args) throws IOException {
		PasswordFinder client = new PasswordFinder();
		client.execute();

	}

	private void execute() throws IOException {
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress("127.0.0.1", 7500));
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		for(int i=1; i<=16;i++)
		{
			getOnePass(is,os,i);
		}
		
		socket.close();
	}

	private void getOnePass(InputStream is, OutputStream os, int i) throws IOException {
		String debut = getDebut(is);
		long taille = getTaille(is);
		skipData(is,taille);
		String end = getEnd(is);
		System.out.println(" Password "+i +" = " +debut+end);
		sendPass(os,debut+end+";");
		
	}

	private void sendPass(OutputStream os, String string) throws IOException {
		byte[] bufE = string.getBytes();
		os.write(bufE);
	}

	private String getEnd(InputStream is) throws IOException {
		byte[] bufR = new byte[1];
		String end = new String("");
		while(true)
		{
			is.read(bufR);
			String s = new String(bufR);
			
			if(s.equals("*"))
			{
				break;
			}
			end+=s;
		}
		
		return end;
	}

	private void skipData(InputStream is, long taille) throws IOException {
		
		int len = 0;
		while(taille>0)
		{
			int size= (int)taille;
			if(10_000<taille)
			{
				size = 10_000;
			}
			byte[] bufR = new byte[size];
			len = is.read(bufR);
			taille= taille - len;
		}
	}

	private long getTaille(InputStream is) throws IOException {
		byte[] bufR = new byte[1];
		String taille = new String("");
		while(true)
		{
			is.read(bufR);
			String s = new String(bufR);
			if(s.equals("*"))
			{
				break;
			}
			taille+=s;
		}
		
		
		return Long.parseLong(taille);
	}

	private String getDebut(InputStream is) throws IOException {
		byte[] bufR = new byte[1];
		String debut = new String("");
		while(true)
		{
			is.read(bufR);
			String s = new String(bufR);
			if(s.equals("*"))
			{
				break;
			}
			debut+=s;
		}
		
		
		return debut;
	}

}
