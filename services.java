public abstract class services {
	
	public String service_name;
	private double monthly_payment;
	
	public services (String service_name, double monthly_payment){
		this.service_name = service_name;
		this.monthly_payment = monthly_payment;
	}
	
	public void set_service_name(String service_name){
		this.service_name = service_name;
	}
	
	public void set_monthly_payment(double monthly_payment){
		this.monthly_payment = monthly_payment;
	}
	
	public double check_monthly_payment(){
		return monthly_payment;
	}
	
	public String toString(){
		return "service_name: " + service_name + " monthly_payment: " + monthly_payment;
	}
	
	public String check_service(){
		return service_name;
	}
	
	public abstract double check_volume();
	
	public abstract double check_time();
	
	public abstract int check_SMS();
	
	public abstract void modify_internet(double new_volume);
	
	public abstract double modify_phone(double new_time, int new_SMS, double discount);
	
	public abstract double check_extra_volume();
	
	public abstract int check_extra_SMS();
	
	public abstract double check_extra_time();
	
	public abstract double check_deposit();
}