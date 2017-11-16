/**
 * BrandBigData.com Inc
 * Copyright (c) 2017 All Rights Reserved
 */
package com.bbd.message.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author $ heyijun
 * @version Id: Start, v 0.1 2017-04-21 下午 5:29 heyijun Exp $$
 */
public class Start {

    private static final Logger logger = LoggerFactory.getLogger(Start.class);
    private static volatile boolean running = true;
    private static String configContext = "classpath:applicationContext.xml";

    public static void main(String[] args) throws IOException {
        final ArrayList var7 = new ArrayList();
        var7.add(new ClassPathXmlApplicationContext(new String[] {configContext}));

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
            try {
                ((ClassPathXmlApplicationContext)var7.get(0)).stop();
                Start.logger.info("reddata-liveness-job " + ((ClassPathXmlApplicationContext)var7.get(0)).getClass().getSimpleName() + " stopped!");
            } catch (Throwable var6) {
                Start.logger.error(var6.getMessage(), var6);
            }

            synchronized(Start.class) {
                Start.running = false;
                Start.class.notify();
            }
            }
        });

        ((ClassPathXmlApplicationContext)var7.get(0)).start();
        logger.warn("************ message job started! ***************");

        synchronized(Start.class) {
            while(running) {
                try {
                    Start.class.wait();
                } catch (Throwable var5) {
                    ;
                }
            }

        }
    }
}
