package com.shop.fruit;

import java.util.ArrayList;
import java.util.List;

public class TestResult {
	

	public enum FRUITS{
		apple,peach,pear,cherry;
	}
	
	public static List<Stand> allStands = new ArrayList<Stand>(); 
		
	static {
		
		Fruit peach1 = new Fruit(FRUITS.peach.toString(), 10, 2, "Basket");
		Fruit cherry1 = new Fruit(FRUITS.cherry.toString() ,20, 2, "Basket");
		Fruit pear1 = new Fruit(FRUITS.pear.toString() ,30, 0, "Basket");
		Fruit apple1 = new Fruit(FRUITS.apple.toString() ,40, 2, "Basket");
		
		ArrayList<Fruit> standOnefruits = new ArrayList<Fruit>();
		standOnefruits.add(peach1);
		standOnefruits.add(cherry1);
		standOnefruits.add(pear1);
		standOnefruits.add(apple1);
		
		Stand standOne = new Stand("StandOne",standOnefruits);
		
		allStands.add(standOne);
		
		Fruit peach2 = new Fruit(FRUITS.peach.toString(), 12, 1, "Basket");
		Fruit cherry2 = new Fruit(FRUITS.cherry.toString() ,15, 1, "Basket");
		Fruit pear2 = new Fruit(FRUITS.pear.toString() ,70, 0, "Basket");
		Fruit apple2 = new Fruit(FRUITS.apple.toString() ,50, 1, "Basket");
	
		ArrayList<Fruit> standTwofruits = new ArrayList<Fruit>();
		standTwofruits.add(peach2);
		//standTwofruits.add(cherry2);
		standTwofruits.add(pear2);
		standTwofruits.add(apple2);
		
		Stand standTwo = new Stand("StandTwo",standTwofruits);
		
		allStands.add(standTwo);
		
		Fruit peach3 = new Fruit(FRUITS.peach.toString(), 10, 3, "Basket");
		Fruit cherry3 = new Fruit(FRUITS.cherry.toString() ,10, 3, "Basket");
		Fruit pear3 = new Fruit(FRUITS.pear.toString() ,5, 1, "Basket");
		Fruit apple3 = new Fruit(FRUITS.apple.toString() ,20, 3, "Basket");
	
		ArrayList<Fruit> standThreefruits = new ArrayList<Fruit>();
		standThreefruits.add(peach3);
		standThreefruits.add(cherry3);
		standThreefruits.add(pear3);
		standThreefruits.add(apple3);
		
		Stand standThree = new Stand("StandThree",standThreefruits);
		
		allStands.add(standThree);
		Inventory.buildStands(allStands);
	
	}
	
	public static void main(String[] args) {
		
		String[] fruitstoBuy = {FRUITS.peach.toString(),FRUITS.cherry.toString()};
		String standtoStop = Inventory.getLeastPricedStand(fruitstoBuy,null);
		System.out.println("@@@@@@Stand to Stop : " +standtoStop);
		
		
		String[] fruitstoBuy2 = {FRUITS.peach.toString(),FRUITS.cherry.toString(),FRUITS.pear.toString()};
		String standtoStop2 = Inventory.getLeastPricedStand(fruitstoBuy2,null);
		System.out.println("@@@@@@Stand to Stop : " +standtoStop2);
		
		//String standtoStop = Inventory.getLeastPricedStand(fruitstoBuy,FRUITS.pear.toString());
		
		//System.out.println("@@@@@@Stand to Stop Mandatory: " +standtoStop);
		
		//String standtoStop2 = Inventory.getLeastPricedStand(fruitstoBuy2,null);
		//System.out.println("@@@@@Stand to Stop : " +standtoStop2);
		
		Inventory.buyFruits(standtoStop, fruitstoBuy);
		Inventory.printCurrentInventory();
		
		System.out.println("After First Buy");
		System.out.println("=========================================");
		
		String standtoStop3 = Inventory.getLeastPricedStand(fruitstoBuy,null);
		System.out.println("@@@@@Stand to Stop : " +standtoStop3); 
		
		
	}
}
