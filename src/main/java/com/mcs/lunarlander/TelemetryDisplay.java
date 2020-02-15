/*
 * Myriad Computer Serivces 2020
 */
package com.mcs.lunarlander;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ctg
 */
public class TelemetryDisplay extends javax.swing.JPanel {
    private static final Logger LOGGER = Logger.getLogger(TelemetryDisplay.class.getName());

    /**
     * Creates new form TelemetryPanel
     */
    public TelemetryDisplay() {
        LogSetup.setup(LOGGER);

        initComponents();
    }
    
    @Override
    public void paint(Graphics g) {
        LOGGER.log(Level.INFO, "start");

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new java.awt.Color(0, 0, 0));
        g2d.fillRect(0, 0, 200, 350);
        
        Lander lander = LunarLander.lander;
        double[] v = lander.v();
        int pa = lander.getPitchAngle();
        int throttle = lander.getDSThrust();
        double[] dv = lander.getDeltav();
        
        if (LunarLander.landersimulator.running()) {
            Font font = new Font("Arial", Font.PLAIN, 14);
            Font fontBold = new Font("Arial", Font.BOLD, 14);

            g2d.setColor(new java.awt.Color(0, 255, 0));

            int keyX = 5;
            int valX = 70;

            int rowY = 15;            
            g2d.setFont(fontBold);
            g2d.drawString("vX", keyX, rowY);
            g2d.setFont(font);
            g2d.drawString(String.format("%.3f", v[Geometry.X]), valX, rowY);

            rowY += 20;            
            g2d.setFont(fontBold);
            g2d.drawString("DvX", keyX, rowY);
            g2d.setFont(font);
            g2d.drawString(String.format("%.3f", dv[Geometry.X]), valX, rowY);

            rowY += 20;            
            g2d.setFont(fontBold);
            g2d.drawString("vY", keyX, rowY);
            g2d.setFont(font);
            g2d.drawString(String.format("%.3f", v[Geometry.Y]), valX, rowY);

            rowY += 20;            
            g2d.setFont(fontBold);
            g2d.drawString("DvY", keyX, rowY);
            g2d.setFont(font);
            g2d.drawString(String.format("%.3f", dv[Geometry.Y]), valX, rowY);

            rowY += 20;            
            g2d.setFont(fontBold);
            g2d.drawString("pitch", keyX, rowY);
            g2d.setFont(font);
            g2d.drawString(String.format("%d deg.", pa), valX, rowY);

            rowY += 20;            
            g2d.setFont(fontBold);
            g2d.drawString("throttle", keyX, rowY);
            g2d.setFont(font);
            g2d.drawString(String.format("%d%%", throttle), valX, rowY);
        } else {
            drawNoop(g2d);
        }
    }

    private void drawNoop(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(new java.awt.Color(255, 0, 0));
        g2d.drawLine(0, 0, 200, 350);
        g2d.drawLine(200, 0, 0, 350);
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
