package thread;

public class Exo1 {
	static public void main(String[] args)
	{
		Exo1 exo1 = new Exo1();
		exo1.execute();
	}
	
	private void execute()
	{
		double resultat =0d;
		long N = 10_000_000_000l;
		long start = System.currentTimeMillis();
		for(long i=0l; i<=N;i++)
		{
			if(i%2==0)
			{
				resultat += 1.0/(2*i +1) ;
			}else
			{
				resultat += (-1.0)/(2*i +1) ;
			}
		}
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stop-start)+" ms");
		System.out.println("Pi vaut : " + resultat*4d);
		
	}
}
