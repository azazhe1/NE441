package thread;

public class Calculatrice extends Thread
{
    private Somme somme;

    public Calculatrice(Somme somme)
    {
        this.somme = somme;
    }

    @Override
    public void run()
    {
    	
        int res = 0;
        for (int i = 0; i < 1000; i++)
        {
            res= somme.somme(res, i);
        }
        System.out.println("La somme 1+2+3+4+...+998+999 est égale à :"+res);
    }


    public static void main(String[] args) throws InterruptedException
    {
        Somme somme = new Somme();
        Calculatrice c1 = new Calculatrice(somme);
        Calculatrice c2 = new Calculatrice(somme);

        c1.start();
        c2.start();
    }


    static  class Somme
    {

        int c;
       Object mutex = new Object();
        
        	public int somme(int a, int b) 
            {
        		synchronized(mutex)
        		{
                c=a+b;
                System.out.println("c="+c);
                return c;
        		}
            }
        

    }

}