package thread;

import java.util.ArrayList;

public class Exo2 extends Thread {
	
	double res =0d;
	public long start;
	public long nb_thread;
	
	public Exo2(long start, long nb_thread){
		this.start = start;
		this.nb_thread = nb_thread;
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		
		long[] nb_Thread= {2l,4l,8l,16l,32l,64l,512l,2048l};
		
		for (long i = 0l; i < nb_Thread.length; i++) {
			System.out.println("Pour "+nb_Thread[(int)i]+ " Thread");
			ArrayList<Exo2> list = new ArrayList<>();
			
			for(long a =0l; a<nb_Thread[(int)i];a++)
			{
			 list.add(new Exo2(a,nb_Thread[(int)i]));
			}
			long start = System.currentTimeMillis();
			for(Exo2 exo2: list)
			{
				exo2.start();
			}
			for(Exo2 exo2: list)
			{
				exo2.join();
			}
			long stop = System.currentTimeMillis();
			double résultat = 0d;
			for(Exo2 exo2: list)
			{
				résultat+=exo2.res;
			}
			System.out.println("Pi vaut :"+ résultat*4);
			System.out.println("Elapsed Time = "+(stop-start)+" ms");
			
        }
	}
	
	public void run()
	{
		long N = 10_000_000_000l;
		for(long i=this.start; i<=N;i+=this.nb_thread)
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
