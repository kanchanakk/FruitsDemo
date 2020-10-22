package com.shop.fruit;

import java.util.List;

public class Stand {
	
	public String standName;
	
	public List<Fruit> fruits;
	
	
	public Stand(String standName , List<Fruit> fruits) {
		this.standName= standName;
		this.fruits= fruits;
	}

	public String getStandName() {
		return standName;
	}

	public void setStandName(String standName) {
		this.standName = standName;
	}

	public List<Fruit> getFruits() {
		return fruits;
	}

	public void setFruits(List<Fruit> fruits) {
		this.fruits = fruits;
	}

	
	public void printInventory() {
		System.out.println(standName);
		System.out.println("---------");
		
		if(fruits!=null && fruits.size()>0) {
			
			for(Fruit fruit: fruits) {
				System.out.println(fruit.toString());
			}
			
		}else {
			System.out.println("This is no longer a Stand");
		}
		System.out.println("======================================================");
	}
}
