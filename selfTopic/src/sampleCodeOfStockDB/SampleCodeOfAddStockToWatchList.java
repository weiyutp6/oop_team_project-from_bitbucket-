import stockDB.*;

public class SampleCodeOfAddStockToWatchList {
    public static void main(String[] args) {
        Watchlist stock = new Watchlist("Ricky", "2330", 3, "2020-06-19 19:19:21.000");
        stock.addToWatchList(314.5);
        System.out.println("-------");
        System.out.println(Watchlist.numberOfWatchList("Ricky"));
    }
}
