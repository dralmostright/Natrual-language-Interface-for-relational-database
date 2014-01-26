import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Synonyms {
	public JTextField txtSym , txtTbl, txtCol;
	public JPanel AllPanel(){
		JPanel temp = new JPanel();
		temp.setOpaque(false);
		temp.setLayout(new GridLayout(3,1,10,10));
		temp.add(this.Synonym());
		temp.add(this.symColumn());
		temp.add(this.symTable());
		temp.setBorder(BorderFactory.createEmptyBorder(40, 200, 0, 200));
		
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BorderLayout());
		JLabel lbl = new JLabel(new ImageIcon("icons\\synonyms-panel.png"));
		lbl.setBorder(BorderFactory.createEmptyBorder(7,0,0,520));
		newPanel.add(lbl,BorderLayout.NORTH);
		newPanel.add(temp, BorderLayout.CENTER);
		newPanel.add(this.AddReset(),BorderLayout.SOUTH);
		newPanel.setOpaque(false);
		return newPanel;
	}
	
	public JPanel CreateListPanel(){
		JPanel temp = new JPanel();
		temp.setLayout(new BorderLayout());
		temp.add(this.AllPanel(),BorderLayout.NORTH);
		JPanel hold = new JPanel();
		hold.setLayout(new FlowLayout());
		hold.add(new ExitOut().ExitOutFunc());
		hold.setOpaque(false);
		hold.setBorder(BorderFactory.createEmptyBorder(0, 128, 0, 0));
		temp.add(hold, BorderLayout.CENTER);
		temp.setOpaque(false);
		return temp;
	}
	
	public JPanel Synonym(){
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(2,1,5,5));
		JLabel lblText = new JLabel("Synonym For Column");
		txtSym = new JTextField(20);
		txtSym.setPreferredSize(new Dimension(200, 30));
		temp.add(lblText);
		temp.add(txtSym);
		temp.setOpaque(false);
		return temp;
	}
	
	public JPanel symTable(){
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(2,1,5,5));
		JLabel lblText = new JLabel("Name of Column");
		txtCol = new JTextField(20);
		temp.add(lblText);
		temp.add(txtCol);
		temp.setOpaque(false);
		return temp;
	}
	
	public JPanel symColumn(){
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(2,1,5,5));
		JLabel lblText = new JLabel("Name of Table");
		txtTbl = new JTextField(20);
		temp.add(lblText);
		temp.add(txtTbl);
		temp.setOpaque(false);
		return temp;
	}
	
	public JPanel AddReset(){
		JPanel temp = new JPanel();
		temp.setLayout(new FlowLayout());
		JLabel add = new JLabel(new ImageIcon("icons\\add.png","add"));
		add.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		JLabel reset = new JLabel(new ImageIcon("icons\\reset.png","reset"));
		reset.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		addResetHandler hdl = new addResetHandler();
		add.addMouseListener(hdl);
		reset.addMouseListener(hdl);
		JPanel hold = new JPanel();
		hold.setLayout(new GridLayout(1,1));
		temp.add(add);
		temp.add(reset);
		temp.setBorder(BorderFactory.createEmptyBorder(20,0,0,120));
		hold.add(temp);
		hold.setOpaque(false);
		temp.setOpaque(false);
		return hold;
	}
	
	public class addResetHandler extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel lbl = (JLabel) e.getSource();
			lbl.setBorder(BorderFactory.createLineBorder(new Color(181,181,181), 1));
			String test = (((ImageIcon)lbl.getIcon()).toString());
			if(test.equals("add")){
				if(txtCol.getText().equals("") || txtSym.getText().equals("") || txtTbl.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Sorry! All fields are required.", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\error.png"));
				}
				else{
					if(SqlLibrary.insert("project", "synonym (syname,scolumn,stable) ", "'"+txtSym.getText()+"'" +
							" ,'"+txtCol.getText()+"' ,'"+txtTbl.getText()+"'")){
						ArrayList<Object> obj = new ArrayList<Object>();
						obj.add(ListSynonyms.tblcls.getRowCount()+1);
						obj.add(txtSym.getText());
						obj.add(txtTbl.getText());
						obj.add(txtCol.getText());
						ListSynonyms.tblcls.AddRow(obj);
						JOptionPane.showMessageDialog(null, "The Synonymn is inserted successfully.", "Success", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\correct.png"));
					}
				}
			}
			
			else if(test.equals("reset")){
				txtCol.setText("");
				txtSym.setText("");
				txtTbl.setText("");
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel lbl = (JLabel) e.getSource();
			//lbl.setBorder(BorderFactory.createLineBorder(new Color(181,181,181), 1));
			String test = (((ImageIcon)lbl.getIcon()).toString()).toLowerCase();
			lbl.setIcon(new ImageIcon("icons\\"+test+"-hover.png",test));
			lbl.setCursor(new Cursor(12));
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel lbl = (JLabel) e.getSource();
			//lbl.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
			String test = (((ImageIcon)lbl.getIcon()).toString()).toLowerCase();
			lbl.setIcon(new ImageIcon("icons\\"+test+".png",test));
		}
	}
}
