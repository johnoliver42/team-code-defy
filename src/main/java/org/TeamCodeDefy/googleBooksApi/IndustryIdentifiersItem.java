package org.TeamCodeDefy.googleBooksApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndustryIdentifiersItem{

	@JsonProperty("identifier")
	private String identifier;

	@JsonProperty("type")
	private String type;

	public void setIdentifier(String identifier){
		this.identifier = identifier;
	}

	public String getIdentifier(){
		return identifier;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"IndustryIdentifiersItem{" + 
			"identifier = '" + identifier + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}