/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.awt.Graphics2D;
import java.util.logging.Logger;

/**
 * @author ctg
 */
public class Draw {
    private static final Logger LOGGER = Logger.getLogger(Draw.class.getName());

    // draw an angled line
    public static int[] line(Graphics2D g2d, int startX, int startY, int length, int degrees) {
        LogSetup.setup(LOGGER);

        int[] xy;

        xy = Geometry.endPoint(startX, startY, length, degrees);
        
        g2d.drawLine(startX, startY, xy[Geometry.X], xy[Geometry.Y]);

        return(xy);
    }
}
