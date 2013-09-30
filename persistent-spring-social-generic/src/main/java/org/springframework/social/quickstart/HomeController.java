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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.view.RedirectView;

/**
 * Simple little @Controller that invokes Facebook and renders the result. The
 * injected {@link Facebook} reference is configured with the required
 * authorization credentials for the current user behind the scenes.
 * 
 * @author Keith Donald
 */
@Controller
public class HomeController {
  
   @Autowired
   SPConnectionRetriever FBConnectionRetriever;
   
   @Autowired
   SPConnectionRetriever LIConnectionRetriever;
   
   
   
    @RequestMapping(value = Uris.MAIN)
    public String home(Model model) {
        List<Person> connections;
        try {
         connections = SecurityContext.getCurrentSpResolver().getConnections();
        model.addAttribute("connections", connections);
        model.addAttribute("serviceProvider", SecurityContext.getCurrentSpResolver().getActiveSP().toString());
        }
        catch (IllegalStateException e) {
            // SP not defined, no contacts to show"
            model.addAttribute("serviceProvider", "No Service Provider selected : no contact to show");
        }
        return Uris.WORK;
    }

    @RequestMapping(value = Uris.IDPROCESS, method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("id") String id) {

        SecurityContext.setCurrentUser(new User(id));
        ModelAndView mav = new ModelAndView(Uris.SIGNINCONFIRM);
        mav.addObject("nom", id);
        return mav;
    }
    
    
    // Changer pour retourner un redirectview vers la bonne connexion et les bons paramètres si jamais on n'est pas connecté au sp.
    @RequestMapping(value = Uris.SPCHOICE, method = RequestMethod.POST)
    public RedirectView Spchoice (@RequestParam("sp") String sp) {

        
        // Rajouter ici le check si SP authorized.
        // ptetre à traiter dans le FBConnectionRetriever null on oun
        //si facebook null : on n'est pas connecté.
        ServiceProviders spasenum = ServiceProviders.valueOf(sp);
        RedirectView toReturn;
        SPConnectionRetriever spResolver=null;
        switch (spasenum) {
        case FACEBOOK :
            spResolver = FBConnectionRetriever;   
            break;
        case LINKEDIN :
            spResolver = LIConnectionRetriever;     
            break;
        }
        SecurityContext.setCurrentSpResolver(spResolver);
        if (spResolver.isconnected())
            toReturn  = new RedirectView(Uris.MAIN, true);
        else
            toReturn = new RedirectView(spResolver.getConnectUrl(), true);
            
        return toReturn;
    }

//    @RequestMapping(value = Uris.SIGNINLI)
//    public RedirectView liSignin(HttpServletRequest request, HttpServletResponse response) {
//        request.setAttribute("scope", "r_fullprofile,r_network");
//         return new RedirectView(Uris.SPRINGLISIGNIN, true);
//       
//           }

}
