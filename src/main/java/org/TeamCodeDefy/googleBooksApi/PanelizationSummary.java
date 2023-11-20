package org.TeamCodeDefy.googleBooksApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PanelizationSummary{

	@JsonProperty("containsImageBubbles")
	private boolean containsImageBubbles;

	@JsonProperty("containsEpubBubbles")
	private boolean containsEpubBubbles;

	public void setContainsImageBubbles(boolean containsImageBubbles){
		this.containsImageBubbles = containsImageBubbles;
	}

	public boolean isContainsImageBubbles(){
		return containsImageBubbles;
	}

	public void setContainsEpubBubbles(boolean containsEpubBubbles){
		this.containsEpubBubbles = containsEpubBubbles;
	}

	public boolean isContainsEpubBubbles(){
		return containsEpubBubbles;
	}

	@Override
 	public String toString(){
		return 
			"PanelizationSummary{" + 
			"containsImageBubbles = '" + containsImageBubbles + '\'' + 
			",containsEpubBubbles = '" + containsEpubBubbles + '\'' + 
			"}";
		}
}