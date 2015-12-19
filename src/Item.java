
public class Item {
	private String productId;
	private String name;
	private double price;
	private int amount;
	public Item(String pid, String name, double price, int amount){
		setProductId(pid);
		setName(name);
		setPrice(price);
		setAmount(amount);
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void increase(int amount) {
		this.amount += amount;
	}
	public void decrease(int amount) {
		this.amount -= amount;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString(){
		return getProductId() 
				+ " , Name : " + getName()
				+ " , Price : " + getPrice()
				+ " , Amount : " + getAmount();
	}
}
