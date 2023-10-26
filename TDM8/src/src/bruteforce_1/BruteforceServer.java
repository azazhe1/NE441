package bruteforce_1;

import java.rmi.AlreadyBoundException;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BruteforceServer{
	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException, AlreadyBoundException
    {
		System.setProperty("java.rmi.server.hostname", "192.168.1.44");
		
		BruteforceMD5 brutemd5 = new BruteforceMD5();
		
		//Bruteforce skeleton = (Bruteforce) UnicastRemoteObject.exportObject(brutemd5,7070);
		Registry registry = LocateRegistry.getRegistry("192.168.1.44",5050);
		registry.rebind("bruteforce_1", brutemd5);
    }

}
