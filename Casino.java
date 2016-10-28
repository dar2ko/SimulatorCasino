package simulatorcasino;

import java.util.Scanner;


public class Casino {
    public static Scanner input = new Scanner(System.in);
    
    //main scenario
    public Casino()
    {   //welcome message
        System.out.println("****************************************************");
        System.out.println("*********************KASYNO*************************");
        System.out.println("****************************************************");
        int money;
        do {
        System.out.print("Powiedz, ile masz pieniedzy: >  ");
        money = input.nextInt();
        if (money<30) System.out.println("Podales zla ilosc pieniedzy, najtansza gra kosztuje $30, sprobuj ponownie:"); 
        } while (money<30);
        
        Gamer.getInst().setState(money); 
        String dec;
        BlackJack bj; 
        JackPot jp;
      do{  
        do {
            System.out.println("Dokonaj wyboru:");
            System.out.println("B - gra w BlackJack ($60)");
            System.out.println("J - gra w JackPot ($30)");
            System.out.println("Q - wyjscie");
            dec = input.next().toUpperCase(); 
           } while(!dec.equals("Q")&&!dec.equals("B")&&!dec.equals("J"));
        if (dec.equals("B"))
            if (Gamer.getInst().getState()>=60) bj = new BlackJack();
            else System.out.println("Masz za malo pieniedzy na gre w BlackJack");
        if (dec.equals("J"))
            if (Gamer.getInst().getState()>=30) jp = new JackPot();
            else System.out.println("Masz za malo pieniedzy na gre w JackPot");
        
      }while (Gamer.getInst().getState()>30 && !dec.equals("Q"));
            
        SimulatorCasino.cls();
        System.out.println("****************************************************");
        System.out.println("******************RAPORT   KONCOWY******************");
        System.out.println("****************************************************");
        System.out.println("Rozegranych gier BlackJack: "+BlackJack.getAll());
        if(BlackJack.getAll()>0) System.out.println("Stosunek zwyciestw: "+BlackJack.ratio());
        System.out.println("Rozegranych gier JackPot: "+JackPot.getAll());
        if(JackPot.getAll()>0) System.out.println("Stosunek zwyciestw: "+JackPot.ratio());
        System.out.println("Bilans koncowy: "+(100000-Bank.getInst().getMoney()));
    }
}
