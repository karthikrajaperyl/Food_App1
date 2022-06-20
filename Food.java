package Food_Delivery_App;

import java.util.List;

public class Food {

	static int count = 0;
	private int foodId;
	private String foodName;
	private boolean veg;
	private Integer prize;
	
	Food(int id, String name, boolean veg) {
		this.foodId = id;
		this.foodName = name;
		this.veg = veg;
	}

	protected int getId() {
		return this.foodId;
	}

	protected String getName() {
		return this.foodName;
	}

	protected void setPrize(int prize) {
		this.prize = prize;
	}

	protected Integer getPrize() {
		return prize;
	}

	protected boolean checkVeg() {
		return this.veg;
	}

	@Override
	public String toString() {
		return "\nid = " + this.foodId + "\nname = " + this.foodName + "\nprize = " + this.prize;
	}

}

class Stew extends Food {
	Stew(int id, String name, boolean veg) {
		super(id, name, veg);
	}
}

class ComboFood {
	public String name;
	public List<Integer> comboList;
	private Integer prize;

	ComboFood(String name, List<Integer> foodIds, int prize) {
		this.name = name;
		this.comboList = foodIds;
		this.prize = prize;
	}

	protected Integer getPrize() {
		return prize;
	}
}
