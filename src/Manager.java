import java.util.Scanner;


public class Manager {
	private Stock stock;	
	
	private String userName = "manager"; // 定义管理员的用户名和密码
	private String password = "0000";
	private String user = ""; // 定义输入的管理员用户名和密码
	private String pwd = "";
	Scanner input = new Scanner(System.in);
	public Manager(){
		initializing();
	}
	private void initializing(){
		stock = new Stock();
		stock.add("1232", "Java", 120.0, 30, "Very Nice!");
		stock.add("2321", "C#", 110.0, 20, "You should buy!");
		stock.add("1231", "C++", 100.3, 40, "Great art.");
		stock.add("2187", "Python", 79.5, 80, "Pretty excel!");
	}
	private void mainMenu() {
		// TODO Auto-generated method stub
		int choose = 0;
		System.out.println("*******************************");
		System.out.println("Welcome to Shopping Manager System");
		System.out.println("*******************************");
		System.out.println("1. Add new items");
		System.out.println("2. Update items");
		System.out.println("3. Delete items");
		System.out.println("4. Show all items in stock");
		System.out.println("5. Exit");
		System.out.println("--------------------------------");
		System.out.print("Enter your selection : ");
		choose = input.nextInt();
		System.out.println("*******************************");
		boolean leave = false;
		switch(choose){
		case 1:
			add();
			break;
		case 2:
			update();
			break;
		case 3:
			delete();
			break;
		case 4:
			showAll();
			break;
		case 5:
			leave = true;
			break;
		default:
			System.out.println("Wrong Operation!");
			break;
		}
		if(!leave) mainMenu();
		System.out.println("\n\nYou finished an operation.");	
	}
	public void login(){
		boolean Permission = false;
		for(int i = 0; i < 3; i++){
			System.out.print("Enter manager's name : ");
			user = input.next();
			System.out.print("Enter password : ");
			pwd = input.next();
			if(user.equals(userName) && pwd.equals(password)){
				Permission = true;
				break;
			}else{
				if(i==2) break;
				System.out.println("You still have "+(2-i) +" chances!");
			}
		}
		if(Permission){
			mainMenu();
		}else{
			System.out.println("You have no permission!");
		}
	}
	public boolean checkItem(String pid){
		return stock.checkItem(pid);
	}
	public String showAll(){
		return stock.list();
	}
	public ItemInStock getItem(String pid){
		return stock.getItem(pid);
	}
	public void add(){
		System.out.println(" *** Please enter new item's information *** ");
		System.out.print("PID : ");
		String pid = input.next();
		System.out.print("Name : ");
		String name = input.next();
		System.out.print("Price : ");
		double price = input.nextDouble();
		System.out.print("Amount : ");
		int amount = input.nextInt();
		System.out.print("Information : ");
		String information = input.next();
		
		stock.add(pid, name, price, amount, information);
		System.out.println("item# "+pid+ " has been added!\n");
	}
	public void update(){
		System.out.println(" *** Please enter the item's PID which you want to Update *** ");
		System.out.print("PID : ");
		String pid = input.next();
		if(!stock.checkItem(pid)){
			System.out.println("Wrong PID!");
			return;
		}
		System.out.println(" You want update (1)Name, (2)Price, (3)Amount, (4)Information ");
		int choose = input.nextInt();
		switch(choose){
		case 1:
			System.out.print("Name : ");
			String name = input.next();
			reSetName(pid, name);
			break;
		case 2:
			System.out.print("Price : ");
			double price = input.nextDouble();
			reSetPrice(pid, price);
			break;
		case 3:
			System.out.print("Amount : ");
			int amount = input.nextInt();
			reSetAmount(pid, amount);
			break;
		case 4:
			System.out.print("Information : ");
			String Information = input.next();
			reSetInformation(pid, Information);
			break;
		}
		System.out.println("item# "+pid+ " has been updated!\n");
	}
	public void delete(){
		System.out.print("Enter PID : ");
		String pid = input.next();
		stock.delete(pid);
		System.out.println("item# "+pid+ " has been removed!\n");
	}
	public boolean checkPID(String pid){
		return stock.checkItem(pid);
	}
	public void decrease(String pid, int amount){
		stock.decrease(pid, amount);
	}
	public void increase(String pid, int amount){
		stock.increase(pid, amount);
	}
	
	public void reSetName(String pid,String newName){
		stock.reSetName(pid, newName);
	}
	public void reSetPrice(String pid,double newPrice){
		stock.reSetPrice(pid, newPrice);
	}
	public void reSetAmount(String pid,int newAmount){
		stock.reSetAmount(pid, newAmount);
	}
	public void reSetInformation(String pid,String newInformation){
		stock.reSetInformation(pid, newInformation);
	}
}
