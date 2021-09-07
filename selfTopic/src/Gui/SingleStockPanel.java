package Gui;

import Crawler.Crawler;
import Crawler.DayChart;
import Crawler.SSLUtilities;
import org.jfree.chart.ChartPanel;
import stockDB.Stock;
import stockDB.Watchlist;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SingleStockPanel extends JPanel{

	private JPanel topPanel;
	private JPanel midPanel;
	private JPanel bottonPanel;
	private JLabel title;
	private JLabel nowPrice;
	private JLabel varPrice;
	private JLabel highPrice;
	private JLabel lowPrice;
	private JLabel diff;
	private JLabel vol;
	private JButton trackBtn;
	
	private StringListener textListener01;
	
	private JPanel NumPanel;
	private JSlider NumSlider;
	private JLabel NumLabel;
	private double var;
	public int Num = 0;

	public SingleStockPanel(Stock company) throws Exception {
		
		Border innerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(innerBorder);

		var = Crawler.crawl(company.getStock_code())[0].getZ() - Crawler.crawl(company.getStock_code())[0].getO();
		DecimalFormat decimalFormat = new DecimalFormat("##.#");

		topPanel = new JPanel();
		midPanel = new JPanel();
		bottonPanel = new JPanel();
		title = new JLabel("(" + company.getStock_code() + ")" + company.getStock_name());
		nowPrice = new JLabel("股價: " + Crawler.crawl(company.getStock_code())[0].getZ());
		varPrice = new JLabel("漲跌幅: " + Crawler.crawl(company.getStock_code())[0].getPriceVariation());
		highPrice = new JLabel("最高價: " + Crawler.crawl(company.getStock_code())[0].getH());
		lowPrice = new JLabel("最低價: " + Crawler.crawl(company.getStock_code())[0].getL());
		diff = new JLabel("漲跌: " + decimalFormat.format(var));
		vol = new JLabel("成交量: " + Crawler.crawl(company.getStock_code())[0].getV());
		trackBtn = new JButton("買進");
		NumPanel = new JPanel();
		NumSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		NumLabel = new JLabel("買進數量: 0");
		
		NumSlider.setMajorTickSpacing(1);
		NumSlider.setPaintTicks(true);
		NumEvent a = new NumEvent();
		NumSlider.addChangeListener(a);
		
		NumPanel.setLayout(new BorderLayout());
		NumPanel.add(NumSlider, BorderLayout.NORTH);
		NumPanel.add(NumLabel, BorderLayout.SOUTH);
		
		Dimension dim = getPreferredSize();
		dim.width = 600;
		dim.height = 50;
		topPanel.setPreferredSize(dim);
		
		topPanel.setBorder(BorderFactory.createEtchedBorder());
		topPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
	    //////First Row (Top)//////
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		topPanel.add(title, gc);
			
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		topPanel.add(trackBtn, gc);

		Dimension dim1 = getPreferredSize();
		dim1.width = 600;
		dim1.height = 30;
		bottonPanel.setPreferredSize(dim1);

		bottonPanel.setBorder(BorderFactory.createEtchedBorder());
		bottonPanel.setLayout(new FlowLayout());
		bottonPanel.add(nowPrice);
		bottonPanel.add(diff);
		bottonPanel.add(varPrice);
		bottonPanel.add(highPrice);
		bottonPanel.add(lowPrice);
		bottonPanel.add(vol);
		
		//////股價折線圖//////
		SSLUtilities.verification();
		String[] output = Crawler.historicalIndividualDay("202006", company.getStock_code());
		DayChart dayChart = new DayChart("",output,"stock value");
		ChartPanel panel1 = dayChart.getPanel();
		panel1.setPreferredSize(new Dimension(600,380));
		midPanel.add(panel1);

		//////股價折線圖//////
		
		//////按下"買進"的行為//////
		trackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int opt = JOptionPane.showConfirmDialog(null, NumPanel, "EToro", JOptionPane.OK_CANCEL_OPTION);
				 if(opt==0) {
				 	 double price = Crawler.crawl(company.getStock_code())[0].getZ();
					 JOptionPane.showMessageDialog(null, "買入價格: " + price + "\n買入數量: " + Num,
							 "EToro", JOptionPane.INFORMATION_MESSAGE);
					 Watchlist stock = new Watchlist("Ricky", company.getStock_code(), Num, getDateTime());
				     stock.addToWatchList(price);
				 }
			}
		});
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(bottonPanel, BorderLayout.SOUTH);
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener01 = listener;
	}
	
	public class NumEvent implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			int value = NumSlider.getValue();
			Num = value;
			NumLabel.setText("買進數量: " + value);
		}
	}
	
	public String getDateTime(){
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		System.out.println(strDate);
		return strDate;
	}
}
