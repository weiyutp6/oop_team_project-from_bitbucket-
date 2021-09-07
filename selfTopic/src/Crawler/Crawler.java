package Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.TimeZone;

public class Crawler{
    //    輸入是用逗號隔開的股票代碼e.g. "2330,5880,0050"
    public static Stock[] crawl(String stock_no){
        int numOfStock;
        try {
            if(stock_no==null) throw new IllegalArgumentException("輸入不可為空白");
            String[] stock = stock_no.split(",");
            numOfStock = stock.length;
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
            StringBuilder stock_url = new StringBuilder();
            stock_url.append("tse_").append(stock[0]).append(".tw");
            for (int i = 1; i < numOfStock; i++) {
                stock_url.append("|tse_").append(stock[i]).append(".tw");
            }
            Stock[] company = new Stock[numOfStock];
            String url = "http://mis.twse.com.tw/stock/api/getStockInfo.jsp?json=1&delay=0&ex_ch=" + stock_url;
            Document document = Jsoup.connect(url).maxBodySize(0).get();
            Element body = document.body();
            Elements paragraphs = body.getElementsByTag("body");
            DecimalFormat decimalFormat = new DecimalFormat("##.##%");
            for (Element paragraph : paragraphs) {
                String line = paragraph.text();
                if (line.length() == 0) continue;
                String stockParam = line.split("\\[")[1].split("\\]")[0];
                String[] companyStock = stockParam.replaceAll("\"","").replaceAll("\\{"," ")
                        .replaceAll("\\}"," ").trim().split(" , ");
                for(int j = 0 ; j < numOfStock ; j++){
                    String[] parameters = companyStock[j].split(",");
                    company[j] = new Stock();
                    for(int k = 0 ; k < parameters.length ; k++){
                        String[] typeVal = parameters[k].split(":");
                        switch (typeVal[0]){
                            case "n":
                                company[j].setN(typeVal[1]);
                                break;
                            case "g":
                                company[j].setG(typeVal[1]);
                                break;
                            case "u":
                                company[j].setU(Double.parseDouble(typeVal[1]));
                                break;
                            case "o":
                                if(typeVal[1].equals("-")){
                                    company[j].setO(0);
                                    break;
                                }
                                company[j].setO(Double.parseDouble(typeVal[1]));
                                break;
                            case "a":
                                company[j].setA(typeVal[1]);
                                break;
                            case "tlong":
                                company[j].setTlong(Double.parseDouble(typeVal[1]));
                                break;
                            case "t":
                                company[j].setT(new Calendar.Builder().setTimeOfDay(
                                        Integer.parseInt(typeVal[1]),Integer.parseInt(typeVal[2]),Integer.parseInt(typeVal[3])));
                                break;
                            case "z":
                                if (typeVal[1].equals("-")){
                                    company[j].setZ(0);
                                    break;
                                }
                                company[j].setZ(Double.parseDouble(typeVal[1]));
                                break;
                            case "tv":
                                if (typeVal[1].equals("-")){
                                    company[j].setTv(0);
                                    break;
                                }
                                company[j].setTv(Integer.parseInt(typeVal[1]));
                                break;
                            case "v":
                                company[j].setV(Integer.parseInt(typeVal[1]));
                                break;
                            case "b":
                                company[j].setB(typeVal[1]);
                                break;
                            case "f":
                                company[j].setF(typeVal[1]);
                                break;
                            case "h":
                                if(typeVal[1].equals("-")){
                                    company[j].setH(0);
                                    break;
                                }
                                company[j].setH(Double.parseDouble(typeVal[1]));
                                break;
                            case "l":
                                if(typeVal[1].equals("-")){
                                    company[j].setL(0);
                                    break;
                                }
                                company[j].setL(Double.parseDouble(typeVal[1]));
                                break;
                            case "y":
                                company[j].setY(Double.parseDouble(typeVal[1]));
                                break;
                            case "w":
                                company[j].setW(Double.parseDouble(typeVal[1]));
                                break;
                            case "d":
                                String[] a = new String[8];
                                for(int i = 0 ; i < 8 ; i++){
                                    a[i] = Character.toString(typeVal[1].charAt(i));
                                }
                                String year = a[0]+a[1]+a[2]+a[3];
                                String month = a[4]+a[5];
                                String day = a[6]+a[7];
                                company[j].setD(new Calendar.Builder().setDate(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day)));
                                break;
                            case "c":
                                company[j].setC(Integer.parseInt(typeVal[1]));
                                break;
                            case "nf":
                                company[j].setNf(typeVal[1]);
                                break;
                        }
                        company[j].setPriceVariation(decimalFormat.format(company[j].getZ()/company[j].getO()-1));
                    }
                }
            }
            return company;
        }catch (IllegalArgumentException | IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void continuousData(String stock_no) throws InterruptedException {
        Calendar timeOfStock = Calendar.getInstance(TimeZone.getTimeZone("GMT + 8"));
        Calendar.Builder start = new Calendar.Builder();
        start.setDate(timeOfStock.get(Calendar.YEAR),timeOfStock.get(Calendar.MONTH),timeOfStock.get(Calendar.DAY_OF_MONTH)).setTimeOfDay(9,0,0);
        Calendar.Builder end = new Calendar.Builder();
        end.setDate(timeOfStock.get(Calendar.YEAR),timeOfStock.get(Calendar.MONTH),timeOfStock.get(Calendar.DAY_OF_MONTH)).setTimeOfDay(13,30,0);
        DecimalFormat decimalFormat = new DecimalFormat("##.##%");
        while(timeOfStock.after(start.build())&&timeOfStock.before(end.build())) {
            Stock[] stocks = Crawler.crawl(stock_no);
            //及時爬的時候有些數值只有成交才會出現沒有值我統一給0
            for(int i = 0 ; i < stock_no.split(",").length ; i++){
                assert stocks != null;
                if(stocks[i].getZ()==0){
                }
                else {
                    System.out.println(stocks[i].getN()+" "+stocks[i].getZ());
                    stocks[i].setPriceVariation(decimalFormat.format((stocks[i].getZ()/stocks[i].getO())-1));
                }
            }
            Thread.sleep(5000);
        }
    }

    public static String index() throws IOException {
        String url = "https://www.google.com/search?q=twse+index&rlz=1C1CHBF_zh-TWTW902TW902&oq=twse+index&aqs=chrome..69i57j69i60l3.5860j0j7&sourceid=chrome&ie=UTF-8";
        Document document = Jsoup.connect(url).maxBodySize(0).get();
        Element body = document.body();
        Elements paragraphs = body.getElementsByTag("span");
        String lastLine = "";
        String currentIndex = "";
        String yesterdayIndex = "";
        String riseFall = "";
        for (Element paragraph : paragraphs) {
            String line = paragraph.text();
            if(lastLine.equals("(Languages)")){
                currentIndex = line;
            }
            if(line.split(" ")[0].equals("上次")){
                yesterdayIndex = line.split(" ")[2];
            }
            if(lastLine.equals(currentIndex)){
                riseFall = line;
            }
            lastLine = line;
        }
        return currentIndex + " " + yesterdayIndex + " " + riseFall;
    }
//    all date should be yyyymmdd
    public static String[] historicalIndex5Second(String date) throws IOException {
        String url = "https://www.twse.com.tw/exchangeReport/MI_5MINS_INDEX?response=html&date="+date;
        Document document = Jsoup.connect(url).maxBodySize(0).get();
        Element body = document.body();
        Elements paragraphs = body.getElementsByTag("tr");
        LinkedList<String> dataPoints = new LinkedList<>();
        LinkedList<String> time = new LinkedList<>();
        for (Element paragraph : paragraphs) {
            String line = paragraph.text();
            if(line.charAt(2)!=':') continue;
            String[] data = line.trim().split(" ");
            time.addLast(data[0]);
            dataPoints.addLast(data[1]);
        }
        String[] output = new String[dataPoints.size()];
        for (int i = 0 ; i < dataPoints.size() ; i++){
            output[i] = time.get(i)+" "+dataPoints.get(i);
        }
        return output;
    }

    public static String[] historicalIndexDay(String date) throws IOException {
        String url = "https://www.twse.com.tw/indicesReport/MI_5MINS_HIST?response=html&date="+date+"01";
        Document document = Jsoup.connect(url).maxBodySize(0).get();
        Element body = document.body();
        Elements paragraphs = body.getElementsByTag("tr");
        LinkedList<String> dataPoints = new LinkedList<>();
        LinkedList<String> time = new LinkedList<>();
        for (Element paragraph : paragraphs) {
            String line = paragraph.text();
            if(line.charAt(3)!='/') continue;
            String[] data = line.trim().split(" ");
            time.addLast(data[0]);
            dataPoints.addLast(data[4]);
        }
        String[] output = new String[dataPoints.size()];
        for (int i = 0 ; i < dataPoints.size() ; i++){
            output[i] = time.get(i)+" "+dataPoints.get(i);
        }
        return output;
    }

    public static String[] historicalIndividualDay(String date, String stock_no) throws IOException {
        String url = "https://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date="+date+"01&stockNo="+stock_no;
        Document document = Jsoup.connect(url).maxBodySize(0).ignoreContentType(true).get();
        Element body = document.body();
        Elements paragraphs = body.getElementsByTag("body");
        String processData = "";
        for (Element paragraph : paragraphs) {
            String line = paragraph.text();
            if (line.length() == 0) continue;
            processData = line.trim().split("\"data\":\\[\\[\"")[1].split("\"\\]\\],\"notes\":")[0];
        }
        String[] dailyData = processData.split("\"\\],\\[\"");
        String[] outputData = new String[dailyData.length];
        for(int i = 0 ; i < dailyData.length ; i++){
            String[] params = dailyData[i].split("\",\"");
            outputData[i] = params[0]+" "+params[6];
        }
        return outputData;
    }
}