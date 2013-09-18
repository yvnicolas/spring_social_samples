package org.springframework.social.quickstart.offline;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Component;

public class FacebookOffline {

    private Facebook fb;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    public FacebookOffline(User user) {
        super();

        ConnectionRepository cr = usersConnectionRepository.createConnectionRepository(user.getId());
        fb = cr.getPrimaryConnection(Facebook.class).getApi();
    }

    public List<String> contacts() {
        List<String> toReturn = new ArrayList<String>();
        List<FacebookProfile> contacts = fb.friendOperations().getFriendProfiles();
        for (FacebookProfile ami : contacts) {
            toReturn.add(ami.getFirstName() + " " + ami.getLastName());
        }
        return toReturn;
    }

}
