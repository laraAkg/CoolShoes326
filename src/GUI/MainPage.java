package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPage {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Cool Shoes");
		JPanel topPanel = new JPanel();
		JPanel downPanel = new JPanel();
		JLabel order = new JLabel("Bestellung");
		JButton historyButton = new JButton();
		topPanel.setLayout(new BorderLayout());

		
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		order.setFont(new Font("Tahoma", Font.PLAIN, 40));

		topPanel.setBackground(Color.WHITE);
		downPanel.setBackground(Color.WHITE);
		
		topPanel.add(order, BorderLayout.WEST);
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(downPanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setSize(900,600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
