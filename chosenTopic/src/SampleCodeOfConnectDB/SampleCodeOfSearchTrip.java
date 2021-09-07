import connectDB.*;

public class SampleCodeOfSearchTrip {
    public static void main(String[] args) {
        AvailableTrips trip1 = new AvailableTrips(20);
        System.out.println(trip1.getProduct_code());
        System.out.println(trip1.getTravel_code());
        System.out.println(trip1.getTitle_code());
        System.out.println(trip1.getTitle());
        System.out.println(trip1.getProduct_key());
        System.out.println(trip1.getPrice());
        System.out.println(trip1.getStart_date());
        System.out.println(trip1.getEnd_date());
        System.out.println(trip1.getLower_bound());
        System.out.println(trip1.getUpper_bound());
        System.out.println(trip1.getRemaining());
        System.out.println("--------");
        AvailableTrips trip2 = new AvailableTrips("VDR0000004620", "2020-10-04");
        System.out.println(trip2.getProduct_code());
        System.out.println(trip2.getTravel_code());
        System.out.println(trip2.getTitle_code());
        System.out.println(trip2.getTitle());
        System.out.println(trip2.getProduct_key());
        System.out.println(trip2.getPrice());
        System.out.println(trip2.getStart_date());
        System.out.println(trip2.getEnd_date());
        System.out.println(trip2.getLower_bound());
        System.out.println(trip2.getUpper_bound());
        System.out.println(trip2.getRemaining());
    }
}