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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.quickstart.SPConnectionRetriever;
import org.springframework.stereotype.Component;

/**
 * SecurityContext that stores the currently signed-in connection in a thread local. As well as the
 * active service provider resolver
 * 
 * @author Keith Donald
 */

public final class SecurityContext {

    private static final ThreadLocal<User> currentUser = new ThreadLocal<User>();
    private static final ThreadLocal<SPConnectionRetriever> currentSpResolver = new ThreadLocal<SPConnectionRetriever>();
    
    @Autowired
    private final UsersConnectionRepository connectionRepository;

    public static User getCurrentUser() {
        User user = currentUser.get();
        if (user == null) {
            throw new IllegalStateException("No user is currently signed in");
        }
        return user;
    }

    public SecurityContext(UsersConnectionRepository connectionRepository) {
        super();
        this.connectionRepository = connectionRepository;
    }

    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public static boolean userSignedIn() {
        return currentUser.get() != null;
    }

    public static void remove() {
        currentUser.remove();
        currentSpResolver.remove();
    }

    public static void removesp() {
        currentSpResolver.remove();
    }

    public static SPConnectionRetriever getCurrentSpResolver() {
        SPConnectionRetriever spResolver = currentSpResolver.get();
        if (spResolver == null) {
            throw new IllegalStateException("No Service Provider Resolver currently defined");
        }
        return spResolver;
    }
    

   public boolean SPAuthorized() {
      if (!userSignedIn())
          return false;
      SPConnectionRetriever spResolver = currentSpResolver.get();
      if (spResolver == null)
          return false;
      return  spResolver.isconnected();
  }

    public static void setCurrentSpResolver(SPConnectionRetriever spResolver) {
        currentSpResolver.set(spResolver);
    }

}
