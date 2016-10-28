package simulatorcasino;

public class Bank 
{
    private static Bank inst;
    private int state = 999999;

        //singleton
        public static synchronized Bank getInstance() 
        {if(inst == null)
		inst = new Bank();
         return inst;
	}
        
        public int getBank() {
		return state;
	}
        
	public void addMoney(int x) {
		state+=x;
	}

	public void substractMoney(int x) {
		state-=100;
	}
        
}
