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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.social.quickstart.offline.FacebookOffline;
import org.springframework.social.quickstart.offline.User;

/**
 * Config Class defining user beans used in MyFBFactoryTest
 * 
 * @author Yves Nicolas
 */
@Configuration
public class TestConfigMyFBFactory {

    @Bean
    @Scope("prototype")
    public User yves() {
        return new User("yves@dynamease");
    }

    @Bean
    @Scope("prototype")
    public User pauline() {
        return new User("pauline");
    }

    @Bean
    @Scope("prototype")
    public User kouser() {
        return new User("kouser");
    }
}
