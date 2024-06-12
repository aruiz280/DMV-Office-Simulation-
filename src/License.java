//done
public abstract class License {
	
	//private instantiate
	public String licenseType; 
	
	public License(String licenseType) { 
		
		setLicenseType(licenseType); 
		
	}

	//getter and setter
	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	
	
	//3 abstract methods calling
	public abstract void setLicenseID();
	public abstract String getLicenseID(); 
	public abstract double getLicenseCost(); 
	
	

}
