import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener, MouseListener{
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem load_services, load_contracts, record_usage;
	
	ArrayList<extra_GUI> extra_GUI_list = new ArrayList<extra_GUI>();
	int extra_GUI_counter = -1;
	
	private JButton back_button;
	private JButton refresh_button;
	private JButton cost_button;
	private JButton update_button;
	
	//services_panel
	//lists for the services_panel
	private JList services_jlist;
	private JList deals_list;
	private DefaultListModel services_model;
	private DefaultListModel deals_model;

	JPanel services_panel = new JPanel();
	JPanel contracts_panel = new JPanel();
	//contracts_panel
	//lists for contracts_panel
	private JList contract_jlist;
	private DefaultListModel contracts_model;
	
	private JTextArea contract_details;
	
	JTabbedPane tabbedPane = new JTabbedPane();
	
	private int bridge;
	public ArrayList<contract> contract_list;
	public ArrayList<services> services_list;
	public ArrayList<services> temp_services_list;
	
	public GUI(int bridge, ArrayList<contract> contract_list, ArrayList<services> services_list, ArrayList<services> temp_services_list){
		this.bridge = bridge;
		this.contract_list = contract_list;
		this.services_list = services_list;
		this.temp_services_list = temp_services_list;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600,400);
		drawFrame();
		back_button.addActionListener(this);
		refresh_button.addActionListener(this);
		cost_button.addActionListener(this);
		update_button.addActionListener(this);
		contract_jlist.addMouseListener(this);
		services_jlist.addMouseListener(this);
		deals_list.addMouseListener(this);
		load_contracts.addActionListener(this);
		load_services.addActionListener(this);
		record_usage.addActionListener(this);
		add(tabbedPane);
	}
	
	private void drawFrame(){
		
		//Menu
		menuBar = new JMenuBar();
		
		menu = new JMenu("Settings");
		
		load_services = new JMenuItem("Load Services");
		menu.add(load_services);
		
		load_contracts = new JMenuItem("Load Contracts");
		menu.add(load_contracts);
		
		record_usage = new JMenuItem("Record Usage\n(Write files)");
		menu.add(record_usage);
		
		menuBar.add(menu);
		
		this.setJMenuBar(menuBar);
		//
		//services
		back_button = new JButton("BACK");
		
		services_model = new DefaultListModel();
		
		services_model.addElement("Internet");
		services_model.addElement("Contract Phone");
		services_model.addElement("Card Phone");
		
		services_jlist = new JList(services_model);
		//deals
		deals_model = new DefaultListModel();
		
		deals_list = new JList(deals_model);
		//
		//scroller
		JScrollPane services_scroller = new JScrollPane(services_jlist);
		JScrollPane deals_scroller = new JScrollPane(deals_list);
		//
		services_panel.add(back_button);
		services_panel.add(services_scroller);
		services_panel.add(deals_scroller);
		
		//contracts
		contract_details = new JTextArea("DO NOT TRY TO REFRESH \nWITHOUT ADDING ANY CONTRACTS");
		refresh_button = new JButton("REFRESH");
		cost_button = new JButton("CALCULATE COST");
		update_button = new JButton("CONTRACT UPDATE");
		contracts_model = new DefaultListModel();
		
		contract_jlist = new JList(contracts_model);
		
		JScrollPane contracts_scroller = new JScrollPane(contract_jlist);
		contracts_panel.add(refresh_button);
		contracts_panel.add(contracts_scroller);
		contracts_panel.add(contract_details);
		contracts_panel.add(cost_button);
		contracts_panel.add(update_button);
		cost_button.setVisible(false);
		update_button.setVisible(false);
		
		tabbedPane.add("Services panel",services_panel);
		tabbedPane.add("Contracts panel",contracts_panel);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == back_button){
			services_jlist.setVisible(true);
			deals_model.clear();
		}
		else if(e.getSource() == refresh_button){
			try{
				contracts_model.clear();
				bridge = extra_GUI_list.get(extra_GUI_counter).get_bridge();
				contract_list = extra_GUI_list.get(extra_GUI_counter).get_contract_list();
			
				for(int i=0; i<bridge+1; i++){
					contracts_model.addElement("Contract Number: " + contract_list.get(i).get_contract_number());
				}
				services_list = extra_GUI_list.get(bridge).get_services_list();
			}
			catch(IndexOutOfBoundsException ex){
				contract_details.setText("DO NOT TRY TO REFRESH \nWITHOUT ADDING ANY CONTRACTS");
			}
		}
		else if(e.getSource() == cost_button){
			double cost = mainApp.cost_calculation(contract_list, services_list, contract_list.get(contract_jlist.getSelectedIndex()).get_contract_number());
			if(contract_list.get(contract_jlist.getSelectedIndex()).get_deal() == 5 || contract_list.get(contract_jlist.getSelectedIndex()).get_deal() == 6){
				JOptionPane.showMessageDialog(this, "The amount of money left in the card is: " + cost);
			}
			else{
				JOptionPane.showMessageDialog(this, "The cost of this contract is: " + cost);
			}
		}
		else if(e.getSource() == update_button){
			int temp_con_num = contract_jlist.getSelectedIndex();
			int con_num = contract_list.get(temp_con_num).get_contract_number();
			//same code
			if(contract_list.get(con_num).get_deal() == 1 || contract_list.get(con_num).get_deal() == 2){
				double new_volume = -1;
				
				new_volume =Double.parseDouble(JOptionPane.showInputDialog(this, "This is a phone service contract\nPlease enter how much volume has been used:"));
				
				if(new_volume != -1){
					services_list.get(con_num).modify_internet(new_volume);
					JOptionPane.showMessageDialog(this, "You have successfully updated a contract");
				}
				else{
					JOptionPane.showMessageDialog(this, "Error the varriable needs to be specified and must be !=-1");
				}
				
			}
			else if(contract_list.get(con_num).get_deal() == 3 || contract_list.get(con_num).get_deal() == 4 || contract_list.get(con_num).get_deal() == 5 || contract_list.get(con_num).get_deal() == 6){
				double new_time = -1;
				int new_SMS = -1;
				double y;
				
				new_time = Double.parseDouble(JOptionPane.showInputDialog(this, "This is a phone service contract\nPlease enter how much time has been consumed:"));
				
				new_SMS = Integer.parseInt(JOptionPane.showInputDialog(this,"Please enter how many SMS have been sent:"));
				
				if(new_time != -1 && new_SMS != -1){
					y = services_list.get(con_num).modify_phone(new_time, new_SMS, 0);
					JOptionPane.showMessageDialog(this, "You have successfully updated a contract");
				}
				else{
					JOptionPane.showMessageDialog(this, "Error both varriables need to be specified and must be !=-1");
				}
			}
			//same code
		}
		else if(e.getSource() == load_services){
			//creating the JFileChooser for the services
			final JFileChooser services_chooser = new JFileChooser();
			services_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			services_chooser.setDialogTitle("Services File selection");
			JFrame services_frame = new JFrame();
			
			//with this varriable we will check if the user has selected a file
			int services_return = services_chooser.showOpenDialog(services_frame);
			
			//load selected file
			if(services_return == JFileChooser.APPROVE_OPTION){
				System.out.println("READING SERVICES...");
				services_list = mainApp.loadfile_services(services_list);
				temp_services_list = mainApp.loadfile_services(temp_services_list);
			}
			else{
				JOptionPane.showMessageDialog(this, "Error during file selection");
			}
		}
		else if(e.getSource() == load_contracts){
			//creating the JFileChooser for the contracts
			final JFileChooser contracts_chooser = new JFileChooser();
			contracts_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			contracts_chooser.setDialogTitle("Contracts File selection");
			JFrame contracts_frame = new JFrame();
			
			//with this varriable we will check if the user has selected a file
			int contracts_return = contracts_chooser.showOpenDialog(contracts_frame);
			
			//load selected file
			if(contracts_return == JFileChooser.APPROVE_OPTION){
				System.out.println("READING CONTRACTS...");
				contract_list = mainApp.loadfile_contract(contract_list, services_list);
				
				for(int y=0; y<bridge+1; y++){
					contracts_model.addElement("Contract Number: " + contract_list.get(y).get_contract_number());
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Error during file selection");
			}
		}
		else if(e.getSource() == record_usage){
			System.out.println("Saving Files...");
			mainApp.createFile("new_services.txt", services_list, contract_list, bridge, "services");
			mainApp.createFile("new_contracts.txt", services_list, contract_list, bridge, "contract");
			System.out.println("Files saved successfully");
		}
	}
	
	public void mouseClicked(MouseEvent event){
		int i;
		if(event.getClickCount() == 2){
			if(event.getSource() == services_jlist){
				i = services_jlist.getSelectedIndex();
				if(services_model.get(i) == "Internet"){
					
					deals_model.clear();
					deals_model.addElement("Deal1 10$ free 200MB");
					deals_model.addElement("Deal2 20$ free 500MB");
					
					services_jlist.setVisible(false);
				}
				else if(services_model.get(i) == "Contract Phone"){
					
					deals_model.clear();
					deals_model.addElement("Deal3 10$ free 100mins 100SMS");
					deals_model.addElement("Deal4 20$ free 200mins 200SMS");
					
					services_jlist.setVisible(false);
				}
				else if(services_model.get(i) == "Card Phone"){
					
					deals_model.clear();
					deals_model.addElement("Deal5 20$ free 200mins 200SMS");
					deals_model.addElement("Deal6 40$ free 400mins 400SMS");
					
					services_jlist.setVisible(false);
				}
			}
			else if(event.getSource() == deals_list){
				i = deals_list.getSelectedIndex();
				if(deals_model.get(i) == "Deal1 10$ free 200MB" && i != -1){
					extra_GUI_list.add(new extra_GUI("Deal1", bridge, contract_list, services_list, temp_services_list));
					extra_GUI_counter++;
				}
				else if(deals_model.get(i) == "Deal2 20$ free 500MB" && i != -1){
					extra_GUI_list.add(new extra_GUI("Deal2", bridge, contract_list, services_list, temp_services_list));
					extra_GUI_counter++;
				}
				else if(deals_model.get(i) == "Deal3 10$ free 100mins 100SMS" && i != -1){
					extra_GUI_list.add(new extra_GUI("Deal3", bridge, contract_list, services_list, temp_services_list));
					extra_GUI_counter++;
				}
				else if(deals_model.get(i) == "Deal4 20$ free 200mins 200SMS" && i != -1){
					extra_GUI_list.add(new extra_GUI("Deal4", bridge, contract_list, services_list, temp_services_list));
					extra_GUI_counter++;
				}
				else if(deals_model.get(i) == "Deal5 20$ free 200mins 200SMS" && i != -1){
					extra_GUI_list.add(new extra_GUI("Deal5", bridge, contract_list, services_list, temp_services_list));
					extra_GUI_counter++;
				}
				else if(deals_model.get(i) == "Deal6 40$ free 400mins 400SMS" && i != -1){
					extra_GUI_list.add(new extra_GUI("Deal6", bridge, contract_list, services_list, temp_services_list));
					extra_GUI_counter++;
				}
			}
			else if(event.getSource() == contract_jlist){
				int s = contract_jlist.getSelectedIndex();
				contract_details.setText("Deal: " + contract_list.get(s).get_deal() + "\n" +
										"Payment method: " + contract_list.get(s).get_payment_method() + "\n" +
										"D.O.A.: " + contract_list.get(s).get_doa() + "\n" +
										"Phone number: " + contract_list.get(s).get_phone_number() + "\n" +
										"Name: " + contract_list.get(s).get_name() + "\n" +
										"Contract number: " + contract_list.get(s).get_contract_number() + "\n" +
										"Discount: " + contract_list.get(s).get_discount());
				String temp_details = contract_details.getText();
				
				if(contract_list.get(s).get_deal() == 1 || contract_list.get(s).get_deal() == 2){
					contract_details.setText(temp_details + "\n" +
											"free MBs left: " + services_list.get(s).check_volume());
				}
				else{
					contract_details.setText(temp_details + "\n" +
											"free mins left: " + services_list.get(s).check_time() + "\n" +
											"free SMS left: " + services_list.get(s).check_SMS());
				}
				cost_button.setVisible(true);
				update_button.setVisible(true);
			}
		}
	}
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mousePressed(MouseEvent event){}

}