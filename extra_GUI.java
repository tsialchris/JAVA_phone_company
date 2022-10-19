import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class extra_GUI extends JFrame implements ActionListener{
	
	private JButton create_contract;
	private JTextArea info;
	private String Deal;
	public ArrayList<contract> contract_list;
	public ArrayList<services> services_list;
	public ArrayList<services> temp_services_list;
	private int bridge;
	
	public extra_GUI(String Deal, int bridge, ArrayList<contract> contract_list, ArrayList<services> services_list, ArrayList<services> temp_services_list){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,400);
		
		this.Deal = Deal;
		this.bridge = bridge;
		this.contract_list = contract_list;
		this.services_list = services_list;
		this.temp_services_list = temp_services_list;
		drawFrame();
		
		create_contract.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == create_contract){
			String name = null;
			String phone_number = null;
			String date_of_activation = null;
			String payment_method = null;
			double discount = -1;
			int deal_num = -1;
			
			name = (String) (JOptionPane.showInputDialog(this, "Now please give a name"));
			phone_number = (String) (JOptionPane.showInputDialog(this, "Now please give a phone number"));
			date_of_activation = (String) (JOptionPane.showInputDialog(this, "Now please give the date of activation", "7/6/2017"));
			payment_method = (String) (JOptionPane.showInputDialog(this, "Now please give the payment method (cash/credit)"));
			discount = Double.parseDouble(JOptionPane.showInputDialog(this, "Now please give the discount you wish to offer:"));
			
			if(Deal == "Deal1"){
				deal_num = 1;
			}
			else if(Deal == "Deal2"){
				deal_num =2;
			}
			else if(Deal == "Deal3"){
				deal_num =3;
			}
			else if(Deal == "Deal4"){
				deal_num =4;
			}
			else if(Deal == "Deal5"){
				deal_num =5;
			}
			else if(Deal == "Deal6"){
				deal_num =6;
			}
			
			if(name != null && phone_number != null && date_of_activation != null && payment_method != null && discount != -1 && deal_num != -1){
				bridge++;
				contract_list.add(new contract(bridge, deal_num, name, phone_number, date_of_activation, payment_method, discount));
				service_contract(contract_list.get(bridge).get_deal(), services_list, temp_services_list);
				JOptionPane.showMessageDialog(null, "You have successfully created a contract");
			}
			else{
				JOptionPane.showMessageDialog(null, "Oops, something went wrong during contract creation!");
			}
		}
	}
	public ArrayList<contract> get_contract_list(){
		return this.contract_list;
	}
	public ArrayList<services> get_services_list(){
		return this.services_list;
	}
	public int get_bridge(){
		return this.bridge;
	}
	
	private void drawFrame(){
		
		Container cp = getContentPane();
		
		cp.setLayout(new BorderLayout());
		
		info = new JTextArea();
		create_contract = new JButton("create contract");
		cp.add(create_contract, BorderLayout.LINE_START);
		
		if(Deal == "Deal1"){
			info.setText("Deal1: 10$ for 2000MB and 2$ for each additional 500MB");
		}
		else if(Deal == "Deal2"){
			info.setText("Deal2: 20$ for 5000MB and 1$ for each additional 500MB");
		}
		else if(Deal == "Deal3"){
			info.setText("Deal3: 10$ for 100 mins of free time \n100 SMS and 2$ for each 50 additional mins \n2$ for each 50 additional SMS");
		}
		else if(Deal == "Deal4"){
			info.setText("Deal4: 20$ for 200 mins of free time \n200 SMS and 1$ for each 50 additional mins \n1$ for each 50 additional SMS");
		}
		else if(Deal == "Deal5"){
			info.setText("Deal5: 20$ Deposit for 200 mins of free time \n200 SMS and 4$ for each 50 additional mins \n4$ for each 50 additional SMS");
		}
		else if(Deal == "Deal6"){
			info.setText("Deal6: 40$ Deposit for 400 mins of free time \n 400 SMS and 2$ for each 50 additional mins \n2$ for each 50 additional SMS");
		}
		info.setFont(new Font("Serif",Font. ITALIC, 18));
		cp.add(info, BorderLayout.CENTER);
		pack();
	}
	public static void service_contract(int check, ArrayList<services> services_list, ArrayList<services> temp_services_list){
		if(check == 1){
			String t1;
			double t2,t3,t4;
			
			t1 = temp_services_list.get(0).check_service();
			t2 = temp_services_list.get(0).check_monthly_payment();
			t3 = temp_services_list.get(0).check_volume();
			t4 = temp_services_list.get(0).check_extra_volume();
			t4 = temp_services_list.get(0).check_extra_volume();
			
			services_list.add(new internet_service(t1, t2, t3, t4));
		}
		else if(check == 2){
			String t1;
			double t2,t3,t4;
			
			t1 = temp_services_list.get(2).check_service();
			t2 = temp_services_list.get(2).check_monthly_payment();
			t3 = temp_services_list.get(2).check_volume();
			t4 = temp_services_list.get(2).check_extra_volume();
			
			services_list.add(new internet_service(t1, t2, t3, t4));
		}
		else if (check == 3){
			String t1;
			double t2,t3,t5;
			int t4,t6;
			
			t1 = temp_services_list.get(4).check_service();
			t2 = temp_services_list.get(4).check_monthly_payment();
			t3 = temp_services_list.get(4).check_time();
			t4 = temp_services_list.get(4).check_SMS();
			t5 = temp_services_list.get(4).check_extra_time();
			t6 = temp_services_list.get(4).check_extra_SMS();
			
			services_list.add(new contract_phone(t1, t2, t3, t4 , t5, t6));
		}
		else if (check == 4){
			String t1;
			double t2,t3,t5;
			int t4,t6;
			
			t1 = temp_services_list.get(6).check_service();
			t2 = temp_services_list.get(6).check_monthly_payment();
			t3 = temp_services_list.get(6).check_time();
			t4 = temp_services_list.get(6).check_SMS();
			t5 = temp_services_list.get(6).check_extra_time();
			t6 = temp_services_list.get(6).check_extra_SMS();
			
			services_list.add(new contract_phone(t1, t2, t3, t4 , t5, t6));
		}
		else if (check == 5){
			String t1;
			double t2,t3,t5,t7;
			int t4,t6;
			
			t1 = temp_services_list.get(8).check_service();
			t2 = temp_services_list.get(8).check_monthly_payment();
			t3 = temp_services_list.get(8).check_time();
			t4 = temp_services_list.get(8).check_SMS();
			t5 = temp_services_list.get(8).check_extra_time();
			t6 = temp_services_list.get(8).check_extra_SMS();
			t7 = temp_services_list.get(8).check_deposit();
			
			services_list.add(new card_phone(t1, t2, t3, t4 , t5, t6, t7));
		}
		else if (check == 6){
			String t1;
			double t2,t3,t5,t7;
			int t4,t6;
			
			t1 = temp_services_list.get(10).check_service();
			t2 = temp_services_list.get(10).check_monthly_payment();
			t3 = temp_services_list.get(10).check_time();
			t4 = temp_services_list.get(10).check_SMS();
			t5 = temp_services_list.get(10).check_extra_time();
			t6 = temp_services_list.get(10).check_extra_SMS();
			t7 = temp_services_list.get(10).check_deposit();
			
			services_list.add(new card_phone(t1, t2, t3, t4 , t5, t6, t7));
		}
	}
}