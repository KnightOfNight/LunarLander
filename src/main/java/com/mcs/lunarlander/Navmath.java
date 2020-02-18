/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

/**
 * @author ctg
 */
public class Navmath {
    private final static double FTPERNM = 6076;
    
    public static double nmToFt(double nm) {
        return(nm * FTPERNM);
    }
}
