import stockDB.*;

public class SampleCodeOfSearchWatchList {
    public static void main(String[] args) {
        String[] stockCodes = Watchlist.searchStockCodeOfWatchList("Ricky");
        for (String e:stockCodes) {
            System.out.println(e);
        }

        System.out.println("-------");
        Watchlist myStock = Watchlist.searchStockOfWatchList("Ricky", 1);
        System.out.println(myStock.getUsername());
        System.out.println(myStock.getList_no());
        System.out.println(myStock.getStock_code());
        System.out.println(myStock.getStock_name());
        System.out.println(myStock.getIndustry());
        System.out.println(myStock.getLot());
        System.out.println(myStock.getBuy_price());
        System.out.println(myStock.getCurrent_price());
        System.out.println(myStock.getRise_fall());
        System.out.println(myStock.getRise_fall_ratio());
        System.out.println(myStock.getAccum_vol());
        System.out.println(myStock.getReward());
        System.out.println(myStock.getROI());

        System.out.println("-------");

        Watchlist[] watchlist = Watchlist.getAllDetailsInWatchList("Ricky");
        for(Watchlist e : watchlist) {
            System.out.println(e.getUsername());
            System.out.println(e.getList_no());
            System.out.println(e.getStock_code());
            System.out.println(e.getStock_name());
            System.out.println(e.getIndustry());
            System.out.println(e.getLot());
            System.out.println(e.getBuy_price());
            System.out.println(e.getCurrent_price());
            System.out.println(e.getRise_fall());
            System.out.println(e.getRise_fall_ratio());
            System.out.println(e.getAccum_vol());
            System.out.println(e.getReward());
            System.out.println(e.getROI());
            System.out.println(e.getBuy_datetime());
            System.out.println(Watchlist.getRefund());
            System.out.println("--------");
        }
    }
}
