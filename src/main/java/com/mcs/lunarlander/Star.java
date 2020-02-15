/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.awt.Graphics2D;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author ctg
 */
public class Star {
    private static final Logger LOGGER = Logger.getLogger(Scenery.class.getName());
    private static final Random RANDOM = new Random();
    
    private int x;
    private int y;
    private int size;
    private int brightness;
    private int color;
    
    public Star(int locX, int locY) {
        x = locX;
        y = locY;
        size = RANDOM.nextInt(4) + 1;
        brightness = Math.max(100, RANDOM.nextInt(256));
        if (size == 4 && brightness > 150 && brightness < 225) {
            if (RANDOM.nextInt(9) == 8) {
                color = RANDOM.nextInt(3) + 1;
            } else {
                color = 0;
            }
        }
    }
    
    public void draw(Graphics2D g2d) {
        int r;
        int g;
        int b;
        
        r = brightness;
        g = brightness;
        b = brightness;

        switch (color) {
            case 1:
                r = 255;
                break;
            case 2:
                g = 255;
                break;
            case 3:
                b = 255;
                break;
            default:
                break;
        }
        
        g2d.setColor(new java.awt.Color(r, g, b));
        g2d.fillOval(x, y, size, size);
    }
}
