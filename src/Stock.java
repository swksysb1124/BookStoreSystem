import java.util.HashMap;
import java.util.Map;


public class Stock {
private Map<String,ItemInStock> container = new HashMap<String,ItemInStock>();
	// add new items
	public void add(String pid, String name, double price, int amount, String information){
		ItemInStock itemInStock = null;
		if(container.containsKey(pid)){ // if this item is already in cart
			print("this item has existed already!");
			return;
		}else{
			itemInStock = new ItemInStock(pid, name, price, amount, information);
			container.put(pid, itemInStock);
		}	
	}
	// modify existing items
	public void reSetName(String pid, String newName){
		ItemInStock itemInStock = null;
		if(container.containsKey(pid)){ // if this item is already in cart
			itemInStock = container.get(pid);
			itemInStock.setName(newName);
		}else{
			print("Error: No Item can be re-named!");
			return;
		}
	}
	public void reSetPrice(String pid, double newPrice){
		ItemInStock itemInStock = null;
		if(container.containsKey(pid)){ // if this item is already in cart
			itemInStock = container.get(pid);
			itemInStock.setPrice(newPrice);
		}else{
			print("Error: No Item'price can be reseted!");
			return;
		}
	}
	public void reSetAmount(String pid, int newAmount){
		ItemInStock itemInStock = null;
		if(container.containsKey(pid)){ // if this item is already in cart
			itemInStock = container.get(pid);
			itemInStock.setAmount(newAmount);
		}else{
			print("Error: No Item'amount can be reset!");
			return;
		}
	}
	public void reSetInformation(String pid, String newInformation){
		ItemInStock itemInStock = null;
		if(container.containsKey(pid)){ // if this item is already in cart
			itemInStock = container.get(pid);
			itemInStock.setInformation(newInformation);
		}else{
			print("Error: No Item'information can be reset!");
			return;
		}
	}
	// delete items
	public boolean delete(String pid){
		if(container.containsKey(pid)){ // if this item is already in cart
			container.remove(pid);
			return true;
		}else{
			print("Error: No such item!");
			return false;
		}
	}
	// show all items
	public void list(){
		print("PID\t\tName\t\tPrice\t\tAmount\t\tInformation\t\t");
		for(Item e: container.values()){
			print(e);
		}
	}
	public boolean checkItem(String pid){
		return container.containsKey(pid);
	}
	
	public void decrease(String pid, int amount){
		if(container.containsKey(pid)){
			ItemInStock itemIS = container.get(pid);
			itemIS.decrease(amount);
		}
	}
	public void increase(String pid, int amount){
		if(container.containsKey(pid)){
			ItemInStock itemIS = container.get(pid);
			itemIS.increase(amount);
		}
	}
	
	
	public ItemInStock getItem(String pid){
		return container.get(pid);
	}
	public void print(Object str){
		System.out.println(str);
	}
	
}
