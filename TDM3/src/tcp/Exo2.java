package tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Exo2 {
	public static void main(String[ ] args) throws IOException
	{
		Exo2 exo2 = new Exo2();
		exo2.execute();
	}
	
	private void execute() throws IOException
	{
		Socket socket = new Socket();

        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4000);
        socket.connect(adrDest);        

        byte[] bufE = new String("test\n").getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        os.close();
        socket.close();	
    }
}