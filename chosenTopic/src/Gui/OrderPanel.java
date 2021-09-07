package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import Gui.SingleTripPanel.adultEvent;
import Gui.SingleTripPanel.infantEvent;
import Gui.SingleTripPanel.kidEvent;

import chosenTopic.*;

public class OrderPanel extends JPanel {
	
	private JButton cancelButton;
	private JButton updateButton;
	private JPanel sliderPanel;
	private JPanel btnPanel;
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
	
    private StringListener textListener;
	
	public int infantNum = 0;
	public int kidNum = 0;
	public int adultNum = 0;

	public OrderPanel(Object[] tripOfList, String ID) {
		
		Border innerBorder = BorderFactory.createTitledBorder("查看訂單");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		sliderPanel = new JPanel();
		btnPanel = new JPanel();
		infantLabel = new JLabel("嬰兒");
		kidLabel = new JLabel("兒童");
		adultLabel = new JLabel("成人");
		infantSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		kidSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		adultSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		infantNumLabel = new JLabel("嬰兒數量: 0");
		kidNumLabel = new JLabel("兒童數量: 0");
		adultNumLabel = new JLabel("成人數量: 0");
		
		cancelButton = new JButton("取消訂單");
		updateButton = new JButton("修改訂單");
		
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
		
        sliderPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
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
		
		btnPanel.setLayout(new FlowLayout());
		btnPanel.add(updateButton);
		btnPanel.add(cancelButton);
		
		//////Seventh Row//////
		gc.gridx = 1;
		gc.gridy = 6;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		sliderPanel.add(btnPanel, gc);
		
		//////顯示訂單//////
		String[] columNames = {"代碼", "行程名稱", "訂單編號", "出發日期", "結束日期", "成人數量", "兒童數量", "嬰兒數量", "總價", "最後修改時間"};
		
		 DefaultTableModel model = new DefaultTableModel(columNames, 0);
	        
        for(int i=0; i<tripOfList.length; i++) {
        	model.addRow(new Object[] {((String[]) tripOfList[i])[2], ((String[]) tripOfList[i])[3], ((String[]) tripOfList[i])[1],
        			((String[]) tripOfList[i])[5], ((String[]) tripOfList[i])[6], ((String[]) tripOfList[i])[7],
        			((String[]) tripOfList[i])[8], ((String[]) tripOfList[i])[9], ((String[]) tripOfList[i])[10],
        			((String[]) tripOfList[i])[11]});
        }
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(600, 250));
		table.setFillsViewportHeight(true);
		
		table.setRowHeight(30);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(40);
		columnModel.getColumn(1).setPreferredWidth(450);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);
		columnModel.getColumn(5).setPreferredWidth(80);
		columnModel.getColumn(6).setPreferredWidth(80);
		columnModel.getColumn(7).setPreferredWidth(80);
		columnModel.getColumn(8).setPreferredWidth(80);
		columnModel.getColumn(9).setPreferredWidth(135);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.setAutoCreateRowSorter(true);
		
	    //////按下取消訂單的行為//////
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "無可刪除訂單",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					}else {
						JOptionPane.showMessageDialog(null, "請選擇欲刪除訂單",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					}
				}else {
					if(CancelOrder.cancelOrder(ID, (String) model.getValueAt(table.getSelectedRow(), 2)).equals("退訂成功,已取消您的預訂紀錄")) {
						JOptionPane.showMessageDialog(null, "退訂成功,已取消您的預訂紀錄",
								" yee遊網 旅遊系統", JOptionPane.INFORMATION_MESSAGE);
						model.removeRow(table.getSelectedRow());
					}else if(CancelOrder.cancelOrder(ID, (String) model.getValueAt(table.getSelectedRow(), 2)).equals("退訂失敗,需於出發前24小時")) {
						JOptionPane.showMessageDialog(null, "退訂失敗,需於出發前24小時",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					}else if(CancelOrder.cancelOrder(ID, (String) model.getValueAt(table.getSelectedRow(), 2)).equals("退訂失敗,此訂位代號不存在")) {
						JOptionPane.showMessageDialog(null, "退訂失敗,此訂位代號不存在",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					}
				}
			}
		});
		
		 //////按下"修改訂單"的行為//////
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "無可修改訂單",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					}else {
						JOptionPane.showMessageDialog(null, "請選擇欲修改訂單",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					}
				}else {
					if(adultNum == 0) {
						JOptionPane.showMessageDialog(null, "成人數量不可為0",
								" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
					}else {
						String Num = AvailableTripsList.getRemainingByProductCode((String) model.getValueAt(table.getSelectedRow(), 0));
						String Adult = (String) model.getValueAt(table.getSelectedRow(), 5);
						String Kid = (String) model.getValueAt(table.getSelectedRow(), 6);
						String Infant = (String) model.getValueAt(table.getSelectedRow(), 7);
						int AdultNum = Integer.parseInt(Adult);
						int KidNum = Integer.parseInt(Kid);
						int InfantNum = Integer.parseInt(Infant);
						int remain = Integer.parseInt(Num) + AdultNum + KidNum + InfantNum;
						if((adultNum + kidNum + infantNum) > remain) {
							JOptionPane.showMessageDialog(null, "修改訂單失敗,剩餘名額不足",
									" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
						}else {
							if(ModifyOrder.modifyOrder(ID, (String) model.getValueAt(table.getSelectedRow(), 2), "" + adultNum,
									"" + kidNum, "" + infantNum).equals("修改失敗,此訂位代號不存在")) {
								JOptionPane.showMessageDialog(null, "修改訂單失敗,此訂位代號不存在",
										" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
							}else if(ModifyOrder.modifyOrder(ID, (String) model.getValueAt(table.getSelectedRow(), 2), "" + adultNum,
									"" + kidNum, "" + infantNum).equals("修改失敗,需於出發前24小時")) {
								JOptionPane.showMessageDialog(null, "修改訂單失敗,需於出發前24小時",
										" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
							}else {
								JOptionPane.showMessageDialog(null, "修改訂單成功",
										" yee遊網 旅遊系統", JOptionPane.INFORMATION_MESSAGE);
								textListener.textEmitted("updateBtn\n");
							}
						}
					}
				}
			}
		});
		
		add(scrollPane, BorderLayout.NORTH);
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
		this.textListener = listener;
	}
}
