/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ctg
 */
public class Lander {
    private static final Logger LOGGER = Logger.getLogger(Lander.class.getName());
    
    public static final int MINSCALE = 2;
    public static final int MAXSCALE = 15;

    @SuppressWarnings("FieldMayBeFinal")    
    private int cgX;
    private int cgY;
    private double cgXd;
    private double cgYd;

    private double vX;
    private double vY;

    private int pitchAngle; // degrees: 180 to -180

    private int dsThrust; // percent
    private int asThrust; // percent

    private int Scale;

    private int Stroke;
    private int dsW;
    private int dsH;
    
    public Lander() {
        LogSetup.setup(LOGGER);

        reset();
    }
    
    // reset
    public final synchronized void reset() {
        cgX = 135;
        cgY = 135;
        cgXd = 135.0;
        cgYd = 135.0;
        
        vX = 50.0;
        vY = 0;
        
        pitchAngle = 0;

        dsThrust = 0;
        asThrust = 0;

        Scale = MINSCALE;

        setDrawParams();        
    }

    // CG
    public synchronized double[] getCg() {
        double[] cg = new double[2];
        cg[Geometry.X] = cgXd;
        cg[Geometry.Y] = cgYd;
        return(cg);
    }
    
    public synchronized void setCg(double[] cg) {
        cgXd = cg[Geometry.X];
        cgYd = cg[Geometry.Y];
        cgX = (int)cgXd;
        cgY = (int)cgYd;        
    }
    
    // V
    public synchronized double[] v() {
        double[] v = new double[2];
        v[Geometry.X] = vX;
        v[Geometry.Y] = vY;
        return(v);
    }
    
    public synchronized void setV(double[] v) {
        vX = v[Geometry.X];
        vY = v[Geometry.Y];
    }
    
    public synchronized double[] getDeltav() {
        double[] dv = new double[2];

        double dvx = 0;
        double dvy = 0;
        double cos = Math.cos(Math.toRadians(pitchAngle));
        double sin = Math.sin(Math.toRadians(pitchAngle));
        
        double thrust = Math.pow(dsThrust * .25, 2);

        if (thrust > 0) {
            if (pitchAngle == -90) {
                dvx = thrust;
            } else if (pitchAngle == 90) {
                dvx = -1 * thrust;
            } else if (pitchAngle > 0 && pitchAngle < 180) {
                dvx = -1 * Math.abs(thrust * sin);
            } else if (pitchAngle < 0 && pitchAngle > -180) {
                dvx = Math.abs(thrust * sin);
            }
            if (pitchAngle == 0) {
                dvy = -1 * thrust;
            } else if (pitchAngle == 180 || pitchAngle == -180) {
                dvy = thrust;
            } else if (pitchAngle < 90 && pitchAngle > -90) {
                dvy = -1 * Math.abs(thrust * cos);
            } else if (pitchAngle > 90 || pitchAngle < -90) {
                dvy = Math.abs(thrust * cos);
            }
        }
        dv[Geometry.X] = dvx;
        dv[Geometry.Y] = dvy;                                            

        return(dv);
    }
    
    // scaling
    public synchronized int getScale() {
        return(Scale);
    }
    
    public synchronized void setScale(int scale) {
        if (scale < MINSCALE || scale > MAXSCALE) {
            throw new IllegalArgumentException();
        }
        Scale = scale;
        setDrawParams();
    }
    
    public synchronized boolean scaleUp() {
        boolean ret = true;
        Scale++;
        if (Scale >= MAXSCALE) {
            ret = false;
            Scale = MAXSCALE;
        }
        setDrawParams();
        return(ret);
    }
    
    public synchronized boolean scaleDn() {
        boolean ret = true;
        Scale--;
        if (Scale <= MINSCALE) {
            ret = false;
            Scale = MINSCALE;
        }
        setDrawParams();
        return(ret);
    }
    
    private void setDrawParams() {
        dsW = Scale * 10;
        dsH = Scale * 5;        
        if (Scale < 8) {
            Stroke = 1;
        } else if (Scale < 13) {
            Stroke = 2;
        } else {
            Stroke = 3;
        }        
    }

    // pitch angle
    public synchronized int getPitchAngle() {
        return(pitchAngle);
    }

    public synchronized void setPitchAngle(int angle) {
        pitchAngle = angle;
    }
    
    public synchronized void pitchUp() {
        pitchAngle++;
        if (pitchAngle > 180) {
            pitchAngle = -179;
        }
    }

