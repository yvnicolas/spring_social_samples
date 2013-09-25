package org.springframework.social.quickstart.offline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Component;

@Component
public class MySPResolverFactory {

   
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;
    

    public MySPResolverFactory() {
        super();

    }
   
    
    public SPConnectionRetriever getConnectionRetriever(User user, ServiceProviders sp) {
        ConnectionRepository cr = usersConnectionRepository.createConnectionRepository(user.getId());
        
        switch (sp) {
        case FACEBOOK :
            Facebook fb =  cr.getPrimaryConnection(Facebook.class).getApi();
            return new FBConnectionRetrieverImpl(fb);
        case LINKEDIN :
            LinkedIn li = cr.getPrimaryConnection(LinkedIn.class).getApi();
            return new LIConnectionRetrieverImpl(li);
        }
        return null;
    }

   
}
