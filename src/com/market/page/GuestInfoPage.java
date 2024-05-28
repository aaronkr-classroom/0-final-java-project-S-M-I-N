package com.market.page;

import javax.swing.*;
import java.awt.*;

public class GuestInfoPage extends JPanel {
	
	public GuestInfoPage(JPanel panel) {
		Font ft;
		ft = new Font("함초롬돋움", Font.BOLD, 15);
		
		setLayout(null);
		
		Rectangle rect = panel.getBounds();
		System.out.println(rect);
		setOreferredSize(rect.getSize());
	}

	public static void main(String[] args) {

	}

}
