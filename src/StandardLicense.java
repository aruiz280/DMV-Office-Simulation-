//done
public class StandardLicense extends License {
	
	private static int standardCounter = 0;
	private String licenseID; 
	private final double stdLicenseCost = 32.50; 
	
	public StandardLicense() { 
		super("Standard"); 
		setLicenseID(); 
		
	}
	
	@Override
	public void setLicenseID() { 
		standardCounter++; 
		licenseID = "STD" + standardCounter; 
	}
	
	@Override
	public String getLicenseID() { 
		return licenseID; 
	}
	
	@Override 
	public double getLicenseCost() { 
		return stdLicenseCost; 
	}

}
