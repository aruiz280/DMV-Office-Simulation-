//done
import java.io.IOException;
import java.util.Scanner;

public class ModelDMVOffice {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        
        //req dmv office name
        System.out.println("Please enter the name of the DMV Office: ");
        String officeName = scan.nextLine();
        
        //req seed for randy
        System.out.println("Please enter a seed value as an int: ");
        int seed = scan.nextInt();
        
        System.out.println("Please enter the number of clerks as an int: ");
        int numClerks = scan.nextInt();
        
        //DMVoffice w/ officename, clerk and seed passed as parameter
        DMVOffice dmvOffice = new DMVOffice(officeName, numClerks, seed);
        // Open DMVOffice line
        dmvOffice.openDMVOfficeLine();
        
        
        System.out.println("Please enter the number of minutes to keep entrance open after the office opens: ");
        int durationForArriving = scan.nextInt();
        scan.nextLine(); 
        
        //DMV operate with durationforarriving
        dmvOffice.operateDMVOffice(durationForArriving);
        
        System.out.println("Please enter the name of the output file for DMV Office results: ");
        String outputFileName = scan.nextLine();

        
        //generate dmvoutputs.txt using generate method in DMVOffice
        dmvOffice.generateDMVOfficeResults(outputFileName);
        
        
        
        
        
        
        System.out.println("DMV Office operation complete. Results have been written to " + outputFileName);
        
        scan.close();
    }
}