package Crawler;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class Charts extends ApplicationFrame {

    public Charts(String applicationTitle, String chartTitle , String[] data, String type) {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "","",
                createDataset(data,type),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new Dimension( 560 , 367 ) );
        final XYPlot plot = xylineChart.getXYPlot( );

        setBackground(Color.BLACK);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }

    private XYDataset createDataset( String[] data , String type) {

        final XYSeries params = new XYSeries( type);
        for(int i = 0 ; i < data.length ; i++) {
            String[] day = data[i].split(" ");
            params.add(Integer.parseInt(day[0].split("\\D")[2]), Double.parseDouble(day[1]));
        }
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( params );
        return dataset;
    }

    public static void plot( String[] data, String companyName ) {
        String applicationTitle = "";
        String chartTitle = "";
        chartTitle = applicationTitle;
        Charts chart = new Charts(applicationTitle,
                chartTitle,
                data,
                companyName);
        chart.pack( );
        chart.setVisible( true );
    }
}