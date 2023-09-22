package tcp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exo1 {
	static public void main(String[] args) throws IOException
	{
		Exo1 exo1 = new Exo1();
		exo1.execute();
	}
	
	private void execute() throws IOException
	{
		FileOutputStream fos = new FileOutputStream("/home/userir/file2");
		FileInputStream fis = new FileInputStream("/home/userir/file1");
		
		byte[] buf = new byte[1_000_000];
		int len = fis.read(buf);
		long start = System.currentTimeMillis();
		while(len!=-1)
		{
			fos.write(buf,0,len);
			len = fis.read(buf);
		}
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stop-start)+" ms");
		fis.close();
		fos.close();
	}
}
