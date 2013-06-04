package org.securityartwork.storm.social.analyzer;

import org.vertx.java.core.Vertx;


public class SocialVertxServer {
	
	private Integer vertxServerPort;
	private String vertxServerAddress;
	
	private VerticleWebSocketCallback callback;
	
	public VerticleWebSocketCallback getCallback() {
		return callback;
	}
	
	public void setCallback(VerticleWebSocketCallback callback) {
		this.callback = callback;
	}

	public void start() {
		
	    Vertx vertx = Vertx.newVertx();
		vertx.createHttpServer().websocketHandler( callback ).listen(9998, "localhost");
		
	}
	
	
}