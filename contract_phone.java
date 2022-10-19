import java.util.*;
import java.awt.*;
import javax.swing.*;

public class contract_phone extends phone_services {
	
	public contract_phone(String service_name, double monthly_payment, double free_time, int free_SMS, double extra_time, int extra_SMS){
		super(service_name, monthly_payment, free_time, free_SMS, extra_time, extra_SMS);
	}
	
	public double check_volume(){
		return -1;
	}
	
	public double check_extra_volume(){
		return -1;
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
	
	public int check_SMS(){
		return this.free_SMS;
	}
	
	public double check_deposit(){
		return -1;
	}
	
	public void modify_internet(double j){
		System.out.println("failure2");
	}
	
	public String toString(){
		return super.toString();
	}
	
	public double modify_phone(double new_time, int new_SMS, double useless){
		double dif_time = new_time - this.free_time;
		int dif_SMS = new_SMS - this.free_SMS;
		
		
		if(free_time < new_time){
			JOptionPane.showMessageDialog(null, "the time provided is greater than the amount of free time left, so it will be charged extra ");
			free_time = 0;
			extra_time = dif_time;
		}
		else{
			this.free_time = free_time - new_time;
			this.extra_time = - dif_time;
		}
		if(free_SMS < new_SMS){
			JOptionPane.showMessageDialog(null, "the SMS provided is greater than the amount of free SMS left, so it will be charged extra ");
			free_SMS = 0;
			extra_SMS = dif_SMS;
		}
		else{
			this.free_SMS = free_SMS - new_SMS;
			this.extra_SMS = - dif_SMS;
		}
		return 0;
	}
}