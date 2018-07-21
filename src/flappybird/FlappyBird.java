package flappybird;

import gui.ImageFrame;
import gui.Animation;
import gui.FlappyBirdFrame;
import gui.SoundPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FlappyBird extends FlappyBirdFrame {

    private BufferedImage birdImage;
    private Image imgGOver, menu;
    private Animation animation;
    private Bird bird;
    private Chimneys chimneys;
    private BackGround bg;
    private int point = 0;
    private int GAME_START = 1;
    private int GAME_PLAY = 2;
    private int GAME_OVER = 3;
    private int currentGame = GAME_START;

    private SoundPlayer fapSound, fallSound, pointSound;

    public static float gravity = 0.2f;

    public FlappyBird() throws IOException {
        super(800, 511);
        try {
            birdImage = ImageIO.read(new File("src/images/bird.png"));
        } catch (Exception e) {

        }

        fapSound = new SoundPlayer(new File("src/sound/fap.wav"));
        fallSound = new SoundPlayer(new File("src/sound/fall.wav"));
        pointSound = new SoundPlayer(new File("src/sound/getpoint.wav"));

        menu = ImageIO.read(new File("src/images/bg_contai.png"));
        imgGOver = ImageIO.read(new File("src/images/game.png"));

        animation = new Animation(100);

        ImageFrame img;

        img = new ImageFrame(0, 0, 60, 60);
        animation.AddFrame(img);

        img = new ImageFrame(60, 0, 60, 60);
        animation.AddFrame(img);

        img = new ImageFrame(120, 0, 60, 60);
        animation.AddFrame(img);

        img = new ImageFrame(60, 0, 60, 60);
        animation.AddFrame(img);

        bird = new Bird(250, 200, 50, 50);
        chimneys = new Chimneys();

        bg = new BackGround();
        startGame();
    }

    private void resetGame() {
        bird.setPos(250, 200);
        bird.setTocDo(0);
        bird.setLive(true);
        point = 0;
        chimneys.reset();
    }

    @Override
    public void GAME_MOVE(long deltaTime) {

        if (currentGame == GAME_START) {
            resetGame();
        } else if (currentGame == GAME_PLAY) {
            if (bird.getLive()) animation.Update_Me(deltaTime);
            bird.move();
            bg.move();
            chimneys.move();


            for (int i = 0; i < Chimneys.SIZE; i++) {
                if (bird.getPosX() > chimneys.getChimney(i).getPosX()
                        && !chimneys.getChimney(i).getCheckDie()
                        && i % 2 == 0) {
                    point++;
                    pointSound.play();
                    chimneys.getChimney(i).setCheckDie(true);
                }
            }

            boolean check = bird.checkChimney(chimneys);

            if (bird.getPosY() + bird.getH() > 420 || check == false) {
                currentGame = GAME_OVER;
                fallSound.play();
            }

        } else {

        }
    }

    @Override
    public void GAME_PAINT(Graphics2D g2D) {

        g2D.setColor(Color.decode("#b8daef"));
        g2D.fillRect(0, 0, MASTER_WIDTH, MASTER_HEIGHT);

        chimneys.draw(g2D);

        bg.draw(g2D);
        g2D.drawImage(menu, 600, 0, 200, 530, null);
        g2D.setColor(Color.RED);
        g2D.setFont(new Font("", Font.BOLD, 18));
        g2D.drawString("POINT : " + point, 670, 100);

        if (bird.isFlying()) {
            animation.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birdImage, g2D, 0, -1);
        } else {
            animation.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birdImage, g2D, 0, 0.5f);
        }

        if (currentGame == GAME_START) {
            g2D.setColor(Color.BLACK);
            g2D.setFont(g2D.getFont().deriveFont(18f));
            g2D.drawString("PRESS SPACE TO PLAY GAME", 150, 300);
        }

        if (currentGame == GAME_OVER) {
            g2D.drawImage(imgGOver, 200, 250, null);
        }
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (currentGame == GAME_START) {
                currentGame = GAME_PLAY;
            } else if (currentGame == GAME_PLAY) {
                if (bird.getLive()) {
                    bird.fly();
                    fapSound.play();
                }
            } else if (currentGame == GAME_OVER) {
                currentGame = GAME_START;
            }
        }
    }
}
