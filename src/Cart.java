
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<String,Item> container = new HashMap<String,Item>();
	public Cart(){
		// default constructor
	}
	public boolean inCart(String pid){
		return container.containsKey(pid);
	}
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
	public void incearse(String pid, int amount){
		Item item = null;
		if(container.containsKey(pid)){ // ensure item is already in cart
			item = container.get(pid);
			item.increase(amount);
		}else{
			print("You probably type the wrong pid!");
		}	
	}
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
	private void print(String str){
		System.out.println(str);
	}
	public String list(){
		StringBuilder str = new StringBuilder();
		for(Item e: container.values()){
			str.append(e);
			str.append("\n");
		}
		return str.toString();
	}
	public double charge(){
		double sum = 0;
		for(Item e: container.values()){
			sum += (e.getAmount() * e.getPrice());
		}
		return sum;
	}
	public void clear(){
		container.clear();
	}
}
