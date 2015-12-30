package com.lynk.editer;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class LevelButton extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static final Color SHOW_COLOR_EMPTY = new Color(255, 255, 255);
	public static final Color SHOW_COLOR_RED = new Color(232, 106, 130);
	public static final Color SHOW_COLOR_GREEN = new Color(152, 220, 85);
	public static final Color SHOW_COLOR_BLUE = new Color(92, 190, 229);
	public static final Color SHOW_COLOR_YELLOW = new Color(254, 198, 61);
	
	public static final Color IMG_COLOR_EMPTY = new Color(255, 255, 255);
	public static final Color IMG_COLOR_STEP = new Color(0, 0, 0);
	public static final Color IMG_COLOR_RED = new Color(255, 0, 0);
	public static final Color IMG_COLOR_GREEN = new Color(0, 255, 0);
	public static final Color IMG_COLOR_BLUE = new Color(0, 0, 255);
	public static final Color IMG_COLOR_YELLOW = new Color(255, 255, 0);

	private CardLayout card;
	private JLabel uiColor;
	
	public Color getImgColor() {
		if (SHOW_COLOR_RED.equals(uiColor.getBackground())) {
			return IMG_COLOR_RED;
		} else if (SHOW_COLOR_GREEN.equals(uiColor.getBackground())) {
			return IMG_COLOR_GREEN;
		} else if (SHOW_COLOR_BLUE.equals(uiColor.getBackground())) {
			return IMG_COLOR_BLUE;
		} else if (SHOW_COLOR_YELLOW.equals(uiColor.getBackground())) {
			return IMG_COLOR_YELLOW;
		} else {
			return IMG_COLOR_EMPTY;
		}
	}
	
	public void setImgColor(int imageColor) {
		if (IMG_COLOR_RED.getRGB() == imageColor) {
			uiColor.setBackground(SHOW_COLOR_RED);
		} else if (IMG_COLOR_GREEN.getRGB() == imageColor) {
			uiColor.setBackground(SHOW_COLOR_GREEN);
		} else if (IMG_COLOR_BLUE.getRGB() == imageColor) {
			uiColor.setBackground(SHOW_COLOR_BLUE);
		} else if (IMG_COLOR_YELLOW.getRGB() == imageColor) {
			uiColor.setBackground(SHOW_COLOR_YELLOW);
		} else {
			uiColor.setBackground(IMG_COLOR_EMPTY);
		}
	}
	
	public LevelButton() {
		super();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		initComponents();
		initListener();
	}
	private void initComponents() {
		card = new CardLayout(0, 0);
		setLayout(card);
		{
			uiColor = new JLabel(" ");
			uiColor.setHorizontalAlignment(SwingConstants.CENTER);
			uiColor.setBackground(SHOW_COLOR_EMPTY);
			uiColor.setOpaque(true);
			add(uiColor, "bg");
		}
	}
	private void initListener() {
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (SHOW_COLOR_RED.equals(uiColor.getBackground())) {
					uiColor.setBackground(SHOW_COLOR_GREEN);
				} else if (SHOW_COLOR_GREEN.equals(uiColor.getBackground())) {
					uiColor.setBackground(SHOW_COLOR_BLUE);
				} else if (SHOW_COLOR_BLUE.equals(uiColor.getBackground())) {
					uiColor.setBackground(SHOW_COLOR_YELLOW);
				} else if (SHOW_COLOR_YELLOW.equals(uiColor.getBackground())) {
					uiColor.setBackground(SHOW_COLOR_EMPTY);
				} else if (SHOW_COLOR_EMPTY.equals(uiColor.getBackground())){
					uiColor.setBackground(SHOW_COLOR_RED);
				}
			}
			
		});
	}
}
