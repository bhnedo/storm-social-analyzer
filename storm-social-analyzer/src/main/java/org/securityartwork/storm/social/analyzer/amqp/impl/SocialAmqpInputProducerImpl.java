package org.securityartwork.storm.social.analyzer.amqp.impl;

import org.securityartwork.storm.social.analyzer.amqp.AbstractAmqpComponent;
import org.securityartwork.storm.social.analyzer.amqp.SocialAmqpInputProducer;
import org.securityartwork.storm.social.analyzer.common.Status;

public class SocialAmqpInputProducerImpl extends AbstractAmqpComponent implements SocialAmqpInputProducer {
	
	@Override
	public void emit(Status status) {
		rabbitTemplate.convertAndSend( amqpSocialInputExchange, 
				   					   amqpSocialRoutingKey, 
				   					   status );		
		
	}

}
