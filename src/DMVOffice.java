//done

import java.io.IOException; 
import java.io.PrintWriter; 
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random; 
import java.util.Iterator; 


public class DMVOffice { 
	
	private Random randy; 
	private PriorityQueue<Driver> waitingQ;
	private static ArrayList<Driver> processedDrivers = new ArrayList<Driver>();
	private static Clerk[] clerks;
	private String officeName; 
	private int currentTime = 0; 
	
	public DMVOffice(String name, int seed, int numClerks) { 
		
		this.officeName = name; 
		waitingQ = new PriorityQueue<Driver>(new DriverPriority());
		randy = new Random(seed);
		createClerks(numClerks);
		
	}
	
	public void createClerks(int numClerks) { 
		clerks = new Clerk[numClerks]; 
		for(int i = 0; i < numClerks; i++) { 
			clerks[i] = new Clerk(); 
		}
	}
	
	public void openDMVOfficeLine() { 
		
		for(currentTime = 0; currentTime < 5; currentTime++) { 
			for(int i = 0; i < 12; i++) { 
				int randNum = randy.nextInt(30) + 1;
				
				if (randNum < 4) {
					waitingQ.add(new RenewingDriver(currentTime, new CommercialLicense()));
				} else if (randNum < 20) {
					waitingQ.add(new RenewingDriver(currentTime, new StandardLicense()));
				} else if (randNum < 21) {
					waitingQ.add(new SeniorDriver(currentTime, new CommercialLicense()));
				} else if (randNum < 24) {
					waitingQ.add(new SeniorDriver(currentTime, new StandardLicense()));
				} else {
					waitingQ.add(new FirstTimeDriver(currentTime, new StandardLicense()));
				}
				
				
				
			}
		}
		currentTime = 5; 
	}
	
	public void operateDMVOffice(int durationForArriving) {
	    int endArrivalsTime = currentTime + durationForArriving;
	    Driver tempDriver;

	    while (processedDrivers.size() != Driver.idCounter || currentTime < endArrivalsTime) {


	    	//driviers arriving until closing time
	        if (currentTime < endArrivalsTime) {
	            for (int i = 0; i < 8; i++) {
	                int randomNum = randy.nextInt(30) + 1;
	                if (randomNum < 4) {
	                    waitingQ.add(new RenewingDriver(currentTime, new CommercialLicense()));
	                } else if (randomNum < 16) {
	                    waitingQ.add(new RenewingDriver(currentTime, new StandardLicense()));
	                } else if (randomNum < 18) {
	                    waitingQ.add(new SeniorDriver(currentTime, new CommercialLicense()));
	                } else if (randomNum < 24) {
	                    waitingQ.add(new SeniorDriver(currentTime, new StandardLicense()));
	                } else {
	                    waitingQ.add(new FirstTimeDriver(currentTime, new StandardLicense()));
	                }
	            }
	        }

	        //check on status for clerk (is busy?)
	        for (Clerk clerk : clerks) {
	            if (!clerk.getisFree()) {
	                clerk.decrementTimeRemainingForInterview();
	                if (clerk.getTimeRemainingForInterview() == 0) {
	                    tempDriver = clerk.getAssignedDriver();
	                    processedDrivers.add(tempDriver);
	                    tempDriver.setTotalTimeAtDMV(currentTime);
	                    clerk.removeAssignedDriver();
	                }
	            }
	        }

	        //send drivers to free clerks
	        for (Clerk clerk : clerks) {
	            if (clerk.getisFree() && !waitingQ.isEmpty()) {
	                tempDriver = waitingQ.remove();
	                tempDriver.setClerkStartTime(currentTime);
	                clerk.setAssignedDriver(tempDriver);
	                int interviewDuration = randy.nextInt(4) + 4;
	                tempDriver.setInterviewDuration(interviewDuration);
	                clerk.setTimeRemainingForInterview(interviewDuration);
	            }
	        }

	        currentTime++;
	    }
	}
	
