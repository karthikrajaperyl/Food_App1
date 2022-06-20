package Food_Delivery_App;

public class CheckOut {
	private int amount;
	private Order orderObj;

	CheckOut(int amount, Order orderObj) {
		this.amount = amount;
		this.orderObj = orderObj;
		applyTax();
	}

	public int getAmount() {
		return amount;
	}

	public void applyTax() {
		amount = (amount + amount * 10 / 100);
		setTotalAmount(amount);
	}

	public void setTotalAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "\nRestaurantName-" + orderObj.getRestaurantName() + "\nName-" + Order.name + "\nAddress-"
				+ Order.address + "\nTotal Amount-" + this.getAmount();
	}
}
