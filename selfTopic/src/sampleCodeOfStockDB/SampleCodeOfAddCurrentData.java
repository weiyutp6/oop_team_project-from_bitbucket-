import stockDB.*;

public class SampleCodeOfAddCurrentData {
    public static void main(String[] args) {
        String time = "20:29:45";
        double index = 12500;
        double rise_fall = 75.2;
        double rise_fall_ratio = 0.029;
        int volume = 11557;
        double open = 11398.8;
        double close = 11425.8;
        WeightedIndex.addCurrentData(time, index, rise_fall, rise_fall_ratio, volume, open, close);
    }
}
