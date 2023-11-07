package blan;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PasswordFinder {
	public static void main(String[] args) throws IOException {
		PasswordFinder pass = new PasswordFinder();
		pass.execute();
	}

	private void execute() throws IOException {
		// TODO Auto-generated method stu
		Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 7500);
        socket.connect(adrDest);
        byte[] bufR = new byte[1];
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        for(int i=0;i<16;i++)
        {
        	getOnePassword(is,os,i);
        }
	}

	private void getOnePassword(InputStream is, OutputStream os, int i) throws IOException{
		// TODO Auto-generated method stub
		String start_pass = getStart(is);
		long taille = getTaille(is);
		skipData(is,taille);
		String end_pass = getEnd(is);
		sendPassword(start_pass,end_pass,os);
		
		
	}

	private void sendPassword(String start_pass, String end_pass, OutputStream os) throws IOException {
		// TODO Auto-generated method stub
		byte[] bufE = new String(start_pass+end_pass+";").getBytes();
		System.out.println(start_pass+end_pass);
		os.write(bufE);
	}

	private String getEnd(InputStream is) throws IOException {
		String buff = new String("");
		byte[] bufR = new byte[1];
		int nb_etoile =0;
		while(nb_etoile<1)
		{
			is.read(bufR);
			String s = new String(bufR);
			if(s.equals("*"))
			{
				nb_etoile+=1;
			}else {
				buff+=s;
			}
		}
		
		return buff;
	}

	private void skipData(InputStream is, long taille) throws IOException {
		// TODO Auto-generated method stub
		
		while(taille>0)
		{
			int bufSize =10_000;
			if(bufSize>taille)
			{
				bufSize = (int) taille;
			}
			byte buf[] = new byte[bufSize];
			int len = is.read(buf);
			taille = taille -len;
		}
		
	}

	private long getTaille(InputStream is) throws IOException {
		String buff = new String("");
		byte[] bufR = new byte[1];
		int nb_etoile =0;
		while(nb_etoile<1)
		{
			is.read(bufR);
			String s = new String(bufR);
			if(s.equals("*"))
			{
				nb_etoile+=1;
			}else {
				buff+=s;
			}
		}
		
		return Long.parseLong(buff);
	}

	private String getStart(InputStream is) throws IOException {
		String buff = new String("");
		byte[] bufR = new byte[1];
		int nb_etoile =0;
		while(nb_etoile<1)
		{
			is.read(bufR);
			String s = new String(bufR);
			if(s.equals("*"))
			{
				nb_etoile+=1;
			}else {
				buff+=s;
			}
		}
		
		return buff;
	}

}
