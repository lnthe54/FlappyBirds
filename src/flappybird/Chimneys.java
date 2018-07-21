package flappybird;

import gui.QueueList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Chimneys {
    private Random rd = new Random();
    private QueueList<Chimney> chimneys;
    private BufferedImage chimneyImageBottom, chimneyImageTop;
    private int chimneyTop = -300;
    private int chimneyBottom = 200;
    private int khoangCach = 250;
    public static int SIZE = 6;

    public int randomChimney() {

        int setRandom;
        setRandom = rd.nextInt(10);
        return setRandom * 15;
    }

    public Chimney getChimney(int i) {
        return chimneys.get(i);
    }

    public Chimneys() {
        try {
            chimneyImageBottom = ImageIO.read(new File("src/images/chimney_bottom_2.png"));
            chimneyImageTop = ImageIO.read(new File("src/images/chimney_top_2.png"));
        } catch (Exception e) {

        }
        chimneys = new QueueList<Chimney>();
        Chimney chimney;

        for (int i = 0; i < SIZE / 2; i++) {
            int getRandom = randomChimney();

            chimney = new Chimney(720 + i * khoangCach, chimneyBottom + getRandom, 74, 400);
            chimneys.push(chimney);

            chimney = new Chimney(720 + i * khoangCach, chimneyTop + getRandom, 74, 400);
            chimneys.push(chimney);
        }
    }

    public void reset() {
        chimneys = new QueueList<Chimney>();
        Chimney chimney;
        int getRandom = randomChimney();

        for (int i = 0; i < SIZE / 2; i++) {
            chimney = new Chimney(720 + i * khoangCach, chimneyBottom + getRandom, 74, 400);
            chimneys.push(chimney);

            chimney = new Chimney(720 + i * khoangCach, chimneyTop + getRandom, 74, 400);
            chimneys.push(chimney);
        }
    }


    public void move() {
        for (int i = 0; i < 6; i++) {
            chimneys.get(i).move();
        }

        if (chimneys.get(0).getPosX() < -74) {
            int getRandom = randomChimney();

            Chimney chimney;

            chimney = chimneys.pop();
            chimney.setPosX(chimneys.get(4).getPosX() + khoangCach);
            chimney.setPosY(chimneyBottom + getRandom);
            chimney.setCheckDie(false);
            chimneys.push(chimney);

            chimney = chimneys.pop();
            chimney.setPosX(chimneys.get(4).getPosX());
            chimney.setPosY(chimneyTop + getRandom);
            chimney.setCheckDie(false);
            chimneys.push(chimney);
        }
    }

    public void draw(Graphics2D g2D) {
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                g2D.drawImage(chimneyImageBottom, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
            } else {
                g2D.drawImage(chimneyImageTop, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
            }
        }
    }
}
