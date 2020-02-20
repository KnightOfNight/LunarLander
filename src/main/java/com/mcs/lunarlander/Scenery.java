/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.util.Random;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ctg
 */
public class Scenery {
    private static final Logger LOGGER = Logger.getLogger(Scenery.class.getName());

    private static final Random RANDOM = new Random();

    private List<Star> stars = new ArrayList<>();
    int[] groundX;
    int[] groundY;
    
    public Scenery() {
        LogSetup.setup(LOGGER);

        Dimension panelD = LunarLander.PrimaryDisplay.getSize();

        int numPixels = panelD.height * panelD.width;

        int maxStars = (int)Math.round((double)numPixels * .001);

        int numStars = Math.max(200, RANDOM.nextInt(maxStars));
        LOGGER.log(Level.FINE, String.format("numStars:%d", numStars));

        for (int i = 0; i < numStars; i++) {
            stars.add(new Star(RANDOM.nextInt(panelD.width), RANDOM.nextInt(panelD.height)));
        }

        groundX = new int[4];
        groundY = new int[4];
        int[] coords = new int[2];
        int i = 0;

        coords[Geometry.X] = 0;
        coords[Geometry.Y] = panelD.height - 25;
        Geometry.appendCoords(groundX, groundY, coords, i++);
        
        coords[Geometry.X] = 0;
        coords[Geometry.Y] = panelD.height;
        Geometry.appendCoords(groundX, groundY, coords, i++);

        coords[Geometry.X] = panelD.width;
        coords[Geometry.Y] = panelD.height;
        Geometry.appendCoords(groundX, groundY, coords, i++);

        coords[Geometry.X] = panelD.width;
        coords[Geometry.Y] = panelD.height - 25;
        Geometry.appendCoords(groundX, groundY, coords, i++);
    }
    
    public void draw(Graphics2D g2d) {
        int x;
        int y;
        int d;        
        int b;
        
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).draw(g2d);
        }
        
        g2d.setColor(new java.awt.Color(200, 200, 200));
        g2d.fillPolygon(groundX, groundY, 4);
    }
}
