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
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Decorations {

    int number = 10;
    int cloudX[] = new int[number];
    int cloudY[] = new int[number];
    int highest = number - 1;
    int lowest = 0;

    public Decorations() {

    }

    public void mark(Graphics g) {
        g.setColor(new Color(102, 204, 0));
        g.fillRect(0, 500, 500, 100);
    }

    public void initClouds() {
        Random rand = new Random();

        cloudX[0] = rand.nextInt((500 - 0) + 1) + 0;
        cloudY[0] = rand.nextInt((300 - 200) + 1) + 200;
        for (int i = 1; i < number; i++) {
            //rand.nextInt((max - min) + 1) + min;
            cloudX[i] = rand.nextInt((500 - 0) + 1) + 0;
            cloudY[i] = cloudY[i - 1] - (rand.nextInt((200 - 100) + 1) + 100);

        }

    }

    public void clouds(Graphics g) {
        g.setColor(Color.white);
        for (int i = 0; i < number; i++) {
            g.fillOval(cloudX[i] - 30, cloudY[i], 40, 40);
            g.fillOval(cloudX[i], cloudY[i], 40, 40);
            g.fillOval(cloudX[i] + 30, cloudY[i], 40, 40);

            g.fillOval(cloudX[i] - 15, cloudY[i] + 17, 40, 40);
            g.fillOval(cloudX[i] + 15, cloudY[i] + 17, 40, 40);
        }

    }

    public void moveClouds(int spY) {
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            cloudY[i]++;
        }

        if (cloudY[lowest] - 500 > spY) {
            cloudY[lowest] = cloudY[highest] - (rand.nextInt((200 - 100) + 1) + 100);
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

    //public void hitta nåt mer kanske.
}
