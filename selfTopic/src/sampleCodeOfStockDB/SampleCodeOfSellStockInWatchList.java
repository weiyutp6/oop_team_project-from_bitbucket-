import stockDB.*;

public class SampleCodeOfSellStockInWatchList {
    public static void main(String[] args) {
//        Watchlist.sellStockInWatchList("Ricky", 1, 2);
//        System.out.println("Refund=" + Watchlist.getRefund());
        Watchlist.sellStockInWatchList("Ricky", 3, 3, 314.5);
        System.out.println("Refund=" + Watchlist.getRefund());
    }
}
