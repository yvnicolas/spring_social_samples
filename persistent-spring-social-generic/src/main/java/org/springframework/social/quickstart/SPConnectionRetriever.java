package org.springframework.social.quickstart;

import java.util.List;

public interface SPConnectionRetriever {

    public List<Person> getConnections();
    
    public ServiceProviders getActiveSP();
    
    @SuppressWarnings("rawtypes")
    public java.lang.Class getSPType();
    
    public String getConnectUrl();
}
