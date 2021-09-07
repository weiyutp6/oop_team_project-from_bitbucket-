package chosenTopic;

import connectDB.Orders;
import connectDB.SearchOrder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class ModifyOrder{
    public static String modifyOrder(String memberID, String order_no, String newAdult, String newKid, String newBaby){
        int[] memberOrder = SearchOrder.getOrderNos(memberID);
        boolean isLegitOrder = false;
        for(int i = 0 ; i < memberOrder.length ; i++){
            if(memberOrder[i] == Integer.parseInt(order_no)){
                isLegitOrder = true;
                break;
            }
        }
        if(!isLegitOrder){
            return "修改失敗,此訂位代號不存在";
        }
        Calendar timeOfCancellation = Calendar.getInstance(TimeZone.getTimeZone("GMT + 8"));
        SearchOrder order = new SearchOrder();
        String[] cancelStr = order.orderContents(Integer.parseInt(order_no)).getStart_date().split("-");
        Calendar.Builder cancelableTime = new Calendar.Builder();
        cancelableTime.setDate(Integer.parseInt(cancelStr[0]),Integer.parseInt(cancelStr[1]),Integer.parseInt(cancelStr[2])-1);
        if(timeOfCancellation.after(cancelableTime.build())){
            return "修改失敗,需於出發前24小時";
        }
        SimpleDateFormat sdFormat_order_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Calendar calendar = Calendar.getInstance();
        Orders.updateWholeOrder(Integer.parseInt(order_no),order.orderContents(Integer.parseInt(order_no)).getProduct_code(),Integer.parseInt(newAdult),Integer.parseInt(newKid),Integer.parseInt(newBaby),sdFormat_order_date.format(calendar.getTime()));
        return "修改成功,已將您的人數變更為 成人:"+newAdult+", 小孩:"+newKid+", 嬰兒:"+newBaby;
    }
}