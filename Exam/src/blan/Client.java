package blan;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.execute();
	}

	private void execute() throws IOException, InterruptedException {
		Socket socket = new Socket();
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 7500);
        socket.connect(adrDest); 
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        int max =0;
        while(max<100_000)
        {
        	makeAddition(is,os);
        	max++;
        	
        }
        getCalcul(is);
        sendReponse("1", os);
        getScore(is);
        
	}

	private void getScore(InputStream is) throws IOException {
		byte[]  buffR = new byte[1];
		int len = 0;
		while(len!=-1)
		{
			len = is.read(buffR);
			System.out.print(new String(buffR));
		}
	}

	private void makeAddition(InputStream is, OutputStream os) throws IOException {
		
		String calcul = getCalcul(is);
		System.out.print(calcul);
		String res = doCalcul(calcul);
		System.out.println(res);
		sendReponse(res,os);
		
	}

	private void sendReponse(String res, OutputStream os) throws IOException {
		byte[] buffE = new String(res+";").getBytes();
		os.write(buffE);
	}

	private String doCalcul(String calcul) {
		String op1 = calcul.substring(0,calcul.indexOf("+"));
		String op2 = calcul.substring(calcul.indexOf("+")+1,calcul.indexOf("="));
		int res = Integer.parseInt(op1) + Integer.parseInt(op2);
		return Integer.toString(res);
	}

	private String getCalcul(InputStream is) throws IOException {
		byte[] buffR = new byte[1];
		String buff = new String("");
		
		while(true)
		{
			is.read(buffR);
			String s = new String(buffR);
			if(s.contains("?"))
			{
				break;
			}else {
				buff+= s;
			}
		}
		return buff;
	}

}
