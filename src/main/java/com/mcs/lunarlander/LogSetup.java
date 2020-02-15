/*
 * Myriad Computer Serivces 2020
 */

package com.mcs.lunarlander;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;

/**
 * @author ctg
 */
public class LogSetup {
    public static void setup(Logger l) {
        l.setUseParentHandlers(false);
        l.setLevel(Level.FINEST);
        Handler[] handlers = l.getHandlers();
        for (Handler h : handlers) {
            l.removeHandler(h);
        }
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(LogFormatter.LEVEL);
        handler.setFormatter(new LogFormatter());
        l.addHandler(handler);
    }
}
