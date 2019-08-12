/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheepgame;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 *
 * @author Michael's pc
 */
public class Task2 extends TimerTask {
    //Data
    Canvas canvas;
    private ArrayList<Sheep> arr;
    private Sheep tempSheep;
    public Task2(Canvas e) {
        canvas = e;
    }
    //Getters/Setters
    
    //Public Methods
    @Override
    public void run() {        
        arr = canvas.getSheepArr();
        if(!canvas.flag) {      
            arr.add(new Sheep(-100,100, 40, canvas.getVelocity(), canvas));
        }
        canvas.flag = true;
    }
}
