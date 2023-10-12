package philosophes;

import tdm.tdm07.exo3.checker.CodeChecker;

public class Exo2 extends Thread{
	int numPhilo;
	private Arbitre arbitre;
	
	
	public Exo2(int numPhilo, Arbitre arbitre)
	{
		this.numPhilo = numPhilo;
		this.arbitre = arbitre;
	}
	
	public static void main(String[] args) {
		int nb_philo = 50;
		Arbitre arbitre = new Arbitre(nb_philo);
		for(int i=1; i<= nb_philo;i++)
		{
			new Exo2(i,arbitre).start();
		}
	}
	
	public void run()
	{

		long leftLimit = 1L;
	    long rightLimit = 10L;
		while(true)
		{
			
		    long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
			//System.out.println("Le philo " + this.numPhilo + " discute");
			try {
				
				//Thread.sleep(generatedLong*1000);
				Thread.sleep(1);
				while(arbitre.autorisation(numPhilo)==false)
				{
					//Thread.sleep(1000);
					Thread.sleep(1);
				}
				//System.out.println("Le philo " + this.numPhilo + " mange");
				generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
				CodeChecker.startEating(numPhilo);
				//Thread.sleep(generatedLong*1000);
				Thread.sleep(1);
				CodeChecker.stopEating(numPhilo);
				arbitre.liberation(numPhilo);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	static class Arbitre
	{
		int[] liste_fourchette;
		int nb_fourchette;
		Object mutex = new Object();
		
		public Arbitre(int nb_fourchette)
		{
			liste_fourchette = new int[nb_fourchette];
			for(int i=0; i<nb_fourchette;i++)
			{
				liste_fourchette[i] = 0;
			}
			this.nb_fourchette = nb_fourchette;
		}
	
		boolean autorisation(int numPhilo)
		{
			synchronized (mutex) {
				if(numPhilo == nb_fourchette)
				{
					if(liste_fourchette[0]==0 && liste_fourchette[nb_fourchette-2]==0)
					{
						liste_fourchette[nb_fourchette-1]=1;
						return true;
					}
					return false;
				}
				if(numPhilo == 1)
				{
					if(liste_fourchette[1]==0 && liste_fourchette[nb_fourchette-1]==0)
					{
						liste_fourchette[0]=1;
						return true;
					}
					return false;
				}
				if(liste_fourchette[numPhilo-2]==0 && liste_fourchette[numPhilo]==0)
				{
					liste_fourchette[numPhilo-1]=1;
					return true;
					
				}
				return false;
			}
		}
		
		void liberation(int numPhilo)
		{
			liste_fourchette[numPhilo-1] = 0;
		}
		
	}
}
