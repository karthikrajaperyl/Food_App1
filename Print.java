package Food_Delivery_App;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.*;

public class Print {
	public void print(CheckOut checkObj) throws FileNotFoundException {
		PrintStream o = new PrintStream(new File("list.txt"));
		PrintStream console = System.out;
		System.setOut(o);
		System.out.println(checkObj.toString());
		System.setOut(console);
		System.out.println(checkObj.toString());

	}

	protected void displayRestaurantWithFood() {
		for (Map.Entry<String, Map<Restaurant, List<Food>>> iterMap1 : Food_App.storageList.entrySet()) {
			String name = iterMap1.getKey();
			Map<Restaurant, List<Food>> foodMap = iterMap1.getValue();
			System.out.println("Restaurant-Name-" + name + "\nAvailable Restaurants are");
			for (Map.Entry<Restaurant, List<Food>> iterMap2 : foodMap.entrySet()) {
				List<Food> foodList = iterMap2.getValue();
				for (Food iterList : foodList)
					System.out.println("Food-Id-" + iterList.getId() + "\nFood-Name-" + iterList.getName() + "\nPrize-"
							+ iterList.getPrize());
			}
		}
	}

	public Restaurant getRestaurant(Map<Restaurant, List<Food>> mapList, String restaurantName) {
		for (Map.Entry<Restaurant, List<Food>> mapIter : mapList.entrySet()) {
			if (mapIter.getKey().getName() == restaurantName) {
				return mapIter.getKey();
			}
		}
		return null;
	}

	protected void printByVeg(boolean veg, Restaurant restaurantObj) {
		Map<Restaurant, List<Food>> tempMap = Food_App.storageList.get(restaurantObj.getName());
		tempMap.get(restaurantObj).stream().filter(tempObj -> tempObj.checkVeg() == veg)
				.forEach(tempObj -> System.out.println("Available foods are" + tempObj.getName()));
	}

	protected void sortFoodByPrize(Restaurant restaurantObj) {
		Map<Restaurant, List<Food>> tempMap = Food_App.storageList.get(restaurantObj.getName());
		List<Food> foodList = tempMap.get(restaurantObj);
		tempMap.get(restaurantObj).stream().sorted((a, b) -> a.getPrize() - b.getPrize())
				.forEach(tempObj -> System.out.println("Available foods are" + tempObj.getName()));
	}
}
