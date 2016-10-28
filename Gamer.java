package simulatorcasino;

public class Gamer {

    private static Gamer inst;
    private static int state;
    
   public static synchronized Gamer getInst() 
   {if(inst == null)
      inst = new Gamer();
    return inst;
   }
   

   public int getState()
   {
       return this.state;
   }
   
   public void setState(int x)
   {
       this.state = x;
   }
   
   public void addMoney(int x) 
   { state+=x;
   }

   public void substractMoney(int x) 
   { state-=x;
   }
}
