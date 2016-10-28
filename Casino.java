package simulatorcasino;

import java.util.Scanner;


public class Casino {
    public static Scanner input = new Scanner(System.in);
    
    public Casino()
    {
        System.out.println("****************************************************");
        System.out.println("*********************KASYNO*************************");
        System.out.println("****************************************************");
        int money;
        do {
        System.out.print("Powiedz, ile masz pieniedzy: >  ");
        money = input.nextInt();
        if (money<=0) System.out.println("Podales zla ilosc pieniedzy, sprobuj ponownie:"); 
        } while (money<=0);
        
        Gamer.getInst().setState(money); 
        char dec;
        
        do {
            System.out.println("Dokonaj wyboru:");
            System.out.println("B - gra w BlackJack ($60)");
            System.out.println("J - gra w JackPot ($30)");
            System.out.println("Q - wyjscie");
            dec = input.nextLine().toUpperCase().charAt(0);
            
        } while (Gamer.getInst().getState()>0 && dec!='q');
            
        
    }
}
