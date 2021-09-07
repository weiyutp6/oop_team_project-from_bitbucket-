import connectDB.*;
public class SampleCodeOfAddOrder1 {
    public static void main(String[] args) {
        Orders newOrder = new Orders(2020050201, "Ricky1008", 20, "【波蘭、波羅的海三小國、俄羅斯】精彩12 日", "VDR0000001255", "2020-09-03 00:00:00.000", "2020-09-14 00:00:00.000", 4, 1, 0, 80000, "2020-05-02 11:32:30.000");
        newOrder.addOrder();
    }
}
