package com.dgit.model;

public class Name {
	private String value;
	
	
	public Name(String value) {
		super();
		this.value = value;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public int lenght(){
		return value.length();
	}

}
