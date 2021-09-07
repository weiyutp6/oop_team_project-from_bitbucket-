import connectDB.*;

public class SampleCodeOfSearchOrder1 {
    public static void main(String[] args) {
        for(int i=0;i<SearchOrder.getOrderNos("Ricky").length;i++) {
            System.out.println(SearchOrder.getOrderNos("Ricky")[i]);
        }
    }
}
