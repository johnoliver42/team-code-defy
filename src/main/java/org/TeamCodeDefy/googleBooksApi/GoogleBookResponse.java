package org.TeamCodeDefy.googleBooksApi;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleBookResponse {

	@JsonProperty("totalItems")
	private int totalItems;

	@JsonProperty("kind")
	private String kind;

	@JsonProperty("items")
	private List<ItemsItem> items;

	public void setTotalItems(int totalItems){
		this.totalItems = totalItems;
	}

	public int getTotalItems(){
		return totalItems;
	}

	public void setKind(String kind){
		this.kind = kind;
	}

	public String getKind(){
		return kind;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"GoogleBookResponse{" +
			"totalItems = '" + totalItems + '\'' + 
			",kind = '" + kind + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}