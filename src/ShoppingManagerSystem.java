import java.util.Scanner;


public class ShoppingManagerSystem {
	public static void menu(){
		System.out.println(" =============================================== ");
		System.out.println(" == Welcome to Online Shopping Manager System ==");
		System.out.println(" =============================================== ");		
		System.out.println(" -> If you are a customer, please type <1>");
		System.out.println(" -> If you are the manager, please type <2>");
		System.out.print("--> Please type your selection : <1 - 2> ");		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Client client;
		Manager manager;
		do{
			menu();
			int answer = scanner.nextInt();
			System.out.println(" =============================================== \n\n");		
			switch(answer){
			case 1:
				client = new Client();
				client.ServiceCenter();
				break;
			case 2:
				manager = new Manager();
				manager.login();
				break;
			default:
				System.out.println("No this funciton!");
				break;
			}
			System.out.print("--- Do you want to enter the system again ? <y/n>");
			String YorN = scanner.next();
			if(YorN.equals("n")) break;
		}while(true);
		System.out.println("You have leaved the system!");
		scanner.close();
		
	}

}
