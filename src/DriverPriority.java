//done
import java.util.Comparator;

public class DriverPriority implements Comparator<Driver> {

    public int compare(Driver d1, Driver d2) {
        if (d1.getDriverType().equals(d2.getDriverType())) {
            // drivers have same type, compare arrivaltimes
            return d1.getArrivalTimeDMV() - d2.getArrivalTimeDMV();
        } else {
            // Drivers have different types, so compare their types
            int typeComparison = d2.getDriverType().compareTo(d1.getDriverType());
            if (typeComparison != 0) {
                return typeComparison;
            } else {
                //if same time/type compare id
                return d1.getDriverID().compareTo(d2.getDriverID());
            }
        }
    }
}