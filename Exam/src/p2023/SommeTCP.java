package p2023;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class SommeTCP extends Thread{
	private int port;
	private Info info;
	
	public SommeTCP(int port, Info info) {
		// TODO Auto-generated constructor stub
		this.port = port;
		this.info = info;
	}

	public static void main(String[] args) throws InterruptedException {
		ArrayList<SommeTCP> liste = new ArrayList<>();
		Info info = new Info();
		
		for(int i=0;i<=2_000;i++)
		{
			liste.add(new SommeTCP(21_000+i,info));
		}
		for(SommeTCP client:liste)
		{
			client.start();
		}
		
		for(SommeTCP client:liste)
		{
			client.join();
		}
		System.out.println("Le montant maximum est "+info.max+" euros");
		System.out.println("Le port d'écoute correspondant à ce maximum est "+info.maxPort);
		System.out.println("La somme des montants retournés par tous les ports est "+info.montant);
		System.out.println("Fin du programme");
		
	}
	
	public void run()
	{
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress("127.0.0.1", this.port));
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			sendMessage(os);
			
			int montant = getMessage(is);
			
			info.update(montant, this.port);
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private int getMessage(InputStream is) throws IOException {
		byte[] bufR = new byte[1];
		
		int len =is.read(bufR);
		String montant = new String("");
		while(len!=-1)
		{
			String s = new String(bufR);
			if(s.equals("="))
			{
				break;
			}
			is.read(bufR);
		}
		len = is.read(bufR);
		while(len!=-1)
		{
			String s = new String(bufR);
			
			if(s.equals("E"))
			{
				break;
			}
			montant+=s;
			is.read(bufR);
		}
		
		while(is.read()!=-1);
		return Integer.parseInt(montant);
	}

	private void sendMessage(OutputStream os) throws IOException {
		byte[] bufE = new String("COMBIEN").getBytes();
		os.write(bufE);
	}


	static class Info{
		int montant =0;
		int max=0;
		int maxPort;
		Object mutex = new Object();
		
		public void update(int value, int port)
		{
			synchronized (mutex) {
				montant +=value;
				if(value>max)
				{
					max = value;
					maxPort = port;
				}
			}
		}
	}
}
