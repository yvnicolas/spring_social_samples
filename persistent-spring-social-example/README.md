Persistent Spring Social Quickstart
===================================
This sample is an adaptation of Spring Social Quickstart from Keith Donald found on the Spring Social web site  (see https://github.com/SpringSource/spring-social/wiki/Quick-Start) to enable facebook coonections to be stored in a mysql Database.

Originally the purpose was to be able to keep connections in a persistent manner.

The main changes made have been to change the signin method from the providerSigninController initially used by Keith to a ConnectController based method.
This enables to play with different signing flows between the application and the provider.

You need to have a mysql database running and set up as follows :
login to your mysql server as root.
create the database :

```sql
CREATE DATABASE spring_social;
```

create a spring_social user with all right on this database:

```sql
CREATE USER 'spring'@'localhost' IDENTIFIED by 'spring';
GRANT ALL on spring_social.* to 'spring'@'localhost';
```

Exit and relog on mysql as spring user.
Create the tables needed : 

```sql
USE spring_social;
create table UserConnection (userId varchar(255) not null,
    providerId varchar(255) not null,
    providerUserId varchar(255),
    rank int not null,
    displayName varchar(255),
    profileUrl varchar(512),
    imageUrl varchar(512),
    accessToken varchar(255) not null,	
    secret varchar(255),
    refreshToken varchar(255),
    expireTime bigint,
    primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
```

To run, simply import the project into your IDE and deploy to a Servlet 2.5 or > container such as Tomcat 6 or 7.
You can also deploy from the command line with:
$ mvn tomcat:run

Access the project at http://localhost:8080/persistent-spring-social (last part of url is by default the artifact ID of the pom.xml file)

Discuss at forum.springsource.org and collaborate with the development team at jira.springframework.org/browse/SOCIAL.
