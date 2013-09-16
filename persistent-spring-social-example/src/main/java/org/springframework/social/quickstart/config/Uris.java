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

	// Application signing-signup page
	public static final String SIGNIN = "/signin";
	
	// Application Id Input form
	public static final String APPLICATIONIDINPUT = "/subscriberinput";
	
	// Processing Application Id post Input
	public static final String IDPROCESS="/inscription";
	
	// Signout page : also disconnects from Facebook
	public static final String SIGNOUT = "/signout";
	
	// Called once application signin has been confirmed
	public static final String SIGNINCONFIRM = "/signinconfirm";
	
	// Application page proposing to connect to Facebook
	public static final String SIGNINFB = "/signinfb";
	
	// Called by Spring Social to manage Facebook authentification
	public static final String SPRINGFBSIGNIN = "/signin/facebook";
	
	//Thank you - good bye
	public static final String BYE = "/bye";
	
}
