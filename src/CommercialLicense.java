//done
public class CommercialLicense extends License {
	
	private static int commercialCounter = 0; 
	private String licenseID; 
	private final double commLicenseCost = 125.75;
	
	
	public CommercialLicense() { 
		super("Commercial"); 
		setLicenseID(); 
	}
	
	@Override 
	public void setLicenseID() { 
		commercialCounter++; 
		licenseID = "COMM" + commercialCounter; 
		
	}
	
	@Override
	public String getLicenseID() { 
		return licenseID; 
	}
	
	@Override 
	public double getLicenseCost() { 
		return commLicenseCost;
	}
	
	
	

}
