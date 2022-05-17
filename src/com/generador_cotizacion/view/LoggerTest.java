package com.generador_cotizacion.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerTest {

	private static Logger logger = LogManager.getLogger(LoggerTest.class);
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info("Hello from Logback");

        logger.error("getNumber() : {}", getNumber());

        logger.warn("Warning some");
    }

    static int getNumber() {
        return 5;
    }

}
