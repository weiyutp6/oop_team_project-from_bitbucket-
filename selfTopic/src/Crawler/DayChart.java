package Crawler;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DayChart extends JFrame {

    private static final long serialVersionUID = 1L;
    private ChartPanel panel;

    public DayChart(String title, String[] data, String company) {
        super(title);
        // Create dataset
        XYDataset dataset = createDataset(data,company);
        // Create chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title, // Chart
                "", // X-Axis Label
                "", // Y-Axis Label
                dataset);

        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(0,0,0));

        panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(String[] data, String company) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        TimeSeries series1 = new TimeSeries(company);
        for(int i = 0 ; i < data.length ; i++) {
            String[] day = data[i].split(" ");
            series1.add(new Day(Integer.parseInt(day[0].split("\\D")[2]),
                            Integer.parseInt(day[0].split("\\D")[1]),
                            Integer.parseInt(day[0].split("\\D")[0])+1911),
                    Double.parseDouble(day[1].replaceAll(",","")));
        }
        dataset.addSeries(series1);

        return dataset;
    }
    public static void plot(String company, String[] data) throws IOException {
        DayChart example = new DayChart(company,data,company);
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setVisible(true);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public ChartPanel getPanel() {
        return panel;
    }
}