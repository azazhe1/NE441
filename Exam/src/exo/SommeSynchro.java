package exo;

public class SommeSynchro extends Thread{

	private int x;
	
	private Object mutex = new Object();

	
	public SommeSynchro(int x) {
		this.x = x;
	}
	
	public void run() {
		int a = f(x);
		synchronized (mutex) {
			
			res = res+a;
		}
	}
	
	private int f(int z )
	{
		return z = z+1;
	}
	static public int res =0;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int nbThread = 100000;
		Thread[] ths = new Thread[nbThread];
		for(int i=0;i<ths.length;i++)
		{
			ths[i] = new SommeSynchro(i);
			ths[i].start();
		}
		for(int i=0;i<ths.length;i++)
		{
			ths[i].join();
		}
		System.out.println("res "+res);
	}

}
