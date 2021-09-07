import stockDB.*;

public class SampleCodeOfInitializeWeightedIndex {
    public static void main(String[] args) {
        //每次查詢歷史股價都必須先清空資料表
        WeightedIndex.initializeWeightedIndex();
    }
}
