


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

	private static final int ACTION_SHOPPING = 1;
	private static final int ACTION_PLACE_ORDER = 2;
	private static final int ACTION_EXIT = 3;
	
	// 參數
	private String mPid;
	private int mAmount;
	private int mSelectedAction;
	private double mCharge;
	
	// 購物車
	private Cart mCart =  new Cart();
	
	// 商店管理者
	private Manager mManager = new Manager();
	
	/**
	 *  UI
	 */
	
	// Label
	private JLabel jlPid; 
	private JLabel jlAmount;
	private JLabel jlPay;
	private JLabel jlState;
	
	// TextField: for user input event
	private JTextField jtfPid; 
	private JTextField jtfAmount;
	private JTextField jtfPay;
	
	// TextArea
	private JTextArea jtaDisplay;
	
	// Button
	private JRadioButton jrbtnShopping; 
	private JRadioButton jrbtnCart; 
	private JRadioButton jrbtnExit; 
	private JRadioButton jrbtnPlaceTheOrder;
	private JButton jbtnSubmit;

	// Panel
	private JPanel jpNorth;
	private JPanel jpCenter;
	private JPanel jpSouth;

	public ClientService(){
		super("Customer Service");

		setSize(640,380);
		setResizable(false);  // 設定應用程式視窗不可改變大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// Define label
		jlState = new JLabel("Please select a service : "); 
		jlPid   = new JLabel("PID");   
		jlAmount  = new JLabel("Amount");
		jlPay = new JLabel("Pay");
		
		// Define text input
		jtfPid = new JTextField(10); 
		jtfPid.setEnabled(false);
		
		jtfAmount  = new JTextField(10); 
		jtfAmount.setEnabled(false);
		
		jtfPay = new JTextField(10); 
		jtfPay.setEnabled(false);
		
		jtaDisplay = new JTextArea(15,50);
		jtaDisplay.setLineWrap(true);  		// 設定自動換行 
		jtaDisplay.setWrapStyleWord(true); 	// 設定會以 word 的邊界來換行
		jtaDisplay.setEditable(false);		// 設定為可編輯 
		
		// Define radio button
		jrbtnShopping = new JRadioButton("Shopping");
		jrbtnCart = new JRadioButton("Check Cart");
		jrbtnExit = new JRadioButton("Exit mall");
		jrbtnPlaceTheOrder = new JRadioButton("Place the Order");
		
		ButtonGroup bg = new ButtonGroup();  // only one can be selected in bg
		bg.add(jrbtnShopping); 
		bg.add(jrbtnCart); 
		bg.add(jrbtnExit); 
		bg.add(jrbtnPlaceTheOrder);
		
		// Define button
		jbtnSubmit = new JButton("Submit");
		
		jrbtnShopping.addActionListener(this);
		jrbtnCart.addActionListener(this);
		jrbtnPlaceTheOrder.addActionListener(this);
		jrbtnExit.addActionListener(this);
		jbtnSubmit.addActionListener(this);
		
		// NORTH BorderLayout
		jpNorth = new JPanel();
		jpNorth.add(jlState);
		jpNorth.add(jrbtnShopping);
		jpNorth.add(jrbtnCart);
		jpNorth.add(jrbtnPlaceTheOrder);
		jpNorth.add(jrbtnExit);
		getContentPane().add(BorderLayout.NORTH, jpNorth);
			
		// CENTER BorderLayout
		jpCenter = new JPanel();

		//JScrollPane  
		JScrollPane qScroller = new JScrollPane(jtaDisplay);

		// 設定可垂直滑動
		qScroller.setVerticalScrollBarPolicy(
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 

		// 設定不可水平滑動
		qScroller.setHorizontalScrollBarPolicy(
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		jpCenter.add(qScroller);
		jpCenter.add(jlPid);
		jpCenter.add(jtfPid);
		jpCenter.add(jlAmount);
		jpCenter.add(jtfAmount);
		jpCenter.add(jlPay);
		jpCenter.add(jtfPay);
		getContentPane().add(BorderLayout.CENTER, jpCenter);

		// SOUTH BorderLayout
		jpSouth = new JPanel();
		jpSouth.add(jbtnSubmit);
		getContentPane().add(BorderLayout.SOUTH, jpSouth);

	}

	public static void main(String[] args){
		new ClientService();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(jrbtnShopping.isSelected()){
			jtfPid.setEnabled(true);
			jtfAmount.setEnabled(true);
			jtfPay.setEnabled(false);
			jtaDisplay.setText(" Current in Store : \n");
			jtaDisplay.append("--------------------------------------------------------------------\n");
			jtaDisplay.append(mManager.showAll());
			jtaDisplay.append("\n");
			jtaDisplay.append("\n\n Please enter PID & Amount ...\n");
			mSelectedAction = ACTION_SHOPPING;
		}  
		
		if(jrbtnCart.isSelected()){
			jtfPid.setEnabled(false);
			jtfAmount.setEnabled(false);
			jtfPay.setEnabled(false);
			jtaDisplay.setText(" Current in Cart : \n");
			jtaDisplay.append("--------------------------------------------------------------------\n");
			jtaDisplay.append(mCart.list());
		}
		
		if(jrbtnPlaceTheOrder.isSelected()){
			jtfPid.setEnabled(false);
			jtfAmount.setEnabled(false);
			jtfPay.setEnabled(true);
			jtaDisplay.setText(" All you want to bud today : \n");
			jtaDisplay.append("--------------------------------------------------------------------\n");
			jtaDisplay.append(mCart.list());
			jtaDisplay.append("\n");
			mCharge = mCart.charge();
			jtaDisplay.append("Charge : " + mCharge+"\n");
			mSelectedAction = ACTION_PLACE_ORDER;
		}
		
		if(jrbtnExit.isSelected()){
			jtfPid.setEnabled(false);
			jtfAmount.setEnabled(false);
			jtfPay.setEnabled(false);
			jtaDisplay.setText(" Will you leave our mall? \n If yes, please press \"Submit\"!");
			mSelectedAction = ACTION_EXIT;
		}
			
		final String action = e.getActionCommand();   
		// 依據 action 觸發 動作
		if(action.equals("Submit")){
			switch(mSelectedAction){
				case ACTION_SHOPPING:
					Shopping();
					break;
				case ACTION_PLACE_ORDER:
					placeOrder();
					break;
				case ACTION_EXIT:
					exit();
					break;
			}
		}
	}
	
	/**
	 * 結算訂單
	 */
	private void placeOrder() {	
		double pay = Double.parseDouble(jtfPay.getText().trim());
		if(pay >= mCharge){
			jtaDisplay.append("You paid $ "+pay+"\n");
			jtaDisplay.append("Return: $ "+(pay-mCharge)+"\n\n");
			mCart.clear();
		}else{
			jtaDisplay.append("\nYou payment is not enough! Please re-pay again.\n\n");
		}
	}
	
	/**
	 * 購物
	 */
	private void Shopping(){
		mPid = jtfPid.getText().trim();
		mAmount = Integer.parseInt(jtfAmount.getText());
		if(mManager.checkItem(mPid)){
			ItemInStock itemInStock = mManager.getItem(mPid);
			if(!mCart.inCart(mPid)){ 
				mCart.add(itemInStock);
			}
			if(mAmount <= itemInStock.getAmount()){ // ∑Ì¶s≥f∂q®¨∞˜Æ…
				mCart.incearse(mPid, mAmount);
				mManager.decrease(mPid, mAmount);
				jtaDisplay.append("You bought PID # "+mPid+", "+mAmount+" books\n\n");
			}else{
				jtaDisplay.append("Sorry, we do not have that much!");
			}
		}else{
			jtaDisplay.append("ohh, No this Item...");
		}
	}
	
	/**
	 * 關閉應用程式
	 */
	private void exit() {
		dispose();
	}
}

