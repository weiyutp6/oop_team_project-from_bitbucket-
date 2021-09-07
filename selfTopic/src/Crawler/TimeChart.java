package Crawler;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TimeChart extends JFrame {

    private static final long serialVersionUID = 1L;
    private ChartPanel panel;

    public TimeChart(String title, String company, String date) throws IOException {
        super(title);
        String[] data = Crawler.historicalIndex5Second(date);
        // Create dataset
        XYDataset dataset = createDataset(data,company,date);
        // Create chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "twse index", // Chart
                "Date", // X-Axis Label
                "value", // Y-Axis Label
                dataset);

        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,255,255));

        panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(String[] data, String company, String date) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        int year = Integer.parseInt(new String(String.valueOf(date.charAt(0)))+
                new String(String.valueOf(date.charAt(1)))+new String(String.valueOf(date.charAt(2)))+
                new String(String.valueOf(date.charAt(3))));
        int month = Integer.parseInt(new String(String.valueOf(date.charAt(4)))+
                new String(String.valueOf(date.charAt(5))));
        int day = Integer.parseInt(new String(String.valueOf(date.charAt(6)))+
                new String(String.valueOf(date.charAt(7))));
        TimeSeries series1 = new TimeSeries(company);
        for (String datum : data) {
            String[] spl = datum.split(" ");
            String[] time = spl[0].split(":");
            series1.add(new Second(Integer.parseInt(time[2]),Integer.parseInt(time[1]),Integer.parseInt(time[0]),day,
                            month,year),
                    Double.parseDouble(spl[1].replaceAll(",", "")));
        }
        dataset.addSeries(series1);

        return dataset;
    }
    // for testing
    public static void plot(String date) throws IOException {
        TimeChart example = new TimeChart("twse index","index",date);
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setVisible(true);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public ChartPanel getPanel(){
        return panel;
    }
}