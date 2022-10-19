import java.util.*;
import java.awt.*;
import javax.swing.*;

public class card_phone extends phone_services {
	
	private double deposit;	//included deposit of the card_phone
	
	public card_phone(String service_name, double monthly_payment, double free_time, int free_SMS, double extra_time, int extra_SMS, double deposit){
		super(service_name, monthly_payment, free_time, free_SMS, extra_time, extra_SMS);
		this.deposit = deposit;
	}
	
	public void set_deposit(){
		this.deposit = deposit;
	}
	
	public void modify_internet(double j){
		System.out.println("failure4");
	}
	
	public double check_extra_volume(){
		return -1;
	}
	
	public double check_volume(){
		return -1;
	}
	
	public int check_SMS(){
		return this.free_SMS;
	}
	
	public double check_time(){
		return this.free_time;
	}
	
	public double check_extra_time(){
		return this.extra_time;
	}
	
	public int check_extra_SMS(){
		return this.extra_SMS;
	}
	
	public double check_deposit(){
		return this.deposit;
	}
	
	public String toString(){
		return super.toString() + " deposit: " + deposit;
	}
	
	public double modify_phone(double new_time, int new_SMS, double discount){
		double dif_time = new_time - this.free_time;
		int dif_SMS = new_SMS - this.free_SMS;
		double x = -1;
		
		while(true){
			if(service_name == "Deal5"){
				deposit = 20;
				
				x = card_control(new_time, new_SMS, dif_time, dif_SMS, 4, discount);
				
			}
			else{
				deposit = 40;
				
				x = card_control(new_time, new_SMS, dif_time, dif_SMS, 2, discount);
				
			}
			if(x > 0){
				break;
			}
		}
		return x;
	}
	
	public double card_control(double new_time, int new_SMS, double dif_time, int dif_SMS, double charge, double discount){
		
		charge = charge - charge*0.3;//GENERAL DISCOUNT FOR CARD PHONES
		double charge1 = charge - charge*discount;//including special contract discount
		
		double deposit_control = deposit - (dif_time/50)*charge1 - (dif_SMS/50)*charge1;
		
		if((free_time < new_time || free_SMS < new_SMS) && deposit_control > 0){
				
			System.out.println("The data provided is greater than the amount of free data left, so it will be charged extra ");
			double storage_time = free_time;
			int storage_SMS = free_SMS;
			
			if(free_time < new_time && free_SMS < new_SMS){
				free_time = 0;
				free_SMS = 0;
				extra_time = dif_time;
				extra_SMS = dif_SMS;
			}
			else if(free_time < new_time){
				free_time = 0;
				extra_time = dif_time;
			}
			else if(free_SMS < new_SMS){
				free_SMS = 0;
				extra_SMS = dif_SMS;
			}
		
			if(extra_time % 50 != 0 && deposit - (charge1) > 0){
				deposit = deposit - charge1;
			}
			else if(extra_time % 50 != 0 && deposit - charge1 < 0){
				JOptionPane.showMessageDialog(null, "Deposit is not enough, charge can not go through for time !");
				free_time = storage_time;
			}
			if(extra_SMS % 50 != 0 && deposit - charge1 > 0){
				deposit = deposit - charge1;
			}
			else if(extra_SMS % 50 != 0 && deposit - charge1 < 0){
				JOptionPane.showMessageDialog(null, "Deposit is not enough, charge can not go through for SMS !");
				free_SMS = storage_SMS;
			}
		}
		else{
			this.free_time = free_time - new_time;
			this.extra_time = - dif_time;
		}
		if(deposit_control < 0){
			JOptionPane.showMessageDialog(null, "The deposit was not enough !");
			Scanner card1 = new Scanner(System.in);
			Scanner card2 = new Scanner(System.in);
			JOptionPane.showMessageDialog(null, "Please give new statistics of use: ");
			new_time = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter how much time has been consumed: "));
			new_SMS = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter how many SMS have been sent: "));
			
			dif_time = new_time - this.free_time;
			dif_SMS = new_SMS - this.free_SMS;
			
			deposit_control = deposit - (dif_time/50)*4 - (dif_SMS/50)*4;
			
			System.out.println(deposit_control);
			return 1;
		}
		else{
			return deposit_control;
		}
	}
}