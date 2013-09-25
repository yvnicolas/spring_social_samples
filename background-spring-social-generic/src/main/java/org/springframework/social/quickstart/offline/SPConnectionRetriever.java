package org.springframework.social.quickstart.offline;

import java.util.List;

public interface SPConnectionRetriever {

    public List<Person> getConnections();
    
    public ServiceProviders getActiveSP();
    
    @SuppressWarnings("rawtypes")
    public java.lang.Class getSPType();
    
}
