package org.securityartwork.storm.social.analyzer.facebook;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.securityartwork.storm.social.analyzer.amqp.SocialAmqpInputProducer;
import org.securityartwork.storm.social.analyzer.common.AsyncFacebookSocialCollector;
import org.securityartwork.storm.social.analyzer.common.Status;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.StatusPost;

import com.google.common.collect.Lists;

public class AsyncFacebookStatusCollector implements AsyncFacebookSocialCollector {
	
	private SocialAmqpInputProducer socialAmqpInputProducer;
	
	private static final Integer STATUS_LIMIT = 10;
	
	private static final Integer THREAD_POOL_SIZE = 10;
	
	
	public SocialAmqpInputProducer getSocialAmqpInputProducer() {
		return socialAmqpInputProducer;
	}

	public void setSocialAmqpInputProducer(
			SocialAmqpInputProducer socialAmqpInputProducer) {
		this.socialAmqpInputProducer = socialAmqpInputProducer;
	}

	@Override
	public void collect(final Connection<Facebook> facebook) {
		
		final List<List<String>> friendIdsGroups = Lists.partition(facebook.getApi().friendOperations().getFriendIds(), THREAD_POOL_SIZE);
		
		ExecutorService executor = Executors.newFixedThreadPool(friendIdsGroups.size());
		
	        for (final List<String> friendIds : friendIdsGroups) {
	    		executor.execute(new Runnable() {
	    			@Override
				public void run() {
					for ( String friendId : friendIds ) {
						List<StatusPost> statuses = facebook.getApi().feedOperations().getStatuses( friendId, 0, STATUS_LIMIT );
						
						for ( StatusPost status : statuses ) {
							
							if ( status.getMessage() != null ) {
								
								socialAmqpInputProducer.emit(new Status(status.getMessage(), 
														status.getFrom().getName()));
								
							}
						}
						
					}
				}
	    		});
	    	}
	}
	
	
}
