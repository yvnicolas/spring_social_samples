package org.springframework.social.quickstart;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.quickstart.config.Uris;
import org.springframework.stereotype.Component;

@Component("FBConnectionRetriever")
public class FBConnectionRetrieverImpl implements SPConnectionRetriever {

    private Facebook facebook;
    static final String DEFAULTPERMISSIONS = "user_about_me,user_groups,read_friendlists,friends_about_me,friends_hometown,friends_groups";

    @Inject
    public FBConnectionRetrieverImpl(Facebook facebook) {
        this.facebook = facebook;
    }

    @Override
    public List<Person> getConnections() {
        List<Reference> friends = facebook.friendOperations().getFriends();
        List<Person> toReturn = new ArrayList<Person>();
        for (Reference ref : friends) {
            String name[] = new String[2];
            name = ref.getName().split(" ", 2);
            toReturn.add(new Person(name[0], name[1]));
        }

        return toReturn;
    }

    @Override
    public ServiceProviders getActiveSP() {

        return ServiceProviders.FACEBOOK;
    }

 
    @Override
    public String getConnectUrl() {
        return Uris.SPRINGFBSIGNIN;
    }

    @Override
    public boolean isconnected() {

        boolean toReturn = false;
        try {
            toReturn = facebook.isAuthorized();
        } catch (Exception e) {
        }
        return toReturn;
    }

    @Override
    public String getPermissions() {

        if (isconnected())
            return facebook.userOperations().getUserPermissions().toString();
        else
            return DEFAULTPERMISSIONS;

    }

}
