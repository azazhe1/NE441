package bruteforce_1;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5decode extends Thread{
	long debut;
	long fin;
	byte[] hash;
	MessageDigest digest ;
	public String mdp;
	
	public MD5decode(long debut, long fin, String hash) throws NoSuchAlgorithmException
	{
		this.debut = debut;
		this.fin = fin;
		int len = hash.length();
		this.hash = new byte[len / 2];
		for (int i = 0; i < len; i += 2) 
		{
			this.hash[i/2] = (byte) ((Character.digit(hash.charAt(i), 16) << 4) + Character.digit(hash.charAt(i+1), 16));
		}
		this.digest = MessageDigest.getInstance("MD5");
	}
	public void run() {
			mdp = try_bruteforce();
	}
	
	public byte[] md5(String str)
	{
		byte[] hash = digest.digest(str.getBytes());
		return hash;
	}
	
	public String try_bruteforce()
	{
		String res =new String("none;");
		
		for(long i=debut;i<=fin;i+=16)
		{
			String str = String.format("%09d", i);
			str = "p2025esisar" + str;
			byte[] hash_test = md5(str);
			if(Arrays.equals(hash_test,hash))
			{
				
				return str;
			}
			
		}
		return res;
	}

}
