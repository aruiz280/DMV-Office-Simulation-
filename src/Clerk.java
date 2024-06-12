//done
public class Clerk {
	
	private static int clerkCounter = 0; 
	private boolean isFree; 
	private int clerkIDNumber, totalDriversProcessedByClerk, timeRemainingForInterview; 
	private Driver assignedDriver;
	
	public Clerk() { 
		setClerkIDNumber(); 
		setIsFree(true); 
	}
	

	
	public Driver getAssignedDriver() {
		return assignedDriver;
	}


	public boolean getisFree() {
		return isFree;
	}

	public int getClerkIDNumber() {
		return clerkIDNumber;
	}

	public int getTotalDriversProcessedByClerk() {
		return totalDriversProcessedByClerk;
	}

	public int getTimeRemainingForInterview() {
		return timeRemainingForInterview;
	}

	
	//setters
	public void setClerkIDNumber() { 
		clerkCounter++; 
		clerkIDNumber = clerkCounter; 
	}
	
	public void setIsFree(boolean isFree) { 
		this.isFree = isFree; 
	}
	
	public void setAssignedDriver(Driver driver) { 
		this.assignedDriver = driver; 
		setIsFree(false); 
	}
	
	public Driver removeAssignedDriver() { 
		Driver tempDriver = assignedDriver; 
		assignedDriver = null; 
		setIsFree(true); 
		totalDriversProcessedByClerk++;
		return tempDriver;
		
	}
	
	public void setTimeRemainingForInterview(int timeRemainingForInterview) { 
		this.timeRemainingForInterview = timeRemainingForInterview; 
	}
	
	public void decrementTimeRemainingForInterview() { 
		timeRemainingForInterview--; 
	}
	
	public String toString() { 
		return "Clerk" + clerkIDNumber + " processes " + totalDriversProcessedByClerk + " drivers";
	}
	

}
