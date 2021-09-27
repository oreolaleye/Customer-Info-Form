public class Customer {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String prov;
	private String postal;
	
	public Customer(String fName, String lName, String email, String phone) {
		
		this.firstName = fName;
		this.lastName = lName;
		this.email = email;
		this.phone = phone;
	}

	public Customer(String firstName, String lastName, String email, String phone, String address, String city, String prov, String postal) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.prov = prov;
		this.postal = postal;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}
}
