package chosenTopic;

import connectDB.SearchOrder;

import java.util.Calendar;
import java.util.TimeZone;

import static connectDB.Orders.deleteOrder;

public class CancelOrder{
    public static String cancelOrder(String memberID, String order_no){
        int[] memberOrder = SearchOrder.getOrderNos(memberID);
        boolean isLegitOrder = false;
        for(int i = 0 ; i < memberOrder.length ; i++){
            if(memberOrder[i] == Integer.parseInt(order_no)){
                isLegitOrder = true;
                break;
            }
        }
        if(!isLegitOrder){
            return "退訂失敗,此訂位代號不存在";
        }
        Calendar timeOfCancellation = Calendar.getInstance(TimeZone.getTimeZone("GMT + 8"));
        SearchOrder cancel = new SearchOrder();
        String[] cancelStr = cancel.orderContents(Integer.parseInt(order_no)).getStart_date().split("-");
        Calendar.Builder cancelableTime = new Calendar.Builder();
        cancelableTime.setDate(Integer.parseInt(cancelStr[0]),Integer.parseInt(cancelStr[1]),Integer.parseInt(cancelStr[2])-1);
        if(timeOfCancellation.after(cancelableTime.build())){
            return "退訂失敗,需於出發前24小時";
        }
        else {
            deleteOrder(Integer.parseInt(order_no));
            return "退訂成功,已取消您的預訂紀錄";
        }
    }
}