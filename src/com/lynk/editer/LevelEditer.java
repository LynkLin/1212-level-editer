package com.lynk.editer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.apache.commons.lang.StringUtils;

import java.awt.Color;

public class LevelEditer extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelEditer frame = new LevelEditer();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private List<LevelButton> buttons;
	private JSpinner uiStep;
	private JFileChooser uiChooserFile;
	private BufferedImage image;

	public LevelEditer() {
		buttons = new ArrayList<LevelButton>();
		initComponents();
	}
	
	private void initComponents() {
		setTitle("1212 Level Editer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 640);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.NORTH);
			panel.setLayout(new MigLayout("", "[][60px:60px:60px][]50[]", "[]"));
			{
				JLabel label = new JLabel("STEP:");
				panel.add(label, "cell 0 0");
			}
			{
				uiStep = new JSpinner();
				uiStep.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				panel.add(uiStep, "cell 1 0,growx");
			}
			{
				JButton uiSave = new JButton("SAVE LEVEL");
				uiSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						uiSaveActionPerformed(e);
					}
				});
				panel.add(uiSave, "cell 2 0");
			}
			{
				JButton uiLoad = new JButton("LOAD LEVEL");
				uiLoad.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						uiLoadActionPerformed(e);
					}
				});
				panel.add(uiLoad, "cell 3 0");
			}
		}
		{
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			panel.setLayout(new MigLayout("", "[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]", "[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]-1[40px:40px:40px]"));
			for(int i = 1; i <= 12; i++) {
				JLabel label = new JLabel(Integer.toString(i));
				label.setBorder(new LineBorder(new Color(0, 0, 0)));
				label.setOpaque(true);
				label.setForeground(Color.WHITE);
				label.setBackground(Color.DARK_GRAY);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(label, "cell " + i +" 0,grow");
			}
			for(int i = 1; i <= 12; i++) {
				JLabel label = new JLabel(Integer.toString(i));
				label.setBorder(new LineBorder(new Color(0, 0, 0)));
				label.setOpaque(true);
				label.setForeground(Color.WHITE);
				label.setBackground(Color.DARK_GRAY);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(label, "cell 0 " + i +",grow");
			}
			for (int row = 1; row <= 12; row++) {
				for (int column = 1; column <= 12; column++) {
					LevelButton button = new LevelButton();
					panel.add(button, "cell " + column + " " + row + ",grow");
					buttons.add(button);
				}
			}
		}
		{
			uiChooserFile = new JFileChooser();
			uiChooserFile.setFileFilter(new FileFilter() {
				
				@Override
				public String getDescription() {
					return ".png";
				}
				
				@Override
				public boolean accept(File file) {
					if (file.isDirectory()) {
						return true;
					} else {
						return file.getAbsolutePath().toLowerCase().endsWith(".png");
					}
				}
			});
		}
	}
	protected void uiSaveActionPerformed(ActionEvent evt) {
		if (uiChooserFile.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = uiChooserFile.getSelectedFile();
			try {
				image = new BufferedImage(12, 13, BufferedImage.TYPE_INT_ARGB);
				for (int i = 0; i < buttons.size(); i++) {
					LevelButton button = buttons.get(i);
					image.setRGB(i % 12, i / 12, button.getImgColor().getRGB());
				}
				
				String binaryStr = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(uiStep.getValue().toString())), 12, "0");
				for (int i = 0; i < binaryStr.length(); i++) {
					if("1".equals(binaryStr.substring(i, i + 1))) {
						image.setRGB(i, 12, LevelButton.IMG_COLOR_STEP.getRGB());
					} else {
						image.setRGB(i, 12, LevelButton.IMG_COLOR_EMPTY.getRGB());
					}
				}
				ImageIO.write(image, "png", file);
				JOptionPane.showMessageDialog(this, "Save OK!", "DONE!", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	protected void uiLoadActionPerformed(ActionEvent evt) {
		if(uiChooserFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			File file = uiChooserFile.getSelectedFile();
			try {
				image = ImageIO.read(file);
				for(int x = 0; x < 12; x++) {
					for (int y = 0; y < 12; y++) {
						int color = image.getRGB(x, y);
						buttons.get(y * 12 + x).setImgColor(color);
					}
				}
				String binaryStr = "";
				for (int i = 0; i < 12; i++) {
					int color = image.getRGB(i, 12);
					binaryStr += color == LevelButton.IMG_COLOR_STEP.getRGB()? "1": "0";
					uiStep.setValue(Integer.valueOf(binaryStr, 2));
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
