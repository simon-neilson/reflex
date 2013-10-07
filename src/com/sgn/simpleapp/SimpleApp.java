package com.sgn.simpleapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleApp {
	
	static final Logger logger = LoggerFactory.getLogger(SimpleApp.class);

	public static final void main(String[] args){
		
		SimpleApp app = new SimpleApp();
		
		while(true){
			
			MyUser user = app.work();
			logger.info(user.getName());
			
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(),e);
			}
		}
		
	}
	
	public SimpleApp(){
		super();
	}
	
	public MyUser work(){
		MyUser user = new MyUser();
		return user;
	}
}
