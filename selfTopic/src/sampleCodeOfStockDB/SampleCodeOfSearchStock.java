import stockDB.*;

public class SampleCodeOfSearchStock {
    public static void main(String[] args) {
        //SearchStock stock = new SearchStock("2330");
        Stock searchStock = Stock.searchStockByStockCode("2330");
        System.out.println(searchStock.getStock_code());
        System.out.println(searchStock.getStock_name());
        System.out.println(searchStock.getIndustry());
        System.out.println(searchStock.getCurrent_price());
        System.out.println(searchStock.getBuy_price());
        System.out.println(searchStock.getSale_price());
        System.out.println("----------");
        for (String[] e:Stock.searchStockCodeByStockName("元大")) {
            System.out.println(e[0] + " " + e[1]);
        }
        System.out.println("----------");
        String[][] stock_code = Stock.StockCodeOfIndustry("半導體業");
        for (String[] s : stock_code) {
            System.out.println(s[0] + " " + s[1]);
        }
    }
}
