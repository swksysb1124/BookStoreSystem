import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class ManagerService extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stock stock;
	private String userName = "manager"; // 定义管理员的用户名和密码
	private String password = "0000";
	
	private String user, pwd, pid, pname, information;
	private double price;
	private int amount,count=0;
	
	private int selection;
	
	private JLabel jluser, jlpwd, jlpid, jlpname, jlprice, jlamount, jlinformation, jlstate;
	private JTextField jtfuser,jtfpwd, jtfpid,jtfpname, jtfprice, jtfamount, jtfinformation;
	private JTextArea display;
	private JRadioButton jrbAdd, jrbUpdate, jrbDelete, jrbShow, jrbExit;
	private ButtonGroup bg;
	private JButton jbSubmit;
	private JPanel NorthPanel, CenterPanel, OperationPanel, SouthPanel;
	
	public ManagerService() {
		super("Manager Service");
		// TODO Auto-generated constructor stub
		initializing();
		// Define Label
		jluser = new JLabel("Manager : ");
		jlpwd = new JLabel("Password : ");
		jlpid = new JLabel("PID : "); 
		jlpname = new JLabel("Name : ");
		jlprice = new JLabel("Price : ");
		jlamount = new JLabel("Amount : ");
		jlinformation = new JLabel("Information : ");
		jlstate = new JLabel("Only manager access!");
		// Define TextField
		jtfuser = new JTextField(8);
		jtfpwd = new JTextField(8); 
		jtfpid = new JTextField(5); jtfpid.setEnabled(false);
		jtfpname = new JTextField(5); jtfpname.setEnabled(false);
		jtfprice = new JTextField(5); jtfprice.setEnabled(false);
		jtfamount = new JTextField(5); jtfamount.setEnabled(false);
		jtfinformation = new JTextField(5); jtfinformation.setEnabled(false);
		
		// Define TextArea
		display = new JTextArea(15,49);
		// Define RadioButton
		jrbAdd = new JRadioButton("Add"); jrbAdd.setEnabled(false);
		jrbUpdate = new JRadioButton("Update"); jrbUpdate.setEnabled(false);
		jrbDelete = new JRadioButton("Delete"); jrbDelete.setEnabled(false);
		jrbShow = new JRadioButton("Show"); jrbShow.setEnabled(false);
		jrbExit = new JRadioButton("Exit"); jrbExit.setEnabled(false);
		bg = new ButtonGroup();
		bg.add(jrbAdd);bg.add(jrbUpdate);bg.add(jrbDelete);bg.add(jrbShow);bg.add(jrbExit);
		// Define Button
		jbSubmit = new JButton("Submit");
		// JPanel
		NorthPanel = new JPanel();
		OperationPanel = new JPanel(); 
		OperationPanel.setLayout(new BoxLayout(OperationPanel, BoxLayout.Y_AXIS));
		OperationPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 15)));
		CenterPanel = new JPanel();
		CenterPanel.setBorder(new EmptyBorder(new Insets(5, 15, 5, 5)));
		SouthPanel = new JPanel();
		//Listener Setting
		jrbAdd.addActionListener(this); 
		jrbUpdate.addActionListener(this); 
		jrbDelete.addActionListener(this);
		jrbShow.addActionListener(this);
		jrbExit.addActionListener(this);
		jbSubmit.addActionListener(this);
		//Panel Configuration
		//NORTH
		NorthPanel.add(jluser);
		NorthPanel.add(jtfuser);
		NorthPanel.add(jlpwd);
		NorthPanel.add(jtfpwd);
		NorthPanel.add(jrbAdd);
		NorthPanel.add(jrbUpdate);
		NorthPanel.add(jrbDelete);
		NorthPanel.add(jrbShow);
		NorthPanel.add(jrbExit);
		getContentPane().add(BorderLayout.NORTH, NorthPanel);
		
		//EAST
		JPanel SelectPanel = new JPanel();
		SelectPanel.setLayout(new BoxLayout(SelectPanel, BoxLayout.Y_AXIS));
		SelectPanel.add(jlpid);		SelectPanel.add(jtfpid);
		SelectPanel.add(jlpname);		SelectPanel.add(jtfpname);
		SelectPanel.add(jlprice);		SelectPanel.add(jtfprice);
		SelectPanel.add(jlamount);		SelectPanel.add(jtfamount);
		SelectPanel.add(jlinformation);		SelectPanel.add(jtfinformation);
		JPanel SubmitPanel = new JPanel();
		SubmitPanel.add(jbSubmit);
		OperationPanel.add(SelectPanel);
		OperationPanel.add(SubmitPanel);
		getContentPane().add(BorderLayout.EAST, OperationPanel);
		
		//CENTER
		display.setLineWrap(true);  //當行的長度大於所分派的寬度時，將換行       
		display.setWrapStyleWord(true); //當行的長度大於所分派的寬度時，將在單詞邊界（空白）處換行
		display.setEditable(false); //不可編輯的 
		JScrollPane qScroller = new JScrollPane(display);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		CenterPanel.add(qScroller);
		getContentPane().add(BorderLayout.CENTER, CenterPanel);
		
		//SOUTH
		SouthPanel.add(jlstate);
		getContentPane().add(BorderLayout.SOUTH, SouthPanel);
		
		setSize(750,390);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	private void initializing(){
		stock = new Stock();
		stock.add("1232", "Java", 120.0, 30, "Very Nice!");
		stock.add("2321", "C#", 110.0, 20, "You should buy!");
		stock.add("1231", "C++", 100.3, 40, "Great art.");
		stock.add("2187", "Python", 79.5, 80, "Pretty excel!");
	}
	public void login(){
		display.setText("Please enter username and password:");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ManagerService();
	}
	public boolean checkPID(String pid){
		return stock.checkItem(pid);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(jrbAdd.isSelected()){
			jtfpid.setEnabled(true);
			jtfpname.setEnabled(true);
			jtfprice.setEnabled(true);
			jtfamount.setEnabled(true);
			jtfinformation.setEnabled(true);
			jbSubmit.setEnabled(true);
			
			display.setText("Enter the information of new product ...\n");
			display.append("--------------------------------------------------------------------\n");
			selection = 1;
		}  
		if(jrbUpdate.isSelected()){
			jtfpid.setEnabled(true);
			jtfpname.setEnabled(false);
			jtfprice.setEnabled(false);
			jtfamount.setEnabled(false);
			jtfinformation.setEnabled(false);
			jbSubmit.setEnabled(true);
			
			display.setText(" Current in stock : \n");
			display.append("--------------------------------------------------------------------\n");
			display.append(stock.list());
			selection = 2;
		}
		if(jrbDelete.isSelected()){
			jtfpid.setEnabled(true);
			jtfpname.setEnabled(false);
			jtfprice.setEnabled(false);
			jtfamount.setEnabled(false);
			jtfinformation.setEnabled(false);
			jbSubmit.setEnabled(true);
			
			display.setText(" Enter the PID you want to delete ... \n");
			display.append("--------------------------------------------------------------------\n");
			selection = 3;
		}
		if(jrbShow.isSelected()){
			jtfpid.setEnabled(false);
			jtfpname.setEnabled(false);
			jtfprice.setEnabled(false);
			jtfamount.setEnabled(false);
			jtfinformation.setEnabled(false);
			jbSubmit.setEnabled(false);
			
			display.setText(" Current in stock : \n");
			display.append("--------------------------------------------------------------------\n");
			display.append(stock.list());
		}
		if(jrbExit.isSelected()){
			jtfpid.setEnabled(false);
			jtfpname.setEnabled(false);
			jtfprice.setEnabled(false);
			jtfamount.setEnabled(false);
			jtfinformation.setEnabled(false);
			jbSubmit.setEnabled(true);
			
			display.setText(" Will you leave our mall? \n If yes, please press \"Submit\"!");
			selection = 4;
		}
		
		
		String str = e.getActionCommand();   
		if(str.equals("Submit")){
			switch(selection){
			case 0:
				loginProcess();
				break;
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
				exit();
				break;
			}
		}
	}
	private void exit() {
		// TODO Auto-generated method stub
		dispose();
	}
	private void delete() {
		// TODO Auto-generated method stub
		pid = jtfpid.getText();
		if(!stock.checkItem(pid)){
			display.append("\n No such item!");
			return;
		}
		stock.delete(pid);
		display.append("\nItem# "+pid+ " has been removed!\n");
	}
	private void update() {
		// TODO Auto-generated method stub
		pid = jtfpid.getText();
		if(!stock.checkItem(pid)){
			display.append("Wrong PID!");
		}else{
			jtfpid.setEnabled(false);
			jtfpname.setEnabled(true);
			jtfprice.setEnabled(true);
			jtfamount.setEnabled(true);
			jtfinformation.setEnabled(true);
			display.setText("Please udpate new information");
			pname = jtfpname.getText(); 
			price = jtfprice.getText().length()==0?-1:Double.parseDouble(jtfprice.getText());
			amount = jtfamount.getText().length()==0?-1:Integer.parseInt(jtfamount.getText());
			information = jtfinformation.getText();
			if(pname ==null || price == -1 || amount == -1 || information ==null) return; 
			reSetName(pid, pname);
			reSetPrice(pid, price);
			reSetAmount(pid, amount);
			reSetInformation(pid, information);
			display.append("\n\nitem# "+pid+ " has been updated!\n");
		}
	}
	
	private void add() {
		// TODO Auto-generated method stub
		pid = jtfpid.getText();
		pname = jtfpname.getText(); 
		price = Double.parseDouble(jtfprice.getText());
		amount = Integer.parseInt(jtfamount.getText());
		information = jtfinformation.getText();
		stock.add(pid, pname, price, amount, information);
		display.append("\n\nitem# "+pid+ " has been added!\n");
	}
	private void loginProcess(){
		if(count < 3){
			user = jtfuser.getText();
			pwd = jtfpwd.getText();
			if(user.equals(userName) && pwd.equals(password)){
				jrbAdd.setEnabled(true);
				jrbUpdate.setEnabled(true);
				jrbDelete.setEnabled(true);
				jrbShow.setEnabled(true);
				jrbExit.setEnabled(true);
				jbSubmit.setEnabled(true);
				display.append("Login successfully!!");
				jtfuser.setEnabled(false);
				jtfpwd.setEnabled(false);
				
			}else{
				display.append("\nError: You still have "+(2-count) +" chances!");
				count++;
			}
		}else{
			display.append("You have no permission!");
			dispose();
		}
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
