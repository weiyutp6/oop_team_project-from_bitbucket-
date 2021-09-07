package Gui;

import Crawler.Crawler;
import stockDB.Watchlist;
import stockDB.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackList extends JPanel {

	public String comNum;
	public double BuyPrice;//
	public int Number;
	public static int list_no = 0;
	
	private JTable table;
	private JPanel topPanel;
	private JPanel btnPanel;
	private JLabel prinLabel;
	private JLabel reLabel;
	private JButton seePriceBtn;
	private JButton cancelBtn;
	private DefaultTableModel model;
	
	private StringListener textListener;

	public TrackList(Watchlist[] watchList) {
		
		Border innerBorder = BorderFactory.createTitledBorder("持有股票");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		User user = new User("Ricky");

		topPanel = new JPanel();
		prinLabel = new JLabel("投入金額: " + user.getPrincipal());
		reLabel = new JLabel("累積獲利: " + user.getTotal_refund());

		topPanel.setLayout(new FlowLayout());
		topPanel.add(prinLabel);
		topPanel.add(reLabel);

		btnPanel = new JPanel();
		seePriceBtn = new JButton("查看公司資料");
		cancelBtn = new JButton("取消追蹤");//
		
		btnPanel.setLayout(new FlowLayout());
		btnPanel.add(seePriceBtn);
		btnPanel.add(cancelBtn);
		
		//////表格內容(搜尋結果)//////
        String[] columNames = {"編碼", "公司代碼", "公司名稱", "買入價格", "買入時間", "持有數量(張)"};
		
        DefaultTableModel model = new DefaultTableModel(columNames, 0);
        
        for(Watchlist e : watchList) {
        	model.addRow(new Object[] {e.getList_no(), e.getStock_code(), e.getStock_name(), e.getBuy_price(),
        			e.getBuy_datetime(), e.getLot()});
        }
		
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(600, 350));
		table.setFillsViewportHeight(true);
		
		table.setRowHeight(30);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(150);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.setAutoCreateRowSorter(true);
		
	    //////按下"查看公司資訊"的行為//////
		seePriceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "請選擇欲查看公司",
							"YeeToro", JOptionPane.OK_OPTION);
				}else {
					JButton clicked = (JButton)e.getSource();
						
					if(clicked == seePriceBtn) {
						if(textListener != null) {
							list_no = (int) model.getValueAt(table.getSelectedRow(), 0) - 1;
							Number = (int) model.getValueAt(table.getSelectedRow(), 5);
							BuyPrice = (double) model.getValueAt(table.getSelectedRow(), 3);//
							comNum = (String) model.getValueAt(table.getSelectedRow(), 1);
							Crawler.crawl(comNum);
							try {
								textListener.textEmitted("seePriceBtn\n");
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
			    }
			}
		});
		
	    //////按下"取消追蹤"的行為//////
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "無可取消追蹤公司",
								"YeeToro", JOptionPane.OK_OPTION);
					}else {
						JOptionPane.showMessageDialog(null, "請選擇欲取消追蹤公司",
								"YeeToro", JOptionPane.OK_OPTION);
					}
				}else {
					////取消追蹤所選擇的公司//////
					Watchlist.deleteWatchList("Ricky", (int) model.getValueAt(table.getSelectedRow(), 0));
					JOptionPane.showMessageDialog(null, "刪除成功",
							"YeeToro", JOptionPane.INFORMATION_MESSAGE);
					model.removeRow(table.getSelectedRow());
				}
			}
		});

		add(topPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}
}
