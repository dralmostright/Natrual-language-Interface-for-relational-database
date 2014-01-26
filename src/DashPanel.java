import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
	/**
	 * This the main GUI of the of the application
	 * It consists of the listing of the overall operation 
	 * related to the application. 
	 */
@SuppressWarnings({"unused" })
public class DashPanel{
	
	public static CardLayout cardLayout = new CardLayout();
	
	private String[] stLbls = {"Query","addOP","list-OP","func","showP","func"};
	private JLabel btnQuery, btnAddFunction, btnListFunctions, btnAddSynonyms, btnListSynonyms,btnQuerySQL;
	private JLabel[] btns = {btnQuery, btnAddFunction, btnListFunctions, btnAddSynonyms, btnListSynonyms,btnQuerySQL};
	private String[] icons = {"query.png","functions-add.png","functions-list.png","synonyms-add.png",
							   "synonyms-list.png","query-sql.png"};
	private String[] labels = {"query-label.png", "fun-label.png","fun-list-label.png","synonyms-add-label.png"
							   ,"synonyms-list-label.png","query-lbl-sql.png"};
	
	public DashPanel(){
		
	}
	
	public JPanel CreateDashboard(){
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 150, 0, 150));
		panel.setLayout(new GridLayout(2,3,40,30));
		Font fnt = new Font("Times New Roman",Font.BOLD,12);
		for(int i = 0; i < 6;i ++){
			if(i > 5)
				break;
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			JLabel lbl = new JLabel(new ImageIcon("icons\\"+labels[i]));
			btns[i] = new JLabel(new ImageIcon("icons\\"+icons[i],stLbls[i]));
			btns[i].setPreferredSize(new Dimension(100,100));
			hdlevent hdl = new hdlevent();
			btns[i].addMouseListener(hdl);
			temp.add(btns[i], BorderLayout.NORTH);
			temp.add(lbl, BorderLayout.CENTER);
			temp.setOpaque(false);
			panel.add(temp);
		}
		//panel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		return panel;
	}
	
	public JPanel CreateAndList(){
		JPanel allGui = new JPanel();
		allGui.setLayout(new BorderLayout());
		JLabel lblhold = new JLabel(new ImageIcon("icons\\dashboard-panel.png"));
		lblhold.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 550));
		allGui.add(lblhold,BorderLayout.NORTH);
		allGui.add(this.CreateDashboard(),BorderLayout.CENTER);
		allGui.add(this.LogExit(),BorderLayout.SOUTH);
		allGui.setOpaque(false);
		return allGui;
	}
	
	public JPanel LogExit(){
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(1,2,20,10));
		JLabel btnLogout = new JLabel(new ImageIcon("icons\\logout.png","logout"));
		JLabel btnExit = new JLabel(new ImageIcon("icons\\exit.png","exit"));
		hdlevent event =  new hdlevent();
		btnLogout.addMouseListener(event);
		btnExit.addMouseListener(event);
		//JButton btnLogout = new JButton("Logout");
		//btnLogout.setCursor(new Cursor(HAND_CURSOR));
//		btnLogout.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				//dispose();
//				//new LoginPage();
//			}
//		});
//		//JButton btnExit = new JButton("Exit");
//		//btnExit.setCursor(new Cursor(HAND_CURSOR));
//		btnExit.setPreferredSize(new Dimension(100, 30));
//		btnExit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				System.exit(0);
//			}
//		});
		temp.add(btnLogout);
		temp.add(btnExit);
		//temp.setBorder(BorderFactory.createEmptyBorder(20, 150, 0, 0));
		temp.setBorder(BorderFactory.createEmptyBorder(10, 310, 0, 150));
		
		temp.setOpaque(false);
		return temp;
	}
//	public class btnHandler implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent event) {
//			JButton btns = (JButton) event.getSource();
//			String test = (((ImageIcon)btns.getIcon()).toString());
//			System.out.println(test);
//			if(test.equals("Query")){
//				AllGui.layout.show(AllGui.allPanels, "Query");
//			}
//			else if(test.equals("func")){
//				AllGui.layout.show(AllGui.allPanels, "Dash");
//			}
//			else if(test.equals("showP")){
//				System.out.println("The code is not working");
//				AllGui.layout.show(AllGui.allPanels, "ListSym");
//			}
//			else if(test.equals("addOP")){
//				System.out.println("The code is not working");
//				AllGui.layout.show(AllGui.allPanels, "AddFun");
//			}
//			else if(test.equals("showP")){
//				System.out.println("The code is not working");
//				AllGui.layout.show(AllGui.allPanels, "ListSym");
//			}
//		}
//		
//	}
	
	public class hdlevent extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent event) {
			JLabel lbl = (JLabel) event.getSource();
			String test = (((ImageIcon)lbl.getIcon()).toString());
			if(test.equals("Query")){
				AllGui.layout.show(AllGui.allPanels, "Query");
			}
			else if(test.equals("func")){
				AllGui.layout.show(AllGui.allPanels, "AddSym");
			}
			else if(test.equals("showP")){
				System.out.println("The code is not working");
				AllGui.layout.show(AllGui.allPanels, "ListSym");
			}
			else if(test.equals("addOP")){
				System.out.println("The code is not working");
				AllGui.layout.show(AllGui.allPanels, "AddFun");
			}
			else if(test.equals("list-OP")){
				System.out.println("The code is not working");
				AllGui.layout.show(AllGui.allPanels, "ListFun");
			}
			else if(test.equals("showP")){
				System.out.println("The code is not working");
				AllGui.layout.show(AllGui.allPanels, "ListSym");
			}
			else if(test.equals("logout")){
				JFrame parent = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, AllGui.allPanels);
				parent.dispose();
				new LoginPage();
			}
			else if(test.equals("exit")){
				System.exit(0);
			}
		}

		@Override
		public void mouseEntered(MouseEvent event) {
			// TODO Auto-generated method stub
			JLabel lbl = (JLabel) event.getSource();
			String test = (((ImageIcon)lbl.getIcon()).toString()).toLowerCase();
			if(test.equals("exit") || test.equals("logout"))
				lbl.setIcon(new ImageIcon("icons\\"+test+"-hover.png",test));
			//lbl.setCursor(new Cursor(12));
			else
				lbl.setBorder(BorderFactory.createLineBorder(new Color(181,181,181), 1));
			lbl.setCursor(new Cursor(12));
		}
		
		public void mouseExited(MouseEvent e) {
			JLabel lbl = (JLabel) e.getSource();
			String test = (((ImageIcon)lbl.getIcon()).toString()).toLowerCase();
			if(test.equals("exit") || test.equals("logout"))
				lbl.setIcon(new ImageIcon("icons\\"+test+".png",test));
			//lbl.setCursor(new Cursor(12));
			else
				lbl.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		}
	}
}