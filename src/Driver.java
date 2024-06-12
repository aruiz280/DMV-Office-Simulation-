//done
public abstract class Driver implements Comparable<Driver> {
	
	public static int idCounter = 0; 
	private int arrivalTimeAtDMV, clerkStartTime, waitTime, interviewDuration, totalTimeAtDMV; 
	private String driverType; 
	private License license; 
	
	public Driver(int arrivalTime) { 
		setArrivalTimeAtDMV(arrivalTime); 
	
	}

	//simple getXXX methods
	public int getArrivalTimeDMV() {
		return arrivalTimeAtDMV;
	}

	public int getClerkStartTime() {
		return clerkStartTime;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public int getInterviewDuration() {
		return interviewDuration;
	}

	public int getTotalTimeAtDMV() {
		return totalTimeAtDMV;
	}

	public String getDriverType() {
		return driverType;
	}

	public License getLicense() {
		return license;
	}
	
	//setXXX methods
	public void setArrivalTimeAtDMV(int arrivalTime) { 
		this.arrivalTimeAtDMV = arrivalTime; 
	}
	
	public void setInterviewDuration(int interviewDuration) { 
		this.interviewDuration = interviewDuration; 
	}
	
	public void setClerkStartTime(int clerkStartTime) { 
		this.clerkStartTime = clerkStartTime;
		this.waitTime = clerkStartTime - arrivalTimeAtDMV; 
	}
	
	public void setTotalTimeAtDMV(int endTime) { 
		this.totalTimeAtDMV = endTime - arrivalTimeAtDMV; 
	}
	
	public void setLicense(License license) { 
		this.license = license; 
	}
	
	public void setDriverType(String driverType) { 
		this.driverType = driverType; 
	}

	
	public abstract void setDriverID(); 
	public abstract String getDriverID();
	public abstract int compareTo(Driver driver); 
	
	
	
}
