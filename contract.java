public class contract {
	
	private String name, date_of_activation, payment_method, phone_number;
	private int contract_number, Deal;
	private double discount;
	
	public contract(int contract_number, int Deal, String name, String phone_number, String date_of_activation, String payment_method, double discount){
		this.contract_number = contract_number;
		this.Deal = Deal;
		this.name = name;
		this.phone_number = phone_number;
		this.date_of_activation = date_of_activation;
		this.payment_method = payment_method;
		this.discount = discount;
	}
	public contract(){}
	
	public int get_deal(){
		return this.Deal;
	}
	
	public String get_payment_method(){
		return this.payment_method;
	}
	
	public String get_doa(){
		return this.date_of_activation;
	}
	
	public String get_phone_number(){
		return this.phone_number;
	}
	
	public String get_name(){
		return this.name;
	}
	
	public int get_contract_number(){
		return this.contract_number;
	}
	
	public double get_discount(){
		return this.discount;
	}
	
	public String toString(){
		return "contract_number: " + contract_number + " Deal: Deal" + Deal + " name: " + name + " phone_number: " + phone_number + " date_of_activation: " + date_of_activation + " payment_method: " + payment_method;
	}
}