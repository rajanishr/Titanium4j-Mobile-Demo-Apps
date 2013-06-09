package com.chazaqdev.ti4jexample.client.data;

public class X {
	public static X[] xItems = {new X("Piet", 20), new X("Jan", 30)};

	public static int lastId = 0;
	
	public X() {
	}
	
	public X(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	private String name = "";
	private int amount = 0;
	private int id = (lastId = lastId+1);
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getId() {
		return id;
	}
}
