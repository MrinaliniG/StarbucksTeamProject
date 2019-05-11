package starbucks.starbucksteam.model;

public class TempOrder {

	private String email;
	
	private String coffeeType;
	
	private String[] toppingTypes;	
	
	public TempOrder()
	{
		
	}
	
	public TempOrder(String email, String coffeeType, String[] toppingTypes) {
		this.email = email;
		this.coffeeType = coffeeType;
		this.toppingTypes = toppingTypes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCoffeeType() {
		return coffeeType;
	}

	public void setCoffeeType(String coffeeType) {
		this.coffeeType = coffeeType;
	}

	public String[] getToppingTypes() {
		return toppingTypes;
	}

	public void setToppingTypes(String[] toppingTypes) {
		this.toppingTypes = toppingTypes;
	}		
	
	public String generateBill()
	{
		return null;
	}
}
