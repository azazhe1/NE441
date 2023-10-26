package bruteforce_1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteforceMD5 extends UnicastRemoteObject implements Bruteforce{
	
	public BruteforceMD5() throws RemoteException 
	{
		
	}
	

	public String bruteforceMD5(long debut, long fin, String hash) throws RemoteException, NoSuchAlgorithmException, InterruptedException {
		ArrayList<MD5decode> cracklist = new ArrayList<>();
		System.out.println("Calcul du hash de "+debut+ " à "+fin);
		for(long i=0;i<16;i++)
		{
			cracklist.add(new MD5decode(debut+i, fin, hash));
		}
		for(MD5decode crack :cracklist)
		{
			crack.run();
		}
		for(MD5decode crack :cracklist)
		{
			crack.join();
			if(!crack.mdp.contains(";"))
			{
				System.out.println("Fin calcul");
				return crack.mdp;
			}
		}
		System.out.println("Fin calcul");
		return "none;";
		
	}
	
	/*
	public static void main(String[] args) throws RemoteException, NoSuchAlgorithmException, InterruptedException {
		//999999999
		String hash = new String("955280f85fd826f5ae016deb6b32ed40");
		BruteforceMD5 brute = new BruteforceMD5();
		String mdp = brute.bruteforceMD5(0,999999999,hash);
		System.out.println(mdp);
	}
	*/
	
}
