package com.levent.webservice.configuration;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static final ConfigManager instance = new ConfigManager();
	
	// Resource Directory - Log file also will be here
	public final String RESOURCE_DIRECTORY;
	public final String LOG4J_PROPERTIES;
	
	public final String ENDPOINT_URI;
	
	
	private ConfigManager() {
		Config config = new Config(Config.CONFIG_FILE_NAME);
		
		// RESOURCE AND LOG
		RESOURCE_DIRECTORY			= Config.RESOURCE_DIR;
		LOG4J_PROPERTIES			= RESOURCE_DIRECTORY + "/log4j.properties";
		
		ENDPOINT_URI				= config.getProperty("endpoint_uri");
	}
	

	
	public static ConfigManager config() {
		return instance;
	}
	
	private static class Config {
		
		private static final String 			ENVIRONMENT_VAR_NAME	= "CUSTOMER_API_CONFIG";
		public static final String 				CONFIG_FILE_NAME 		= "customer_api.properties";
		private static final String 			RESOURCE_DIR;
		private Properties 						configFile;
		
		static {
			String envVal = System.getenv(ENVIRONMENT_VAR_NAME);
			System.out.print("RESOURCE_DIR SET TO: ");
			if(envVal == null) {
				RESOURCE_DIR = ".";
				System.out.print("Default Value = Working dir of jar\n");
			} else {
				RESOURCE_DIR = envVal;
				System.out.print("Environment Parameter = " + RESOURCE_DIR + "\n");
			}
		}

		public Config(String configFileName) {
			configFile = new java.util.Properties();

			try (FileInputStream fileInputStream = new FileInputStream(RESOURCE_DIR + "//" + configFileName)) {
				configFile.load(fileInputStream);
			} catch (Exception eta) {
				System.out.println("Cannot find \"" + configFileName +  "\" in \"" + RESOURCE_DIR + "\"");
				System.out.println("Exiting program.");
				System.exit(1);
//				eta.printStackTrace();
			}
		}

		public String getProperty(String key) {
			return this.configFile.getProperty(key);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append("Resource Directory               : " + RESOURCE_DIRECTORY).append("\n")
		.append("Log4J Property file              : " + LOG4J_PROPERTIES).append("\n")
		.append("Endpoint URI                     : " + ENDPOINT_URI).append("\n")
		.append("\n");
		
		return sb.toString();
	}
	
	

}
