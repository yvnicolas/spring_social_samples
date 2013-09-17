/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.quickstart.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Before a request is handled: 1. sets the current User in the
 * {@link SecurityContext} from a cookie, if present and the user is still
 * connected to Facebook. 2. requires that the user sign-in if he or she hasn't
 * already.
 * 
 * @author Keith Donald
 */
public final class UserInterceptor extends HandlerInterceptorAdapter {

	private final UsersConnectionRepository connectionRepository;

	private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();

	public UserInterceptor(UsersConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		rememberUser(request, response);
		
		
		// Handle the requests that should go thru
		if (request.getServletPath().contains("/signinconfirm")){
			return true;
		}
		
		if (request.getServletPath().contains("/subscriberinput"))
			return true; 
	
		if (request.getServletPath().contains("/inscription"))
			return true; 

		if ((request.getServletPath().equals("/signin") ) ||(request.getServletPath().equals("/connect/facebook") ) || (request.getServletPath().equals("/signinfb")) )
			return true;
		
		// checking whether connection do the application has been made
		if (!SecurityContext.userSignedIn()) {
			new RedirectView("/signin", true).render(null, request, response);
						return false;
		}
		// checking whether connection to facebook has been made :
		if (!FacebookAuthorized(SecurityContext.getCurrentUser().getId())) {
			new RedirectView("/signinfb", true).render(null, request, response);
			return false;
		}
	    // Signing out	
		if (handleSignOut(request, response)) {
			new RedirectView("/bye", true).render(null, request, response);
			return false;
		}

		// At this stage, we can proceed to the regular controller as signing is effective
		return true;
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// SecurityContext.remove();
	}

	// internal helpers

	// Gets a potential user ID from cookies existing on the system.
	private void rememberUser(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = userCookieGenerator.readCookieValue(request);
		if (userId == null) {
			
			// No Cookie : no potential user found
			return;
		}
		else if (!userIsValid(userId)) {
			
			// Cookie referencing an invalid user, should be removed
			// Then proceed as if no user on system
			userCookieGenerator.removeCookie(response);
			return;
						}
		
		else  {
			// User Id is a valid one, we check it as the current user
			SecurityContext.setCurrentUser(new User(userId));
			}
		
		
	}

	// If signout has been asked for sign out
	private boolean handleSignOut(HttpServletRequest request,
			HttpServletResponse response) {
		if (SecurityContext.userSignedIn()
				&& request.getServletPath().startsWith("/signout")) {
			connectionRepository.createConnectionRepository(
					SecurityContext.getCurrentUser().getId())
					.removeConnections("facebook");
//			userCookieGenerator.removeCookie(response);
			SecurityContext.remove();
			return true;
		} else
			return false;
	}


	// Checks validity of userId
	// For the moment, any non void String is considered valid
	private boolean userIsValid(String userId) {
		// pour premiers essais : on est toujours supposé etre un nouveau.
		return (userId != "");
	}

	private boolean FacebookAuthorized(String userId) {
		// doesn't bother checking a local user database: simply checks if the
		// userId is connected to Facebook
		return connectionRepository.createConnectionRepository(userId)
				.findPrimaryConnection(Facebook.class) != null;
	}
	


}
