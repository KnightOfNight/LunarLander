/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ctg
 */
public class PrimaryDisplay extends javax.swing.JPanel {
    private static final Logger LOGGER = Logger.getLogger(PrimaryDisplay.class.getName());

    /**
     * Creates new form PrimaryDisplay
     */
    public PrimaryDisplay() {
        LogSetup.setup(LOGGER);

        initComponents();
    }
    
    @Override
    public void paint(Graphics g) {
//        LOGGER.log(Level.INFO, "start");
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new java.awt.Color(0, 0, 0));
        g2d.fillRect(0, 0, 1400, 800);

        Telemetry telemetry = LunarLander.lander.telemetry();
        
        List<double[]> history = List.copyOf(telemetry.getHistory());
        
        g2d.setColor(new java.awt.Color(255, 255, 255));
        int[] drawXY;
//        LOGGER.log(Level.INFO, String.format("history.size:%d", history.size()));
        for (double[] loc : history) {
            drawXY = Geometry.realToDraw(loc);
//            LOGGER.log(Level.INFO, String.format("x,y:%d,%d", drawXY[Geometry.X], drawXY[Geometry.Y]));
            g2d.drawOval(drawXY[Geometry.X], drawXY[Geometry.Y], 1, 1);
        }
        LunarLander.scenery.draw(g2d);
//        LunarLander.lander.draw(g2d);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
