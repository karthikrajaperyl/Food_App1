package Food_Delivery_App;

import java.util.*;
import java.io.*;

interface Admin {
	String adminName = "xxxx";
	String address = "xyz";

	void addRestaurantsDetails();
}

interface IRestaurant {
	void addRestaurant() throws Exception;

	Restaurant getRestaurant(Map<Restaurant, List<Food>> mapList, String restaurantName);

	Performance getRestaurantPerformance() throws Exception;
}

interface IFoodDetails {
	void addFoodToRestaurant(Restaurant restaurantObj, Food foodObj);

	Food addFood() throws Exception;

	void addCombo() throws Exception;

	Food getFood(Map<Restaurant, List<Food>> mapList, int foodId);
}

interface FoodOrder {
	Cart addToCart(String name) throws Exception;

	int buyCartProduct(Cart cartObj) throws Exception;

	int orderFood(Order orderObj, Restaurant restuarantObj) throws Exception;
}

public class Food_App implements IRestaurant, IFoodDetails, FoodOrder {
	public static TreeMap<String, Map<Restaurant, List<Food>>> storageList = new TreeMap<>();

	public Performance getRestaurantPerformance() throws IOException {
		BufferedReader readerObj = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Star rating");
		int rating = Integer.parseInt(readerObj.readLine());
		System.out.println("Enter a review");
		String review = readerObj.readLine();
		Performance tempObj = new Performance(rating, review);
		return tempObj;
	}

	public int buyCartProduct(Cart cartObj) throws IOException {
		int amount = 0;
		amount = cartObj.getAmount();
		return amount;
	}

	public Cart addToCart(String restaurantName) throws IOException {
		Print printObj = new Print();
		boolean orderContinuation = true;
		BufferedReader readerObj = new BufferedReader(new InputStreamReader(System.in));
		Map<Restaurant, List<Food>> mapList = storageList.get(restaurantName);
		printObj.displayRestaurantWithFood();
		Cart cartObj = new FoodCart(restaurantName);
		Food foodObj;
		while (orderContinuation) {

			System.out.println("Choose the food-Id");
			int foodId = Integer.parseInt(readerObj.readLine());
			foodObj = getFood(mapList, foodId);
			cartObj.addToCart(foodObj);
			System.out.println("Do you want to continue-0/1");
			int i = Integer.parseInt(readerObj.readLine());
			if (i == 0) {
				orderContinuation = false;
			}
		}
		return cartObj;
	}

	protected int addComboFood(Restaurant restaurantsObj) throws IOException {
		int amount = 0;
		BufferedReader readerObj = new BufferedReader(new InputStreamReader(System.in));
		for (ComboFood iterObj : restaurantsObj.getComboList()) {
			System.out.println("Combo Name-" + iterObj.name + "Combo Prize-" + iterObj.getPrize());
			System.out.println("Do you want to add this-0/1");
			int i = Integer.parseInt(readerObj.readLine());
			if (i == 1)
				amount += iterObj.getPrize();
			else
				continue;
		}
		return amount;
	}

	public void addCombo() throws IOException {
		Print printObj = new Print();
		BufferedReader readerObj = new BufferedReader(new InputStreamReader(System.in));
		printObj.displayRestaurantWithFood();
		System.out.println("please enter your restaurent name");
		String restaurantName = readerObj.readLine();
		Restaurant restaurantObj;
		Map<Restaurant, List<Food>> map = storageList.get(restaurantName);
		restaurantObj = getRestaurant(map, restaurantName);

		List<Integer> comboList = new ArrayList<>();
		boolean available = true;
		while (available) {
			System.out.println("Choose the Food id , please enter -1 if you added the food items");
			int foodId = Integer.parseInt(readerObj.readLine());
			if (foodId == -1) {
				available = false;
			} else {
				comboList.add(foodId);
			}
		}
		System.out.println("Enter the combo name");
		String comboName = readerObj.readLine();
		System.out.println("enter the price amount");
		int price = Integer.parseInt(readerObj.readLine());
		ComboFood comboObj = new ComboFood(comboName, comboList, price);
		restaurantObj.addComboFood(comboObj);
	}

	public Restaurant getRestaurant(Map<Restaurant, List<Food>> mapList, String restaurantName) {
		for (Map.Entry<Restaurant, List<Food>> mapIter : mapList.entrySet()) {
			if (mapIter.getKey().getName() == restaurantName) {
				return mapIter.getKey();
			}
		}
		return null;
	}

	public Food getFood(Map<Restaurant, List<Food>> mapList, int foodId) {
		for (Map.Entry<Restaurant, List<Food>> mapIter : mapList.entrySet()) {
			if (mapIter.getValue().get(foodId) != null) {
				return mapIter.getValue().get(foodId);
			}
		}
		return null;
	}

