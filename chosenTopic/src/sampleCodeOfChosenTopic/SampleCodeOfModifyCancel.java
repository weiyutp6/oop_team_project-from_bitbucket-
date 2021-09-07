import chosenTopic.CancelOrder;
//import chosenTopic.ModifyOrder;

public class SampleCodeOfModifyCancel {
    public static void main(String[] args) {
        String memberID = "Bill";
        String order_no = "2020060401";
        String newAdult = "2";
        String newKid = "2";
        String newBaby = "1";
        //System.out.println(ModifyOrder.modifyOrder(memberID,order_no,newAdult,newKid,newBaby));
        System.out.println(CancelOrder.cancelOrder(memberID,order_no));
    }
}
