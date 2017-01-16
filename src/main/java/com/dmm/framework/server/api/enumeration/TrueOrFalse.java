package com.dmm.framework.server.api.enumeration;

public enum TrueOrFalse {
	TRUE("true"),FALSE("false");
	private final String name;
	
	private TrueOrFalse(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}
