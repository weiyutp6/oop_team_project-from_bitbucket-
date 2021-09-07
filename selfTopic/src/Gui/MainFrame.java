package Gui;

import stockDB.Stock;
import stockDB.Watchlist;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

	//////Create Elements//////
	private SearchBar searchBar;
	private ComCatPanel comCatPanel;
	private HomePanel homePanel;
	
	//////Elements Created Only If assigned Button is Clicked//////
	private TrackList trackList;
	private ComSelectPanel comSelectPanel;
	private SearchNameResultPanel searchNameResultPanel;
	private SingleStockPanel singleStockPanel;
	private TradeSingleStockPanel tradeSingleStockPanel;
	
	//////Set Elements Which is expected to be Shown//////
	private JPanel CLayOutForSouthEast;
	private CardLayout layout;
	
	public MainFrame() throws Exception {
		super("EToro");
		
		
		searchBar = new SearchBar();
		comCatPanel = new ComCatPanel();
		homePanel = new HomePanel();
		
		setLayout(new BorderLayout());
		
		CLayOutForSouthEast = new JPanel();
        layout = new CardLayout();
		
		CLayOutForSouthEast.setLayout(layout);
		CLayOutForSouthEast.add("Home", homePanel);
		
	    //////Ten Field on the Left Hand Side of the Window (ComCatPanel)//////
		comCatPanel.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				
				Stock ordersList = new Stock();
				Object[] list = ordersList.StockCodeOfIndustry(text);
				
				comSelectPanel = new ComSelectPanel(list, text);
				CLayOutForSouthEast.add("ComSelect", comSelectPanel);
				layout.show(CLayOutForSouthEast, "ComSelect");
				//////Function "查看公司資料" in "comSelectPanel"//////
				comSelectPanel.setStringListener(new StringListener() {
					public void textEmitted(String text) throws Exception {
						if(text.equals("detailButton\n")) {
							Stock stock = Stock.searchStockByStockCode(comSelectPanel.companyNum);
							//上一層的東西，讓SingleStockPanel知道要顯示那些資料//////
							try {
								singleStockPanel = new SingleStockPanel(stock);
								CLayOutForSouthEast.add("SingleStockPanel", singleStockPanel);
								layout.show(CLayOutForSouthEast, "SingleStockPanel");
							} catch (Exception e){
								JOptionPane.showMessageDialog(null, "查無此資料",
										"EToro", JOptionPane.OK_OPTION);
							}
						}
					}
				});
				
			}
		});
		
	    //////Two Functions on the Top of the Window (On the Left Side of SearchBar)//////
		searchBar.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				if(text.equals("Home\n")) {
					layout.show(CLayOutForSouthEast, "Home");
				}else if(text.equals("Track\n")) {
					Watchlist[] stockCodes = Watchlist.getAllDetailsInWatchList("Ricky");
					
					trackList = new TrackList(stockCodes);
					CLayOutForSouthEast.add("TrackList", trackList);
					layout.show(CLayOutForSouthEast, "TrackList");
				    //////Function "查看公司資料" in "comSelectPanel"//////
					trackList.setStringListener(new StringListener() {
						public void textEmitted(String text) throws Exception {
							if(text.equals("seePriceBtn\n")) {
								//上一層的東西，讓SingleStockPanel知道要顯示那些資料//////
								Stock stock = Stock.searchStockByStockCode(trackList.comNum);
								tradeSingleStockPanel = new TradeSingleStockPanel(stock, trackList.Number, trackList.BuyPrice);
								CLayOutForSouthEast.add("TradeSingleStockPanel", tradeSingleStockPanel);
								layout.show(CLayOutForSouthEast, "TradeSingleStockPanel");
							}
						}
					});
				}
			}
		});
		
	    //////Function "提交" in SearchBar(subBtn1)//////
		searchBar.setSearchBarNameListener(new SearchBarNameListener() {
			public void SearchBarNameEventOccurred(SearchBarNameEvent e) {
				String comName = e.getName();
				Object[] stock = Stock.searchStockCodeByStockName(comName);
				//上一層的東西，讓SingleStockPanel知道要顯示那些資料//////
				if(stock.length==0) {
		        	JOptionPane.showMessageDialog(null, "此關鍵字無對應股票",
							"EToro", JOptionPane.OK_OPTION);
		        }else {
		        	searchNameResultPanel = new SearchNameResultPanel(stock);
					CLayOutForSouthEast.add("SearchNameResultPanel", searchNameResultPanel);
					layout.show(CLayOutForSouthEast, "SearchNameResultPanel");
				    //////Function "查看公司資料" in "comSelectPanel"//////
					searchNameResultPanel.setStringListener(new StringListener() {
						public void textEmitted(String text) throws Exception {
							if(text.equals("detailButton\n")) {
								Stock stock = Stock.searchStockByStockCode(searchNameResultPanel.companyNum);
								//上一層的東西，讓SingleStockPanel知道要顯示那些資料//////
								try {
									singleStockPanel = new SingleStockPanel(stock);
									CLayOutForSouthEast.add("SingleStockPanel", singleStockPanel);
									layout.show(CLayOutForSouthEast, "SingleStockPanel");
								} catch (Exception e){
									JOptionPane.showMessageDialog(null, "查無此資料",
											"EToro", JOptionPane.OK_OPTION);
								}
							}
						}
					});
		        }
			}
		});
		
	    //////Function "提交" in SearchBar(subBtn2)//////
		searchBar.setSearchBarNumListener(new SearchBarNumListener() {
			public void SearchBarNumEventOccurred(SearchBarNumEvent e) throws Exception {
				String comNum = e.getNum();
				Stock stock = Stock.searchStockByStockCode(comNum);
				if(stock.getStock_code()==null) {
					JOptionPane.showMessageDialog(null, "此代碼無對應股票",
							"EToro", JOptionPane.OK_OPTION);
				}else {
					//上一層的東西，讓SingleStockPanel知道要顯示那些資料//////
					try {
						singleStockPanel = new SingleStockPanel(stock);
						CLayOutForSouthEast.add("SingleStockPanel", singleStockPanel);
						layout.show(CLayOutForSouthEast, "SingleStockPanel");
					} catch (Exception exc){
						JOptionPane.showMessageDialog(null, "查無此資料",
								"EToro", JOptionPane.OK_OPTION);
					}
				}
			}
		});
		
        //////Add Panels to the Window//////
		add(searchBar, BorderLayout.NORTH);
		add(CLayOutForSouthEast, BorderLayout.CENTER);
		add(comCatPanel, BorderLayout.WEST);
			
		setSize(1000, 600);//Size of Window//
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
