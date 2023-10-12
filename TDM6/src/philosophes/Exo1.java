package philosophes;

public class Exo1 extends Thread {
	
	int num;
	
	public Exo1(int i)
	{
		this.num =i;
	}

	public static void main(String[] args) {
		int nb_philo =50;
		for(int i=1; i<= nb_philo;i++)
		{
			new Exo1(i).start();
		}
	}
	
	public void run()
	{
		long leftLimit = 1000L;
	    long rightLimit = 10000L;
		while(true)
		{
			
		    long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
			System.out.println("Le philo " + this.num + " discute");
			try {
				Thread.sleep(generatedLong);
				System.out.println("Le philo " + this.num + " mange");
				generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
				Thread.sleep(generatedLong);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
