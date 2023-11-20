package org.TeamCodeDefy.googleBooksApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Epub{

	@JsonProperty("isAvailable")
	private boolean isAvailable;

	public void setIsAvailable(boolean isAvailable){
		this.isAvailable = isAvailable;
	}

	public boolean isIsAvailable(){
		return isAvailable;
	}

	@Override
 	public String toString(){
		return 
			"Epub{" + 
			"isAvailable = '" + isAvailable + '\'' + 
			"}";
		}
}