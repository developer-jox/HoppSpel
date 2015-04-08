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
import java.awt.event.KeyEvent;
import java.util.Random;

public class MinPanel extends javax.swing.JPanel {
    
        //Variabler
    
    //saker till spelaren
    Spelare sp = new Spelare();
    
    //håller reda på hur många gånger man hoppat sedan man duddade mark/platform
    int hopp = 0; 
    
    //saker till platformar
    int antalPlattformar = 40;
    Platform[] p = new Platform[antalPlattformar];
    int lowestPlatform = 0;
    int highestPlatform = antalPlattformar-1;
    
    //annat
    long time;
    int score = 0;
    Color bakgrund = new Color(130,180,250);
    
    //mer saker
    Monster mons = new Monster();
    
    Dekorationer dek = new Dekorationer();
    
    boolean gameOver = false;
    boolean offGround = false;
    
    public MinPanel() {
        initComponents();
        
    }
    
    void run(){
        
        this.setFocusable(true);
        
        start();
        
        while (!gameOver){
            
            time = System.nanoTime();
            repaint();
            
            HanteraPlatformer();
            
            Collision();
            
            Flytta();
            
            Gravitation();
            
            
            
            //ser till att programmet håller en stabil uppdateringsfrekvens
            time = (System.nanoTime()-time)/1000000;
                try {
                    Thread.sleep(17-time);
                } catch (InterruptedException ex) {
                }
                
                
                jLabel1.setText(Integer.toString(score));

        }
        System.out.println("Game Over");
        
    }
    
    //ritar varje ny frame
    @Override
    public void paint(Graphics g){
        
        //suddar och ritar om varje frame
        g.clearRect(0, 0, 1000, 1000);
        
        super.setBackground(bakgrund);
        super.paintComponent(g);
        
        dek.clouds(g);
        
        for (int i = 0; i < antalPlattformar; i++) {
            p[i].paint(g);
        }
        sp.paint(g);
        
        mons.paint(g);

        dek.mark(g);
        
        //ritar ut jlableln/poängräknaren
        super.paintChildren(g);
    }
    
    //slumpar ut saker vid start
    private void start(){
        
        //skapar plattformerna
        int highestY = 500;
        for (int i = 0; i < antalPlattformar; i++) {
            p[i] = new Platform(highestY);
            highestY = p[i].y;
        }
        
        dek.initClouds();
        
        
    }
    
    private void HanteraPlatformer(){
        //platform hantering. Kollar om spelaren står på en platform
        for (int i = 0; i < antalPlattformar; i++) {
            if( sp.x > p[i].x1 && sp.x < p[i].x2 &&
                sp.y <= p[i].y && sp.y + sp.vy >= p[i].y){
                
                sp.setvy(0);
                sp.sety(p[i].getY());
                hopp = 0;
                break;
            }
        }
        

        //flyttar upp understa plattformen
        if(p[lowestPlatform].y-500 > sp.y){
            int highestY = p[highestPlatform].y;
            p[lowestPlatform] = new Platform(highestY);
            lowestPlatform++;
            highestPlatform++;
            if(highestPlatform == antalPlattformar)highestPlatform = 0;
            if(lowestPlatform == antalPlattformar)lowestPlatform = 0; 
        }
        
    }
    
    private void Flytta(){
        
        //flyttar allt nedåt när man är högt upp
        if(sp.y+sp.vy < 200){
            for (int i = 0; i < antalPlattformar; i++) {
                p[i].y -= sp.vy;
            }
            
            dek.moveClouds(sp.y);
            if(!offGround){
                dek.markY -= sp.vy;
                if (dek.markY > 600)offGround = true;
            }
            
            mons.moveY(sp.vy);
            sp.y = 200;
            score++;
        }
        else{
            sp.moveY();
        }
        if(sp.y+500<mons.y){
            mons.moveUp(sp.y);
        }
        
        
        mons.moveX();
        
        sp.moveX();
        
    }
    
    private void Gravitation(){
        //hanterar gravitationen, stannar vid bottenlinjen
        if (offGround || sp.y < dek.markY){
            sp.vy += 1;
        }else{
            sp.y = dek.markY;
            sp.vy = 0;
            hopp = 0;
        }
        if(offGround && sp.y > 600){
            gameOver = true;
        }
        
    }
    
    private void Collision(){
        
        if(mons.inBounds(sp.x, sp.y) || mons.inBounds(sp.x, sp.y-2*sp.r)){
            sp.setColor(Color.red);
            gameOver = true;
        } 
        else sp.setColor(Color.BLACK);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 366, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 286, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP && hopp <= 1){
            sp.setvy(-14);
            hopp++;
        }
        else if (evt.getKeyCode() == KeyEvent.VK_LEFT)  sp.setvx(-4);
        else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) sp.setvx(4);
        else if (evt.getKeyCode() == KeyEvent.VK_DOWN)  sp.sety(sp.gety()+1);
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if      (evt.getKeyCode() == KeyEvent.VK_LEFT  && sp.vx < 0) sp.setvx(0);
        else if (evt.getKeyCode() == KeyEvent.VK_RIGHT && sp.vx > 0) sp.setvx(0);
    }//GEN-LAST:event_formKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
