/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheepgame;

import java.util.Timer;

/**
 *
 * @author Michael's pc
 */
public class SheepGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Canvas canvas = new Canvas((int)(1920/2), (int)(1080/1.5));
        Painter pt = new Painter(1920, 1080, canvas);
        
        Task tk = new Task(canvas);
        Timer tm = new Timer();
        //Task2 tk2 = new Task2(canvas);
        //Timer tm2 = new Timer();
        //tm2.schedule(tk2, 0, 2000);
        tm.schedule(tk, 0, 1000/144);
    }
    
}
