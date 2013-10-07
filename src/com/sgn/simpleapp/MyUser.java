package com.sgn.simpleapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyUser {
	
	static final Logger logger = LoggerFactory.getLogger(MyUser.class);
	
	public MyUser(){
		super();
	}

    public String getName() {
        return "foo";
    }

}
