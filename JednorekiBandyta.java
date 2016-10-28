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
class JednorekiBandyta {
    private static JednorekiBandyta jednorekibandyta;
    private static int all=0;
    private static int won=0;
    
    public static synchronized JednorekiBandyta inst() {
        if(jednorekibandyta == null) {
            jednorekibandyta = new JednorekiBandyta();
        }
        return jednorekibandyta;}

    static int getAll() {
        return all;
    }

    static double ratio() {
        return won/all;
    }

    void gameJednorekiBandyta() {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
