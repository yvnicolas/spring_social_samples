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
	public static final String URISPREFIX = "/persistent-connections";
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
	
	// Uri called to select service provider
    public static final String SPCHOICE = APPCONNECTPREFIX +"/spchoice";

    // Confirmation of Service Provider choice
    public static final String SPCONFIRM = APPCONNECTPREFIX +"/spchoiceconfirm";

	
	// Processing Application Id post Input
	public static final String IDPROCESS=APPCONNECTPREFIX+"/inscription";
	
	
	// Called once application signin has been confirmed
	public static final String SIGNINCONFIRM = APPCONNECTPREFIX+"/signinconfirm";
	
	// Application pages proposing to connect to serviceProvider
	public static final String SIGNINFB = APPCONNECTPREFIX+"/signinfb";
	public static final String SIGNINLI = APPCONNECTPREFIX+"/signinli";
	
	//Thank you - good bye
	public static final String BYE = APPCONNECTPREFIX+"/bye";
	
	// Complete Signout page : also disconnects from Facebook
	public static final String SIGNOUT = "/signout";

	// Complete Signout page : also disconnects from Facebook
//	public static final String PARTIALSIGNOUT = SIGNOUT + "/app";
	
	// To Disconnect from a Service Provider
	public static final String DISCONNECT = "/disconnect";


	//Application Connection Dance Prefix
		public static final String SPRINGCONNECTPREFIX="/connect";

	// Called by Spring Social to manage Service providers authentification
	public static final String SPRINGFBSIGNIN = SPRINGCONNECTPREFIX+"/facebook";
	public static final String SPRINGLISIGNIN = SPRINGCONNECTPREFIX+"/linkedin";
	
	
}
