package Food_Delivery_App;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Cart {
	void addToCart(Food foodObj);

	public Map<String, List<Food>> getCartStorage();

	public int getAmount();
}

interface Icart {
	String getRestaurantName();
}

public class FoodCart implements Cart, Icart {
	private int totalAmount;
	private Icart orderObj;
	private String restaurantName;
	private Map<String, List<Food>> cartMap = new HashMap<>();

	FoodCart(String restaurantName) {
		this.orderObj = new OrderCartFood(restaurantName);
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public Icart getUserInstance() {
		return this.orderObj;
	}

	/*
	 * public void setUserInstance() { orderObj = new Order(); }
	 */

	public void addToCart(Food food) {
		if (cartMap.get(restaurantName) != null) {
			cartMap.get(restaurantName).add(food);
		} else {
			ArrayList<Food> foodList = new ArrayList<>();
			foodList.add(food);
			cartMap.put(restaurantName, foodList);
		}
		// space complexity,Time Complexity;
		updateAmount(food.getPrize());
	}

	private void updateAmount(int amount) {
		totalAmount += amount;
	}

	public Map<String, List<Food>> getCartStorage() {
		return cartMap;
	}

	public int getAmount() {
		return totalAmount;
	}
}
