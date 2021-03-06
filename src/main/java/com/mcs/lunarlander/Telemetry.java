/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author ctg
 */
public class Telemetry {
    private final static Logger LOGGER = Logger.getLogger(Telemetry.class.getName());

    private double[] LOC = new double[2]; // absolute coords off origin, feet
    private double[] V = new double[2]; // velocity, fps
    private Orbit orbit; // an orbital program
    private List<double[]> history = new ArrayList<>();
    
    public Telemetry() {
        LogSetup.setup(LOGGER);
        reset();
    }

    // V
    public double[] getV() {
        return(V);
    }
    
    public void setV(double[] v) {
        V[Geometry.X] = v[Geometry.X];
        V[Geometry.Y] = v[Geometry.Y];
    }

    // LOC
    public double[] getLOC() {
        return(LOC);
    }
    
    public void setLOC(double[] loc) {
        LOC[Geometry.X] = loc[Geometry.X];
        LOC[Geometry.Y] = loc[Geometry.Y];
    }

    // Telemetry history
    public List<double[]> getHistory() {
        return(history);
    }

    // altitude in feet
    public double getAlt() {
        double distance = Geometry.distance(Geometry.ORIGIN, LOC);
        distance -= Moon.RADIUS;
        return(distance);
    }
    
    public final void reset () {
        V[Geometry.X] = 5500;
        V[Geometry.Y] = 0;

        LOC[Geometry.X] = 0;
        LOC[Geometry.Y] = Navmath.nmToFt(109.82074) + Moon.RADIUS;
        
        orbit = new Orbit(Navmath.nmToFt(60) + Moon.RADIUS, Navmath.nmToFt(170) + Moon.RADIUS);
        
        history.clear();
        
        history.add(LOC);
    }

    // angle of inetia in radians
    private double findThetaI() {
        double thetaI;

        double x = LOC[Geometry.X];
        double y = LOC[Geometry.Y];

        double opp = Math.abs(y);
        double adj = Math.abs(x);
        double hyp = Geometry.distance(Geometry.ORIGIN, LOC);

        double angleOffX;
        double angle;
        double tangentAngle;

        if ((adj + opp) > hyp) {
            angleOffX = Math.asin(opp / hyp);
        } else {
            angleOffX = 0;
        }

        if (x > 0) {
            if (y >= 0) {
                angle = angleOffX;
            } else {
                angle = Geometry.D360 - angleOffX;
            }
        } else if (x < 0) {
            if (y >= 0) {
                angle = Geometry.D180 - angleOffX;
            } else {
                angle = Geometry.D180 + angleOffX;
            }
        } else {
            if (y > 0) {
                angle = Geometry.D90;
            } else {
                angle = Geometry.D270;
            }
        }
        
        thetaI = angle + Geometry.D90;

        if (orbit.circular() || y == 0) {
            return(thetaI);
        }

        double focalDis = orbit.focalDistance();
        double[] focalB = new double[]{focalDis, 0.0};

        double a = Geometry.distance(Geometry.ORIGIN, LOC);
        double b = Geometry.distance(focalB, LOC);
        double c = focalDis;

        double C = Math.acos( (Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b));

        C /= 2;

        if (LOC[Geometry.Y] < 0) {
            C *= -1;
        }

        thetaI += C;

        return(thetaI);
    }

    public double[] move(double slices) {
        double vx = V[Geometry.X] / slices;
        double vy = Moon.GRAVITY / slices;        
        
        double thetaI = findThetaI();
        if (V[Geometry.X] > 0) {
            thetaI += Geometry.D180;
        }

        double[] endXY = Geometry.newPoint(LOC, vx, thetaI);
        
        double a = Geometry.distance(LOC, endXY);
        double b = Geometry.distance(endXY, Geometry.ORIGIN);
        double c = Geometry.distance(Geometry.ORIGIN, LOC);

        double C = Math.acos( (Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b));

        double gravityAngle;
        
        if (thetaI <= Geometry.D180) {
            gravityAngle = thetaI + Geometry.D180 - C;
        } else {
            gravityAngle = thetaI - Geometry.D180 + C;
        }

        endXY = Geometry.newPoint(endXY, vy, gravityAngle);
        
        int[] LOCdrawXY = Geometry.realToDraw(LOC);
        int[] enddrawXY = Geometry.realToDraw(endXY);

        if (LOCdrawXY[Geometry.X] != enddrawXY[Geometry.X] || LOCdrawXY[Geometry.X] != enddrawXY[Geometry.X]) {
            history.add(endXY);
        }

        LOC[Geometry.X] = endXY[Geometry.X];
        LOC[Geometry.Y] = endXY[Geometry.Y];

        return(endXY);
    }
}
