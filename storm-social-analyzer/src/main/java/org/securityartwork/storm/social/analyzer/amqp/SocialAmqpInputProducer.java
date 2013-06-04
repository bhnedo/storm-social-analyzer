package org.securityartwork.storm.social.analyzer.amqp;

import org.securityartwork.storm.social.analyzer.common.Status;


public interface SocialAmqpInputProducer {
	
	void emit(Status status);
}
