package org.springframework.social.quickstart.offline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;

@Component
public class MyFBFactory {

   
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    public MyFBFactory() {
        super();

    }
   
    
    public Facebook getFacebook(User user) {
        ConnectionRepository cr = usersConnectionRepository.createConnectionRepository(user.getId());
        return cr.getPrimaryConnection(Facebook.class).getApi();
    }

   
}
