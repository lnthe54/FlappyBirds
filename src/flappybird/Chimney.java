package flappybird;

import gui.Objects;

import java.awt.*;

public class Chimney extends Objects {
    private Rectangle rect;
    private boolean checkDie = false;

    public Chimney(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w-5, h-10);
    }

    public void move() {
        setPosX(getPosX() - 2);
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setCheckDie(boolean die) {
        checkDie = die;
    }

    public boolean getCheckDie() {
        return checkDie;
    }
}
