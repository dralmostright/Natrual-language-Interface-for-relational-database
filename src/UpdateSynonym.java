import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateSynonym {
	public static JTextField txtSym , txtTbl, txtCol;
	public static int curRow;
	public static String prevName;
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
		JLabel lbl = new JLabel(new ImageIcon("icons\\synonym-update-panel.png"));
		lbl.setBorder(BorderFactory.createEmptyBorder(9,0,0,520));
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
		JLabel add = new JLabel(new ImageIcon("icons\\update.png","update"));
		add.setToolTipText("Update Synonym");
		add.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		JLabel reset = new JLabel(new ImageIcon("icons\\reset.png","reset"));
		reset.setToolTipText("Reset");
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
			//lbl.setBorder(BorderFactory.createLineBorder(new Color(181,181,181), 1));
			String test = (((ImageIcon)lbl.getIcon()).toString());
			if(test.equals("update")){
				if(txtCol.getText().equals("") || txtSym.getText().equals("") || txtTbl.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Sorry! All fields are required.", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\error.png"));
				}
				else{
					String syn = txtSym.getText();
					String col = txtCol.getText();
					String tbl = txtTbl.getText();
					int row = UpdateSynonym.curRow;
					ArrayList<Object> temp = new ArrayList<Object>();
					temp.add(row +1);
					temp.add(syn);
					temp.add(col);
					temp.add(tbl);
					if(SqlLibrary.update("synonym", "syname = '"+syn+"' ,scolumn = '"+col+"' ,stable = '"+tbl+"'",
							"syname like '"+UpdateSynonym.prevName+"%'")){
					ListSynonyms.tblcls.UpdateTow(temp, row);
					JOptionPane.showMessageDialog(null, "The Synonymn is updated successfully.", "Success", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\correct.png"));
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
