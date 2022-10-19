import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class mainApp{ /*2nd Semester
						Tsialamanis Christodoulos (3160177)
						*/
						
	public static void main(String[] args) {
	   
	   //monthly usage will not be included in the contracts,
	   //instead it is calculated in the service item itself
	   
	   ArrayList<services> services_list = new ArrayList<services>();
	   ArrayList<services> temp_services_list = new ArrayList<services>();
	   ArrayList<contract> contract_list = new ArrayList<contract>();
	   
	   int bridge = 11;//this will act as a link between contracts and the deals chosen and as a counter for the contracts
	   //3RD STAGE
	   
	   
	   GUI first = new GUI(bridge, contract_list, services_list, temp_services_list);
	   first.setVisible(true);
	   
	   //3RD STAGE
	}
	public static double cost_calculation(ArrayList<contract> contract_list, ArrayList<services> services_list,int con_num){
		
		double cost = 0;
		
		if(contract_list.get(con_num).get_deal() == 1){//DEAL 1 COST
			cost = 10;
			
			if(services_list.get(con_num).check_extra_volume() > 0){
				double mod_cost;
				
				mod_cost = services_list.get(con_num).check_extra_volume() % 500;
				cost = cost + services_list.get(con_num).check_extra_volume() / 500;
				if(mod_cost != 0){
					cost = cost + 2;
				}
			}
			
			cost = cost - cost * 0.3;//general discount
			cost = cost - cost * contract_list.get(con_num).get_discount();//special discount	
		}
		else if(contract_list.get(con_num).get_deal() == 2){//DEAL 2 COST
			cost = 20;
			
			if(services_list.get(con_num).check_extra_volume() > 0){
				double mod_cost;
				
				mod_cost = services_list.get(con_num).check_extra_volume() % 500;
				cost = cost + services_list.get(con_num).check_extra_volume() / 500;
				if(mod_cost != 0){
					cost = cost + 1;
				}
			}
			
			cost = cost - cost * 0.3;//general discount
			cost = cost - cost * contract_list.get(con_num).get_discount();//special discount
		}
		else if(contract_list.get(con_num).get_deal() == 3){//DEAL 3 COST
			cost = 10;
			
			if(services_list.get(con_num).check_extra_time() > 0){
				double mod_cost;
				
				mod_cost = services_list.get(con_num).check_extra_time() % 50;
				cost = cost + services_list.get(con_num).check_extra_time() / 50;
				if(mod_cost != 0){
					cost = cost + 2;
				}
			}
			
			if(services_list.get(con_num).check_extra_SMS() > 0){
				double mod_cost;
				
				mod_cost = services_list.get(con_num).check_extra_SMS() % 50;
				cost = cost + services_list.get(con_num).check_extra_SMS() / 50;
				if(mod_cost != 0){
					cost = cost + 2;
				}
			}
			
			cost = cost - cost * 0.2;//general discount
			cost = cost - cost * contract_list.get(con_num).get_discount();//special discount
		}
		else if(contract_list.get(con_num).get_deal() == 4){//DEAL 4 COST
			cost = 20;
			
			if(services_list.get(con_num).check_extra_time() > 0){
				double mod_cost;
				
				mod_cost = services_list.get(con_num).check_extra_time() % 50;
				cost = cost + services_list.get(con_num).check_extra_time() / 50;
				if(mod_cost != 0){
					cost = cost + 1;
				}
			}
			
			if(services_list.get(con_num).check_extra_SMS() > 0){
				double mod_cost;
				
				mod_cost = services_list.get(con_num).check_extra_SMS() % 50;
				cost = cost + services_list.get(con_num).check_extra_SMS() / 50;
				if(mod_cost != 0){
					cost = cost + 1;
				}
			}
			
			cost = cost - cost * 0.2;//general discount
			cost = cost - cost * contract_list.get(con_num).get_discount();//special discount
		}
		else if(contract_list.get(con_num).get_deal() == 5){//DEAL 5 COST
			
			double x1 = services_list.get(con_num).check_time() + services_list.get(con_num).check_extra_time();
			int x2 = services_list.get(con_num).check_SMS() + services_list.get(con_num).check_extra_SMS();
			
			cost = services_list.get(con_num).modify_phone(x1, x2, contract_list.get(con_num).get_discount());//calling the "modify" function in order to check the deposit
		}
		else if(contract_list.get(con_num).get_deal() == 6){//DEAL 6 COST
			
			double x1 = services_list.get(con_num).check_time() + services_list.get(con_num).check_extra_time();
			int x2 = services_list.get(con_num).check_SMS() + services_list.get(con_num).check_extra_SMS();
			
			cost = services_list.get(con_num).modify_phone(x1, x2, contract_list.get(con_num).get_discount());//discount is already included for card_phones
		}
		
		return cost;
	}
	
	
	public static ArrayList<services> loadfile_services (ArrayList<services> services_list){
		File f = null;
		BufferedReader reader = null;
		String line;

		try{
			f = new File("services.txt");
		} 
		catch(NullPointerException e){
			System.err.println("File not found.");
		}

		try{
			reader = new BufferedReader(new FileReader(f));
		} 
		catch(FileNotFoundException e){
			System.err.println("Error opening file!");
		}
		
		int counter = 0;
		
		try{
			line = reader.readLine();
			
			boolean true1 = false;
			boolean true2 = false;
			boolean true3 = false;
			boolean true4 = false;
			
			boolean detector1 = false;//these varriables will be used to detect what kind of service is needed(aka internet, card , etc)
			boolean detector2 = false;
			boolean detector3 = false;
			
			String temp_service_name = "-1";//mandatory fields
			String temp_type = "-1";
			double temp_monthly_price = -1;
			
			double temp_free_time = -1;
			double temp_extra_time = -1;
			double temp_deposit = -1;
			double temp_free_volume = -1;
			double temp_extra_volume = -1;
			
			int temp_free_SMS = -1;
			int temp_extra_SMS = -1;
			
			while(line != null){
			
				if(line.trim().equalsIgnoreCase("SERVICES_LIST") || true1){
						
					if(true1 == false){
						line = reader.readLine();
					}
					
					true1 = true;
				
					if(line.trim().equalsIgnoreCase("{") || true2){
						
						if(true2 == false){
							line = reader.readLine();
						}
					
						true2 = true;
						
						if(line.trim().equalsIgnoreCase("SERVICE") || true3){
						
							if(true3 == false){
								line = reader.readLine();
							}
							
							true3 = true;
							
							if(line.trim().equalsIgnoreCase("{") || true4){
								
								if(true4 == false){
									line = reader.readLine();
								}
								
								true4 = true;
							
								line.toUpperCase();
							
								if(line.trim().startsWith("SERVICE_NAME")){
								
									temp_service_name = line.trim().substring(12).trim();
								
								}
								else if(line.trim().startsWith("TYPE")){
								
									temp_type = line.trim().substring(4).trim();
								
									if(line.trim().substring(4).trim().equalsIgnoreCase("INTERNET")){
										detector1 = true;
									}
									else if(line.trim().substring(4).trim().equalsIgnoreCase("CONTRACT")){
										detector2 = true;
									}
									else if(line.trim().substring(4).trim().equalsIgnoreCase("CARD")){
										detector3 = true;
									}
								}
								else if(line.trim().startsWith("MONTHLY_PRICE")){
								
									temp_monthly_price = Double.parseDouble(line.trim().substring(13).trim());
									
								}
								else if(line.trim().startsWith("FREE_VOLUME")){
								
									temp_free_volume = Double.parseDouble(line.trim().substring(11).trim());
									
								}
								else if(line.trim().startsWith("EXTRA_VOLUME")){
								
									temp_extra_volume = Double.parseDouble(line.trim().substring(12).trim());
									
								}
								else if(line.trim().startsWith("FREE_MINUTES")){
								
									temp_free_time = Double.parseDouble(line.trim().substring(12).trim());
									
								}
								else if(line.trim().startsWith("FREE_SMS")){
								
									temp_free_SMS = Integer.parseInt(line.trim().substring(8).trim());
									
								}
								else if(line.trim().startsWith("EXTRA_MINUTES")){
								
									temp_extra_time = Double.parseDouble(line.trim().substring(13).trim());
								
								}
								else if(line.trim().startsWith("EXTRA_SMS")){
								
									temp_extra_SMS = Integer.parseInt(line.trim().substring(9).trim());
									
								}
								else if(line.trim().startsWith("DEPOSIT")){
								
									temp_deposit = Double.parseDouble(line.trim().substring(7).trim());
									
								}
							}
							else if(line.trim() != "}"){
								System.out.println("{ WAS NOT FOUND, MOVING TO NEXT LINE ");
							}
						}
						else if(line.trim() != "}"){
							System.out.println("SERVICE WAS NOT FOUND, MOVING TO NEXT LINE ");
						}
					}
					else if(line.trim() != "}"){
						System.out.println("{ WAS NOT FOUND, MOVING TO NEXT LINE ");
					}
				}
				else if(line.trim() != "}"){
					System.out.println("SERVICES_LIST WAS NOT FOUND, MOVING TO NEXT LINE ");
				}
				
				counter++;
			
				if(line.trim().equals("}") && temp_service_name != "-1" && temp_type != "-1" && temp_monthly_price != -1){
					
					if(detector1){
						services_list.add(new internet_service(temp_service_name, temp_monthly_price, temp_free_volume, temp_extra_volume));
					}
					else if(detector2){
						services_list.add(new contract_phone(temp_service_name, temp_monthly_price, temp_free_time, temp_free_SMS, temp_extra_time, temp_extra_SMS));
					}
					else if(detector3){
						services_list.add(new card_phone(temp_service_name, temp_monthly_price, temp_free_time, temp_free_SMS, temp_extra_time, temp_extra_SMS, temp_deposit));
					}
					
					true3 = false;
					true4 = false;
					detector1 = false;
					detector2 = false;
					detector3 = false;
					
					temp_service_name = "-1";
					temp_type = "-1";
					temp_monthly_price = -1;
					
					temp_free_time = -1;
					temp_extra_time = -1;
					temp_deposit = -1;
					temp_free_volume = -1;
					temp_extra_volume = -1;
					
				}
				else if(line.equalsIgnoreCase("}")){
					System.out.println("finished reading file");
				}
				else if(line.trim().equalsIgnoreCase("}")){
					System.out.println("Error, one of the mandatory fields is not present, moving to the next service");
				}
				
				line = reader.readLine();
				
			}
		}
		catch (IOException e){
			System.out.println("Error reading line " + counter + ".");
		}
		
		try {
			reader.close();
		} 
		catch (IOException e){
			System.err.println("Error closing file.");
		}
		
		return services_list;
		
	}
	public static ArrayList<contract> loadfile_contract (ArrayList<contract> contract_list, ArrayList<services> services_list){
		File f = null;
		BufferedReader reader = null;
		String line;

		try{
			f = new File("contracts.txt");
		} 
		catch(NullPointerException e){
			System.err.println("File not found.");
		}

		try{
			reader = new BufferedReader(new FileReader(f));
		} 
		catch(FileNotFoundException e){
			System.err.println("Error opening file!");
		}
		
		int counter = 0;
		
		try{
			line = reader.readLine();
			
			boolean true1 = false;
			boolean true2 = false;
			boolean true3 = false;
			boolean true4 = false;
			boolean true5 = false;
			boolean true6 = false;
			boolean true7 = false;
			boolean true8 = false;
			boolean true9 = false;
			
			int temp_service_name = -1;//mandatory fields
			String temp_type = "-1"; //mandatory field
			String temp_customer = "-1"; //mandatory field
			String temp_phone_number = "-1"; //mandatory field
			String temp_activation_date = "-1"; //mandatory field
			String temp_payment_method = "-1"; 
			int temp_contract_number = -1; //mandatory field
			double temp_discount = -1;
			double tmp_volume = -1;
			double tmp_time = -1;
			int tmp_SMS = -1;
			
			while(line != null){
			
				if(line.trim().equalsIgnoreCase("CONTRACTS_LIST") || true1){
						
					if(true1 == false){
						line = reader.readLine();
					}
					
					true1 = true;
				
					if(line.trim().equalsIgnoreCase("{") || true2){
						
						if(true2 == false){
							line = reader.readLine();
						}
					
						true2 = true;
						
						if(line.trim().equalsIgnoreCase("CONTRACT") || true3){
						
							if(true3 == false){
								line = reader.readLine();
							}
							
							true3 = true;
							
							if(line.trim().equalsIgnoreCase("{") || true4){
								
								if(true4 == false){
									line = reader.readLine();
								}
								
								true4 = true;
							
								line.toUpperCase();
							
								if(line.trim().startsWith("CONTRACT_NUMBER")){
								
									temp_contract_number = Integer.parseInt(line.trim().substring(15).trim());
								
								}
								else if(line.trim().startsWith("TYPE")){
								
									temp_type = line.trim().substring(4).trim();
									
								}
								else if(line.trim().startsWith("SERVICE_NAME")){//deal
								
									temp_service_name = Integer.parseInt(line.trim().substring(12).trim());
									
								}
								else if(line.trim().startsWith("CUSTOMER")){
								
									temp_customer = (line.trim().substring(8).trim());
									
								}
								else if(line.trim().startsWith("PHONE_NUMBER")){
								
									temp_phone_number = (line.trim().substring(12).trim());
									
								}
								else if(line.trim().startsWith("ACTIVATION_DATE")){
								
									temp_activation_date = (line.trim().substring(15).trim());
									
								}
								else if(line.trim().startsWith("PAYMENT_METHOD")){
								
									temp_payment_method = (line.trim().substring(14).trim());
									
								}
								else if(line.trim().startsWith("DISCOUNT")){
								
									temp_discount = Double.parseDouble(line.trim().substring(8).trim());
								
								}
								else if(line.trim().startsWith("MONTHLY_USAGE") || true5){
									
									if(true5 == false){
										line = reader.readLine();
									}
									
									true5 = true;
									
									if(line.trim().startsWith("[") || true6){
										
										if(true6 == false){
											line = reader.readLine();
										}
										
										true6 = true;
										
										if(line.trim().startsWith("TIME")){
											true7 = true;
											tmp_time = Double.parseDouble(line.trim().substring(5).trim());
											
										}
										else if(line.trim().startsWith("SMS")){
											true8 = true;
											tmp_SMS = Integer.parseInt(line.trim().substring(4).trim());
											
										}
										else if(line.trim().startsWith("VOLUME")){
											true9 = true;
											tmp_volume = Double.parseDouble(line.trim().substring(6).trim());
										}
									}
								}
							}
							else if(line.trim() != "}"){
								System.out.println("{ WAS NOT FOUND, MOVING TO NEXT LINE ");
							}
						}
						else if(line.trim() != "}"){
							System.out.println("CONTRACT WAS NOT FOUND, MOVING TO NEXT LINE ");
						}
					}
					else if(line.trim() != "}"){
						System.out.println("{ WAS NOT FOUND, MOVING TO NEXT LINE ");
					}
				}
				else if(line.trim() != "}"){
					System.out.println("CONTRACTS_LIST WAS NOT FOUND, MOVING TO NEXT LINE ");
				}
			
				if(line.trim().equals("}") && temp_service_name != -1 && temp_type != "-1" && temp_contract_number != -1 && temp_customer != "-1" && temp_phone_number != "-1" && temp_activation_date != "-1"){
					
					contract_list.add(new contract(temp_contract_number,temp_service_name,temp_customer,temp_phone_number,temp_activation_date,temp_payment_method,temp_discount));
					
					if(true5 == true && true6 == true && true7 == true && true8 == true){
						services_list.get(temp_contract_number).modify_phone(tmp_time,tmp_SMS,temp_discount);
						
						tmp_time = -1;
						tmp_SMS = -1;
						
						true5 = false;
						true6 = false;
						true7 = false;
						true8 = false;
					}
					else if(true5 == true && true6 == true && true9 == true){
						services_list.get(temp_contract_number).modify_internet(tmp_volume);
						
						tmp_volume = -1;
						
						true5 = false;
						true6 = false;
						true9 = false;
					}
					else{
						System.out.println("No usage data has been given for contract " + temp_contract_number + ",initial usage set to 0");
					}
					true3 = false;
					true4 = false;
					true5 = false;
					true6 = false;
					true7 = false;
					true8 = false;
					true9 = false;
					
					tmp_volume = -1;
					tmp_time = -1;
					tmp_SMS = -1;
					
					temp_service_name = -1;
					temp_type = "-1";
					temp_customer = "-1"; 
					temp_phone_number = "-1"; 
					temp_activation_date = "-1"; 
					temp_contract_number = -1; 
					
					temp_payment_method = "-1"; 
					temp_discount = -1;
					
				}
				else if(line.equalsIgnoreCase("}")){
					System.out.println("finished reading file");
				}
				else if(line.trim().equalsIgnoreCase("}")){
					System.out.println("Error, one of the mandatory fields is not present, moving to the next contract");
				}
				
				counter++;
				line = reader.readLine();
				
			} 
		}
		catch (IOException e){
			System.out.println("Error reading line " + counter + ".");
		}
		
		try {
			reader.close();
		} 
		catch (IOException e){
			System.err.println("Error closing file.");
		}
		
		return contract_list;
	}
	public static void createFile(String path, ArrayList<services> services_list, ArrayList<contract> contract_list, int bridge, String detector){

		File f = null;
		BufferedWriter writer = null;

		try	{
			f = new File(path);
		}
		catch (NullPointerException e) {//path error
			System.err.println ("Error creating file!");
		}

		try	{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
		}
		catch (FileNotFoundException e) {//access rights error
			System.err.println("Error opening file for writing!");
		}
		
		try	{
			
			if(detector == "services"){
				writer.write("SERVICES_LIST" + "\n" + "{");
			}
			else if(detector == "contract"){
				writer.write("CONTRACTS_LIST" + "\n" + "{");
			}
			
			for (int i = 0; i < bridge+1; i++){
				if(detector == "services"){
					
					writer.write("\n" + "\t" + "SERVICE" + "\n" + "\t" + "{");
					
					if(services_list.get(i) instanceof internet_service){
						writer.write(
						"\n" + "\t" + "\t" + "SERVICE_NAME" + "\t" + services_list.get(i).check_service() +
						"\n" + "\t" + "\t" + "TYPE" + "\t" + "INTERNET" +
						"\n" + "\t" + "\t" + "MONTHLY_PRICE" + "\t" + services_list.get(i).check_monthly_payment() +
						"\n" + "\t" + "\t" + "FREE_VOLUME" + "\t" + services_list.get(i).check_volume() +
						"\n" + "\t" + "\t" + "EXTRA_VOLUME" + "\t" + services_list.get(i).check_extra_volume() +
						"\n" + "\t" + "}");
					}
					else if(services_list.get(i) instanceof contract_phone){
						writer.write(
						"\n" + "\t" + "\t" + "SERVICE_NAME" + "\t" + services_list.get(i).check_service() +
						"\n" + "\t" + "\t" + "TYPE" + "\t" + "CONTRACT" +
						"\n" + "\t" + "\t" + "MONTHLY_PRICE" + "\t" + services_list.get(i).check_monthly_payment() +
						"\n" + "\t" + "\t" + "FREE_MINUTES" + "\t" + services_list.get(i).check_time() +
						"\n" + "\t" + "\t" + "FREE_SMS" + "\t" + services_list.get(i).check_SMS() +
						"\n" + "\t" + "\t" + "EXTRA_MINUTES" + "\t" + services_list.get(i).check_extra_time() +
						"\n" + "\t" + "\t" + "EXTRA_SMS" + "\t" + services_list.get(i).check_extra_SMS() +
						"\n" + "\t" + "}");
					}
					else if(services_list.get(i) instanceof card_phone){
						writer.write(
						"\n" + "\t" + "\t" + "SERVICE_NAME" + "\t" + services_list.get(i).check_service() +
						"\n" + "\t" + "\t" + "TYPE" + "\t" + "CARD" +
						"\n" + "\t" + "\t" + "MONTHLY_PRICE" + "\t" + services_list.get(i).check_monthly_payment() +
						"\n" + "\t" + "\t" + "FREE_MINUTES" + "\t" + services_list.get(i).check_time() +
						"\n" + "\t" + "\t" + "FREE_SMS" + "\t" + services_list.get(i).check_SMS() +
						"\n" + "\t" + "\t" + "EXTRA_MINUTES" + "\t" + services_list.get(i).check_extra_time() +
						"\n" + "\t" + "\t" + "EXTRA_SMS" + "\t" + services_list.get(i).check_extra_SMS() +
						"\n" + "\t" + "\t" + "DEPOSIT" + "\t" + services_list.get(i).check_deposit() +
						"\n" + "\t" + "}");
					}
				}
				else if(detector == "contract"){
					
					String service_check = services_list.get(i).check_service(); //converting Service1 to 1, Service2 to 2,...
					int store_service = 0;
					
					if(service_check == "SERVICE1"){
						store_service = 1;
					}
					else if(service_check == "SERVICE2"){
						store_service = 2;
					}
					else if(service_check == "SERVICE3"){
						store_service = 3;
					}
					else if(service_check == "SERVICE4"){
						store_service = 4;
					}
					else if(service_check == "SERVICE5"){
						store_service = 5;
					}
					else if(service_check == "SERVICE6"){
						store_service = 6;
					}
					
					writer.write(
					"\n" + "\t" + "CONTRACT" +
					"\n" + "\t" + "{" +
					"\n" + "\t" + "CONTRACT_NUMBER" + "\t" + contract_list.get(i).get_contract_number() +
					"\n" + "\t" + "TYPE" + "\t");
					
					if(services_list.get(i) instanceof internet_service){
						writer.write("INTERNET");
					}
					else if(services_list.get(i) instanceof contract_phone){
						writer.write("CONTRACT");
					}
					else{
						writer.write("CARD");
					}
					
					writer.write(
					"\n" + "\t" + "SERVICE_NAME" + "\t" + store_service + 
					"\n" + "\t" + "CUSTOMER" + "\t" + contract_list.get(i).get_name() +
					"\n" + "\t" + "PHONE_NUMBER" + "\t" + contract_list.get(i).get_phone_number() +
					"\n" + "\t" + "ACTIVATION_DATE" + "\t" + contract_list.get(i).get_doa() +
					"\n" + "\t" + "PAYMENT_METHOD" + "\t" + contract_list.get(i).get_payment_method() +
					"\n" + "\t" + "DISCOUNT" + "\t" + contract_list.get(i).get_discount() +
					"\n" + "\t" + "}");
				}
			}
			writer.write("\n" + "}");
		}
		catch(IOException e){
			System.err.println("Write error!");
		}
		try {
			writer.close();
		}
		catch(IOException e){
			System.err.println("Error closing file.");
		}
	}
}