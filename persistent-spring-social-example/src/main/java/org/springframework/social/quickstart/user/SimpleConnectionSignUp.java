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

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 * Simple little {@link ConnectionSignUp} command. Is bundled to the
 * UsersConnectionRepository as the mean to get a new user ID if no one is found
 * at this stage Supposes here that the new id is given by the application
 * security context as the user should have signed in into the application
 * before.
 * 
 * @author Yves Nicolas
 */

// set in the creation of the userconnectionrepository, is called to get a new
// userID in the connection repository
public final class SimpleConnectionSignUp implements ConnectionSignUp {

	

	public String execute(Connection<?> connection) {
		return SecurityContext.getCurrentUser().getId();
	}

}
