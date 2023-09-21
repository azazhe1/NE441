package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Exo4 {
	public static void main(String[] args) throws IOException
	{
		Exo4 exo4 = new Exo4();
		exo4.execute();
	}
	
	private void execute() throws IOException
	{
		Socket socket = new Socket();

        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 7500);
        socket.connect(adrDest);
        String buffer = new String("");
        byte[] bufR = new byte[2048];
        InputStream is = socket.getInputStream();
        int lenBufR = is.read(bufR);
        
        
        while(lenBufR!=-1)
        {
        	String msg = buffer + new String(bufR,0,lenBufR);
        	while(!msg.contains("?"))
        	{
        		lenBufR = is.read();
        		msg = msg + new String(bufR,0,lenBufR);
        	}
        	
        	String Fullmsg = msg.substring(0,msg.lastIndexOf("\\?")+1);
        	buffer = msg.substring(msg.lastIndexOf("\\?")+1);
        	
        	 System.out.println(msg);
        	 String reponse = new String("");
             
             System.out.println(msg);
             
             //On sépare toutes les opérations complètes par le "?"
             String[] operations = Fullmsg.split("\\?"); 
             
             for(int i = 0; i< operations.length; i++) {
             	//Pour chaque opération complète on enlève le "=" et on split au niveau du "+" 
             	//pour avoir les deux opérandes
             	
             	String[] op = operations[i].split("\\=");
             	String[] operandes = op[0].split("\\+");
             	
             	//On construit notre réponse finale en concaténant toutes les réponses des opérations 
             	//(séparées par un ";")
             	reponse = reponse + (Integer.parseInt(operandes[0]) + Integer.parseInt(operandes[1])) + ";";
             	
             }
            
             byte[] bufE = new String(reponse).getBytes();
             OutputStream os = socket.getOutputStream();
             os.write(bufE);
             System.out.println("Message envoye : " + reponse);
             lenBufR = is.read(bufR);
             
        }
        
        
        socket.close();
	}
}
