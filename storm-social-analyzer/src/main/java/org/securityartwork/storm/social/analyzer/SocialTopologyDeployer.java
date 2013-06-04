package org.securityartwork.storm.social.analyzer;

import org.securityartwork.storm.social.analyzer.bolts.StatusSegmentatorBolt;
import org.securityartwork.storm.social.analyzer.bolts.StatusSplittingBolt;
import org.securityartwork.storm.social.analyzer.spouts.AmqpStatusEmitterSpout;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * Main class where all topology
 * components are assembled and
 * where local cluster is bootstrapped.
 * 
 * @author Nedo
 *
 */

public class SocialTopologyDeployer {
	
	private TopologyBuilder topologyBuilder = new TopologyBuilder();
	private LocalCluster cluster = new LocalCluster();

	public void deploy() {
		
		topologyBuilder.setSpout("status-emitter", new AmqpStatusEmitterSpout());
		topologyBuilder.setBolt("status-splitter", new StatusSplittingBolt(), 2)
						    .shuffleGrouping("status-emitter");
		topologyBuilder.setBolt("status-segmentator", new StatusSegmentatorBolt(), 5)
							.fieldsGrouping("status-splitter", new Fields("word"));
		
		Config conf = new Config();
		conf.setDebug(true);
	
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		
		cluster.submitTopology( "social-topology", 
				                conf, 
				                topologyBuilder.createTopology());
		
	}
	
	public void shutdown() {
		cluster.shutdown();
	}
}
