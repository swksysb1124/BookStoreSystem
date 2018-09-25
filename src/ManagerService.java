import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class ManagerService 
	extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int ACTION_LOGIN_PROCESS = 0;
	private static final int ACTION_ADD = 1;
	private static final int ACTION_UPDATE = 2;
	private static final int ACTION_DELETE = 3;
	private static final int ACTION_EXIT = 4;
	
	private Stock stock;
	private static final String defaultManagerName = "manager"; // 定义管理员的用户名和密码
	private static final String defaultManagerPassword = "0000";
	
	private String mManagerName;
	private String mManagerPassword;
	private String mPid;
	private String mPname;
	private String mInformation;
	
	private double mPrice;
	
	private int mAmount;
	private int mLoginCount = 0;
	
	private int mSelectedAction;
	
	private JLabel jlManagerName;
	private JLabel jlManagerPassword;
	private JLabel jlPid;
	private JLabel jlPname;
	private JLabel jlPrice;
	private JLabel jlAmount;
	private JLabel jlInformation; 
	private JLabel jlState;
	
	private JTextField jtfUser;
    private JTextField jtfPwd; 
	private JTextField jtfPid;
	private JTextField jtfPname;
	private JTextField jtfPrice; 
	private JTextField jtfAmount; 
	private JTextField jtfInformation;
	
	private JTextArea jtaDisplay;
	
	private ButtonGroup btnGrp;
	private JRadioButton jrbtnAdd; 
	private JRadioButton jrbtnUpdate;
	private JRadioButton jrbtnDelete;
	private JRadioButton jrbtnShow;
	private JRadioButton jrbtnExit;
	
	private JButton jbtnSubmit;
	
	private JPanel jpNorth;
	private JPanel jpCenter;
	private JPanel jpEast;
	private JPanel jpSouth;
	
	public ManagerService() {
		super("Manager Service");
		initializing();
		
		// Define Label
		jlManagerName = new JLabel("Manager : ");
		jlManagerPassword = new JLabel("Password : ");
		jlPid = new JLabel("PID : "); 
		jlPname = new JLabel("Name : ");
		jlPrice = new JLabel("Price : ");
		jlAmount = new JLabel("Amount : ");
		jlInformation = new JLabel("Information : ");
		jlState = new JLabel("Only manager access!");
		
		// Define TextField
		jtfUser = new JTextField(8);
		jtfPwd = new JTextField(8); 
		jtfPid = new JTextField(5); jtfPid.setEnabled(false);
		jtfPname = new JTextField(5); jtfPname.setEnabled(false);
		jtfPrice = new JTextField(5); jtfPrice.setEnabled(false);
		jtfAmount = new JTextField(5); jtfAmount.setEnabled(false);
		jtfInformation = new JTextField(5); jtfInformation.setEnabled(false);
		
		// Define TextArea
		jtaDisplay = new JTextArea(15,49);
		
		// Define RadioButton
		jrbtnAdd = new JRadioButton("Add"); jrbtnAdd.setEnabled(false);
		jrbtnUpdate = new JRadioButton("Update"); jrbtnUpdate.setEnabled(false);
		jrbtnDelete = new JRadioButton("Delete"); jrbtnDelete.setEnabled(false);
		jrbtnShow = new JRadioButton("Show"); jrbtnShow.setEnabled(false);
		jrbtnExit = new JRadioButton("Exit"); jrbtnExit.setEnabled(false);
		
		btnGrp = new ButtonGroup();
		btnGrp.add(jrbtnAdd);
		btnGrp.add(jrbtnUpdate);
		btnGrp.add(jrbtnDelete);
		btnGrp.add(jrbtnShow);
		btnGrp.add(jrbtnExit);
		
		// Define Button
		jbtnSubmit = new JButton("Submit");
		
		
		
		// Listener Setting
		jrbtnAdd.addActionListener(this); 
		jrbtnUpdate.addActionListener(this); 
		jrbtnDelete.addActionListener(this);
		jrbtnShow.addActionListener(this);
		jrbtnExit.addActionListener(this);
		jbtnSubmit.addActionListener(this);
		
		// JPanel
		
		// North
		jpNorth = new JPanel();
				
		// East
		jpEast = new JPanel(); 
		jpEast.setLayout(new BoxLayout(jpEast, BoxLayout.Y_AXIS)); // 設定配置方向為 Y-軸
		jpEast.setBorder(new EmptyBorder(new Insets(5, 5, 5, 15)));
				
		// Center
		jpCenter = new JPanel();
		jpCenter.setBorder(new EmptyBorder(new Insets(5, 15, 5, 5)));
				
		// South
		jpSouth = new JPanel();
		
		// Panel Configuration
		
		// NORTH
		jpNorth.add(jlManagerName);
		jpNorth.add(jtfUser);
		
		jpNorth.add(jlManagerPassword);
		jpNorth.add(jtfPwd);
		
		jpNorth.add(jrbtnAdd);
		jpNorth.add(jrbtnUpdate);
		jpNorth.add(jrbtnDelete);
		jpNorth.add(jrbtnShow);
		jpNorth.add(jrbtnExit);
		getContentPane().add(BorderLayout.NORTH, jpNorth);
		
		// EAST
		JPanel SelectPanel = new JPanel();
		SelectPanel.setLayout(new BoxLayout(SelectPanel, BoxLayout.Y_AXIS));
		SelectPanel.add(jlPid);		
		SelectPanel.add(jtfPid);
		SelectPanel.add(jlPname);		
		SelectPanel.add(jtfPname);
		SelectPanel.add(jlPrice);		
		SelectPanel.add(jtfPrice);
		SelectPanel.add(jlAmount);		
		SelectPanel.add(jtfAmount);
		SelectPanel.add(jlInformation);		
		SelectPanel.add(jtfInformation);
		
		JPanel SubmitPanel = new JPanel();
		SubmitPanel.add(jbtnSubmit);
		
		jpEast.add(SelectPanel);
		jpEast.add(SubmitPanel);
		getContentPane().add(BorderLayout.EAST, jpEast);
		
		// CENTER
		jtaDisplay.setLineWrap(true);  //當行的長度大於所分派的寬度時，將換行       
		jtaDisplay.setWrapStyleWord(true); //當行的長度大於所分派的寬度時，將在單詞邊界（空白）處換行
		jtaDisplay.setEditable(false); //不可編輯的 
		JScrollPane qScroller = new JScrollPane(jtaDisplay);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jpCenter.add(qScroller);
		getContentPane().add(BorderLayout.CENTER, jpCenter);
		
		// SOUTH
		jpSouth.add(jlState);
		getContentPane().add(BorderLayout.SOUTH, jpSouth);
		
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
		jtaDisplay.setText("Please enter username and password:");
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
		if(jrbtnAdd.isSelected()){
			jtfPid.setEnabled(true);
			jtfPname.setEnabled(true);
			jtfPrice.setEnabled(true);
			jtfAmount.setEnabled(true);
			jtfInformation.setEnabled(true);
			jbtnSubmit.setEnabled(true);
			
			jtaDisplay.setText("Enter the information of new product ...\n");
			jtaDisplay.append("--------------------------------------------------------------------\n");
			mSelectedAction = ACTION_ADD;
		}  
		
		if(jrbtnUpdate.isSelected()){
			jtfPid.setEnabled(true);
			jtfPname.setEnabled(false);
			jtfPrice.setEnabled(false);
			jtfAmount.setEnabled(false);
			jtfInformation.setEnabled(false);
			jbtnSubmit.setEnabled(true);
			
			jtaDisplay.setText(" Current in stock : \n");
			jtaDisplay.append("--------------------------------------------------------------------\n");
			jtaDisplay.append(stock.list());
			mSelectedAction = ACTION_UPDATE;
		}
		
		if(jrbtnDelete.isSelected()){
			jtfPid.setEnabled(true);
			jtfPname.setEnabled(false);
			jtfPrice.setEnabled(false);
			jtfAmount.setEnabled(false);
			jtfInformation.setEnabled(false);
			jbtnSubmit.setEnabled(true);
			
			jtaDisplay.setText(" Enter the PID you want to delete ... \n");
			jtaDisplay.append("--------------------------------------------------------------------\n");
			mSelectedAction = ACTION_DELETE;
		}
		
		if(jrbtnShow.isSelected()){
			jtfPid.setEnabled(false);
			jtfPname.setEnabled(false);
			jtfPrice.setEnabled(false);
			jtfAmount.setEnabled(false);
			jtfInformation.setEnabled(false);
			jbtnSubmit.setEnabled(false);
			
			jtaDisplay.setText(" Current in stock : \n");
			jtaDisplay.append("--------------------------------------------------------------------\n");
			jtaDisplay.append(stock.list());
		}
		
		if(jrbtnExit.isSelected()){
			jtfPid.setEnabled(false);
			jtfPname.setEnabled(false);
			jtfPrice.setEnabled(false);
			jtfAmount.setEnabled(false);
			jtfInformation.setEnabled(false);
			jbtnSubmit.setEnabled(true);
			
			jtaDisplay.setText(" Will you leave our mall? \n If yes, please press \"Submit\"!");
			mSelectedAction = ACTION_EXIT;
		}
		
		
		final String action = e.getActionCommand();   
		if("Submit".equals(action)){
			switch(mSelectedAction){
			case ACTION_LOGIN_PROCESS:
				loginProcess();
				break;
			case ACTION_ADD:
				add();
				break;
			case ACTION_UPDATE:
				update();
				break;
			case ACTION_DELETE:
				delete();
				break;
			case ACTION_EXIT:
				exit();
				break;
			}
		}
	}
	private void exit() {
		dispose();
	}
	
	private void delete() {
		mPid = jtfPid.getText().trim();
		if(!stock.checkItem(mPid)){
			jtaDisplay.append("\n No such item!");
			return;
		}
		stock.delete(mPid);
		jtaDisplay.append("\nItem# "+mPid+ " has been removed!\n");
	}
	
	private void update() {
		mPid = jtfPid.getText().trim();
		if(!stock.checkItem(mPid)){
			jtaDisplay.append("Wrong PID!");
		}else{
			jtfPid.setEnabled(false);
			jtfPname.setEnabled(true);
			jtfPrice.setEnabled(true);
			jtfAmount.setEnabled(true);
			jtfInformation.setEnabled(true);
			jtaDisplay.setText("Please udpate new information");
			mPname = jtfPname.getText(); 
			mPrice = jtfPrice.getText().length()==0?-1:Double.parseDouble(jtfPrice.getText());
			mAmount = jtfAmount.getText().length()==0?-1:Integer.parseInt(jtfAmount.getText());
			mInformation = jtfInformation.getText();
			if(mPname ==null || mPrice == -1 || mAmount == -1 || mInformation ==null) return; 
			reSetName(mPid, mPname);
			reSetPrice(mPid, mPrice);
			reSetAmount(mPid, mAmount);
			reSetInformation(mPid, mInformation);
			jtaDisplay.append("\n\nitem# "+mPid+ " has been updated!\n");
		}
	}
	
	private void add() {
		mPid = jtfPid.getText().trim();
		mPname = jtfPname.getText(); 
		mPrice = Double.parseDouble(jtfPrice.getText());
		mAmount = Integer.parseInt(jtfAmount.getText());
		mInformation = jtfInformation.getText();
		stock.add(mPid, mPname, mPrice, mAmount, mInformation);
		jtaDisplay.append("\n\nitem# "+mPid+ " has been added!\n");
	}
	
	private void loginProcess(){
		if(mLoginCount < 3){
			mManagerName = jtfUser.getText().trim();
			mManagerPassword = jtfPwd.getText().trim();
			if(mManagerName.equals(defaultManagerName) && mManagerPassword.equals(defaultManagerPassword)){
				jrbtnAdd.setEnabled(true);
				jrbtnUpdate.setEnabled(true);
				jrbtnDelete.setEnabled(true);
				jrbtnShow.setEnabled(true);
				jrbtnExit.setEnabled(true);
				jbtnSubmit.setEnabled(true);
				jtaDisplay.append("Login successfully!!");
				jtfUser.setEnabled(false);
				jtfPwd.setEnabled(false);
				
			}else{
				jtaDisplay.append("\nError: You still have "+(2-mLoginCount) +" chances!");
				mLoginCount++;
			}
		}else{
			jtaDisplay.append("You have no permission!");
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