	public int orderFood(Order orderObj, Restaurant restaurantObj) throws IOException {
		// List<Order> orderList = new ArrayList<Order>();
		int foodId;
		String restaurantName = restaurantObj.getName();
		Food foodObj;
		Print printObj = new Print();
		List<Integer> foodIdList = new ArrayList<>();
		BufferedReader readerObj = new BufferedReader(new InputStreamReader(System.in));
		printObj.displayRestaurantWithFood();

		boolean orderContinuation = true;
		int amount = 0;
		while (orderContinuation) {
			System.out.println("Enter the Food Id");
			foodId = Integer.parseInt(readerObj.readLine());
			if (Food_App.storageList.get(restaurantName).get(restaurantObj).get(foodId) != null) {
				foodIdList.add(foodId);
				foodObj = Food_App.storageList.get(restaurantName).get(restaurantObj).get(foodId);// Accesing through
																									// Index
				amount = amount + foodObj.getPrize();
				// orderList.add(userObj);
			} else {
				System.out.println("Given Food doesn't exist");
				System.out.println("do you want to order another food\n1.continue\n0.exit");
				if (Integer.parseInt(readerObj.readLine()) == 0) {
					orderContinuation = false;
				}
			}
		}
		System.out.println("Do you want to add combo food-0/1");
		if (Integer.parseInt(readerObj.readLine()) == 1) {
			amount += addComboFood(restaurantObj);
		}
		if (foodIdList.size() > 1) {
			orderObj = new OrderMultipleFood(foodIdList, restaurantName);
		} else {
			orderObj = new OrderSingleFood(restaurantName, foodIdList.get(0));
		}
		return amount;
	}

	public void addRestaurant() throws IOException {
		BufferedReader readerObj = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Restuarant Name");
		String restaurantName = readerObj.readLine();
		System.out.println("Enter the Restaurant Address");
		String address = readerObj.readLine();
		List<Food> foodList = new ArrayList<>();
		Restaurant restaurantObj = new RestaurantFoodDetails(restaurantName, address);
		boolean continuation = true;
		int i;
		while (continuation) {
			addFoodToRestaurant(restaurantObj, addFood());
			System.out.println("Do you want to add the food- 1/0");
			i = Integer.parseInt(readerObj.readLine());
			if (i == 0) {
				continuation = false;
			}
		}
	}

	public Food addFood() throws IOException {
		int count = Food.count++;
		BufferedReader readerObj = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the food name");
		String foodName = readerObj.readLine();
		System.out.println("please enter the price");
		Integer prize = Integer.parseInt(readerObj.readLine());
		System.out.println("Enter the food is veg or non-veg 1/0");
		int i = Integer.parseInt(readerObj.readLine());
		boolean veg = (i == 1);
		Food foodObj = new Food(count, foodName, veg);
		foodObj.setPrize(prize);
		return foodObj;
	}

	public void addFoodToRestaurant(Restaurant restaurantObj, Food foodObj) {
		Map<String, Map<Restaurant, List<Food>>> temp = new HashMap<>(Food_App.storageList);
		if (temp.get(restaurantObj.getName()).get(restaurantObj) != null) {
			temp.get(restaurantObj.getName()).get(restaurantObj).add(foodObj);
		} else {
			List<Food> foodList = new ArrayList<>();
			foodList.add(foodObj);
			Map<Restaurant, List<Food>> mapList = new HashMap<>();
			mapList.put(restaurantObj, foodList);
			temp.put(restaurantObj.getName(), mapList);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scannerObj = new Scanner(System.in);
		boolean continuation = true;
		IRestaurant restaurantIObj = new Food_App();
		IFoodDetails restaurantFoodObj = new Food_App();
		FoodOrder orderObj = new Food_App();
		Cart cart = null;
		Order tempObj = null;
		CheckOut checkObj = null;
		Print printObj = new Print();
		while (continuation) {
			Thread.sleep(50);
			System.out.println("1.As An admin\n2.As an Customer\n3.Exit");
			int i = scannerObj.nextInt();
			switch (i) {
			case 1:
				Admin adminObj;
				System.out.println("1.Add restaurants Details\n2.Add an combo Food\n3.Exit");
				int j = scannerObj.nextInt();
				inner: switch (j) {
				case 1:
					restaurantIObj.addRestaurant();
					break;
				case 2:
					restaurantFoodObj.addCombo();
					break;
				case 3:
					break inner;
				}
				break;
			case 2:
				System.out.println("Enter the Restaurant name");
				String restaurantName = scannerObj.nextLine();
				if (!Food_App.storageList.containsKey(restaurantName)) {

					System.out.println("There is no restaurants available for given name");
				}
				Restaurant restaurantObj;
				Map<Restaurant, List<Food>> map = storageList.get(restaurantName);
				restaurantObj = restaurantIObj.getRestaurant(map, restaurantName);
				System.out.println(
						"1.Add food on cart\n2.Buy an Cart Product\n3.Order an food\n4.Show checkOut\n5.set performance\n6.Display\n7.Exit");
				int k = scannerObj.nextInt();
				int amount = 0;
				inner: switch (k) {
				case 1:
					cart = orderObj.addToCart(restaurantName);
					break;
				case 2:
					amount = orderObj.buyCartProduct(cart);
					break;
				case 3:
					amount = orderObj.orderFood(tempObj, restaurantObj);
					break;
				case 4: {
					checkObj = new CheckOut(amount, tempObj);
					printObj.print(checkObj);
				}
				case 5:
					restaurantObj.setRestaurantPerformance(restaurantIObj.getRestaurantPerformance());
					break;
				case 6:
					System.out.println("Display by\n1.Veg\n2.prize");
					int l = scannerObj.nextInt();
					switch (l) {
					case 1:
						boolean veg = scannerObj.nextBoolean();
						printObj.printByVeg(veg, restaurantObj);
						break;
					case 2:
						printObj.sortFoodByPrize(restaurantObj);
						break;
					}
				case 7:
					break inner;
				}
				break;
			case 3:
				System.exit(0);
			}
		}
	}

}
