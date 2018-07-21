package flappybird;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BackGround {
    private int x1, x2;
    private int y1, y2;
    private BufferedImage bg;

    public BackGround() {
        try {
            bg = ImageIO.read(new File("src/images/background_3.png"));
        } catch (Exception e) {

        }
        x1 = 0;
        y1 = 420;
        x2 = x1 + 830;
        y2 = 420;
    }

    public void move() {
        x1 -= 2;
        x2 -= 2;

        if (x2 < 0){
            x1 = x2 + 830;
        }
        if (x1 < 0){
            x2 = x1 + 830;
        }
    }

    public void draw(Graphics2D g2D) {
        g2D.drawImage(bg, x1, y1, null);
        g2D.drawImage(bg, x2, y2, null);
    }
}
