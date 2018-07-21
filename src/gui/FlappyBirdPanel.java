package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class FlappyBirdPanel extends JPanel implements Runnable {

    private FlappyBirdFrame context;

    private Thread thread;

    private Graphics ThisGraphics;

    public static int FPS = 70;

    private BufferedImage buffImage;

    private int MasterWidth, MasterHeight;
    public static float scaleX_ = 1, scaleY_ = 1;

    public FlappyBirdPanel(FlappyBirdFrame context) {
        this.context = context;

        MasterWidth = context.CUSTOM_WIDTH;
        MasterHeight = context.CUSTOM_HEIGHT;

        this.thread = new Thread(this);

    }

    public void StartThread() {
        thread.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, context.CUSTOM_WIDTH, context.CUSTOM_HEIGHT);

        Graphics2D g2D = (Graphics2D) g;

        if (buffImage != null) {
            g2D.scale(scaleX_, scaleY_);
            g2D.drawImage(buffImage, 0, 0, this);
        }
    }

    private void UpdateSize() {
        if (this.getWidth() <= 0) return;

        context.CUSTOM_WIDTH = this.getWidth();
        context.CUSTOM_HEIGHT = this.getHeight();

        scaleX_ = (float) context.CUSTOM_WIDTH / (float) MasterWidth;
        scaleY_ = (float) context.CUSTOM_HEIGHT / (float) MasterHeight;
    }


    @Override
    public void run() {

        long T = 1000 / FPS;
        long TimeBuffer = T / 2;

        long BeginTime = System.currentTimeMillis();
        long EndTime;
        long sleepTime;

        while (true) {

            UpdateSize();

            context.GAME_MOVE(System.currentTimeMillis());
            try {

                buffImage = new BufferedImage(MasterWidth, MasterHeight, BufferedImage.TYPE_INT_ARGB);
                if (buffImage == null) return;
                Graphics2D g2D = (Graphics2D) buffImage.getGraphics();

                if (g2D != null) {
                    context.GAME_PAINT(g2D);
                }

            } catch (Exception myException) {
                myException.printStackTrace();
            }

            repaint();

            EndTime = System.currentTimeMillis();
            sleepTime = T - (EndTime - BeginTime);
            if (sleepTime < 0) sleepTime = TimeBuffer;

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
            }

            BeginTime = System.currentTimeMillis();
        }
    }
}