	public void generateDMVOfficeResults(String fileName) throws IOException{
		
		
		PrintWriter printWrite = new PrintWriter(fileName);
		printWrite.printf("Data for DMV Office %s\n\nSummary Data\n", officeName);
		for(Clerk clerk : clerks) {
			printWrite.printf("%s\n",clerk.toString());
		}
		
		//initialized to 0
		int numSeniors = 0;
		int sumSeniorTime = 0;
		int numAdults = 0;
		int sumAdultTime = 0;
		int numFTD = 0;
		int sumFTDTime = 0;
		//will hold values for calling getMethods when print
		Driver tempDriver;
		
		double averageSeniorWaitlist, averageAdultWait, averageFTDWait, averageTotalWait;
		
		
		
		Iterator<Driver> iter = processedDrivers.iterator();
		while(iter.hasNext()) {
			tempDriver = iter.next();
			if(tempDriver instanceof SeniorDriver) {
				numSeniors++;
				sumSeniorTime = sumSeniorTime + tempDriver.getTotalTimeAtDMV();
			}
			else if(tempDriver instanceof RenewingDriver) {
				numAdults++;
				sumAdultTime = sumAdultTime + tempDriver.getTotalTimeAtDMV();
			}
			else {
				numFTD++;
				sumFTDTime = sumFTDTime + tempDriver.getTotalTimeAtDMV();
			}
		}
		
		averageSeniorWaitlist = (double)sumSeniorTime/numSeniors;
		averageAdultWait = (double)sumAdultTime/numAdults;
		averageFTDWait = (double)sumFTDTime/numFTD;
		averageTotalWait = (double)(sumSeniorTime + sumAdultTime + sumFTDTime)/(processedDrivers.size());
		
		//issues after this point (does not print out needed information... )
	    //writer.printf("Renewing Commercial License: %.2f minutes\n", avgWaitTimeRenewingCommercial / numOfDrivers);
	    //writer.printf("Renewing Standard License: %.2f minutes\n", avgWaitTimeRenewingStandard / numOfDrivers);
	    //writer.printf("Senior Commercial License: %.2f minutes\n", avgWaitTimeSeniorCommercial / numOfDrivers);
	    //writer.printf("Senior Standard License: %.2f minutes\n", avgWaitTimeSeniorStandard / numOfDrivers);
	    //writer.printf("First Time Drivers: %.2f minutes\n", avgWaitTimeFirstTime / numOfDrivers);
	    //writer.printf("\nAVERAGE TOTAL TIME FOR ALL DRIVERS: %.2f minutes\n", totalWaitTime / numOfDrivers);
		
		printWrite.printf("\nThe Average total time in office per driver for %d Senior Drivers is %.2f minutes\n", numSeniors, averageSeniorWaitlist);
		printWrite.printf("The Average total time in office per driver for %d Renewing Drivers is %.2f minutes\n", numAdults, averageAdultWait);
		printWrite.printf("The Average total time in office per driver for %d First Time Drivers is %.2f minutes\n", numFTD, averageFTDWait);
		printWrite.printf("The average total time in office per driver for %d Drivers is %.2f minutes\n\n", processedDrivers.size(), averageTotalWait);
		printWrite.printf("%-20s %20s %20s %20s %20s %20s %20s %20s %20s\n", "DRIVER ID", "DRIVER TYPE", "LICENSE TYPE", "LICENSE ID", "ARRIVAL TIME", "WAIT TIME", "INTERVIEW TIME", "TOTAL TIME", "COST");
		
		
		//attempted without printf, does print but not properly....
				//writer.println("\n");
			    //writer.println("\t\tDRIVER ID" + "\t\t\tDRIVER TYPE" + "\t\tLICENSE TYPE" + "\t\tLICENSE ID" + "\t\tARRIVAL TIME" + "\t\tWAIT TIME" + "\t\tINTERVIEW TIME" + "\t\tTOTAL TIME" + "\t\tCOST");
			    //for (Driver driver : processedDrivers) {
			    //    writer.println(driver.getDriverID() + "\t\t\t" + driver.getDriverType() + "\t\t\t" + driver.getLicense().getLicenseType() + "\t\t\t\t" + driver.getArrivalTimeDMV() + "\t\t\t\t\t" + driver.getWaitTime() + "\t\t\t\t\t" + driver.getInterviewDuration() + "\t\t\t\t" + driver.getTotalTimeAtDMV() + "\t\t\t\t\t\t" + driver.getLicense().getLicenseCost());
			    //}
		
		//using iterator to print and printf
		Iterator<Driver> data = processedDrivers.iterator();
		while(data.hasNext()) {
			tempDriver = data.next();
			printWrite.printf("%-20s %19s %20s %19s %17d %21d %17d %22d \t$%24.2f\n", tempDriver.getDriverID(), tempDriver.getDriverType(), tempDriver.getLicense().getLicenseType(), tempDriver.getLicense().getLicenseID(), tempDriver.getArrivalTimeDMV(), tempDriver.getWaitTime(), tempDriver.getInterviewDuration(), tempDriver.getTotalTimeAtDMV(), tempDriver.getLicense().getLicenseCost());
		}
		printWrite.close();
	}
	
}