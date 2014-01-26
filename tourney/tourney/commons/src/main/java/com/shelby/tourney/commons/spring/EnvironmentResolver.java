package com.shelby.tourney.commons.spring;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentResolver {

	private Logger log = LoggerFactory.getLogger(EnvironmentResolver.class);
	private static Properties environments = new Properties();
	private static String environment = "local";
	private static String machine = null;
	private static String artifactName = null;
	
	public EnvironmentResolver(String machineEnvironmentPropertyFile, String artifact)
	{
		
		if (machine!=null)
			return;
		
		artifactName = artifact;
		
		try{
			
			/*
			 * load the requested machine environment property file
			 */
			log.debug("loading machine environment properties file {}",machineEnvironmentPropertyFile);
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(machineEnvironmentPropertyFile);
			if (is == null){
				throw new FileNotFoundException(machineEnvironmentPropertyFile + " not found in the classpath");
			}
			
			environments.load(is);
			
			for(String machine : environments.stringPropertyNames()){
				log.debug("found mapping: " + machine + " -> " + environments.getProperty(machine));
			}
			log.debug("detecting machine name...");
			machine = InetAddress.getLocalHost().getHostName().toUpperCase();
			log.info("running on machine: " + machine);
			
			if (environments.containsKey(machine))
			{
				environment = environments.getProperty(machine);
			}else
			{
				log.warn("no machine->environment mappingn found fo rthis machine, defaulting to \"local\"");
			}
			
			log.info("resolved machine: " + machine + " -> " + environment);
			
		}catch (UnknownHostException e)
		{
			log.error("UNABLE TO EXECUTE: InetAddress.getLocalHost().getHostName();");
		
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			log.error("UNABLE TO FIND machine properties file", e);
		}
		
		log.info("artifact: " + getArtifactName());
		log.info("environment: " + getEnvironment());
		
	}
	
	public static String getEnvironment(){
		return environment;
	}
	
	public static String getArtifactName(){
		return artifactName;
	}
	
	public static Properties getEnvironments(){
		return environments;
	}
	
	public static String getMachineName(){
		
		return machine;
	}
}
