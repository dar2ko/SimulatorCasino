package simulatorcasino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


class BlackJack {
    private static BlackJack blackjack;
    private static int all=0;
    private static int won=0;
    private static boolean bidup=true;
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
        score(gamer(),croupier());
      
        
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
        bidup=false;
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
        System.out.println("\n\nNacisnij dowolny klawisz aby kontynuowac...");
        input.nextLine();
        
    
       
    } 
    /*Movements of gamer    
    */
    public int gamer()
    { int part_sum=0;
      String dec="O";
      SimulatorCasino.cls();
      System.out.println("************************************************");
      System.out.println("**************RUCHY GRACZA**********************");
      System.out.println("************************************************");
      
   do{   
      
      part_sum=0;
     //displays cards 
      System.out.println("\n\n\nGracz ma nastepujace karty:");
           for(int x: gamerCards) System.out.print("  "+x+"  ");
                System.out.println("\n");
     //sum of cards           
      for(int x : gamerCards)
            part_sum+=x;
      System.out.println("Suma kart to: "+part_sum);
      
      if(part_sum==21) {System.out.println("Gracz nie ma juz dostepnych ruchow"); break;}
      else if(part_sum>21&&gamerCards.contains(11)) ace();
      else if (part_sum>21) {System.out.println("Gracz nie ma juz dostepnych ruchow"); break;}
      else{ do
            {System.out.println("Prosze wybrac dzialanie: ");
            System.out.println("P - przeczekaj ");
            System.out.println("H - dobierz karte");
            if(Gamer.getInst().getState()>60) System.out.println("B - podbij stawke");
            dec = input.next().toUpperCase(); 
           } while(!dec.equals("P")&&!dec.equals("H")&&!dec.equals("B"));
          
          if(dec.equals("P")) break;
          if(dec.equals("H")) hit(gamerCards);
          if(dec.equals("B")) bid(); 
      }  
   } while(!dec.equals("P"));
   return part_sum;
    }
    public void bid() {
        if (bidup==false)
            {System.out.println("\nZ konta zostaje pobrane $60, ktore zwieksza stawke");
            Bank.getInst().addMoney(60);
            Gamer.getInst().subtractMoney(60);
            bidup=true;}
        else System.out.println("\nJuz raz podwoiles stawke - nie mozesz tego zrobic ponownie.");
    }
    /* This function decides about changes in croupier's cards using the rules:
    1. if sum of his cards is <=16 -> crouper hits new card
    2. if sum is >16 and there's no ace in cards -> crouper stands
    3. if sum is >21 and there's ace in cards -> ace changes value to 1'
    Default value of ace is 11.
    */
    public int croupier() 
    { int part_sum;
      boolean var = false;
      System.out.println("************************************************");
      System.out.println("**************RUCHY KRUPIERA********************");
      System.out.println("************************************************");
      
        do{part_sum=0;
           System.out.println("Krupier ma nastepujace karty:");
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
    /*This function changes the value of ace to 1.
    */
    public void ace() {
       System.out.println("\n\nSuma kart jest wieksza od 21, ale w kartach gracza znajduje sie as\n"
                          +"Jego wartosc zmienila sie na 1...");
       gamerCards.set(gamerCards.lastIndexOf(11), 1);
       System.out.println("Nacisnij dowolny klawisz aby kontynuowac...");
       input.nextLine();
    }

    public void score(int gamer, int croupier) {
        System.out.printf("  WYNIK GRACZA: %15d", gamer);
        System.out.printf("WYNIK KRUPIERA: %15d", croupier);
        if (gamer>21)
            if (croupier>21) System.out.println("Nikt nie wygral.");
            else System.out.println("Gracz przegral");
        if (gamer==21) System.out.println("GRACZ WYGRAL BLACKJACKA!!! ");
    }

    
}
