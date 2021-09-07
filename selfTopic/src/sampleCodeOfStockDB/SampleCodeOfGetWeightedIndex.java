import stockDB.*;

public class SampleCodeOfGetWeightedIndex {
    public static void main(String[] args) {
        WeightedIndex data = new WeightedIndex();
        System.out.println("time:");
        for(String t:data.getTime()) {
            System.out.println(t);
        }
        System.out.println("index:");
        for(double i:data.getIndex()) {
            System.out.println(i);
        }
        System.out.println("volume:");
        for(int v:data.getVolume()) {
            System.out.println(v);
        }

        System.out.println("------");

        WeightedIndex currentData = new WeightedIndex("隨便你想打什麼就打什麼XD");
        System.out.println("time:");
        System.out.println(currentData.getCurrentTime());
        System.out.println("index:");
        System.out.println(currentData.getCurrentIndex());
        System.out.println("rise_fall:");
        System.out.println(currentData.getRise_fall());
        System.out.println("rise_fall_ratio:");
        System.out.println(currentData.getRise_fall_ratio());
        System.out.println("volume:");
        System.out.println(currentData.getCurrentVolume());
        System.out.println("open:");
        System.out.println(currentData.getOpen());
        System.out.println("close:");
        System.out.println(currentData.getClose());
    }
}
