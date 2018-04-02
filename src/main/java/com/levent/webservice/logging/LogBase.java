package com.levent.webservice.logging;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.levent.webservice.configuration.ConfigManager;

public class LogBase {
	
	public static Logger log;
	
	// When Base class extends this class, you can use this constructor in future
	public LogBase(Class clazz) {
		// log
		Properties p = new Properties();
		
		try {
			String propFile = ConfigManager.config().LOG4J_PROPERTIES;
			System.out.println("LOG4J properties file location: " + propFile);
		    p.load(new FileInputStream(propFile));
		    PropertyConfigurator.configure(p);
		    
		    log = Logger.getLogger(clazz.getName());
		    
		    log.info("Wow! I'm configured!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Logger failed");
		}
	}
	
	// Use this method to receive Logger
	public static Logger getLogger(Class clazz) {
		// log
		Properties p = new Properties();
		
		try {
			String propFile = ConfigManager.config().LOG4J_PROPERTIES;
			System.out.println("LOG4J properties file location: " + propFile);
		    p.load(new FileInputStream(propFile));
		    PropertyConfigurator.configure(p);
		    
		    log = Logger.getLogger(clazz.getName());
		    
		    log.info("Log configuration is successfully set.");
		} catch (IOException e) {
			System.out.println("The log file: " + ConfigManager.config().LOG4J_PROPERTIES + 
					" is not found on the directory: " + ConfigManager.config().RESOURCE_DIRECTORY);
			e.printStackTrace();
			
			throw new RuntimeException("Logger failed");
		}
		
		return log;
	}
	
}
