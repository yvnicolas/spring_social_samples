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
package org.springframework.social.quickstart;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.quickstart.config.Uris;
import org.springframework.social.quickstart.user.SecurityContext;
import org.springframework.social.quickstart.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Simple little @Controller that invokes Facebook and renders the result. The injected
 * {@link Facebook} reference is configured with the required authorization credentials for the
 * current user behind the scenes.
 * 
 * @author Keith Donald
 */
@Controller
public class HomeController {

 

//  private final Facebook facebook;
    private final LinkedIn linkedIn;


//  @Inject
//  public HomeController(Facebook facebook) {
//      this.facebook = facebook;
//  }
    
       @Inject
        public HomeController(LinkedIn linkedIn) {
            this.linkedIn = linkedIn;
        }


//  @RequestMapping(value = Uris.MAIN, method = RequestMethod.GET)
//  public String home(Model model) {
//      List<Reference> friends = facebook.friendOperations().getFriends();
//      model.addAttribute("friends", friends);
//      return Uris.WORK;
//  }
    
       @RequestMapping(value = Uris.MAIN, method = RequestMethod.GET)
        public String home(Model model) {
            List<LinkedInProfile> connections = linkedIn.connectionOperations().getConnections();
            model.addAttribute("connections", connections);
            return Uris.WORK;
        }
    @RequestMapping(value=Uris.IDPROCESS, method = RequestMethod.POST) 
        public ModelAndView login (@RequestParam("id") String id){
        
        SecurityContext.setCurrentUser(new User(id));
        ModelAndView mav = new ModelAndView(Uris.SIGNINCONFIRM);
        mav.addObject("nom", id);
        return mav;
    }

    
    

}
