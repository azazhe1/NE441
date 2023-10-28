package clienthttp;

import java.io.FileOutputStream;
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
		int nb_image = 1;
        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 8000);
        socket.connect(adrDest);  
        byte[] bufR = new byte[1];
        InputStream is = socket.getInputStream();
        int lenBufR;
        String buffer = new String("");
        //while(true)
        //{
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
	        is.read(bufR);//On lis \r\n
	        is.read(bufR);
	        byte[] data = new byte[len];
	        System.out.println(len);
	        FileOutputStream fos = new FileOutputStream("image_"+nb_image+".bmp");
	        nb_image++;
	        while(len!=0)
	        {
	        	lenBufR = is.read(bufR);
	        	String reponse = new String(bufR, 0 , lenBufR );
	        	fos.write(bufR,0,1);
	        	len--;
	        }
	        System.out.println("Fin copie image "+nb_image);
	        //int test = 100;
	        while(true)
	        {
	        	lenBufR = is.read(bufR);

	        	String reponse = new String(bufR, 0 , lenBufR );
	        	if(reponse.equals("\n"))
	        	{
	        		break;
	        	}
	        }
	        is.read(bufR);
	        String reponse = new String(bufR, 0 , lenBufR );
	        System.out.println(reponse);
        //}
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
