import connectDB.*;

public class SampleCodeOfSearchProductCodeByTitleCode {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        int[] a = AvailableTrips.relatedProductCode(274, 20200311, 20201231);
        for(int e : a) {
            System.out.println(e);
        }
        long endTime=System.currentTimeMillis();
        System.out.println("程式執行時間： " + (((double)endTime-(double)startTime)/1000) + "s");
        System.out.println(a.length);
    }
}