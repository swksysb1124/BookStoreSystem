
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ClientService extends JFrame 
	implements ActionListener {
		
	/**
	 * Client Service 
	 *
	 * 給使用者的購物介面
	 */

	private static final long serialVersionUID = 1L;

	//øÈ•X 
	private String pid;
	private int amount;
	private int selection;
	private double charge;
	
	// 購物車
	private Cart cart =  new Cart();
	
	// 商店管理者
	private Manager manager = new Manager();
	
	/**
	 *  UI
	 */
	
	// Label
	private JLabel lPid; 
	private JLabel lAmount;
	private JLabel lPay;
	private JLabel lState;
	
	// TextField: for user input event
	private JTextField tfPid; 
	private JTextField tfAmount;
	private JTextField tfPay;
	
	// TextArea
	private JTextArea taDisplay;
	
	// Button
	private JRadioButton rbtnShopping; 
	private JRadioButton rbtnCart; 
	private JRadioButton rbtnExit; 
	private JRadioButton rbtnPlaceTheOrder;
	private JButton btnSubmit;

	// Panel
	private JPanel NorthPanel;
	private JPanel CenterPanel;
	private JPanel SouthPanel;

	public ClientService(){
		super("Customer Service");
		// Define label
		lState = new JLabel("Please select a service : "); 
		lPid   = new JLabel("PID");   
		lAmount  = new JLabel("Amount");
		lPay = new JLabel("Pay");
		
		// Define text input
		tfPid = new JTextField(10); 
		tfPid.setEnabled(false);
		
		tfAmount  = new JTextField(10); 
		tfAmount.setEnabled(false);
		
		tfPay = new JTextField(10); 
		tfPay.setEnabled(false);
		
		taDisplay = new JTextArea(15,50);
		
		// Define radio button
		rbtnShopping = new JRadioButton("Shopping");
		rbtnCart = new JRadioButton("Check Cart");
		rbtnExit = new JRadioButton("Exit mall");
		rbtnPlaceTheOrder = new JRadioButton("Place the Order");
		ButtonGroup bg=new ButtonGroup();  // only one can be selected in bg
		bg.add(rbtnShopping); bg.add(rbtnCart); bg.add(rbtnExit); bg.add(rbtnPlaceTheOrder);
		
		// Define button
		btnSubmit = new JButton("Submit");
		
		rbtnShopping.addActionListener(this);
		rbtnCart.addActionListener(this);
		rbtnPlaceTheOrder.addActionListener(this);
		rbtnExit.addActionListener(this);
		btnSubmit.addActionListener(this);
		
		// NORTH BorderLayout
		NorthPanel = new JPanel();
		NorthPanel.add(lState);
		NorthPanel.add(rbtnShopping);
		NorthPanel.add(rbtnCart);
		NorthPanel.add(rbtnPlaceTheOrder);
		NorthPanel.add(rbtnExit);
		getContentPane().add(BorderLayout.NORTH, NorthPanel);
			
		// CENTER BorderLayout
		CenterPanel = new JPanel();
		taDisplay.setLineWrap(true);  //∑Ì¶Ê™∫™¯´◊§j©Û©“§¿¨£™∫ºe´◊Æ…°A±N¥´¶Ê       
		taDisplay.setWrapStyleWord(true); //∑Ì¶Ê™∫™¯´◊§j©Û©“§¿¨£™∫ºe´◊Æ…°A±N¶b≥Êµ¸√‰¨…°]™≈•’°^≥B¥´¶Ê
		taDisplay.setEditable(false); //§£•iΩsøË™∫ 
		//JScrollPane  
		JScrollPane qScroller = new JScrollPane(taDisplay);
		//´´™Ω∫u∞   
		qScroller.setVerticalScrollBarPolicy(
		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
		//§Ù•≠∫u∞ 
		qScroller.setHorizontalScrollBarPolicy(
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		CenterPanel.add(qScroller);
		CenterPanel.add(lPid);
		CenterPanel.add(tfPid);
		CenterPanel.add(lAmount);
		CenterPanel.add(tfAmount);
		CenterPanel.add(lPay);
		CenterPanel.add(tfPay);
		getContentPane().add(BorderLayout.CENTER, CenterPanel);
		

		// SOUTH BorderLayout
		SouthPanel = new JPanel();
		SouthPanel.add(btnSubmit);
		getContentPane().add(BorderLayout.SOUTH, SouthPanel);
		
		setSize(640,380);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args){
		new ClientService();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(rbtnShopping.isSelected()){
			tfPid.setEnabled(true);
			tfAmount.setEnabled(true);
			tfPay.setEnabled(false);
			taDisplay.setText(" Current in Store : \n");
			taDisplay.append("--------------------------------------------------------------------\n");
			taDisplay.append(manager.showAll());
			taDisplay.append("\n");
			taDisplay.append("\n\n Please enter PID & Amount ...\n");
			selection = 1;
		}  
		if(rbtnCart.isSelected()){
			tfPid.setEnabled(false);
			tfAmount.setEnabled(false);
			tfPay.setEnabled(false);
			taDisplay.setText(" Current in Cart : \n");
			taDisplay.append("--------------------------------------------------------------------\n");
			taDisplay.append(cart.list());
		}
		if(rbtnPlaceTheOrder.isSelected()){
			tfPid.setEnabled(false);
			tfAmount.setEnabled(false);
			tfPay.setEnabled(true);
			taDisplay.setText(" All you want to bud today : \n");
			taDisplay.append("--------------------------------------------------------------------\n");
			taDisplay.append(cart.list());
			taDisplay.append("\n");
			charge = cart.charge();
			taDisplay.append("Charge : " + charge+"\n");
			selection = 2;
		}
		if(rbtnExit.isSelected()){
			tfPid.setEnabled(false);
			tfAmount.setEnabled(false);
			tfPay.setEnabled(false);
			taDisplay.setText(" Will you leave our mall? \n If yes, please press \"Submit\"!");
			selection = 3;
		}
			
		String str=e.getActionCommand();   
		//´ˆ§U≥]©w
		if(str.equals("Submit")){
			switch(selection){
			case 1:
				Shopping();
				break;
			case 2:
				placeOrder();
				break;
			case 3:
				dispose();
				break;
			}
		}
	}
	private void placeOrder() {
		// TODO Auto-generated method stub		
		double pay = Double.parseDouble(tfPay.getText());
		if(pay >= charge){
			taDisplay.append("You paid $ "+pay+"\n");
			taDisplay.append("Return: $ "+(pay-charge)+"\n\n");
			cart.clear();
		}else{
			taDisplay.append("\nYou payment is not enough! Please re-pay again.\n\n");
		}
	}
	private void Shopping(){
		pid = tfPid.getText();
		amount = Integer.parseInt(tfAmount.getText());
		if(manager.checkItem(pid)){
			ItemInStock itemInStock = manager.getItem(pid);
			if(!cart.inCart(pid)){ 
				cart.add(itemInStock);
			}
			if(amount <= itemInStock.getAmount()){ // ∑Ì¶s≥f∂q®¨∞˜Æ…
				cart.incearse(pid, amount);
				manager.decrease(pid, amount);
				taDisplay.append("You bought PID # "+pid+", "+amount+" books\n\n");
			}else{
				taDisplay.append("Sorry, we do not have that much!");
			}
		}else{
			taDisplay.append("ohh, No this Item...");
		}
	}
}

