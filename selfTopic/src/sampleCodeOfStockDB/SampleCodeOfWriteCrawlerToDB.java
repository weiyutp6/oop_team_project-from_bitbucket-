import stockDB.*;

public class SampleCodeOfWriteCrawlerToDB {
    public static void main(String[] args) {
        Stock stock = new Stock("2454", 470, -1.5, -0.0032, 8699, 70, 463, 461.5, 509.3, 416.7, 470.0, 460.0, "461.5, 461.0, 460.5, 460.0, 459.5", "462.0, 462.5, 463.0, 463.5, 464.0", 542, 812, "13:29:00.0000000", "2020-06-01");
        stock.crawlerToDB();
    }
}
