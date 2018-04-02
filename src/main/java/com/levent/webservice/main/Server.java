package com.levent.webservice.main;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.levent.webservice.configuration.ConfigManager;

public class Server {
	
	private final URI ADDRESS = UriBuilder.fromPath(ConfigManager.config().ENDPOINT_URI).build();
	
	public Server() {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.packages("com.levent.webservice.controller");
		GrizzlyHttpServerFactory.createHttpServer(ADDRESS, resourceConfig);
	}
	
}
