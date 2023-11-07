package p2023;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTCP {

	public static void main(String[] args) throws Exception {
		ClientTCP clientTCP = new ClientTCP();
		clientTCP.execute();
	}

	private void execute() throws IOException {
		System.out.println("Début du programme");

		Socket socket1 = new Socket();
		InetSocketAddress adrDest1 = new InetSocketAddress("127.0.0.1", 8000);
		socket1.connect(adrDest1);

		Socket socket2 = new Socket();
		InetSocketAddress adrDest2 = new InetSocketAddress("127.0.0.1", 8200);
		socket2.connect(adrDest2);

		InputStream is1 = socket1.getInputStream();
		InputStream is2 = socket2.getInputStream();
		OutputStream os2 = socket2.getOutputStream();

		byte[] bytes = new byte[1_000_000];
		Integer len = is1.read(bytes);
		Integer i = 0;
		Integer count;
		
		System.out.println("Début du transfert des données depuis serveur 1 vers serveur 2");
		
		while (len != -1) {
			byte[] filtered = new byte[bytes.length];
			count = 0;
			
			for (int j = 0; j < len; j++) {
				if (i % 2 == 0) {
					filtered[j / 2] = bytes[j];
					count ++;
				}

				i += 1;
			}
			
			os2.write(filtered, 0, count);
			len = is1.read(bytes);
		}
		
		System.out.println("Fin du transfert des données.");

		String password = getPassword(is2);
		System.out.println(password);

		os2.close();
		is2.close();
		is1.close();
		socket2.close();
		socket1.close();
		
		System.out.println("Fin du programme.");
	}

	private String getPassword(InputStream is) throws IOException {
		String output = "";
		byte[] bufR = new byte[2048];
		int lenBufR = is.read(bufR);

		while (lenBufR != -1) {
			output += new String(bufR, 0, lenBufR);
			lenBufR = is.read(bufR);
		}

		return output;
	}
}