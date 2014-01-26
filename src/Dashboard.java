import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

	/**
	 * This the main GUI of the of the application
	 * It consists of the listing of the overall operation 
	 * related to the application. 
	 */
@SuppressWarnings({ "serial", "unused" })
public class Dashboard extends JFrame{
	
	public Dashboard(){
		
		super("ANLID Interface");
		//System.out.println(Cursor.HAND_CURSOR);
		this.setContentPane(new JLabel(new ImageIcon("background.png")));
		this.setLayout(new FlowLayout());
		this.add(AllGui.createPanel());
		this.pack();
		this.setSize(808, 530);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
		public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception unused) {
			; // Ignore exception because we can't do anything. Will use
				// default.
		}
		new Dashboard();
	}

}
