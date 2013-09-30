package org.springframework.social.quickstart;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.quickstart.config.Uris;
import org.springframework.stereotype.Component;

@Component("LIConnectionRetriever")
public class LIConnectionRetrieverImpl implements SPConnectionRetriever {

    private LinkedIn linkedIn;

    static final String DEFAULTPERMISSIONS = "r_fullprofile,r_network";

    @Inject
    public LIConnectionRetrieverImpl(LinkedIn linkedIn) {
        this.linkedIn = linkedIn;
    }

    @Override
    public List<Person> getConnections() {
        List<LinkedInProfile> connections = linkedIn.connectionOperations().getConnections();
        List<Person> toReturn = new ArrayList<Person>();
        for (LinkedInProfile connection : connections) {
            toReturn.add(new Person(connection.getFirstName(), connection.getLastName()));
        }
        return toReturn;
    }

    @Override
    public ServiceProviders getActiveSP() {

        return ServiceProviders.LINKEDIN;
    }

    @Override
    public String getConnectUrl() {

        return Uris.SPRINGLISIGNIN;
    }

    @Override
    public boolean isconnected() {
        boolean toReturn = false;
        try {
            toReturn = linkedIn.isAuthorized();
        } catch (Exception e) {
        }
        return toReturn;
    }

    @Override
    public String getPermissions() {
        // TODO : ameliorer en retournant les permissions autorisees si connectees
        return DEFAULTPERMISSIONS;

    }

}
