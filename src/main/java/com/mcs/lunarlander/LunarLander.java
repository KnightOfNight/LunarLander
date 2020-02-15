/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ctg
 */
public class LunarLander extends javax.swing.JFrame {
    private static final Logger LOGGER = Logger.getLogger(LunarLander.class.getName());

    public static final double GRAVITY = 1.6;
    
    /**
     * Creates new form LunarLander
     */
    public LunarLander() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PrimaryDisplay = new com.mcs.lunarlander.PrimaryDisplay();
        scaleDnButton = new javax.swing.JButton();
        scaleUpButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        startSimButton = new javax.swing.JButton();
        stopSimButton = new javax.swing.JButton();
        resetSimButton = new javax.swing.JButton();
        TelemetryDisplay = new com.mcs.lunarlander.TelemetryDisplay()
        ;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        PrimaryDisplay.setFocusable(false);
        PrimaryDisplay.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout PrimaryDisplayLayout = new javax.swing.GroupLayout(PrimaryDisplay);
        PrimaryDisplay.setLayout(PrimaryDisplayLayout);
        PrimaryDisplayLayout.setHorizontalGroup(
            PrimaryDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        PrimaryDisplayLayout.setVerticalGroup(
            PrimaryDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        scaleDnButton.setText("Scale Dn");
        scaleDnButton.setEnabled(false);
        scaleDnButton.setFocusable(false);
        scaleDnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleDnButtonActionPerformed(evt);
            }
        });

        scaleUpButton.setText("Scale Up");
        scaleUpButton.setEnabled(false);
        scaleUpButton.setFocusable(false);
        scaleUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleUpButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Simulation");
        jLabel1.setFocusable(false);

        startSimButton.setBackground(new java.awt.Color(200, 255, 200));
        startSimButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        startSimButton.setText("Start");
        startSimButton.setEnabled(false);
        startSimButton.setFocusable(false);
        startSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSimButtonActionPerformed(evt);
            }
        });

        stopSimButton.setBackground(new java.awt.Color(255, 200, 200));
        stopSimButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        stopSimButton.setText("Stop");
        stopSimButton.setEnabled(false);
        stopSimButton.setFocusable(false);
        stopSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopSimButtonActionPerformed(evt);
            }
        });

        resetSimButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resetSimButton.setText("Reset");
        resetSimButton.setEnabled(false);
        resetSimButton.setFocusable(false);
        resetSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetSimButtonActionPerformed(evt);
            }
        });

        TelemetryDisplay.setFocusable(false);
        TelemetryDisplay.setPreferredSize(new java.awt.Dimension(200, 350));
        TelemetryDisplay.setSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout TelemetryDisplayLayout = new javax.swing.GroupLayout(TelemetryDisplay);
        TelemetryDisplay.setLayout(TelemetryDisplayLayout);
        TelemetryDisplayLayout.setHorizontalGroup(
            TelemetryDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        TelemetryDisplayLayout.setVerticalGroup(
            TelemetryDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startSimButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopSimButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetSimButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PrimaryDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scaleUpButton)
                            .addComponent(TelemetryDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scaleDnButton))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TelemetryDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scaleUpButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scaleDnButton))
                    .addComponent(PrimaryDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startSimButton)
                    .addComponent(jLabel1)
                    .addComponent(stopSimButton)
                    .addComponent(resetSimButton))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        LOGGER.log(Level.FINE, String.format("evt code,extcode,char,text: %d, %d, %c, %s", 
            evt.getKeyCode(), evt.getExtendedKeyCode(), evt.getKeyChar(), KeyEvent.getKeyText(evt.getKeyCode()))
        );
        if (! painter.running()) {
            return;
        }
        int key = evt.getKeyCode();
        switch (key) {
            case 39:
                lander.pitchDn();
                break;
            case 37:
                lander.pitchUp();
                break;
            case 38:
                lander.dsThrottleUp();
                break;
            case 40:
                lander.dsThrottleDn();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_formKeyPressed

    private void scaleDnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scaleDnButtonActionPerformed
        lander.scaleDn();
        Buttons.configure();
    }//GEN-LAST:event_scaleDnButtonActionPerformed

    private void scaleUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scaleUpButtonActionPerformed
        lander.scaleUp();
        Buttons.configure();
    }//GEN-LAST:event_scaleUpButtonActionPerformed

    private void startSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSimButtonActionPerformed
        painter.start();
        landersimulator.start();
        Buttons.configure();
    }//GEN-LAST:event_startSimButtonActionPerformed

    private void stopSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopSimButtonActionPerformed
        landersimulator.stop();
        painter.stop();
        Buttons.configure();
    }//GEN-LAST:event_stopSimButtonActionPerformed

    private void resetSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetSimButtonActionPerformed
        landersimulator.stop();
        painter.stop();
        lander.reset();
        painter.paint();
        Buttons.configure();
    }//GEN-LAST:event_resetSimButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        LogSetup.setup(LOGGER);

        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } 


        lunarlander = new LunarLander();        

        lander = new Lander();
        
        landersimulator = new LanderSimulator();

        scenery = new Scenery();

        painter = new Painter();
        
        Buttons.configure();

        lunarlander.setVisible(true);
    }
    
    public static LunarLander lunarlander;
    public static Lander lander;
    public static LanderSimulator landersimulator;
    public static Scenery scenery;
    public static Painter painter;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel PrimaryDisplay;
    public static javax.swing.JPanel TelemetryDisplay;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JButton resetSimButton;
    public static javax.swing.JButton scaleDnButton;
    public static javax.swing.JButton scaleUpButton;
    public static javax.swing.JButton startSimButton;
    public static javax.swing.JButton stopSimButton;
    // End of variables declaration//GEN-END:variables
}
