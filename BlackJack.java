package simulatorcasino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


class BlackJack {
    private static BlackJack blackjack;
    private static int all=0;
    private static int won=0;
    private static Scanner input = new Scanner (System.in);
    private static Random rand = new Random();
    private static List<Integer> deckOfCards = new ArrayList<Integer>();
    private static List<Integer> gamerCards = new ArrayList<Integer>();
    private static List<Integer> croupierCards = new ArrayList<Integer>();
    
    
    public static synchronized BlackJack inst() 
    {if(blackjack == null) 
        {
            blackjack = new BlackJack();
        }
        return blackjack;}
    public int getAll() 
    { return all;
    }
    public double getRatio() 
    { return won/all;
    }
    public void gameBlackJack() 
    {//payment for game
        Bank.getInst().addMoney(60);
        Gamer.getInst().subtractMoney(60);
        all++;
        SimulatorCasino.cls();
        
     //game
        prepare();
    }
    public void prepare()
    { //add cards to deck and remove (if it isn't first round) cards from decks of croupier and gamer
        for(int i=0; i<4; i++)
        {for(int j=2;j<=11;j++)
            {deckOfCards.add(j);
                if (j==10) { for(int k=0;k<3;k++) deckOfCards.add(j); }
            }
        }
        gamerCards.clear();
        croupierCards.clear();
      //add two cards into decks of croupier and gamer
        int index;
        for(int i=0;i<2;i++)
        {
            index = rand.nextInt(deckOfCards.size());
            gamerCards.add(deckOfCards.get(index));
            deckOfCards.remove(index);
        }
        
        for(int i=0;i<2;i++)
        {
            index = rand.nextInt(deckOfCards.size());
            croupierCards.add(deckOfCards.get(index));
            deckOfCards.remove(index);
        }
        for(int i=0;i<deckOfCards.size();i++) System.out.print(" ( "+i+")"+deckOfCards.get(i)+"  ");
        System.out.println("\nKarty gracza");
        for(int i=0;i<gamerCards.size();i++) System.out.print("  "+gamerCards.get(i)+"  ");
        System.out.println("\nKarty krupiera");
        for(int i=0;i<croupierCards.size();i++) System.out.print("  "+croupierCards.get(i)+"  ");
    }
    
}
