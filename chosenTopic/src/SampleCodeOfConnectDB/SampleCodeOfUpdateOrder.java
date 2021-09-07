import connectDB.*;

public class SampleCodeOfUpdateOrder {
    public static void main(String[] args) {
        Orders.UpdateProductCode(2020050406, 34, "2020-05-24 17:05:20.000");
        Orders.UpdateAdultsQty(2020050406, 3, "2020-05-24 17:05:20.000");
        Orders.UpdateKidsQty(2020050406, 2, "2020-05-24 17:05:20.000");
        Orders.UpdateBabysQty(2020050406, 2, "2020-05-24 17:05:20.000");
        //Orders.updateWholeOrder(2020050406, 100, 2, 2, 1, "2020-05-24 17:05:20.000");
    }
}
