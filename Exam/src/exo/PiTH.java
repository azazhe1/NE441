package exo;

import java.util.ArrayList;

public class PiTH extends Thread{

	public static int nb_thread=16;
	public static int N=2000;
	int debut;
	double res;
	public PiTH(int debut) {
		this.debut = debut;
		this.res = 0;
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		ArrayList<PiTH> list = new ArrayList<>();
		for(int i=0;i<nb_thread;i++)
		{
			list.add(new PiTH(i));
		}
		
		for(PiTH pi: list)
		{
			pi.start();
		}
		for(PiTH pi: list)
		{
			pi.join();
		}
		double resultat = 0;
		for(PiTH pi: list)
		{
			resultat += pi.res;
		}
		System.out.println(resultat*4);
		
	}
	
	public void run()
	{
		for(long i=this.debut; i<=N;i+=this.nb_thread)
		{
			if(i%2==0)
			{
				this.res += 1.0/(2*i +1) ;
			}else
			{
				this.res += (-1.0)/(2*i +1) ;
			}
		}
	}
	
}
