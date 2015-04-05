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

/**
 *
 * @author jeseri767
 */
public class Player {

    int x = 100;
    int y = 300;

    int vx;
    int vy;

    int r = 10;

    Color c = new Color(0, 0, 0);

    public Player() {

    }

    //Hastigheter

    public void setVx(int input) {
        vx = input;
    }

    public void setVy(int input) {
        vy = input;
    }

    public void addVx(int input) {
        vx += input;
    }

    public void addVy(int input) {
        vy += input;
    }

    public int getVy() {
        return vy;
    }

    public int getVx() {
        return vx;
    }

    public void move() {
        x += vx;
        y += vy;
    }

    public void moveX() {
        x += vx;
    }

    public void moveY() {
        y += vy;
    }

    //positioner

    public void setX(int input) {
        x = input;
    }

    public void setY(int input) {
        y = input;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setColor(Color a) {
        c = a;
    }

    public void paint(Graphics g) {
        g.setColor(c);

        if (x > 500) {
            x = 0;
        } else if (x > 500 - r) {

            g.fillOval((x - r) - 500, y - 2 * r, 2 * r, 2 * r);
        } else if (x < 0) {
            x = 500;

        } else if (x - r < 0) {
            g.fillOval((x - r) + 500, y - 2 * r, 2 * r, 2 * r);
        }

        g.fillOval(x - r, y - 2 * r, 2 * r, 2 * r);

    }
}
