import java.util.HashMap;
import java.util.Map;

public class Cart {
	/** container (instance variable) : use a map to implement shopping cart 
	 *  users can use the product ID to access the item they add into the cart.
	 */
	private Map<String,Item> container = new HashMap<String,Item>();
	public Cart(){
		// default constructor
	}
	// check if this item put into the cart by its product ID
	public boolean inCart(String pid){
		return container.containsKey(pid);
	}
	// add items into the cart
	public void add(ItemInStock item){
		add(item.getProductId(),item.getName(),item.getPrice(), 0);
	}
	public void add(String pid, String name, double price, int amount){
		Item item = null;
		if(container.containsKey(pid)){ // if this item is already in cart
			System.out.println("This item has existed already!");
		}else{
			item = new Item(pid, name, price, amount);
			container.put(pid, item);
		}	
	}
	// increase the number of items in cart
	public void increase(String pid, int amount){
		Item item = null;
		if(container.containsKey(pid)){ // ensure item is already in cart
			item = container.get(pid);
			item.increase(amount);
		}else{
			print("You probably type the wrong pid!");
		}	
	}
	// decrease the number of items in cart
	public void decrease(String pid, int amount){
		Item item = null;
		if(container.containsKey(pid)){ // ensure item is already in cart
			item = container.get(pid);
			if(item.getAmount() < amount){
				print("\"PID : " + pid + "\" has a error : Amount decrease too much and the amount will keep.");
				return;
			}
			item.decrease(amount);
		}else{
			print("You probably type the wrong pid!");
		}	
	}
	// print the string in console (for test)
	private void print(String str){
		System.out.println(str);
	}
	// list all items in cart
	public void list(){
		for(Item e: container.values()){
			System.out.println(e);
		}
	}
	// calculate the charge of items in cart
	public double charge(){
		double sum = 0;
		for(Item e: container.values()){
			sum += (e.getAmount() * e.getPrice());
		}
		return sum;
	}
	// clear the cart
	public void clear(){
		container.clear();
	}
}