    public synchronized void pitchDn() {
        pitchAngle--;
        if (pitchAngle < -180) {
            pitchAngle = 179;
        }
    }

    // thrust
    public synchronized int getDSThrust() {
        return(dsThrust);
    }

    public synchronized int getASThrust() {
        return(asThrust);
    }

    public synchronized void setDSThrust(int thrust) {
        dsThrust = thrust;
    }

    public synchronized void setASThrust(int thrust) {
        asThrust = thrust;
    }

    public synchronized void dsThrottleUp() {
        dsThrust++;
        dsThrust = Math.min(100, dsThrust);
    }
    
    public synchronized void dsThrottleDn() {
        dsThrust--;
        dsThrust = Math.max(0, dsThrust);
    }

    // main draw
    public synchronized void draw (Graphics2D g2d) {
        _drawAS(g2d);
        _drawDS(g2d);
//        _drawCG(g2d);
    }

    private void _drawAS (Graphics2D g2d) {
        int endXY[];
        int txStartXY[];

        int startX;
        int startY;

        final int asEdge = dsW / 3;
        final int asAngle = 45;
        
        startX = cgX - (asEdge / 2);
        startY = cgY - (dsH / 2);

        txStartXY = Geometry.translate(cgX, cgY, startX, startY, pitchAngle);

        int[] x = new int[8];
        int[] y = new int[8];

        int i = 0;

        Geometry.appendCoords(x, y, txStartXY, i);

        endXY = Geometry.endPoint(txStartXY[Geometry.X], txStartXY[Geometry.Y], asEdge, pitchAngle + 0);
        Geometry.appendCoords(x, y, endXY, ++i);

        endXY = Geometry.endPoint(endXY[Geometry.X], endXY[Geometry.Y], asEdge, pitchAngle + asAngle);
        Geometry.appendCoords(x, y, endXY, ++i);

        endXY = Geometry.endPoint(endXY[Geometry.X], endXY[Geometry.Y], asEdge, pitchAngle + 90);
        Geometry.appendCoords(x, y, endXY, ++i);

        endXY = Geometry.endPoint(endXY[Geometry.X], endXY[Geometry.Y], asEdge, pitchAngle + 90 + asAngle);
        Geometry.appendCoords(x, y, endXY, ++i);

        endXY = Geometry.endPoint(endXY[Geometry.X], endXY[Geometry.Y], asEdge, pitchAngle + 180);
        Geometry.appendCoords(x, y, endXY, ++i);

        endXY = Geometry.endPoint(endXY[Geometry.X], endXY[Geometry.Y], asEdge, pitchAngle + 180 + asAngle);
        Geometry.appendCoords(x, y, endXY, ++i);

        endXY = Geometry.endPoint(endXY[Geometry.X], endXY[Geometry.Y], asEdge, pitchAngle + 270);
        Geometry.appendCoords(x, y, endXY, ++i);

        g2d.setStroke(new BasicStroke(Stroke));
        g2d.setColor(new java.awt.Color(0, 0, 0));
        g2d.fillPolygon(x, y, 8);
        g2d.setColor(new java.awt.Color(255, 255, 255));
        g2d.drawPolygon(x, y, 8);
    }

