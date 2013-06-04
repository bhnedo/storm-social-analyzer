package org.securityartwork.storm.social.analyzer.bolts;

import static org.securityartwork.storm.social.analyzer.utils.MultiSetUtils.topByCount;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.securityartwork.storm.social.analyzer.VerticleWebSocketCallback;
import org.securityartwork.storm.social.analyzer.context.AppContext;
import org.securityartwork.storm.social.analyzer.utils.JacksonSerializer;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

public class StatusSegmentatorBolt implements IRichBolt {

	private static final long serialVersionUID = 8504530108911841862L;
	
	private static final Logger logger = Logger.getLogger(StatusSegmentatorBolt.class);
	
	private OutputCollector collector;
	private SortedMultiset<String> counters = TreeMultiset.create();
	
    private transient VerticleWebSocketCallback callback;
    
    @Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
		this.callback = AppContext.getApplicationContext().getBean(VerticleWebSocketCallback.class);
		
	}
	
	@Override
	public void execute(Tuple input) {
		
			counters.add(input.getString(0));
			
			ImmutableList<Entry<String>> topByCount = topByCount(counters, 20);
			List<ImmutableMap> payload = new ArrayList<ImmutableMap>();
			for(Entry<String> e : topByCount) {
				payload.add(ImmutableMap.of("word", e.getElement(), "count", e.getCount()));
			}
			
			callback.send(JacksonSerializer.toJson(payload));
			
			collector.ack(input);
			
	}

	@Override
	public void cleanup() {
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
	
	
}
