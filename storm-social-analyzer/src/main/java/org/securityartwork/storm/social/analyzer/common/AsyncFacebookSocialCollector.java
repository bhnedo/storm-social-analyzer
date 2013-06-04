package org.securityartwork.storm.social.analyzer.common;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;

public interface AsyncFacebookSocialCollector {
	
	public void collect(Connection<Facebook> facebook);
}
