package org.securityartwork.storm.social.analyzer;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.ServerWebSocket;

public class VerticleWebSocketCallback implements Handler<ServerWebSocket> {
	
	private ServerWebSocket socket;
	
	@Override
	public void handle(ServerWebSocket socket) {
		this.socket = socket;
	}
	
	public void send(String payload) {
		if ( socket != null )
			 socket.writeTextFrame(payload);
		
	} 

}
