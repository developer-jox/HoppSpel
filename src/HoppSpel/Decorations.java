/* 
 * Copyright (C) 2015 jjeessppeer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package HoppSpel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Decorations {

    int number = 10;
    int cloudX[] = new int[number];
    int cloudY[] = new int[number];
    int highest = number - 1;
    int lowest = 0;

    BufferedImage image[] = new BufferedImage[number];
    BufferedImage cloud = null;

    int groundY = 500;

    Random rnd = new Random();

    String cloudURL = "../textures/cloud.png";

    public Decorations() {
        double cloudScale;
        cloud = loadImage(cloudURL);
        for (int i = 0; i < number; i++) {
            cloudScale = rndRect(0.1, 1.0);
            image[i] = resize(cloud, cloudScale);
        }
    }

    public void mark(Graphics g) {
        g.setColor(new Color(102, 204, 0));
        g.fillRect(0, groundY, 500, 100);
    }

    public int rndRect(int min, int max) {
        return rnd.nextInt(max - min) + min;
    }

    private double rndRect(double min, double max) {
        return rnd.nextDouble() * (max - min) + min;
    }

    public void initClouds() {
        cloudX[0] = rndRect(-500 * 2 / 3, 500 * 2 / 3);
        cloudY[0] = rndRect(200, 300);
        for (int i = 1; i < number; i++) {
            //rand.nextInt((max - min) + 1) + min;
            cloudX[i] = rndRect(-500 * 2 / 3, 500 * 2 / 3);
            cloudY[i] = cloudY[i - 1] - rndRect(200, 300);
        }

    }

    public void paintClouds(Graphics g) {
//        g.setColor(Color.white);
        for (int i = 0; i < number; i++) {
            g.drawImage(image[i], cloudX[i], cloudY[i], null);
        }

    }

    public void moveClouds(int playerY) {
        for (int i = 0; i < number; i++) {
            cloudY[i]++;
        }

        if (cloudY[lowest] - 500 > playerY) {
            cloudY[lowest] = cloudY[highest] - rndRect(100, 200);
            double cloudScale = rndRect(0.1, 1.0);
            image[lowest] = resize(cloud, cloudScale);
            lowest++;
            highest++;
            if (lowest == number) {
                lowest = 0;
            }
            if (highest == number) {
                highest = 0;
            }
        }

    }

    private BufferedImage loadImage(String imageURL) {
        try {
            File f = new File(getClass().getResource(imageURL).getPath());
            BufferedImage localImage = ImageIO.read(f);
            return localImage;

        } catch (IOException ex) {
            Logger.getLogger(Decorations.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private BufferedImage resize(BufferedImage bImage, double wantedScale) {
        BufferedImage localImage;
        int width = (int) (bImage.getWidth() * wantedScale);
        int height = (int) (bImage.getHeight() * wantedScale);
        Image tmpImage = bImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        localImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = localImage.createGraphics();
        g2.drawImage(tmpImage, 0, 0, null);
        g2.dispose();
        return localImage;
    }
}
