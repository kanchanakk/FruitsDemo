package com.shop.fruit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

	public static HashMap<String, Stand> allStands = new HashMap<String, Stand>();

	public static void buildStands(List<Stand> stands) {

		for (Stand stand : stands) {
			allStands.put(stand.getStandName(), stand);
		}
		printCurrentInventory();
	}

	public static String getLeastPricedStand(String[] fruitstoBuy, String mandatoryFruit) {

		displayFruitsToBuy(fruitstoBuy);
		
		if (mandatoryFruit != null && mandatoryFruit.length() > 0) {
			System.out.println("Mandatory Fruit to Buy : " + mandatoryFruit);
		}

		ArrayList<Stand> allStandsList = null;

		if (fruitstoBuy != null && fruitstoBuy.length > 0) {
			if (allStands != null && allStands.size() > 0) {
				allStandsList = allStands.values().stream().collect(Collectors.toCollection(ArrayList::new));
			} else {
				return "No Fruit Stand(s) configured";
			}
		} else {
			return "Fruit(s) to buy is not Specified";
		}
		ArrayList<Stand> consideredStandsList = null;
		if (mandatoryFruit != null) {
			consideredStandsList = new ArrayList<Stand>();
			for (Stand stand : allStandsList) {
				boolean mandatoryFruitexists = false;
				ArrayList<Fruit> fruits = (ArrayList<Fruit>) stand.getFruits();
				for (Fruit fruit : fruits) {
					if (mandatoryFruit.equals(fruit.getFruitName()) && fruit.getAvailableQty() > 0) {
						mandatoryFruitexists = true;
					}
				}
				if (mandatoryFruitexists) {
					consideredStandsList.add(stand);
				}
			}
			if (consideredStandsList.size() < 1) {
				return mandatoryFruit + " is not available in any of the stand";
			} else {
				allStandsList = consideredStandsList;
			}
		}
		return getLeastPricedStand(fruitstoBuy, mandatoryFruit, allStandsList);
	}

	public static String getLeastPricedStand(String[] fruitstoBuy, String mandatoryFruit,
			ArrayList<Stand> allStandsList) {
		String leastPricedStand = "";
		double leastPrice = 0;
		if (allStandsList != null && allStandsList.size() > 0) {

			boolean availbility = false; // for Fruit count availability Check
			boolean isExists = false; // for FRUIT Object existence Check
			double mandatoryFruitPrice = 0;
			for (Stand stand : allStandsList) {

				availbility = true;
				double sumofPrices = 0;
				ArrayList<Fruit> fruits = (ArrayList<Fruit>) stand.getFruits();

				if (mandatoryFruit != null) {
					mandatoryFruitPrice = 0;
					isExists = false;
					for (Fruit fruit : fruits) {
						if (mandatoryFruit.equals(fruit.getFruitName())) {
							isExists = true;
							mandatoryFruitPrice = fruit.getPrice();
						}
					}
				}

				String leastPricedStandwithMandatoryFruit = "";
				for (String fruitName : fruitstoBuy) {
					isExists = false;

					for (Fruit fruit : fruits) {
						if (mandatoryFruit != null) {
							double mandatorysumofPrices = 0;
							isExists = true;
							if (fruitName.equals(fruit.getFruitName())) {

								if (fruit.getAvailableQty() < 1) {
									System.out.println(
											fruit.getFruitName() + " is Not avialble at " + stand.getStandName());
									availbility = false;
									break;
								} else {
									availbility = true;
									mandatorysumofPrices = fruit.getPrice() + mandatoryFruitPrice;

									if (leastPricedStandwithMandatoryFruit.equals("")) {
										leastPricedStandwithMandatoryFruit = "Dummy";
										sumofPrices = fruit.getPrice() + mandatoryFruitPrice;
									} else {
										if (sumofPrices > mandatorysumofPrices) {
											sumofPrices = mandatorysumofPrices;
										}
									}
								}
							}

						} else {
							if (fruitName.equals(fruit.getFruitName())) {
								isExists = true;
								if (fruit.getAvailableQty() < 1) {
									System.out.println(
											fruit.getFruitName() + " is Not avialble at " + stand.getStandName());
									availbility = false;
									break;
								} else {
									availbility = true;
									sumofPrices += fruit.getPrice();
								}
							}
						}
					}
				}
				if (availbility && isExists) {
					if (leastPricedStand.equals("")) {
						leastPricedStand = stand.getStandName();
						leastPrice = sumofPrices;
					} else {
						if (leastPrice > sumofPrices) {
							leastPricedStand = stand.getStandName();
							leastPrice = sumofPrices;
						}
					}
				}
			}
		}
		System.out.println("The Least Price : " + leastPrice);
		return leastPricedStand;
	}

	public static String buyFruits(String standName, String[] fruitstoBuy) {

		ArrayList<Fruit> fruitsafterSold = new ArrayList<Fruit>();

		if (fruitstoBuy != null && fruitstoBuy.length > 0) {

			if (allStands != null && allStands.size() > 0 && allStands.containsKey(standName)) {

				Stand stand = (Stand) allStands.get(standName);

				ArrayList<Fruit> fruits = (ArrayList<Fruit>) stand.getFruits();

				for (Fruit fruit : fruits) {
					int soldQty = 0;
					for (String fruitName : fruitstoBuy) {
						if (fruitName.equals(fruit.getFruitName())) {
							if (fruit.getAvailableQty() < 1) {
								System.out
										.println(fruit.getFruitName() + " is Not avialble at " + stand.getStandName());
								break;
							} else {
								soldQty = 1;
							}
						}
					}
					fruit.setAvailableQty(fruit.getAvailableQty() - soldQty);
					fruitsafterSold.add(fruit);
				}
			} else {
				return "No Fruit stand(s) configured";
			}
		} else {
			return "Fruit(s) to buy is not Specified";
		}
		if (fruitsafterSold != null) {
			Stand standafterSold = new Stand(standName, fruitsafterSold);
			allStands.replace(standName, standafterSold);
		}

		return "Bought and Inventory adjusted";
	}

	public static void printCurrentInventory() {
		if (allStands != null && allStands.size() > 0) {

			ArrayList<Stand> allStandsList = allStands.values().stream()
					.collect(Collectors.toCollection(ArrayList::new));
			for (Stand stand : allStandsList) {
				stand.printInventory();
			}
		}
	}

	public static void displayFruitsToBuy(String[] fruitstoBuy) {
		System.out.println("**************************************");
		System.out.print("** Fruits to Buy  : ");
		for (String fruitName : fruitstoBuy)
			System.out.print(fruitName + "  ");
		System.out.println("**");
		System.out.println("**************************************");
	}

}
