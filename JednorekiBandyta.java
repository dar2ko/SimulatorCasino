/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorcasino;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Mariusz Krz
 */
class JednorekiBandyta {
    private static JednorekiBandyta jednorekibandyta;
    private static int all=0;
    private static int won=0;
    
    public static synchronized JednorekiBandyta inst() 
    {   if(jednorekibandyta == null) 
        { jednorekibandyta = new JednorekiBandyta();
        }
        return jednorekibandyta;}

    public int getAll() 
    { return all;
    }

    public double getRatio() 
    { return won/all;
    }

    public void gameJednorekiBandyta() 
    {//payment for game
        Bank.getInst().addMoney(30);
        Gamer.getInst().subtractMoney(30);
        all++;
        SimulatorCasino.cls();
        
     //game
        Random rand = new Random();
        int a = rand.nextInt(6)+1;
        int b = rand.nextInt(6)+1;
        int c = rand.nextInt(6)+1;
     // this function randomize 3 numbers.
     // if number on each of them is the same - you win
     // if additionally this number is 3 - you win double reward   
        System.out.printf("Wylosowane liczby to:\n %30s %30s %30s\n\n",a,b,c);
        if(a==b && b==c)

            if (a==3) {
                System.out.println("WYGRANA PODWOJNA NAGRODA!!!! $200 trafia na Twoje konto.");
                Bank.getInst().subtractMoney(200);
                Gamer.getInst().addMoney(200);
                won++;
            }
            else {
                System.out.println("WYGRANA!!!! $100 trafia na Twoje konto.");
                Bank.getInst().subtractMoney(100);
                Gamer.getInst().addMoney(100);
                won++;
            }
        else {
            System.out.println("Niestety przegrana. Moze innym razem sie uda");
        }
        System.out.println("Nacisnij dowolny klawisz aby kontynuowac...");
        Scanner input = new Scanner(System.in);
        input.nextLine();
        SimulatorCasino.cls();
    }
    
}
