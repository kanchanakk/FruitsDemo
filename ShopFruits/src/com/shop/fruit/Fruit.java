package com.shop.fruit;

public class Fruit {

	private String fruitName;
	private double price;
	private int availableQty;
	private String sellingForm;
	
	public Fruit(String fruitName,
	double price,
	int availableQty , String sellingForm) {
		this.fruitName = fruitName;
		this.price = price;
		this.availableQty = availableQty;
		this.sellingForm = sellingForm;
	}

	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public String getSellingForm() {
		return sellingForm;
	}

	public void setSellingForm(String sellingForm) {
		this.sellingForm = sellingForm;
	}
	
	@Override
	public String toString() {
		 return fruitName+" is "+price+" SEK per "+sellingForm+" & Availability : "+availableQty;
	}
	
	

}
