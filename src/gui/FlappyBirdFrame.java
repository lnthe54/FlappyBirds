
package gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public abstract class FlappyBirdFrame extends JFrame implements KeyListener{

    public static int KEY_PRESSED = 0;
    public static int KEY_RELEASED = 1;
    
    public int CUSTOM_WIDTH  = 500;
    public int CUSTOM_HEIGHT = 500;
    
    private FlappyBirdPanel G_Thread;
    
    public static int MASTER_WIDTH = 500, MASTER_HEIGHT = 500;
    
    public FlappyBirdFrame(){
        InitThread();
        InitScreen();
        setLocationRelativeTo(null);
    }
    
    public void RegisterImage(int id, BufferedImage image){
        
    }
    
    public BufferedImage getImageWithID(int id){
        return null;
    }
    
    public FlappyBirdFrame(int w, int h){
        this.CUSTOM_WIDTH = w;
        this.CUSTOM_HEIGHT = h;
        MASTER_WIDTH = CUSTOM_WIDTH;
        MASTER_HEIGHT = CUSTOM_HEIGHT;
        InitThread();
        InitScreen();
        setLocationRelativeTo(null);
    }
    
    private void InitScreen(){
        setTitle("Flappy Bird 2018");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setSize(CUSTOM_WIDTH, CUSTOM_HEIGHT);
        setVisible(true);

    }
    
    public void startGame(){
        G_Thread.StartThread();
    }
    
    private void InitThread(){
        G_Thread = new FlappyBirdPanel(this);
        add(G_Thread);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        KEY_ACTION(e, FlappyBirdFrame.KEY_PRESSED);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KEY_ACTION(e, FlappyBirdFrame.KEY_RELEASED);
    }
    
    public abstract void GAME_MOVE(long deltaTime);
    public abstract void GAME_PAINT(Graphics2D g2);
    public abstract void KEY_ACTION(KeyEvent e, int Event);
    
}
