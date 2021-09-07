package Gui;

import Crawler.Crawler;
import Crawler.DayChart;
import Crawler.SSLUtilities;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.DecimalFormat;

public class HomePanel extends JPanel{

	private JPanel btnPanel;
	private JPanel midPanel;
	private JPanel bottomPanel;
	private JButton preBtn;
	private JButton nextBtn;
	
	private JPanel CardLayOutForToolBar;
	private CardLayout layout;

	private JLabel nowPrice;
	private JLabel diff;
	private JLabel var;

	public HomePanel() throws Exception {

		midPanel = new JPanel();
		bottomPanel = new JPanel();

		Border innerBorder = BorderFactory.createTitledBorder("首頁");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		SSLUtilities.verification();
		String[] output = Crawler.historicalIndexDay("202006");
		DayChart dayChart = new DayChart("TWSE June",output,"TWSE Index");
		ChartPanel panel1 = dayChart.getPanel();
		panel1.setPreferredSize(new Dimension(600,420));
		midPanel.add(panel1);

		String[] info = Crawler.index().split(" ");
		DecimalFormat decimalFormat = new DecimalFormat("##.##");

		nowPrice = new JLabel("加權指數: " + info[0]);
		diff = new JLabel("漲跌: " + info[2]);
		var = new JLabel("漲跌幅: " + info[3].replace("(", "").replace(")", ""));

		Dimension dim1 = getPreferredSize();
		dim1.width = 600;
		dim1.height = 30;
		bottomPanel.setPreferredSize(dim1);

		bottomPanel.setBorder(BorderFactory.createEtchedBorder());
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(nowPrice);
		bottomPanel.add(diff);
		bottomPanel.add(var);

		add(midPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
	}
}
