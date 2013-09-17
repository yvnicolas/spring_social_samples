package org.springframework.social.quickstart.config;

/**
 * Constant classes listing all the URIs
 * @author Yves Nicolas
 *
 */
public final class Uris {
	
	// View Resolvers contants
	public static final String VIEWS = "/WEB-INF/views/";
	public static final String SUFFIX = ".jsp";
	
	
	// Main Application Uri Prefix
	public static final String URISPREFIX = "/persistent-spring-social";
	// Main Starting page
	public static final String MAIN = "/";
	
	// Uris called once all connections have been established
	public static final String WORK = "/home";
	
	//Application Connection Dance Prefix
	public static final String APPCONNECTPREFIX="/appconnect";

	// Application signing-signup page
	public static final String SIGNIN = APPCONNECTPREFIX+"/signin";
	
	// Application Id Input form
	public static final String APPLICATIONIDINPUT = APPCONNECTPREFIX+"/subscriberinput";
	
	// Processing Application Id post Input
	public static final String IDPROCESS=APPCONNECTPREFIX+"/inscription";
	
	
	// Called once application signin has been confirmed
	public static final String SIGNINCONFIRM = APPCONNECTPREFIX+"/signinconfirm";
	
	// Application page proposing to connect to Facebook
	public static final String SIGNINFB = APPCONNECTPREFIX+"/signinfb";
	
	//Thank you - good bye
	public static final String BYE = APPCONNECTPREFIX+"/bye";
	
	// Complete Signout page : also disconnects from Facebook
	public static final String SIGNOUT = "/signout";

	// Complete Signout page : also disconnects from Facebook
	public static final String PARTIALSIGNOUT = SIGNOUT + "/app";


	//Application Connection Dance Prefix
		public static final String SPRINGCONNECTPREFIX="/connect";

	// Called by Spring Social to manage Facebook authentification
	public static final String SPRINGFBSIGNIN = SPRINGCONNECTPREFIX+"/facebook";
	
	
}
