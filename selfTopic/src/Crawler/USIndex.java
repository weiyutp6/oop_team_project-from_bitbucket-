package Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class USIndex {
    public static String dowJones() throws IOException {
        String url = "https://www.google.com/search?rlz=1C1CHBF_zh-TWTW902TW902&sxsrf=ALeKk027Rs6FZtobr5Mohr0cOu0HlpyiCQ%3A1591951952700&ei=UELjXrOyKqqKr7wPzv-y4AU&q=dow+jones&oq=dow+jones&gs_lcp=CgZwc3ktYWIQA1Dt4IEBWK3qgQFgg-uBAWgAcAB4AIABAIgBAJIBAJgBAKABAaoBB2d3cy13aXo&sclient=psy-ab&ved=0ahUKEwizmo-V8_vpAhUqxYsBHc6_DFwQ4dUDCAw&uact=5";
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

    public static String nasdaq() throws IOException {
        String url = "https://www.google.com/search?rlz=1C1CHBF_zh-TWTW902TW902&sxsrf=ALeKk03GK0uVSJExAsfAWoQH1Ijjm3ZbSg%3A1591954082503&ei=okrjXtCSHruHr7wPu-WE4A4&q=nasdaq&oq=nasda&gs_lcp=CgZwc3ktYWIQAxgAMgkIABBDEEYQ-gEyBAgAEEMyBAgAEEMyBAgAEEMyBAgAEEMyBAgAEEMyBAgAEEMyBAgAEEMyBAgAEEMyBAgAEEM6BAgjECc6AggAUMGFIFjNpCBgt6wgaAdwAHgDgAGtBYgB4yWSAQczLTMuMy40mAEAoAEBqgEHZ3dzLXdpeg&sclient=psy-ab";
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

    public static String sP500() throws IOException {
        String url = "https://www.google.com/search?rlz=1C1CHBF_zh-TWTW902TW902&sxsrf=ALeKk02RpU7IFY_0fOpbAU8N6hlqxvQE7A%3A1591954615128&ei=t0zjXrOpB4yImAXY0YvgDA&q=s%26p500&oq=s%26p500&gs_lcp=CgZwc3ktYWIQAzIECAAQQzIECAAQQzICCAAyAggAMgIIADIECAAQCjIECAAQCjIECAAQCjICCAAyAggAOgQIIxAnUNzlBliw9wZgufkGaABwAHgAgAHEBogBhxeSAQc0LTEuMC4zmAEAoAEBqgEHZ3dzLXdpeg&sclient=psy-ab&ved=0ahUKEwjz4NSK_fvpAhUMBKYKHdjoAswQ4dUDCAw&uact=5";
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
}
