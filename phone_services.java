public abstract class phone_services extends services {
	
	public double free_time, extra_time;	//included free time to mobile phones and home-phones+free SMS
	public int free_SMS, extra_SMS;	//extra time/SMS
	
	public phone_services(String service_name, double monthly_payment, double free_time, int free_SMS, double extra_time, int extra_SMS){
		super(service_name, monthly_payment);
		this.free_time = free_time;
		this.free_SMS = free_SMS;
		this.extra_time = extra_time;//extra mins
		this.extra_SMS = extra_SMS;//extra SMS
	}

	public String toString(){
		return super.toString() + " free_time: " + free_time + " free_SMS: " + free_SMS + " extra_time: " + extra_time + " extra_SMS: " + extra_SMS;
	}
}