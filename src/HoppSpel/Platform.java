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
    int x1;
    int x2;
    int L;
    int mitt;

    public Platform(int hY) {
        Random rand = new Random();
        y = hY - (rand.nextInt((150 - 20) + 1) + 20);
        mitt = rand.nextInt((500 - 0) + 1) + 0;
        L = rand.nextInt((100 - 40) + 1) + 40;
        x1 = mitt - L;
        x2 = mitt + L;
      
    }
    
    public void setY(int a){
        y = a;
    }
 
    public void setX1(int a){
        x1 = a;
        x2 = x1+L;
    }
    public int getX1(){
        return x1;
    }
    public int getX2(){
        return x2;
    }
    public int getY(){
        return y;
    }

    public void paint(Graphics g){
        g.setColor(new Color(120,45,45));
       // g.drawLine(x1, y, (x1+x2), y);
       
        g.fillRect(x1, y, L*2, 6);
    }
    

}
