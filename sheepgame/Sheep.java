/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheepgame;

import java.awt.Image;

/**
 *
 * @author Michael's pc
 */
public class Sheep {
    //Data
    private int num1, num2;
    private char op;
    private int result;
    private String text;
    private Image image;
    
    int x, y;
    int width, height;
    int velocity;
    
    Canvas canvas;
    
    public Sheep(int init_x, int init_w, int init_h, int init_v, Canvas init_c) {
        //Game data
        num1 = genRand(9);
        num2 = genRand(9);
        op = getOp();
        result = calculate();
        text = "" + num1 + op + num2 + " = ?";
        
        //Positional
        canvas = init_c;
        x = init_x; 
        y = genRand(canvas.getCanvasY()-init_h);
        width = init_w; 
        height = init_h;
        velocity = init_v;        
    }
    //Getters/Setters
    public String getResult() {
        return "" + result;
    }
    public void setResult(int newResult) {
        if(newResult != result)
            result = newResult;
    }
    public String getText() {
        return text;
    }
    public void setText(String newText) {
        if(newText != null)
            text = newText;
    }
    //Public Methods
    public int calculate() {
        if(op == '+')
            return num1 + num2;
        else if(op == '-')
            return num1 - num2;
        else if(op == '/')
            return (int)Math.floor(num1/num2);
        else
            return num1*num2;
    }
    //Handle movement
    public void update() {
        x += velocity;
    }
    public boolean checkStatus() {
        if(x > canvas.getCanvasX())
            return true;
        return false;
    }
    //Private Methods
    private char getOp() {
        char[] operators = { '+', '-', '*', '/'};
        if(num2 == 0) {
            return operators[genRand(2)];
        }
        return operators[genRand(3)];
    }
            
    private int genRand(int size) {
        return (int)(Math.random() * size);
    }
}
