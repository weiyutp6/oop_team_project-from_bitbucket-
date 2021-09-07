import connectDB.*;
public class SampleCodeOfAddOrder2 {
    public static void main(String[] args) {
        Orders newOrder = new Orders(2020051501, "RickyLiao", 10, 3, 2, 1, "2020-05-15 13:32:30.000");
        newOrder.addOrder();
    }
}
