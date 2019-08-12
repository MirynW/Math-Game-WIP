/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheepgame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 *
 * @author Michael's pc
 */
public class Canvas extends JPanel implements KeyListener {
    private int canvasX;
    private int canvasY;
    private String path;
    private Image image;
    private ArrayList<Sheep> sheepArr;
    private boolean delete;
    private String userInput;
    private String enteredText;
    private int velocity;
    boolean flag;
    boolean gameOver;
    boolean win;
    boolean userChoice;
    
    public Canvas(int width, int height) {
        super();
        path = "C:\\Users\\Michael's pc\\Documents\\NetBeansProjects\\BrickBreak\\src\\img\\Chad.jpg";
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        canvasX = width;
        canvasY = height-40;
        setLayout(null);
        setBounds(0, 0, canvasX, canvasY);
        setFocusable(true);
        setVisible(true);
        setRequestFocusEnabled(true);
        requestFocus();
        
        
        //Sheep Creation
        velocity = 1;
        sheepArr = new ArrayList<Sheep>();
        userInput = "";
        enteredText = null;
        sheepArr.add(new Sheep(-100,100, 40, velocity, this));
        flag = false;
        gameOver = false;
        win = false;
        userChoice = false;
        addKeyListener(this);
    }
    
    //Getters/Setters
    public int getCanvasX() {
        return canvasX;
    }
    public int getCanvasY() {
        return canvasY;
    }
    public void setSheepArr(ArrayList<Sheep> newArr) {
        if(newArr != null)
            sheepArr = newArr;
    }
    public ArrayList<Sheep> getSheepArr() {
        return sheepArr;
    }
    public int getVelocity() {
        return velocity;
    }
    public void setVelocity(int newVelocity) {
        if(newVelocity > 0) 
            velocity = newVelocity;
    }
    public String getEnteredText() {
        return enteredText;
    }
    public void setEnteredText(String newText) {
        enteredText = newText;
    }
    public boolean getUserChoice() {
        return userChoice;
    }
    public void setUserChoice(boolean a) {
        userChoice = a;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if(userChoice) {
            gamePlay( g2d );
        } else {
            menu( g2d ); 
        }
        g.dispose();
    }
    
    private void menu( Graphics2D g2d ) {
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
        g2d.drawString("Welcome, Please enter your twitter username...", 20, 60);
        
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
        g2d.drawString(userInput + "|", canvasX/2 -100, canvasY-500);
    }
    
    private void gamePlay( Graphics2D g2d ) {
        if(!gameOver && !win) {
            //Draw Paddle
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.setColor(Color.DARK_GRAY);
            g2d.setStroke( new BasicStroke(5));
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
            g2d.drawString(userInput + "|", canvasX/2, canvasY-30);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
            //Iterate through brick arr and draw all valid bricks
                for(Sheep e : sheepArr) {
                    g2d.setColor(Color.gray);
                    g2d.fillRect(e.x, e.y, e.width, e.height);
                    g2d.setStroke( new BasicStroke(2));
                    g2d.setColor(Color.black);
                    g2d.drawString(e.getText(), (e.x + e.width/4), (int)(e.y + e.height/1.5));
                flag = false;
            }
        } else if(gameOver) {
            g2d.setColor( new Color(244, 66, 66));
            g2d.fillRect(0, 0, canvasX, canvasY);
            g2d.setFont(new Font("ComicSans", Font.PLAIN, 50)); 
            g2d.setColor(Color.white);
            g2d.drawString("Game Over,\n you were too slow..", 200, (int)(canvasY/2.1));
        }else {
            g2d.setColor( new Color(158, 255, 109));
            g2d.fillRect(0, 0, canvasX, canvasY);
            g2d.setFont(new Font("ComicSans", Font.PLAIN, 50)); 
            g2d.setColor(Color.white);
            g2d.drawString("You Win,\n congrats!", 200, (int)(canvasY/2.1));
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        if((int)e.getKeyChar() == 10/*keycode of enter*/) {
            System.out.println("Entered");
            enteredText = userInput;
            userInput = "";
            System.out.println("Entered Text: " + enteredText);
        }
        else if((int)e.getKeyChar() != 8){ 
            userInput += e.getKeyChar();        
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 8) {
            if(!userInput.isEmpty())
                userInput = userInput.substring(0,userInput.length()-1);
        }     
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        if(e.getKeyCode() == 8/*keycode of backspace*/ ) {
//            delete = false;
//        }
    }

    
}
