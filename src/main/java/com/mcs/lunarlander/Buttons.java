/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

/**
 * @author ctg
 */
public class Buttons {
    public static void configure() {
        if (LunarLander.painter.running()) {
            LunarLander.startSimButton.setEnabled(false);
            LunarLander.stopSimButton.setEnabled(true);
            LunarLander.resetSimButton.setEnabled(true);

            if (LunarLander.lander.getScale() > Lander.MINSCALE) {
                LunarLander.scaleDnButton.setEnabled(true);                
            } else {
                LunarLander.scaleDnButton.setEnabled(false);                
            }

            if (LunarLander.lander.getScale() < Lander.MAXSCALE) {
                LunarLander.scaleUpButton.setEnabled(true);                
            } else {
                LunarLander.scaleUpButton.setEnabled(false);                
            }
        } else {
            LunarLander.startSimButton.setEnabled(true);
            LunarLander.stopSimButton.setEnabled(false);
            LunarLander.resetSimButton.setEnabled(true);
            LunarLander.scaleDnButton.setEnabled(false);
            LunarLander.scaleUpButton.setEnabled(false);            
        }
    }
}
