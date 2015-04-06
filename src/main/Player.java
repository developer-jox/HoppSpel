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
import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author jeseri767
 */
public class Player {

    int x = 504 / 2;
    int y = 500;

    int oldX, oldY;
    LinkedList<Point> oldPosList = new LinkedList<>();

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

    public void moveX() {
        Point oldPos = new Point(x, y);
        oldPosList.add(oldPos);
        x += vx;
    }

    public void moveY() {
        Point oldPos = new Point(x, y);
        oldPosList.add(oldPos);
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

    public void translateY(int vy, boolean lDebugmode) {
        if (lDebugmode) {
            ListIterator<Point> listIterator = oldPosList.listIterator();
            Point p = null;
            while (listIterator.hasNext()) {
                p = listIterator.next();
                p.y -= vy;
            }
        }
    }

    public void removeOldPos(boolean lDebugmode) {
        if (lDebugmode) {
            ListIterator<Point> listIterator = oldPosList.listIterator();
            Point p = null;
            while (listIterator.hasNext()) {
                p = listIterator.next();
                if (p.y > 500) {
                    listIterator.remove();
                }
            }
        }
    }

    public void paint(Graphics g, boolean lDebugmode) {
        if (lDebugmode) {
            g.setColor(Color.MAGENTA);
            ListIterator<Point> listIterator = oldPosList.listIterator();
            Point p1 = null, p2 = null;
            while (listIterator.hasNext()) {
                p1 = listIterator.next();
                if (p2 != null) {
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
                p2 = p1;
            }
        }

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
