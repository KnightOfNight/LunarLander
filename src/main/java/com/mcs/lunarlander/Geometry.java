/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ctg
 */
public class Geometry {
    private static final Logger LOGGER = Logger.getLogger(Geometry.class.getName());
    
    public static final int X = 0;
    public static final int Y = 1;

    public static final double[] ORIGIN = new double[]{0.0, 0.0};

    public static final double RT = 0;
    public static final double UP = Math.toRadians(90);
    public static final double LF = Math.toRadians(180);
    public static final double DN = Math.toRadians(270);

    public static final double D90 = Math.toRadians(90);
    public static final double D180 = Math.toRadians(180);
    public static final double D270 = Math.toRadians(270);
    public static final double D360 = Math.toRadians(360);
    
    // translate a point's coords new angle off the origin
    public static int[] translate(int originX, int originY, int pointX, int pointY, int angle) {
        LogSetup.setup(LOGGER);

        LOGGER.log(Level.FINE, String.format("originX/originY: %d/%d", originX, originY));
        LOGGER.log(Level.FINE, String.format("pointX/pointY: %d/%d", pointX, pointY));

        int[] newPointXY = new int[2];

        int dx = pointX - originX; // adjacent side
        int dy = pointY - originY; // opposite side
                
        double length = Math.hypot((double)dx, (double)dy); // hypotenuse
        
        double theta; 

        if ((dx == 0) || (dy == 0)) {
            theta = 0;
        } else {
            theta = Math.abs(Math.atan((double)dy/(double)dx));
        }

        double finalAngle = Math.toRadians(angle);

        if (dx > 0) {
            if (dy < 0) {
                finalAngle += theta;
            } else if (dy > 0) {
                finalAngle += (Math.toRadians(360) - theta);
            }
        } else if (dx < 0) {
            finalAngle += Math.toRadians(180);
            if (dy < 0) {
                finalAngle -= theta;
            } else if (dy > 0) {
                finalAngle += theta;
            }
        } else { // dx == 0
            if (dy < 0) {
                finalAngle += Math.toRadians(90);
            } else if (dy > 0) {
                finalAngle += Math.toRadians(270);
            } 
        }

        LOGGER.log(Level.FINE, String.format("adj/opp/hyp/theta/finalAngle: %d/%d/%f/%f/%f", dx, dy, length, theta, finalAngle));
        
        double offsetX = length * Math.cos(finalAngle);
        double offsetY = length * Math.sin(finalAngle);
        
        newPointXY[X] = (int)Math.round(originX + offsetX);
        newPointXY[Y] = (int)Math.round(originY - offsetY);
        
        LOGGER.log(Level.FINE, String.format("offsetX/offsetY/: %f/%f", offsetX, offsetY));
        LOGGER.log(Level.FINE, String.format("endX/endY: %d/%d", newPointXY[X], newPointXY[Y]));

        return(newPointXY);
    }
    
    // return the coordinates at the end of the line
    public static int[] endPoint(int startX, int startY, int length, int degrees) {
        LogSetup.setup(LOGGER);

        int[] endXY = new int[2];

        double angle = Math.toRadians(degrees);

        endXY[X] = (int)Math.round(startX + (length * Math.cos(angle)));
        endXY[Y] = (int)Math.round(startY - (length * Math.sin(angle)));

        return(endXY);        
    }
    
    // append the source XY coords into the respective separate destination arrays
    public static void appendCoords(int[] destX, int[] destY, int[] sourceXY, int idx) {
        LogSetup.setup(LOGGER);

        destX[idx] = sourceXY[X];
        destY[idx] = sourceXY[Y];
    }
    
    public static double distance(double[] xy1, double[] xy2) {
        double x = Math.pow(xy1[Geometry.X] - xy2[Geometry.X], 2);
        double y = Math.pow(xy1[Geometry.Y] - xy2[Geometry.Y], 2);
        return(Math.sqrt(x + y));
    }

    public static double[] newPoint(double[] startXY, double length, double radians) {
        double[] newXY = new double[2];

        newXY[X] = startXY[X] + (length * Math.cos(radians));
        newXY[Y] = startXY[Y] + (length * Math.sin(radians));

        return(newXY);
    }
}
