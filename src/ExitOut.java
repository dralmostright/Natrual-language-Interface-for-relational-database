import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ExitOut {

	public JPanel ExitOutFunc(){
		JPanel temp = new JPanel();
		temp.setLayout(new FlowLayout());
		JPanel mid = new JPanel();
		mid.setLayout(new GridLayout(1,2,20,20));
		//mid.setBorder(BorderFactory.createEmptyBorder(20, 450, 0, 0));
		JLabel lblLogout = new JLabel(new ImageIcon("icons\\logout.png","logout"));
		JLabel lblDashboard = new JLabel(new ImageIcon("icons\\home.png","home"));
		lblLogout.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		lblDashboard.setToolTipText("Back to dashboard");
		lblDashboard.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		lblLogout.setToolTipText("Logout");
		hdlevent hdl = new hdlevent();
		lblLogout.addMouseListener(hdl);
		lblDashboard.addMouseListener(hdl);
		mid.add(lblLogout);
		mid.add(lblDashboard);
		mid.setOpaque(false);
		temp.add(mid);
		temp.setOpaque(false);
		return temp;
	}
	
	public class hdlevent extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent event) {
			JLabel lbl = (JLabel) event.getSource();
			String test = (((ImageIcon)lbl.getIcon()).toString());
			if(test.equals("Logout")){
				JFrame parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, AllGui.allPanels);
				parent.dispose();
				new LoginPage();
			}
			
			else if(test.equals("home")){
				AllGui.layout.show(AllGui.allPanels, "Dash");
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel lbl = (JLabel) e.getSource();
			String test = (((ImageIcon)lbl.getIcon()).toString()).toLowerCase();
			lbl.setIcon(new ImageIcon("icons\\"+test+"-hover.png",test));
			//lbl.setBorder(BorderFactory.createLineBorder(new Color(181,181,181), 1));
			lbl.setCursor(new Cursor(12));
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel lbl = (JLabel) e.getSource();
			String test = (((ImageIcon)lbl.getIcon()).toString()).toLowerCase();
			lbl.setIcon(new ImageIcon("icons\\"+test+".png",test));
			//lbl.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		}
	}
}
