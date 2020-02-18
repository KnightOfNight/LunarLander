/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ctg
 */
public class LanderSimulator {
    private static final Logger LOGGER = Logger.getLogger(LanderSimulator.class.getName());
    
    private boolean running;
    private boolean run;
    
    public LanderSimulator() {
        LogSetup.setup(LOGGER);
        running = false;
        run = false;
    }
    
    public synchronized void start() {
        if (running) {
            return;
        }
        
        running = true;
        run = true;

        LOGGER.log(Level.INFO, "start");

        Runnable r;

//        Lander lander = LunarLander.lander;
        
        r = () -> {
            int rateMillisecs = 25;
            double slices = 1000.0 / rateMillisecs;
            int slice = 0;
            Timing timer = new Timing();
            Lander lander = LunarLander.lander;
            
            while(true) {
                
                if (! run) {
                    LOGGER.log(Level.INFO, "finish");
                    running = false;
                    break;
                }
                
                if (slice == 0) {
                    slice++;
                    continue;
                }

                timer.start();

                lander.telemetry().move(1);

                timer.sleepUpToMillisecs(rateMillisecs);
                
                slice++;
            }
        };

        Thread t = new Thread(r, "simulator");
        
        t.start();        
    }
    
    public synchronized void Oldstart() {
        if (running) {
            return;
        }
        
        running = true;
        run = true;

        LOGGER.log(Level.INFO, "start");

        Runnable r;
        
        r = () -> {
            double[] cg;
            double[] v;
            int rateMillisecs = 25;
            double slices = 1000.0 / rateMillisecs;
            int slice = 0;
            Timing timer = new Timing();
            Lander lander = LunarLander.lander;
            
            while(true) {
                if (! run) {
                    LOGGER.log(Level.INFO, "finish");
                    running = false;
                    break;
                }
                
                timer.start();
                
                cg = lander.getCg();
                v = lander.v();
                
                if (slice > 0) {
                    v[Geometry.Y] += LunarLander.GRAVITY / slices;
                    
                    double[] dv = lander.getDeltav();
                    
                    dv[Geometry.X] /= slices;
                    dv[Geometry.Y] /= slices;

                    v[Geometry.X] += dv[Geometry.X];
                    v[Geometry.Y] += dv[Geometry.Y];
                }
                
                double dx = v[Geometry.X] / slices;
                double dy = v[Geometry.Y] / slices;

                cg[Geometry.X] += dx;
                cg[Geometry.Y] += dy;
                
//                LOGGER.log(Level.INFO, String.format("cg: %f,%f", cg[Geometry.X], cg[Geometry.Y]));

                Dimension panelD = LunarLander.PrimaryDisplay.getSize();
                
                if (cg[Geometry.X] > panelD.width) {
                    cg[Geometry.X] = 0;
                }
                if (cg[Geometry.X] < 0) {
                    cg[Geometry.X] = panelD.width;
                }

                if (cg[Geometry.Y] > panelD.height) {
                    cg[Geometry.Y] = 0;
                }
                if (cg[Geometry.Y] < 0) {
                    cg[Geometry.Y] = panelD.height;
                }

                LunarLander.lander.setCg(cg);
                LunarLander.lander.setV(v);

                timer.sleepUpToMillisecs(rateMillisecs);
                
                slice++;
            }
        };

        Thread t = new Thread(r, "simulator");
        
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
