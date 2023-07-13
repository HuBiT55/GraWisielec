import javax.swing.*;
import java.awt.*;

public class HangmanDraw extends JPanel {
    private int currentPart;

    public HangmanDraw() {
        setBackground(Color.WHITE);
        reset();
    }

    public void reset() {
        currentPart = 0;
        repaint();
    }

    public void drawNextPart() {
        currentPart++;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        int width = getWidth();
        int height = getHeight();

        int poleHeight = height / 6;
        int poleWidth = width / 3;
        int headRadius = poleWidth / 6;

        int poleX = (width - poleWidth) / 2;
        int poleY = poleHeight;

        // Rysowanie szubienicy
        g2d.drawLine(poleX, poleY, poleX, poleY + 5 * poleHeight);
        g2d.drawLine(poleX, poleY, poleX + poleWidth, poleY);
        g2d.drawLine(poleX + poleWidth, poleY, poleX + poleWidth, poleY + poleHeight);
        g2d.drawLine(poleX, poleY + 5 * poleHeight, poleX + poleWidth, poleY + 5 * poleHeight);

        if (currentPart >= 1) {
            // Rysowanie głowy
            int headX = poleX + poleWidth / 2 - headRadius;
            int headY = poleY + poleHeight + headRadius;

            g2d.drawOval(headX, headY, 2 * headRadius, 2 * headRadius);
        }

        if (currentPart >= 2) {
            // Rysowanie szyi
            int neckX = poleX + poleWidth / 2;
            int neckY1 = poleY + 2 * poleHeight + 2 * headRadius;
            int neckY2 = poleY + 2 * poleHeight + 2 * headRadius + poleHeight / 4;

            g2d.drawLine(neckX, neckY1, neckX, neckY2);
        }

        if (currentPart >= 3) {
            // Rysowanie tułowia
            int bodyX1 = poleX + poleWidth / 2;
            int bodyY1 = poleY + 2 * poleHeight + 2 * headRadius + poleHeight / 4;
            int bodyX2 = bodyX1;
            int bodyY2 = poleY + 3 * poleHeight + 2 * headRadius + poleHeight / 4;

            g2d.drawLine(bodyX1, bodyY1, bodyX2, bodyY2);
        }

        if (currentPart >= 4) {
            // Rysowanie lewej ręki
            int armLength = poleWidth / 4;
            int armX1 = poleX + poleWidth / 2 - poleWidth / 8;
            int armY1 = poleY + 2 * poleHeight + 2 * headRadius + poleHeight / 4;
            int armX2 = armX1 - armLength;
            int armY2 = armY1 - poleHeight / 8;

            g2d.drawLine(armX1, armY1, armX2, armY2);
        }

        if (currentPart >= 5) {
            // Rysowanie prawej ręki
            int armLength = poleWidth / 4;
            int armX1 = poleX + poleWidth / 2 + poleWidth / 8;
            int armY1 = poleY + 2 * poleHeight + 2 * headRadius + poleHeight / 4;
            int armX2 = armX1 + armLength;
            int armY2 = armY1 - poleHeight / 8;

            g2d.drawLine(armX1, armY1, armX2, armY2);
        }

        if (currentPart >= 6) {
            // Rysowanie lewej nogi
            int legLength = poleWidth / 4;
            int legX1 = poleX + poleWidth / 2 - poleWidth / 8;
            int legY1 = poleY + 3 * poleHeight + 2 * headRadius + poleHeight / 4;
            int legX2 = legX1 - legLength;
            int legY2 = legY1 + poleHeight / 8;

            g2d.drawLine(legX1, legY1, legX2, legY2);
        }

        if (currentPart >= 7) {
            // Rysowanie prawej nogi
            int legLength = poleWidth / 4;
            int legX1 = poleX + poleWidth / 2 + poleWidth / 8;
            int legY1 = poleY + 3 * poleHeight + 2 * headRadius + poleHeight / 4;
            int legX2 = legX1 + legLength;
            int legY2 = legY1 + poleHeight / 8;

            g2d.drawLine(legX1, legY1, legX2, legY2);
        }
    }
}
