/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ctg
 */
public class Timing {
    private static final Logger LOGGER = Logger.getLogger(Painter.class.getName());

    private double startSecs;
    
    public Timing() {
        LogSetup.setup(LOGGER);
    }

    public void start() {
        startSecs = nanosecs();
    }
    
    public void sleepUpToMillisecs(int milliSecs) {
        int sleepTime = milliSecs - elapMillisecs();

        if (sleepTime < 1) {
            LOGGER.log(Level.WARNING, "sleep time already exceeded");
            return;
        }

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public int elapMillisecs() {
        return((int)Math.round((nanosecs() - startSecs) * 1000.0));
    }

    public static double nanosecs() {
        long time = System.nanoTime();

        double seconds = time / 1000000000.0;

        return(seconds);
    }
}
