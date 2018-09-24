import java.util.Scanner;


public class Client {
	private Cart cart =  new Cart();
	private Manager manager = new Manager();
	Scanner request;
	int pid = 0; 
	int amount = 0;
	private void showShop(){
		System.out.println("\n\nCurrent in Store : ");
		System.out.println("------------------------------------------------------------");
		manager.showAll();
		System.out.println("\n");
	}
	public void ServiceCenter(){
		request = new Scanner(System.in);
		//String answer = null;
		do{
			System.out.println("***** Welcome to our online shopping mall *****");
			System.out.println("1. Shopping");
			System.out.println("2. Check Cart");
			System.out.println("3. Show items in shop");
			System.out.println("4. Place the order");
			System.out.println("5. Exit");
			System.out.print("--- Please elect service <1-5> : ");
			int select = request.nextInt();
			boolean leave = false;
			switch(select){
			case 1:
				shopping();
				break;
			case 2:
				checkCart();
				break;
			case 3:
				showShop();
				break;
			case 4:
				placeOrder();
				break;	
			case 5:
				leave = true;
				break;
			default:
				System.out.println("No this service");
				break;
			}
			if(leave) break;
		}while(true);
		System.out.println("\n\nThank you for coming. Wish you have a good one!");
	}

	private void placeOrder() {
		// TODO Auto-generated method stub
		System.out.println("========   You Order include   ========");
		checkCart();
		System.out.println("---------------------------------------");
		double charge = cart.charge();
		System.out.println("Charge : " + charge);
		do{
			System.out.print("Payment $ : ");
			double pay = request.nextDouble();
			if(pay >= charge){
				System.out.println("You paid $ "+pay);
				System.out.println("Return: $ "+(pay-charge)+"\n\n");
				cart.clear();
				break;
			}else{
				System.out.println("You payment is not enough! Please re-pay again.\n\n");
			}
		}while(true);
	}
	private void checkCart() {
		// TODO Auto-generated method stub
		System.out.println("\n\nIn Cart : ");
		System.out.println("------------------------------");
		cart.list();
		System.out.println("\n");
	}
	private void shopping() {
		// TODO Auto-generated method stub
		System.out.print("Please enter PID : ");
		String pid = request.next();
		if(manager.checkItem(pid)){
			ItemInStock itemInStock = manager.getItem(pid);
			if(!cart.inCart(pid)){ 
				cart.add(itemInStock);
			}
			System.out.print("Enter amount : ");
			int amount = request.nextInt();
			if(amount <= itemInStock.getAmount()){ // 當存貨量足夠時
				cart.incearse(pid, amount);
				manager.decrease(pid, amount);
				System.out.println("You bought PID# "+pid+", "+amount+" books\n\n");
			}else{
				System.out.println("Sorry, we do not have that much!");
			}
		}else{
			System.out.println("ohh, No this Item...");
		}
	}
	
}
