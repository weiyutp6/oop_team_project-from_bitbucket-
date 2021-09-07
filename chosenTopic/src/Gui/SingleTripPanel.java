package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import chosenTopic.AvailableTripsList;
import chosenTopic.OrdersTrip;

public class SingleTripPanel extends JPanel {
	
	private JLabel tripName;
	private JButton orderButton;
	private JPanel topPanel;
	private JPanel sliderPanel;
	private JLabel infantLabel;
	private JLabel kidLabel;
	private JLabel adultLabel;
	private JLabel infantNumLabel;
	private JLabel kidNumLabel;
	private JLabel adultNumLabel;
	private JSlider infantSlider;
	private JSlider kidSlider;
	private JSlider adultSlider;
	
	private JTable table;
	private DefaultTableModel model;
	
	private StringListener textListener01;
	
	public int infantNum = 0;
	public int kidNum = 0;
	public int adultNum = 0;

	public SingleTripPanel(Object[] tripOfList, String ID, String tripname) {
		
		Border innerBorder = BorderFactory.createTitledBorder("查看行程");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		tripName = new JLabel(tripname);
		topPanel = new JPanel();
		sliderPanel = new JPanel();
		infantLabel = new JLabel("嬰兒");
		kidLabel = new JLabel("兒童");
		adultLabel = new JLabel("成人");
		infantSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		kidSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		adultSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		infantNumLabel = new JLabel("嬰兒數量: 0");
		kidNumLabel = new JLabel("兒童數量: 0");
		adultNumLabel = new JLabel("成人數量: 0");
		
		orderButton = new JButton("我要報名");
		
		infantSlider.setMajorTickSpacing(1);
		kidSlider.setMajorTickSpacing(1);
		adultSlider.setMajorTickSpacing(1);
		
		infantSlider.setPaintTicks(true);
		kidSlider.setPaintTicks(true);
		adultSlider.setPaintTicks(true);
		
		infantEvent a = new infantEvent();
		infantSlider.addChangeListener(a);
		
		kidEvent b = new kidEvent();
		kidSlider.addChangeListener(b);
		
		adultEvent c = new adultEvent();
		adultSlider.addChangeListener(c);
		
		topPanel.setLayout(new GridBagLayout());
		sliderPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//////topPanel//////
	    //////First Row//////
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		topPanel.add(tripName, gc);
			
		//////sliderPanel//////
	    //////First Row//////
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		sliderPanel.add(infantLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		sliderPanel.add(infantSlider, gc);
		
	    //////Second Row//////
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		sliderPanel.add(infantNumLabel, gc);
		
		//////Third Row//////
		gc.gridx = 0;
		gc.gridy = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		sliderPanel.add(kidLabel, gc);
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		sliderPanel.add(kidSlider, gc);
		
	    //////Fourth Row//////
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		sliderPanel.add(kidNumLabel, gc);
		
	    //////Fifth Row//////
		gc.gridx = 0;
		gc.gridy = 4;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		sliderPanel.add(adultLabel, gc);
			
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		sliderPanel.add(adultSlider, gc);
		
	    //////Sixth Row//////
		gc.gridx = 1;
		gc.gridy = 5;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		sliderPanel.add(adultNumLabel, gc);
		
	    //////Seventh Row//////
		gc.gridx = 1;
		gc.gridy = 6;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		sliderPanel.add(orderButton, gc);
		
		//////表格內容(搜尋結果)//////
        String[] columNames = {"代碼", "出發日期", "結束日期", "價格", "最少出團人數" , "最多出團人數" , "剩餘名額"};
		
        DefaultTableModel model = new DefaultTableModel(columNames, 0);
        
        for(int i=0; i<tripOfList.length; i++) {
        	model.addRow(new Object[] {((String[]) tripOfList[i])[0], ((String[]) tripOfList[i])[1], ((String[]) tripOfList[i])[2],
        			((String[]) tripOfList[i])[3], ((String[]) tripOfList[i])[4], ((String[]) tripOfList[i])[5], ((String[]) tripOfList[i])[6]});
        }
		
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(600, 250));
		table.setFillsViewportHeight(true);
		
		table.setRowHeight(30);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(40);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);
		columnModel.getColumn(5).setPreferredWidth(80);
		columnModel.getColumn(6).setPreferredWidth(80);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.setAutoCreateRowSorter(true);
		
	    //////按下"我要報名"的行為//////
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "請選擇欲報名行程",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
				}else {
					if(adultNum == 0) {
						JOptionPane.showMessageDialog(null, "成人數量不可為0",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					}else {
						JButton clicked = (JButton)e.getSource();
						
						if(clicked == orderButton) {
							String code = (String) model.getValueAt(table.getSelectedRow(), 0);
							String Num = (String) model.getValueAt(table.getSelectedRow(), 6);
							int remain = Integer.parseInt(Num);
							if(textListener01 != null) {
								if((infantNum + kidNum + adultNum) > remain) {
									JOptionPane.showMessageDialog(null, "報名失敗\n剩餘名額不足",
											" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
								}else {
									OrdersTrip tripList = new OrdersTrip();//
									if(tripList.addOrders(ID, code, "" + adultNum, "" + kidNum, "" + infantNum) != null) {
										JOptionPane.showMessageDialog(null, "報名成功",
												" yee遊網 旅遊系統", JOptionPane.INFORMATION_MESSAGE);
										textListener01.textEmitted("orderBtnInSTrip\n");
									}else {
										JOptionPane.showMessageDialog(null, "報名失敗\n行程已逾期",
												" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
									}
								}
							}
						}
					}
				}
			}
		});
		
		add(topPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(sliderPanel, BorderLayout.SOUTH);
	}
	
	public class infantEvent implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			int value = infantSlider.getValue();
			infantNum = value;
			infantNumLabel.setText("嬰兒數量: " + value);
		}
	}
	
	public class kidEvent implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			int value = kidSlider.getValue();
			kidNum = value;
			kidNumLabel.setText("兒童數量: " + value);
		}
	}
	
	public class adultEvent implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			int value = adultSlider.getValue();
			adultNum = value;
			adultNumLabel.setText("成人數量: " + value);
		}
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener01 = listener;
	}
}

