package com.gameofthree.model;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


public class Bot {
	
	private static String BOT = "BOT ";
	private static final AtomicInteger nameCounter = new AtomicInteger(1);
	String name;

	public Bot(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}		
	
	//GENERATE ID FOR THE BOT
	
    public static Bot generateName() {
        return new Bot(BOT+nameCounter.incrementAndGet());
    }
	
	
}
