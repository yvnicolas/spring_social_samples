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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.quickstart.config.Uris;
import org.springframework.social.quickstart.user.SecurityContext;
import org.springframework.social.quickstart.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Simple little @Controller that invokes Facebook and renders the result. The injected
 * {@link Facebook} reference is configured with the required authorization credentials for the
 * current user behind the scenes.
 * 
 * @author Keith Donald
 */
@Controller
public class HomeController {

    @Autowired
    SPResolver SPBank;
    
    @Autowired
    private UsersConnectionRepository connectionRepository;


  


    @RequestMapping(value = Uris.MAIN)
    public String home(Model model) {
        List <SPInfo> SPStatusList = new ArrayList<SPInfo>();
        for (ServiceProviders sp : ServiceProviders.values()) {
            SPConnectionRetriever spAccess = SPBank.getSPConnection(sp);
            SPStatusList.add(new SPInfo(sp.toString(), spAccess.isconnected(), spAccess.getPermissions(),spAccess.getConnectUrl()));
        }
        
        model.addAttribute("nom", SecurityContext.getCurrentUser().getId());
           model.addAttribute("serviceProviders", SPStatusList);
     
        return Uris.WORK;
    }

    @RequestMapping(value = Uris.IDPROCESS, method = RequestMethod.POST)
    public RedirectView login(@RequestParam("id") String id) {

        SecurityContext.setCurrentUser(new User(id));
        return new RedirectView(Uris.URISPREFIX + Uris.MAIN);
    }
    
    @RequestMapping(value = Uris.DISCONNECT, method = RequestMethod.POST)
    public RedirectView disconnect(@RequestParam("sp") ServiceProviders sp) {

      connectionRepository.createConnectionRepository(SecurityContext.getCurrentUser().getId())
      .removeConnections(sp.toString().toLowerCase());

        return new RedirectView(Uris.URISPREFIX + Uris.MAIN);
    }

    

    /**
     * Rendering bean class used to exchange info with the JSP
     * @author Yves Nicolas
     *
     */
    public class SPInfo {
        private String name;
        private boolean connected;
        private String permissions;
        private String URL;
       

        public SPInfo(String name, boolean isConnected, String permissions, String URL) {
            super();
            this.name = name;
            this.connected = isConnected;
            this.permissions = permissions;
            this.URL = URL;
        }


        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public boolean isConnected() {
            return connected;
        }


        public void setConnected(boolean isConnected) {
            this.connected = isConnected;
        }


        public String getPermissions() {
            return permissions;
        }


        public void setPermissions(String permissions) {
            this.permissions = permissions;
        }


        public String getURL() {
            return URL;
        }


        public void setURL(String uRL) {
            URL = uRL;
        }
        

    }

}
