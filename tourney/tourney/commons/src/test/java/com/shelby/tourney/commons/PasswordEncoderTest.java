package com.shelby.tourney.commons;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class PasswordEncoderTest 
    extends TestCase
{
	private static final Logger log = Logger.getLogger(PasswordEncoderTest.class);
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PasswordEncoderTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PasswordEncoderTest.class );
    }
    
    public void testPasswordEncrypt() throws NoSuchAlgorithmException, IOException
    {
    	String base64Password = PasswordEncoder.getInstance().encode("12345");
    	log.debug(base64Password);
        assertNotNull( base64Password );
    }
    
    public void testRepeatable() throws NoSuchAlgorithmException, IOException
    {
    	String base64Password = PasswordEncoder.getInstance().encode("12345");
    	String base64Password2 = PasswordEncoder.getInstance().encode("12345");
    	
        assertEquals(base64Password , base64Password2);
    }
    public void testNegative() throws NoSuchAlgorithmException, IOException
    {
    	String base64Password = PasswordEncoder.getInstance().encode("12345");
    	String base64Password2 = PasswordEncoder.getInstance().encode("12346");
    	
        assertNotSame(base64Password , base64Password2);
    }
}
