package bruteforce_1;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;

public class BruteforceClient extends Thread{
	private String hash;
	private Bruteforce stub;
	private long min;
	private long max;
	
	public BruteforceClient(String hash,Bruteforce stub,long min,long max)
	{
		this.hash = hash;
		this.stub = stub;
		this.min =min;
		this.max =max;
	}
	
	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException, NotBoundException
	{
		Registry registry = LocateRegistry.getRegistry("192.168.1.44",5050);
		Bruteforce stub = (Bruteforce) registry.lookup("bruteforce");
		
		System.out.println("Début du programme client");
		String hash = new String("955280f85fd826f5ae016deb6b32ed40");
		
		long largeur = (999999999-0)/16;
		for(int i=0;i<16;i++)
		{
			BruteforceClient client = new BruteforceClient(hash,stub,i*largeur,(i+1)*largeur);
			client.start();
		}
		
	}
	
	public void run() 
	{
		try {
			String res = stub.bruteforceMD5(min, max, hash);
			if(!res.contains(";"))
			{
				System.out.println("Le password est " + res);
			}
		} catch (RemoteException | NoSuchAlgorithmException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
