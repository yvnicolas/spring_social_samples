package org.springframework.social.quickstart;

import java.util.List;

/**
 * Interface which unifies the information expected from a spring social service provider connection
 * @author Yves Nicolas
 *
 */
public interface SPConnectionRetriever {

    public List<Person> getConnections();
    
    public ServiceProviders getActiveSP();
   
 
    public String getConnectUrl();
    
    public boolean isconnected();
    
    public String getPermissions();
}
