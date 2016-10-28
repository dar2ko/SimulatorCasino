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
        int gamer_sum = gamer();
        int croupier_sum = croupier();
      //200  sum();
        
    }
   /*This function recovers default settings and 2 cards are dealt to croupier and gamer
    */
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
        {   index = rand.nextInt(deckOfCards.size());
            gamerCards.add(deckOfCards.get(index));
            deckOfCards.remove(index);
        }
        
        for(int i=0;i<2;i++)
        {   index = rand.nextInt(deckOfCards.size());
            croupierCards.add(deckOfCards.get(index));
            deckOfCards.remove(index);
        }
     //displays information about both cards of gamer and one of the cards of croupier   
        System.out.printf("Karty gracza: %10d %10d\n", gamerCards.get(0), gamerCards.get(1));
        System.out.printf("Karta krupiera: %10d", croupierCards.get(0));
        
    
       
    }
    /* This function decides about changes in croupier's cards using the rules:
    1. if sum of his cards is <=16 -> crouper hits new card
    2. if sum is >16 and there's no ace in cards -> crouper stands
    3. if sum is >21 and there's ace in cards -> ace changes value to 1'
    Default value of ace is 11.
    */
    public int gamer()
    {
        
    }
    public int croupier() //function returns sum of croupier's cards after potential automatic changes
    { int part_sum;
      boolean var = false;
      SimulatorCasino.cls();
      
        do{part_sum=0;
           System.out.println("\n\n\nKrupier ma nastepujace karty:");
           for(int x: croupierCards) System.out.print("  "+x+"  ");
                System.out.println("\n");
           for(int x : croupierCards)
            part_sum+=x;
            System.out.println("Suma kart krupiera to: "+part_sum);
           if(part_sum>16) 
               if (part_sum>21&&croupierCards.contains(11)) {croupierCards.set(croupierCards.lastIndexOf(11), 1);
                               System.out.println("W kartach byl as, a suma kart przekroczyla 21, wiec w miejsce asa wstawiamy wartosc 1");}
               else var = true; 
           else {hit(croupierCards); System.out.println("Krupier dobiera nowa karte...");}
        } while(var == false);
        
        return part_sum;
    }
    
    public void hit(List<Integer> name)
    {
            int index = rand.nextInt(deckOfCards.size());
            name.add(deckOfCards.get(index));
            deckOfCards.remove(index);
    }
}
