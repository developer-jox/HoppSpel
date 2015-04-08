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


    
public class Dekorationer {
    int antal = 10;
    int mX[] = new int[antal];
    int mY[] = new int[antal];
    int highest = antal-1;
    int lowest = 0;
    
    int markY = 500;
    
    public Dekorationer() {
        
    }
    
    public void mark(Graphics g){
        g.setColor(new Color(102, 204, 0));
        g.fillRect(0, markY, 500, 100);
    }
    public void initClouds(){
        Random rand = new Random();
        
        
        mX[0] = rand.nextInt((500 - 0) + 1) + 0;
        mY[0] = rand.nextInt((300 - 200) + 1) + 200;
        for (int i = 1; i < antal; i++) {
            //rand.nextInt((max - min) + 1) + min;
            mX[i] = rand.nextInt((500 - 0) + 1) + 0;
            mY[i] = mY[i-1] - (rand.nextInt((200 - 100) + 1) + 100);
            
        }
        
    }
    public void clouds(Graphics g){
        g.setColor(Color.white);
        for (int i = 0; i < antal; i++) {
            g.fillOval(mX[i]-30, mY[i], 40, 40);
            g.fillOval(mX[i], mY[i], 40, 40);
            g.fillOval(mX[i]+30, mY[i], 40, 40);
        
            g.fillOval(mX[i]-15, mY[i]+17, 40, 40);
            g.fillOval(mX[i]+15, mY[i]+17, 40, 40);
        }
        
    }
    
    public void moveClouds(int spY){
        Random rand = new Random();
        for (int i = 0; i < antal; i++) {
            mY[i]++;
        }
        
        if (mY[lowest]-500 > spY) {
            mY[lowest] = mY[highest] - (rand.nextInt((200 - 100) + 1) + 100);
            mX[lowest] = rand.nextInt((500 - 0) + 1) + 0;
            lowest++;
            highest++;
            if(lowest == antal)lowest = 0;
            if(highest == antal)highest = 0;
        }
        
    }
    
    //public void hitta nåt mer kanske.
    
}
