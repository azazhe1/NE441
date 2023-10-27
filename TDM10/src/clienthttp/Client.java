package clienthttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception{
		Client client = new Client();
		client.execute();

	}
	
	private void execute() throws IOException, InterruptedException
	{
		Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 8000);
        socket.connect(adrDest);  
        byte[] bufR = new byte[1];
        InputStream is = socket.getInputStream();
        int lenBufR;
        String buffer = new String("");
        while(true)
        {
        	lenBufR = is.read(bufR);
        	String reponse = new String(bufR, 0 , lenBufR );
        	buffer +=reponse;
        	
        	if(reponse.equals("\n"))
        	{
        		if(test_header(buffer))
        		{
        			System.out.print(buffer);
        			break;
        		}else {
        			System.out.print(buffer);
        			buffer = "";
        		}
        	}
        }
        int len = parse_len(buffer);
        System.out.println(len);
		//Il faut lire encore \r\n. Puis on fait un tableau de la taille de len et on a juste à lire une fois. (avoir pas forcément
	}

	private int parse_len(String buffer) {
		int r = buffer.indexOf("\r");
		String len_str = buffer.substring(16,r);
		return Integer.parseInt(len_str);
	}

	private boolean test_header(String buffer) {
		return buffer.contains("Content-Lengt");
	}
	

}
