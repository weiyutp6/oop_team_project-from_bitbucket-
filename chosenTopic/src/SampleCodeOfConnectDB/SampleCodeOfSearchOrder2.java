import connectDB.*;

public class SampleCodeOfSearchOrder2 {
    public static void main(String[] args) {
        SearchOrder myOrder = new SearchOrder();

        System.out.println(myOrder.orderContents(2020050404).getMember_id());
        System.out.println(myOrder.orderContents(2020050404).getProduct_code());
        System.out.println(myOrder.orderContents(2020050404).getTitle());
        System.out.println(myOrder.orderContents(2020050404).getProduct_key());
        System.out.println(myOrder.orderContents(2020050404).getStart_date());
        System.out.println(myOrder.orderContents(2020050404).getEnd_date());
        System.out.println(myOrder.orderContents(2020050404).getAdults_qty());
        System.out.println(myOrder.orderContents(2020050404).getKids_qty());
        System.out.println(myOrder.orderContents(2020050404).getBabys_qty());
        System.out.println(myOrder.orderContents(2020050404).getTotal_price());
        System.out.println(myOrder.orderContents(2020050404).getOrder_date());
    }
}