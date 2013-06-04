package org.securityartwork.storm.social.analyzer.spouts;

import java.util.Map;

import org.apache.log4j.Logger;
import org.securityartwork.storm.social.analyzer.amqp.SocialAmqpInputConsumer;
import org.securityartwork.storm.social.analyzer.common.Status;
import org.securityartwork.storm.social.analyzer.context.AppContext;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class AmqpStatusEmitterSpout implements IRichSpout {
	
	private static final long serialVersionUID = 289625169644161057L;
	
	private static final Logger logger = Logger.getLogger(AmqpStatusEmitterSpout.class);
	
	private SpoutOutputCollector collector;
	
	private transient SocialAmqpInputConsumer socialAmqpInputConsumer;

	@Override
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		this.collector = collector;
		this.socialAmqpInputConsumer = AppContext.getApplicationContext().getBean(SocialAmqpInputConsumer.class);
	}

	@Override
	public void close() {
		
	}

	@Override
	public void activate() {
	
	}

	@Override
	public void deactivate() {
		
	}

	@Override
	public void nextTuple() {
		try {
			Status status = socialAmqpInputConsumer.consume();
			if ( status != null ) {
				logger.info("Emitting status " + status.getMessage() );
				this.collector.emit(new Values( status.getMessage(), status.getFrom()  ) );
			} else {
				Utils.sleep(500);
			}
		} catch(Exception e) {
			logger.error("Error while reading tuples from AMQP queue", e);
		}
	}

	@Override
	public void ack(Object msgId) {
		// TODO : ack delivery tag
	}

	@Override
	public void fail(Object msgId) {
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("status", "from"));
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
	
	
}
