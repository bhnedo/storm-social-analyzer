package org.securityartwork.storm.social.analyzer.controller;

import org.securityartwork.storm.social.analyzer.common.AsyncFacebookSocialCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnalyzerController {
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@Autowired
	private AsyncFacebookSocialCollector asyncFacebookSocialCollector;
	
	@RequestMapping(value="analyze", method=RequestMethod.POST)
	public void analyze() {
		
		Connection<Facebook> facebook = connectionRepository.findPrimaryConnection(Facebook.class);
		
		asyncFacebookSocialCollector.collect(facebook);
    }
	
	@RequestMapping(value="signin", method=RequestMethod.GET)
	public void signin() {
		
	}
	
	
}
