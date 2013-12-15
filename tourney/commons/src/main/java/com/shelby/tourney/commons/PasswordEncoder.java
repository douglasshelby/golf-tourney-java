package com.shelby.tourney.commons;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 * @author Ashish Shukla
 *
 */
public class PasswordEncoder {
    /**
     * Logger for this class
     */
    private static final Logger log = Logger.getLogger(PasswordEncoder.class);

    private static PasswordEncoder instance;

    /**
     * Empty Constructor
     */
    private PasswordEncoder() {
    }

    /**
     * @return
     * @author Ashish Shukla
     */
    public static synchronized PasswordEncoder getInstance() {
	if (log.isDebugEnabled()) {
	    log.debug("getInstance() - start");
	}

	if (instance == null) {
	    PasswordEncoder returnPasswordEncoder = new PasswordEncoder();
	    log.info("New instance created");
	    if (log.isDebugEnabled()) {
		log.debug("getInstance() - end");
	    }
	    return returnPasswordEncoder;
	} else {
	    if (log.isDebugEnabled()) {
		log.debug("getInstance() - end");
	    }
	    return instance;
	}
    }

    /**
     *
     * @param password
     * @param saltKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @author Ashish Shukla
     */
    public synchronized String encode(String password)
	    throws NoSuchAlgorithmException, IOException {
	if (log.isDebugEnabled()) {
	    log.debug("encode(String, String) - start");
	}

	String encodedPassword = null;

	byte[] btPass = DigestUtils.sha256(password.getBytes("UTF-8"));
	encodedPassword = Base64.encodeBase64String(btPass);

	if (log.isDebugEnabled()) {
	    log.debug("encode(String, String) - end");
	}
	return encodedPassword;
    }


}
