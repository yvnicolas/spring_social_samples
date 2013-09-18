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
package org.springframework.social.quickstart.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

/**
 * Spring Social Configuration.
 * 
 * @author Keith Donald
 */
    @Configuration
    public class SocialConfig {
    
    	@Inject
    	private Environment environment;
    
    	@Inject
    	private DataSource dataSource;
    
    	/**
    	 * When a new provider is added to the app, register its
    	 * {@link ConnectionFactory} here.
    	 * 
    	 * @see FacebookConnectionFactory
    	 */
    	@Bean
    	public ConnectionFactoryLocator connectionFactoryLocator() {
    		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
    		registry.addConnectionFactory(new FacebookConnectionFactory(environment
    				.getProperty("facebook.clientId"), environment
    				.getProperty("facebook.clientSecret")));
    		return registry;
    	}
    
    	/**
    	 * Singleton data access object providing access to connections across all
    	 * users. We do not set any ConnectionSignup here compared to initial Keith
    	 * Donald project as main signup is managed by the application and there
    	 * should not be any call to the usersConnectionRepository when a user is not signed in in the application
    	 * (ie the user id is always known)
    	 */
    	@Bean
    	public UsersConnectionRepository usersConnectionRepository() {
    		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(
    				dataSource, connectionFactoryLocator(), Encryptors.noOpText());
    		return repository;
    	}
    
    }
