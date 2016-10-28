/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorcasino;

/**
 *
 * @author Mariusz Krz
 */
class BlackJack {
    private static BlackJack blackjack;
    private static int all=0;
    private static int won=0;
    
    public static synchronized BlackJack inst() 
    {if(blackjack == null) 
        {
            blackjack = new BlackJack();
        }
        return blackjack;}
    static int getAll() 
    { return all;
    }

    static double ratio() 
    { return won/all;
    }

    void gameBlackJack() {
    
    }
    
}
