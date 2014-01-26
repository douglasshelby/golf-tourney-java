package com.shelby.tourney.commons.spring;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class PropertyPlaceholderConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer{

	private Logger log = LoggerFactory.getLogger(PropertyPlaceholderConfigurer.class);
	private Set<String> properties = new TreeSet<String>();
	private static final String ACTIVE_PROPERTIES_FILE="/logs/active.properties";
	
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {

		super.processProperties(beanFactoryToProcess, props);
		logProperties();
		if ("local" != EnvironmentResolver.getEnvironment()){
			saveProperties();
		}
	}
	
	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {
		String value =  super.resolvePlaceholder(placeholder, props);
		if (value == null)
			return value;
		
		properties.add(placeholder + "=" + value);
		
		return value;
	}
	private void logProperties(){
		
		log.info("===============================================================================================================");
		for(String p : properties){
			
			log.info(p);
		}
		log.info("===============================================================================================================");	
		
	}
	
	private void saveProperties(){
		StringBuilder sb = new StringBuilder();
		for(String p : properties){
			
			sb.append(p);
			sb.append('\n');
		}
		FileWriter file;
		try{
			log.info("saving all active properties to: " + ACTIVE_PROPERTIES_FILE);
			file = new FileWriter(new File(ACTIVE_PROPERTIES_FILE));		
			file.write(sb.toString());
			file.flush();
			file.close();
		}
		catch(IOException e){
			log.error("unable to write active properties to: " + ACTIVE_PROPERTIES_FILE,e);
		}
	}
	
}