    private void _drawDS (Graphics2D g2d) {
        int startX;
        int startY;
        int txStartXY[];
        int endXY[];

        int i;

        // body
        int[] bodyX = new int[4];
        int[] bodyY = new int[4];
        startX = cgX - (dsW / 2);
        startY = cgY + (dsH / 2);
        i = 0;

        txStartXY = Geometry.translate(cgX, cgY, startX, startY, pitchAngle);
        Geometry.appendCoords(bodyX, bodyY, txStartXY, i++);

        endXY = Geometry.endPoint(txStartXY[Geometry.X], txStartXY[Geometry.Y], dsW, pitchAngle + 0);
        Geometry.appendCoords(bodyX, bodyY, endXY, i++);

        endXY = Geometry.endPoint(endXY[Geometry.X], endXY[Geometry.Y], dsH, pitchAngle + 90);
        Geometry.appendCoords(bodyX, bodyY, endXY, i++);

        endXY = Geometry.endPoint(endXY[Geometry.X], endXY[Geometry.Y], dsW, pitchAngle + 180);
        Geometry.appendCoords(bodyX, bodyY, endXY, i++);

        g2d.setStroke(new BasicStroke(Stroke));        
        g2d.setColor(new java.awt.Color(0, 0, 0));
        g2d.fillPolygon(bodyX, bodyY, 4);
        g2d.setColor(new java.awt.Color(255, 255, 255));
        g2d.drawPolygon(bodyX, bodyY, 4);

        // engine bell
        final int engineL = Scale * 3;
        final int engineAngle = 25;
        int[] bellX = new int[4];
        int[] bellY = new int[4];
        startX = cgX - (dsW / 7);
        startY = cgY + (dsH / 2);
        i = 0;
        
        txStartXY = Geometry.translate(cgX, cgY, startX, startY, pitchAngle);        
        Geometry.appendCoords(bellX, bellY, txStartXY, i++);

        endXY = Geometry.endPoint(txStartXY[Geometry.X], txStartXY[Geometry.Y], engineL, pitchAngle + 270 - engineAngle);
        Geometry.appendCoords(bellX, bellY, endXY, i++);

        startX = cgX + (dsW / 7);
        txStartXY = Geometry.translate(cgX, cgY, startX, startY, pitchAngle);        

        endXY = Geometry.endPoint(txStartXY[Geometry.X], txStartXY[Geometry.Y], engineL, pitchAngle + 270 + engineAngle);
        Geometry.appendCoords(bellX, bellY, endXY, i++);
        
        Geometry.appendCoords(bellX, bellY, txStartXY, i++);
        
        g2d.setStroke(new BasicStroke(Stroke));
        g2d.setColor(new java.awt.Color(255, 255, 255));
        g2d.drawPolygon(bellX, bellY, 4);

        // rocket exhaust
        if (dsThrust > 0) {
            int exhX[] = new int[3];
            int exhY[] = new int[3];
            startY = cgY + (dsH / 2) + engineL + Stroke;
            startX = cgX + (dsW / 5);
            i = 0;
            txStartXY = Geometry.translate(cgX, cgY, startX, startY, pitchAngle);
            Geometry.appendCoords(exhX, exhY, txStartXY, i++);

            startX = cgX - (dsW / 5);
            txStartXY = Geometry.translate(cgX, cgY, startX, startY, pitchAngle);
            Geometry.appendCoords(exhX, exhY, txStartXY, i++);

            startX = cgX;
            startY = startY + (int)Math.round((double)(dsThrust * Scale) / 10.0)  + (int)((double)Scale / 2.0);
            txStartXY = Geometry.translate(cgX, cgY, startX, startY, pitchAngle);
            Geometry.appendCoords(exhX, exhY, txStartXY, i++);

            g2d.setColor(new java.awt.Color(255, 135, 0));
            g2d.fillPolygon(exhX, exhY, 3);
        }
        
        // landing gear
        final int lgW = Scale * 3;
        final int lgA = 30;
        int lgH = (int)Math.round((double)dsH * 1.5);
        startX = cgX;
        startY = cgY;

        g2d.setStroke(new BasicStroke(Stroke));
        g2d.setColor(new java.awt.Color(255, 255, 255));
        Draw.line(g2d, startX, startY, lgH, pitchAngle + 270);
        txStartXY = Geometry.translate(cgX, cgY, startX - (lgW / 2), startY + lgH, pitchAngle);
        Draw.line(g2d, txStartXY[Geometry.X], txStartXY[Geometry.Y], lgW, pitchAngle);

        startX = cgX - (dsW / 2);
        lgH = (int)Math.round((double)lgH / Math.cos(Math.toRadians(lgA)));
        txStartXY = Geometry.translate(cgX, cgY, startX, startY, pitchAngle);
        endXY = Draw.line(g2d, txStartXY[Geometry.X], txStartXY[Geometry.Y], lgH, pitchAngle + 270 - lgA);
        Draw.line(g2d, endXY[Geometry.X], endXY[Geometry.Y], lgW / 2, pitchAngle);
        Draw.line(g2d, endXY[Geometry.X], endXY[Geometry.Y], lgW / 2, pitchAngle + 180);
        
        startX = cgX + (dsW / 2);
        txStartXY = Geometry.translate(cgX, cgY, startX, startY, pitchAngle);
        endXY = Draw.line(g2d, txStartXY[Geometry.X], txStartXY[Geometry.Y], lgH, pitchAngle + 270 + lgA);
        Draw.line(g2d, endXY[Geometry.X], endXY[Geometry.Y], lgW / 2, pitchAngle);
        Draw.line(g2d, endXY[Geometry.X], endXY[Geometry.Y], lgW / 2, pitchAngle + 180);
    }
    
    private void _drawCG(Graphics2D g2d) {
        g2d.setColor(new java.awt.Color(255, 0, 0));
        g2d.drawOval(cgX, cgY, 2, 2);        
    }
}
