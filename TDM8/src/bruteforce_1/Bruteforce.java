package bruteforce_1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

public interface Bruteforce extends Remote{
	
	public String bruteforceMD5(long debut, long fin, String hash) throws RemoteException,NoSuchAlgorithmException,InterruptedException ;

}
