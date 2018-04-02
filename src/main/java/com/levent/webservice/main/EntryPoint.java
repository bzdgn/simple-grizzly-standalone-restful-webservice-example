package com.levent.webservice.main;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.levent.webservice.configuration.ConfigManager;
import com.levent.webservice.logging.LogBase;

public class EntryPoint {

	private static Logger log = LogBase.getLogger(EntryPoint.class);
	
	public static void main(String[] args) throws IOException {
		log.info("customer-api is initializing.");
		@SuppressWarnings("unused")
		Server server = new Server();
		
		printConfiguration();
	}
	
	private static void printConfiguration() {
		System.out.println(ConfigManager.config());
		log.info(ConfigManager.config());
	}

}
