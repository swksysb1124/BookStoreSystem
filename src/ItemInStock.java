public class ItemInStock extends Item{
	private String information;
	public ItemInStock(String pid, String name, double price, int amount, String information){
		super(pid,name,price,amount);
		setInformation(information);
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	@Override
	public String toString(){
		return getProductId() 
				+ "\t\t" + getName()
				+ "\t\t" + getPrice()
				+ "\t\t" + getAmount()
				+ "\t\t" + getInformation();
	}
}
