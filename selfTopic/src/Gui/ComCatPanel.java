package Gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComCatPanel extends JPanel implements ActionListener{

	private JButton ETFButton;
	private JButton SemiButton;
	private JButton MoneyButton;
	private JButton EEButton;
	private JButton NetButton;
	private JButton LightButton;
	private JButton SteelButton;
	private JButton CheButton;
	private JButton PlaButton;
	private JButton GGButton;
	private JButton BuildButton;
	private JButton TradeButton;
	private JButton MoveButton;
	private JButton TripButton;
	private JButton WeaveButton;
	private JButton FoodButton;
	
	private StringListener textListener;
	
	public ComCatPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("產業別");
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		ETFButton = new JButton("ETF");
		SemiButton = new JButton("半導體業");
		MoneyButton = new JButton("金融保險業");
		EEButton = new JButton("電子零組件業");
		NetButton = new JButton("通信網路業");
		LightButton = new JButton("光電業");
		SteelButton = new JButton("鋼鐵工業");
		CheButton = new JButton("化學工業");
		PlaButton = new JButton("塑膠工業");
		GGButton = new JButton("生技醫療業");
		BuildButton = new JButton("建材營造業");
		TradeButton = new JButton("貿易百貨業");
		MoveButton = new JButton("航運業");
		TripButton = new JButton("觀光事業");
		WeaveButton = new JButton("紡織纖維");
		FoodButton = new JButton("食品工業");
		
		ETFButton.addActionListener(this);
		SemiButton.addActionListener(this);
		MoneyButton.addActionListener(this);
		EEButton.addActionListener(this);
		NetButton.addActionListener(this);
		LightButton.addActionListener(this);
		SteelButton.addActionListener(this);
		CheButton.addActionListener(this);
		PlaButton.addActionListener(this);
		GGButton.addActionListener(this);
		BuildButton.addActionListener(this);
		TradeButton.addActionListener(this);
		MoveButton.addActionListener(this);
		TripButton.addActionListener(this);
		WeaveButton.addActionListener(this);
		FoodButton.addActionListener(this);
		
        setLayout(new GridLayout(8,2));
		
		add(ETFButton);
		add(SemiButton);
		add(MoneyButton);
		add(EEButton);
		add(NetButton);
		add(LightButton);
		add(SteelButton);
		add(CheButton);
		add(PlaButton);
		add(GGButton);
		add(BuildButton);
		add(TradeButton);
		add(MoveButton);
		add(TripButton);
		add(WeaveButton);
		add(FoodButton);
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}
	
    //////Set the Action While Clicked//////
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == ETFButton) {
			if(textListener != null) {
				try {
					textListener.textEmitted("ETF");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == SemiButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("半導體業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == MoneyButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("金融保險業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == EEButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("電子零組件業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == NetButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("通信網路業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == LightButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("光電業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == SteelButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("鋼鐵工業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == CheButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("化學工業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == PlaButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("塑膠工業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == GGButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("生技醫療業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == BuildButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("建材營造業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == TradeButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("貿易百貨業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == MoveButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("航運業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == TripButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("觀光事業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == WeaveButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("紡織纖維");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if(clicked == FoodButton){
			if(textListener != null) {
				try {
					textListener.textEmitted("食品工業");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
