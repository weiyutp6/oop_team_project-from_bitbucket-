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

public class TradeSingleStockPanel extends JPanel{

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
	private JButton buyBtn;
	private JButton sellBtn;
	
	private StringListener textListener01;

	private double var;

	private JPanel BuyPanel;
	private JSlider BuySlider;
	private JLabel BuyLabel;
	public int Buy = 0;
	
	private JPanel SellPanel;
	private JSlider SellSlider;
	private JLabel SellLabel;
	public int Sell = 0;

	public TradeSingleStockPanel(Stock company, int Number, double BuyPrice) throws Exception {
		
		Border innerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(innerBorder);

		var = Crawler.crawl(company.getStock_code())[0].getZ() - Crawler.crawl(company.getStock_code())[0].getO();
		DecimalFormat decimalFormat = new DecimalFormat("##.#");

		topPanel = new JPanel();
		midPanel = new JPanel();
		bottonPanel = new JPanel();
		title = new JLabel("(" + company.getStock_code() + ")" + company.getStock_name());
		nowPrice = new JLabel("股價: " + Crawler.crawl(company.getStock_code())[0].getZ());
		varPrice = new JLabel("漲跌: " + Crawler.crawl(company.getStock_code())[0].getPriceVariation());
		highPrice = new JLabel("最高價: " + Crawler.crawl(company.getStock_code())[0].getH());
		lowPrice = new JLabel("最低價: " + Crawler.crawl(company.getStock_code())[0].getL());
		diff = new JLabel("漲跌: " + decimalFormat.format(var));
		vol = new JLabel("成交量: " + Crawler.crawl(company.getStock_code())[0].getV());
		buyBtn = new JButton("買進");
		sellBtn = new JButton("賣出");
		
		BuyPanel = new JPanel();
		BuySlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		BuyLabel = new JLabel("買進數量: 0");
		
		BuySlider.setMajorTickSpacing(1);
		BuySlider.setPaintTicks(true);
		BuyEvent a = new BuyEvent();
		BuySlider.addChangeListener(a);
		
		BuyPanel.setLayout(new BorderLayout());
		BuyPanel.add(BuySlider, BorderLayout.NORTH);
		BuyPanel.add(BuyLabel, BorderLayout.SOUTH);
		
		SellPanel = new JPanel();
		SellSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		SellLabel = new JLabel("賣出數量: 0");
		
		SellSlider.setMajorTickSpacing(1);
		SellSlider.setPaintTicks(true);
		SellEvent b = new SellEvent();
		SellSlider.addChangeListener(b);
		
		SellPanel.setLayout(new BorderLayout());
		SellPanel.add(SellSlider, BorderLayout.NORTH);
		SellPanel.add(SellLabel, BorderLayout.SOUTH);
		
		
		Dimension dim = getPreferredSize();
		dim.width = 600;
		dim.height = 50;
		topPanel.setPreferredSize(dim);
		
		topPanel.setBorder(BorderFactory.createEtchedBorder());
		topPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
	    //////First Row//////
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 100;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		topPanel.add(title, gc);
			
		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		topPanel.add(buyBtn, gc);
		
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		topPanel.add(sellBtn, gc);

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
		DayChart dayChart = new DayChart("",output,"");
		ChartPanel panel1 = dayChart.getPanel();
		panel1.setPreferredSize(new Dimension(600,380));
		midPanel.add(panel1);
		//////股價折線圖//////
		
		//////按下"買進"的行為//////
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(null, BuyPanel, "EToro", JOptionPane.OK_CANCEL_OPTION);
				if(opt==0) {
					double price = Crawler.crawl(company.getStock_code())[0].getZ();//
					JOptionPane.showMessageDialog(null, "買入價格: " + price + "\n買入數量: " + Buy,
							"EToro", JOptionPane.INFORMATION_MESSAGE);
					Watchlist stock = new Watchlist("Ricky", company.getStock_code(), Buy, getDateTime());
			        stock.addToWatchList(price);
				}
			}
		});
		
	    //////按下"賣出"的行為//////
		sellBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(null, SellPanel, "EToro", JOptionPane.OK_CANCEL_OPTION);
				if(opt==0) {
					if(Sell>Number) {
						JOptionPane.showMessageDialog(null, "擁有股票張數不足",
								"EToro", JOptionPane.OK_OPTION);
					}else {
						double price = Crawler.crawl(company.getStock_code())[0].getZ();//
						JOptionPane.showMessageDialog(null, "買入價格: " + BuyPrice + "\n賣出價格: "
								 + price + "\n賣出數量: " + Sell, "EToro", JOptionPane.INFORMATION_MESSAGE);
						Watchlist.sellStockInWatchList("Ricky", TrackList.list_no+1, Sell,price);
					}
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
	
	public class BuyEvent implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			int value = BuySlider.getValue();
			Buy = value;
			BuyLabel.setText("買進數量: " + value);
		}
	}
	
	public class SellEvent implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			int value = SellSlider.getValue();
			Sell = value;
			SellLabel.setText("賣出數量: " + value);
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
