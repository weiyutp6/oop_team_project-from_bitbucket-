import connectDB.*;
public class SampleCodeOfGetDetailOfTrip {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        String[] a = AvailableTrips.searchRelatedTitle("亞洲 歐洲 大洋洲", 20200301, 20201231);
        for(String e : a) {
            System.out.println(e);
        }
        long endTime=System.currentTimeMillis();
        System.out.println("程式執行時間： " + (((double)endTime-(double)startTime)/1000) + "s");
        System.out.println(a.length);

//        Object[][] o = AvailableTrips.searchDetailByTitle("【菲向巴拉望】虎航★巴拉望三跳島+空中飛人+按摩+一島一飯店6日遊 兩人成行 含稅簽(參團當地即贈送芒果乾一包)");
//        for(Object[] e : o) {
//            System.out.println(e[0]);
//            System.out.println(e[1]);
//            System.out.println(e[2]);
//            System.out.println(e[3]);
//            System.out.println(e[4]);
//            System.out.println(e[5]);
//            System.out.println(e[6]);
//            System.out.println(e[7]);
//            System.out.println("-------------");
//        }
    }
}
