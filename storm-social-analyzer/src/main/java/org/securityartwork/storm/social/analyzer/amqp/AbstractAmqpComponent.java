package org.securityartwork.storm.social.analyzer.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class AbstractAmqpComponent {
	
	protected RabbitTemplate rabbitTemplate;
	
	protected String amqpSocialInputExchange;
	protected String amqpSocialRoutingKey;
	
	protected String amqpSocialInputQueue;

	public String getAmqpSocialInputQueue() {
		return amqpSocialInputQueue;
	}

	public void setAmqpSocialInputQueue(String amqpSocialInputQueue) {
		this.amqpSocialInputQueue = amqpSocialInputQueue;
	}

	public String getAmqpSocialInputExchange() {
		return amqpSocialInputExchange;
	}

	public void setAmqpSocialInputExchange(String amqpSocialInputExchange) {
		this.amqpSocialInputExchange = amqpSocialInputExchange;
	}

	public String getAmqpSocialRoutingKey() {
		return amqpSocialRoutingKey;
	}

	public void setAmqpSocialRoutingKey(String amqpSocialRoutingKey) {
		this.amqpSocialRoutingKey = amqpSocialRoutingKey;
	}

	public RabbitTemplate getRabbitTemplate() {
		return rabbitTemplate;
	}

	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
}
