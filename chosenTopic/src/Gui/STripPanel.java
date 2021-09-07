package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class STripPanel extends JPanel {
	
	private JButton orderButton;
	private JButton tripButton;
	private JPanel btnPanel;
	private JTable table;
	private DefaultTableModel model;
	
	private StringListener textListener;
	
	public String tripName;

	public STripPanel(Object[] tripOfList) {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("查看可報名行程");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		orderButton = new JButton("我要報名");
		btnPanel = new JPanel();
		
		btnPanel.setLayout(new FlowLayout());
		
		btnPanel.add(orderButton);
		
		//////表格內容(搜尋結果)//////
        String[] columNames = {"行程名稱"};
		
        DefaultTableModel model = new DefaultTableModel(columNames, 0);
        
        for(Object i : tripOfList) {
        	model.addRow(new Object[] {i});
        }
		
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(600, 400));
		table.setFillsViewportHeight(true);
		
		table.setRowHeight(30);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane, BorderLayout.NORTH);
		add(btnPanel, BorderLayout.SOUTH);
		
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "請選擇欲報名行程",
							" yee遊網 旅遊系統", JOptionPane.OK_OPTION);
				}else {
					JButton clicked = (JButton)e.getSource();
						
					if(clicked == orderButton) {
						if(textListener != null) {
							tripName = (String) model.getValueAt(table.getSelectedRow(), 0);
							textListener.textEmitted("orderBtnInSTrip\n");
						}
					}
			    }
			}
		});
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}
}

