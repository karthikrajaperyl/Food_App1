package Food_Delivery_App;

import java.util.ArrayList;
import java.util.List;

interface Order {
	String name = "raja";
	String address = "abz";

	String getRestaurantName();
}

abstract class OrderDetails implements Order {
	private String restaurantName;
	private int totalAmount;

	OrderDetails(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	protected void setAmount(int amount) {
		this.totalAmount = totalAmount;
	}

	protected int getAmount() {
		return totalAmount;
	}

	abstract int getFoodId();
}

class OrderSingleFood extends OrderDetails {
	private int foodId;

	OrderSingleFood(String restaurantName, int foodId) {
		super(restaurantName);
		this.foodId = foodId;
	}

	public int getFoodId() {
		return foodId;
	}
}

class OrderMultipleFood implements Order {
	protected boolean hasOffer;
	protected String restaurantName;
	private List<Integer> orderList = new ArrayList<>();

	OrderMultipleFood(List<Integer> orderList, String name) {
		this.orderList = orderList;
		this.restaurantName = name;
	}

	public List<Integer> getOrderList() {
		return orderList;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setOffer() {
		hasOffer = true;
	}
}

class OrderCartFood implements Order, Icart {
	protected String restaurantName;

	OrderCartFood(String name) {
		this.restaurantName = name;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

}