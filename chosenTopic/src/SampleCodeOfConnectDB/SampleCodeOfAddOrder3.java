import connectDB.*;
public class SampleCodeOfAddOrder3 {
    public static void main(String[] args) {
        Orders newOrder = new Orders(2020050401, "Ricky", "VDR0000001255", "2020-09-03 00:00:00.000", 4, 1, 0, "2020-05-02 11:32:30.000");
        newOrder.addOrder();
    }
}
