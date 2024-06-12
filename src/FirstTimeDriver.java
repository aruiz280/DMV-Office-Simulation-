//need compareTo (fixed)//done
public class FirstTimeDriver extends Driver {
	
	private String driverID; 
	
	public FirstTimeDriver(int arrivalTime, License licP) { 
		super(arrivalTime); 
		setDriverType("FirstTime "); 
		setLicense(licP); 
		setDriverID(); 
	}
	
	@Override
	public String getDriverID() { 
		return driverID; 
	}
	
	@Override
	public void setDriverID() { 
		idCounter++; 
		driverID = "CAL " +getDriverType() + " " + getLicense().getLicenseID() + idCounter;
		
	}
	
	////confused hereeeeeeeee
	@Override
    public int compareTo(Driver otherDriver) {
        String otherType = otherDriver.getDriverType();
        if (getDriverType().equals(otherType)) {
            return 0;
        } else if (getDriverType().equals("Senior") && (otherType.equals("Renewing") || otherType.equals("First Time"))) {
            return -1;
        } else if (getDriverType().equals("Renewing") && otherType.equals("First Time")) {
            return -1;
        } else {
            return 1;
        }
    }
}
