package Food_Delivery_App;

import java.util.*;
import java.io.*;

/*enum OfferType {
	AMOUNT, CARD;
	int amount;
	int percentage;
}*/
abstract class Restaurant {
	private String name;
	private String address;
	// public OfferType offerType;
	private Performance restaurantPerformance;

	Restaurant(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setRestaurantPerformance(Performance performance) {
		this.restaurantPerformance = performance;
	}

	/*
	 * public void setOffer(OfferType type) { this.offerType=type; }
	 */
	abstract public void addComboFood(ComboFood comboObj);

	abstract public List<ComboFood> getComboList();
}

class RestaurantFoodDetails extends Restaurant {
	private List<ComboFood> comboFood = new ArrayList<>();

	RestaurantFoodDetails(String name, String address) {
		super(name, address);
	}

	public void addComboFood(ComboFood comboObj) {
		comboFood.add(comboObj);
	}

	public List<ComboFood> getComboList() {
		return comboFood;
	}
}

class Performance {
	public int reviewIndex = -1;
	public Integer rating;
	public String[] review;

	Performance(int rating, String reviewField) {
		this.rating = rating;
		this.review[++this.reviewIndex] = reviewField;
	}
}