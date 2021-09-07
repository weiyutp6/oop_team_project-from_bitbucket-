package Gui;

import javax.swing.*;

public class app {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			try {
				new MainFrame();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
