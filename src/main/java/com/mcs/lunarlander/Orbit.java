/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

/**
 * @author ctg
 */
public class Orbit {
    private double apolune;
    private double perilune;

    public Orbit(double perilune, double apolune) {
        this.apolune = apolune;
        this.perilune = perilune;
    }

    public boolean circular() {
        return(apolune == perilune);
    }
    
    public double focalDistance() {
        return(apolune - perilune);
    }
}
