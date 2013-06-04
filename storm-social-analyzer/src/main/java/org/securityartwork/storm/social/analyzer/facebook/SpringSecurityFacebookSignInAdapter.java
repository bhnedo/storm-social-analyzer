package org.securityartwork.storm.social.analyzer.facebook;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

public class SpringSecurityFacebookSignInAdapter implements SignInAdapter {

	@Override
	public String signIn(String arg0, Connection<?> connection, NativeWebRequest request) {
	     SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("", null, null));
	     return null;
	}

}
