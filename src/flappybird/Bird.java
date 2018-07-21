package flappybird;

import gui.Objects;

import java.awt.*;

public class Bird extends Objects {
    private float tocDo = 0;
    private boolean isFlying = false;
    private Rectangle rect;
    private boolean checkLive = true;

    public Bird(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w-5, h-5);
    }

    public void setLive(boolean live) {
        checkLive = live;
    }

    public boolean getLive() {
        return checkLive;
    }

    public void move() {
        tocDo += FlappyBird.gravity;

        setPosY(getPosY() + tocDo);
        rect.setLocation((int) getPosX(), (int) getPosY());
        if (tocDo < 0) {
            isFlying = true;
        } else {
            isFlying = false;
        }
    }

    public void fly() {
        tocDo = -3.2f;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setTocDo(float tocDo) {
        this.tocDo = tocDo;
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean checkChimney(Chimneys chimneys) {
        for (int i = 0; i < Chimneys.SIZE; i++) {
            Rectangle rectangle = getRect().intersection(chimneys.getChimney(i).getRect());
            if (rectangle.isEmpty() == false) {
                return false;
            }
        }
        return true;
    }
}
