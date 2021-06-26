package br.com.systemsgs.Model;

import java.io.Serializable;

public class ModelDescription implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String method;
	private String currency;
	private String intent;
	private String description;
	private double price;
	
	public ModelDescription(String method, String currency, String intent, String description, double price) {
		this.method = method;
		this.currency = currency;
		this.intent = intent;
		this.description = description;
		this.price = price;
	}
	
	public ModelDescription() {
		
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
