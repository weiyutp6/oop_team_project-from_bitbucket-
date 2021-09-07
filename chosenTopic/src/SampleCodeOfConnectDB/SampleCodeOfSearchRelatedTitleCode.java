import connectDB.*;

public class SampleCodeOfSearchRelatedTitleCode {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        int[] a = AvailableTrips.relatedTitleCode("中國 日本 歐洲", 20200311, 20201231);
        for(int e : a) {
            System.out.println(e);
        }
        long endTime=System.currentTimeMillis();
        System.out.println("程式執行時間： " + (((double)endTime-(double)startTime)/1000) + "s");
        System.out.println(a.length);
    }
}
