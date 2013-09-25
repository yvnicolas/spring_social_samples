package org.springframework.social.quickstart.offline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;


/**OfflineFacebook information getting, non managed by Spring Container
 * @author Yves Nicolas
 *
 */
public class FacebookOfflineNS {

    private final String facebookclientId = "164437043758895";
    private final String facebookclientSecret = "34c5602562939d36ad00d55cf542451c";

     private DriverManagerDataSource dataSource;

    private Facebook fb;

 
    public FacebookOfflineNS(User user) {
        super();
        dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/spring_social");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("spring");
        dataSource.setPassword("spring");

        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory(facebookclientId, facebookclientSecret));

        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, registry,
                Encryptors.noOpText());

        ConnectionRepository cr = repository.createConnectionRepository(user.getId());
        fb = cr.getPrimaryConnection(Facebook.class).getApi();
    }

    public List<String> unemethode() {
        List<String> toReturn = new ArrayList<String>();
        List<FacebookProfile> contacts = fb.friendOperations().getFriendProfiles();
        for (FacebookProfile ami : contacts) {
            toReturn.add(ami.getFirstName() + " " + ami.getLastName());
        }
        return toReturn;
    }

 

}
