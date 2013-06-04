package org.securityartwork.storm.social.analyzer.amqp.impl;

import org.securityartwork.storm.social.analyzer.amqp.AbstractAmqpComponent;
import org.securityartwork.storm.social.analyzer.amqp.SocialAmqpInputConsumer;
import org.securityartwork.storm.social.analyzer.common.Status;

public class SocialAmqpInputConsumerImpl extends AbstractAmqpComponent implements SocialAmqpInputConsumer {

	@Override
	public Status consume() {
		return (Status) rabbitTemplate.receiveAndConvert(amqpSocialInputQueue);
		
	}

}
