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


public class Monster {

    int x = 100;
    int y = 100;
    int height = 25;
    int width = 10;

    int vx = 2;

    public Monster() {

    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x - height, y - width, 2 * height, 2 * width);
        g.setColor(Color.green);
        g.fillOval(x + 7, y - 3, 5, 5);
        g.fillOval(x - 7, y - 3, 5, 5);
    }

    public void moveX() {
        x += vx;
        if (x > 500 || x < 0) {
            vx = -vx;
        }
    }

    public void moveY(int v) {
        y -= v;
    }

    public void moveUp(int yIn) {
        Random rand = new Random();
        y = yIn - (rand.nextInt((500 - 250) + 1) + 250);
        vx = rand.nextInt((5 - 1) + 1) + 1;
    }

    public boolean inBounds(int inX, int inY) {
        return inX >= x - height && inX <= x + height
                && inY >= y - width && inY <= y + width;

    }

}
