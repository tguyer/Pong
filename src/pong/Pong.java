/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import java.util.Random;
/**
 *
 * @author Travis
 */
public class Pong extends JComponent implements ActionListener, MouseMotionListener {

    //Initialize variables related to ball speed, direction, and location
    Random rand = new Random();
    private int ballX = 600;
    private int ballY = 300;
    
    private int paddleY = 340;
    
    private int paddle2Y = 340;
    
    private int ballXSpeed = 2;
    private int ballYSpeed = 1;
    
    int bounces = 0;

    /*
    * Set constant related to the difficulty of the game. Note the huge difference
    * a small change to this value makes.
    */
    private final double DIFFICULTY = 0.150;
    
    private int block;
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       JFrame window = new JFrame("Pong by Travis Guyer");
       
       Pong game = new Pong();
       
       //Set up the window to play pong in
       window.add(game);
       window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
       window.setLocationRelativeTo(null);
       window.setSize(800,600);
       window.setVisible(true);
      
       //Timer which controls how often the game gets repainted 
       Timer timer = new Timer(10, game);
       timer.start();
       
       window.addMouseMotionListener(game);
    }
   
        
        @Override
        protected void paintComponent(Graphics g){
            
            //Set the background on the window to black
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 600);
            
            //Draw the user's paddle as white
            g.setColor(Color.WHITE);
            g.fillRect(100,paddleY,15,50);
            
            //Draw the computer's paddle as white
            g.fillRect(660, paddle2Y, 15, 50);
                    
            //Draw the ball (a circle) as blue
            g.setColor(Color.CYAN);
            g.fillOval(ballX, ballY, 20, 20);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ballX -= ballXSpeed;
        ballY -= ballYSpeed;
        repaint();
        
        if (ballY >= paddleY -10 && ballY <= paddleY + 50 && (ballX <= 115 && ballX >= 95)){
            
            ballXSpeed = - (int)(2 + (bounces * DIFFICULTY));
            bounces++;
            
        }
        
        if (ballX < 40){
            
            System.exit(0);
        }
        
        if (ballY >= paddle2Y -10 && ballY <= paddle2Y + 50 && (ballX >= 640 && ballX <= 650)){
            
            if (ballXSpeed < 15)
                ballXSpeed = (int)(2 + (bounces * DIFFICULTY));
            else
                ballXSpeed += 0;
            bounces++;
            
            System.out.println(ballXSpeed);
            
        }
        
        if (ballX > 720){
            
            System.exit(0);
        }
        
        if (ballY <= 0 ){
            
            ballYSpeed = -1 * (rand.nextInt(4));
        }
        
        if (ballY >= 535){
            
            ballYSpeed = (rand.nextInt(4));
        }
        
        block = rand.nextInt(100);
    
        while (block > 49){
            
            block = rand.nextInt(50);
            
            paddle2Y = ballY;
            repaint();
            
            try{
            Thread.sleep(bounces * (ballXSpeed / 75));
            }
            catch(InterruptedException ex){
                
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        paddleY = e.getY() - 62;       
    
        repaint();       
       
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    
}
    

