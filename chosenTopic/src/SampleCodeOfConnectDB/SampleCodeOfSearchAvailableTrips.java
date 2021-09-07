import connectDB.*;

public class SampleCodeOfSearchAvailableTrips {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        String[] a = AvailableTrips.searchAvailableTitle("張家界 北歐 巴拉望", 20200301, 20201231);
        for(String e : a) {
            System.out.println(e);
        }
        long endTime=System.currentTimeMillis();
        System.out.println("程式執行時間： " + (((double)endTime-(double)startTime)/1000) + "s");
        System.out.println(a.length);
    }
}