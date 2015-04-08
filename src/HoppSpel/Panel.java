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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class Panel extends JPanel {

    //Variabler
    //saker till spelaren
    Player player = new Player();

    //håller reda på hur många gånger man hoppat sedan man nuddade mark/platform
    int nJumps = 0;

    //saker till platformar
    int nPlattforms = 40;
    Platform[] p = new Platform[nPlattforms];
    int lowestPlatform = 0;
    int highestPlatform = nPlattforms - 1;

    //annat
    long time;
    long frameTic = 0, fps = 0;
    long fpsT = System.nanoTime();
    int score = 0;
    Color background = new Color(130, 180, 250);

    //mer saker
    Monster monster = new Monster();

    Decorations dec = new Decorations();

    boolean gameOver = false;
    boolean debugmode = false;
    boolean offGround = false;

    public Panel() {
        setDoubleBuffered(true);
        initComponents();
        start();
    }

    void run() {

        this.setFocusable(true);

        start();

        while (!gameOver) {

            time = System.nanoTime();

            ManagePlatforms();

            Collision();

            Move();

            Gravity();

            //ser till att programmet håller en stabil uppdateringsfrekvens
            time = (System.nanoTime() - time) / 1000000;
            try {
                Thread.sleep(17 - time); //17ms sleep ~ 60fps
            } catch (InterruptedException ex) {
            }
            jLabel1.setText("Score: " + Integer.toString(score));

            if (System.nanoTime() - fpsT >= 1000000000) {
                fps = frameTic;
                fpsT = System.nanoTime();
                frameTic = 0;
            }
            repaint();
        }
        System.out.println("Game Over");

    }

    //ritar varje ny frame
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //suddar och ritar om varje frame

        super.setBackground(background);
        super.paintComponent(g2);

        dec.clouds(g2);

        for (int i = 0; i < nPlattforms; i++) {
            p[i].paint(g2, debugmode);
        }

//        g2.drawLine(0, 500, 500, 500);
        player.paint(g2, debugmode);

        monster.paint(g2);

        dec.mark(g2);

        //ritar ut jlableln/poängräknaren
        super.paintChildren(g2);
        if (debugmode) {
            g2.setColor(Color.black);
            g2.setFont(new Font("Dialog", Font.BOLD, 11));
            g2.drawString("FPS: " + fps, 2, 40);
        }
        frameTic++;
    }

    //slumpar ut saker vid start
    private void start() {

        //skapar plattformerna
        int highestY = 500;
        for (int i = 0; i < nPlattforms; i++) {
            p[i] = new Platform(highestY, 504);
            highestY = p[i].y;
        }
        dec.initClouds();
    }

    private void ManagePlatforms() {
        //platform hantering. Kollar om spelaren står på en platform
        for (int i = 0; i < nPlattforms; i++) {
            if (player.x > p[i].getX1() && player.getX() < p[i].getX1() + p[i].getX2()
                    && player.y <= p[i].getY() && player.getY() + player.getVy() > p[i].getY()) {

                player.setVy(0);
                player.setY(p[i].getY() - player.getVy());
                nJumps = 0;
                break;
            }
        }

        //flyttar upp understa plattformen
        if (p[lowestPlatform].y - 500 > player.y) {
            int highestY = p[highestPlatform].y;
            p[lowestPlatform] = new Platform(highestY, 504);
            lowestPlatform++;
            highestPlatform++;
            if (highestPlatform == nPlattforms) {
                highestPlatform = 0;
            }
            if (lowestPlatform == nPlattforms) {
                lowestPlatform = 0;
            }
        }

    }

    private void Move() {

        //flyttar allt nedåt när man är högt upp
        if (player.y + player.vy < 200) {
            player.translateY(player.vy, debugmode);
            player.removeOldPos(debugmode);

            for (int i = 0; i < nPlattforms; i++) {
                p[i].y -= player.vy;
            }
            dec.moveClouds(player.y);
            if(!offGround){
                dec.groundY -= player.vy;
                if (dec.groundY > 600)offGround = true;
            }
            monster.moveY(player.vy);
            player.y = 200;
            score++;
        } else {
            player.moveY();
        }
        if (player.y + 500 < monster.y) {
            monster.moveUp(player.y);
        }

        monster.moveX();

        player.moveX();

    }

    private void Gravity() {
        if (offGround || player.y < dec.groundY){
            player.vy += 1;
        }else{
            player.y = dec.groundY;
            player.vy = 0;
            nJumps = 0;
        }
        if(offGround && player.y > 600){
            gameOver = true;
        }
    }

    private void Collision() {

        if (monster.inBounds(player.x, player.y) || monster.inBounds(player.x, player.y - 2 * player.r)) {
            player.setColor(Color.red);
            gameOver = true;
        } else {
            player.setColor(Color.BLACK);
        }

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
        if (evt.getKeyCode() == KeyEvent.VK_UP && nJumps <= 1) {
            player.setVy(-14);
            nJumps++;
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setVx(-6);
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setVx(6);
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            player.setY(player.getY() + 1);
        }
        if (evt.getKeyCode() == 0) {
            debugmode = !debugmode;
            System.out.println("debugmode: " + debugmode);
        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_LEFT && player.vx < 0) {
            player.setVx(0);
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT && player.vx > 0) {
            player.setVx(0);
        }
    }//GEN-LAST:event_formKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
