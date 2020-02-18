/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ctg
 */
public class Painter {
    private static final Logger LOGGER = Logger.getLogger(Painter.class.getName());
    
    private boolean running;
    private boolean run;
    
    public Painter() {
        LogSetup.setup(LOGGER);
        running = false;
        run = false;
    }
    
    public synchronized void paint() {
//        LunarLander.PrimaryDisplay.repaint(0);
        LunarLander.TelemetryDisplay.repaint(0);
    }
    
    public synchronized void start() {
        LOGGER.log(Level.INFO, "start");

        running = true;
        run = true;

        Runnable r;
        
        r = () -> {
            int rateMillisecs = 10;
            
            Timing timer = new Timing();
            
            while (true) {
                if (! run) {
                    LOGGER.log(Level.INFO, "finish");
                    running = false;
                    break;
                }
                
                timer.start();
                
                paint();

                timer.sleepUpToMillisecs(rateMillisecs);
            }
        };

        Thread t = new Thread(r, "repainter");
        
        t.start();
    }

    public synchronized void stop() {
        LOGGER.log(Level.INFO, "stop");

        run = false;

        while (running) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public synchronized boolean running() {
        return(running);
    }
}
