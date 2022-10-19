import java.util.*;
import java.awt.*;
import javax.swing.*;
public class internet_service extends services {
	
	private double volume, extra_volume;	
	
	public internet_service(String service_name, double monthly_payment, double volume, double extra_volume){
		super(service_name, monthly_payment);
		this.volume = volume;	//included volume of data for internet_service
		this.extra_volume = extra_volume;//extra MBs
	}
	
	public void modify_internet(double new_volume){
		if(volume < new_volume){
			double dif = new_volume - volume;
			
			JOptionPane.showMessageDialog(null, "the new volume provided is greater than the amount of free volume left, so it will be charged extra ");
			
			this.volume = 0;
			this.extra_volume = dif;
		}
		else{
			this.volume = this.volume - new_volume;
			this.extra_volume = 0;
		}
	}
	
	public double modify_phone(double t, int j, double k){
		return -1;
	}
	
	public double check_volume(){
		return volume;
	}
	
	public double check_time(){
		return -1;
	}
	
	public  double check_extra_time(){
		return -1;
	}
	
	public int check_extra_SMS(){
		return -1;
	}
	
	public int check_SMS(){
		return -1;
	}
	
	public double check_extra_volume(){
		return extra_volume;
	}
	
	public double check_deposit(){
		return -1;
	}
	
	public String toString(){
		return super.toString() + " volume: " + volume;
	}
}