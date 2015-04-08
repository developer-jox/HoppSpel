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
import java.util.Random;

public class Platform {

    int y;
    int x;
    int length;
    int width;
    boolean colTest = false, left = false;

    public Platform(int hY, int lWidth) {
        width = lWidth;
        Random rand = new Random();
        y = hY - (rand.nextInt((150 - 60) + 1) + 60);
        x = rand.nextInt((490 + 10) + 1) + 10;
        length = rand.nextInt((120 - 60) + 1) + 60;
        checkIfOfTrack(lWidth);
    }

    public int getX1() {
        return x;
    }

    public int getX2() {
        return length;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g, boolean lDebugmode) {
        if (lDebugmode) {
            g.setColor(Color.red);
            g.drawString("length: " + length, x, y + 17);
            g.drawString("x: " + x, x, y + 44);
            g.drawString("width: " + (x + length), x, y + 58);
            g.drawString("col: " + colTest, x, y + 30);
            g.setColor(Color.blue);
            g.drawRect(x - 1, y - 1, 2, 2);
            g.drawRect(x - 1, y + 6, 2, 2);
            g.drawRect(x + length - 1, y - 1, 2, 2);
            g.drawRect(x + length - 1, y + 6, 2, 2);
            if (left && colTest) {
                g.fillRoundRect(x - length, y, length, 6, 7, 7);
            } else if (colTest) {
                g.fillRoundRect(x + length, y, length, 6, 7, 7);
            }
        }
        g.setColor(new Color(120, 45, 45));
        g.fillRoundRect(x, y, length, 6, 7, 7);
        g.setColor(Color.red);
    }

    public void checkIfOfTrack(int lWidth) {
        if (x < 0) {
            x = x + length;
            left = true;
            colTest = true;
        } else if (x + length > lWidth) {
            x = x - length;
            left = false;
            colTest = true;
        } else {
            colTest = false;
        }
    }

    public void placeHighest(int hY) {
        Random rand = new Random();
        y = hY - (rand.nextInt((150 - 20) + 1) + 20);
        x = rand.nextInt((490 + 10) + 1) + 10;
        length = rand.nextInt((120 - 60) + 1) + 60;
        checkIfOfTrack(width);
    }

}
