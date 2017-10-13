package addressfinder;

public class Address {

	private String BusinessName;
	private String AddressLine1;
	private String AddressLine2;
	private String City;
	private String State;
	private String Zip;
	
	public Address() {
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}

	public String getAddressLine1() {
		return AddressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return AddressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		AddressLine2 = addressLine2;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
	}
	
}	
