package org.securityartwork.storm.social.analyzer.bolts;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.google.common.base.Splitter;

public class StatusSplittingBolt implements IRichBolt {

	private static final long serialVersionUID = -9100761779774559607L;
	
	private static final Logger logger = Logger.getLogger(StatusSplittingBolt.class);
	
	private OutputCollector collector;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		try {
			
			String status = input.getString(0);
			Iterator<String> segments = Splitter.on(" ")
											.trimResults()
											.omitEmptyStrings()
											.split(status).iterator();
			while( segments.hasNext() ) {
				String segment = segments.next().toLowerCase();
				if ( segment.length() > 4 )
					collector.emit(new Values(segment, input.getString(1)));
			}
			collector.ack(input);
		
		} catch(Exception e) {
			logger.error("Error while spliting statuses", e);
		}
		
	}

	@Override
	public void cleanup() {
	
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word", "from"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
