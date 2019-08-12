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
public class Task extends TimerTask {
    //Data
    Canvas canvas;
    private ArrayList<Sheep> arr;
    private ArrayList<Sheep> removeList;
    private Sheep tempSheep;
    private String userInput;
    private int counter;
    private char gameStatus;
    private int solved;
    private int amnt;
    private String twitterName;
    public Task(Canvas e) {
        canvas = e;
        userInput = null;
        counter = 0;
        removeList = new ArrayList<>();
        solved = 0;
        gameStatus = 'c';
        amnt = 10;
    }
    //Getters/Setters
    
   //Public Methods
    @Override
    public void run() {        
        if( canvas.getUserChoice() ) {
            gamePlay();
        } else {
            if( canvas.getEnteredText() != null ) {
                twitterName = canvas.getEnteredText();
                canvas.setEnteredText( null );
                canvas.setUserChoice(true);
            }
        }
        
        canvas.repaint();
    }
    private void gamePlay() {
        counter++;
        if(counter == (144)) {
            arr.add(new Sheep(-100,100, 40, canvas.getVelocity(), canvas));
            counter = 0;
        }
        arr = canvas.getSheepArr();
        userInput = canvas.getEnteredText();
        if(userInput != null) {
            for(Sheep e : arr) {
                //System.out.println(userInput + " Compares to " + e.getResult());
                if(userInput.equals(e.getResult())) {
                    removeList.add(e);          
                    solved++;
                }
            }
            canvas.setEnteredText(null);
        }
        for(Sheep e : removeList) {
            arr.remove(e);
        }
        removeList.clear();
         for(Sheep e : arr) {
            if(solved >= amnt)
                //gameStatus = 'w';
                canvas.win = true;
            else if(e.checkStatus())
                //gameStatus = 'L';
                canvas.gameOver = true;
            e.update();
         }            
         canvas.setSheepArr(arr);
    }
}
