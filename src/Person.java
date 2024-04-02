
public class Person {
	
	//클래스 변수들
	private String name;
	private int phone;
	private String address;
	
	//클래스 생성자들
	public Person(String name, int phone) {
		this.name = name;
		this.phone = phone;
	}
	
	public Person(String name, int phone, String address) {
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	
	
	// get, set
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
