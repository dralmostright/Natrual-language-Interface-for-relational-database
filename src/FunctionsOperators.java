import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class FunctionsOperators {
	public JTextField txtSym , txtOP;
	public JComboBox<String> box;
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
		JLabel lbl = new JLabel(new ImageIcon("icons\\func-op-panel.png"));
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
		JLabel lblText = new JLabel("Synonym For Function or Operator");
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
		JLabel lblText = new JLabel("Fucntion or operator e.g highest: max , greater : >");
		txtOP = new JTextField(20);
		temp.add(lblText);
		temp.add(txtOP);
		temp.setOpaque(false);
		return temp;
	}
	
	public JPanel symColumn(){
		JPanel temp = new JPanel();
		String[] boxText = {"Select the statement","Select","Where"};
		box = new JComboBox<>(boxText);
		temp.setLayout(new GridLayout(2,1,5,5));
		JLabel lblText = new JLabel("Function or Operator that apper in:");
		temp.add(lblText);
		temp.add(box);
		temp.setOpaque(false);
		return temp;
	}
	
	public JPanel AddReset(){
		JPanel temp = new JPanel();
		temp.setLayout(new FlowLayout());
		JLabel add = new JLabel(new ImageIcon("icons\\add.png","add"));
		add.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		add.setToolTipText("Add to dataDictionary");
		JLabel reset = new JLabel(new ImageIcon("icons\\reset.png","reset"));
		reset.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		reset.setToolTipText("Reset");
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
				if(txtOP.getText().equals("") || txtSym.getText().equals("") || box.getSelectedIndex() == 0){
					JOptionPane.showMessageDialog(null, "Sorry! All fields are required.", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\error.png"));
				}
				else{
					if(SqlLibrary.insert("project", "funcop (fsynonym,operator,type) ", "'"+txtSym.getText()+"'" +
							" ,'"+txtOP.getText()+"' ,'"+(box.getSelectedIndex() == 1 ? 11 : 22)+"'")){
						ArrayList<Object> obj = new ArrayList<Object>();
						obj.add(ListFunctionOperators.tblcls.getRowCount()+1);
						obj.add(txtSym.getText());
						obj.add(txtOP.getText());
						obj.add(box.getSelectedItem());
						ListFunctionOperators.tblcls.AddRow(obj);
						JOptionPane.showMessageDialog(null, "The Synonymn for Function/Operator is inserted successfully.", "Success", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\correct.png"));
					}
				}
			}
			
			else if(test.equals("reset")){
				txtOP.setText("");
				txtSym.setText("");
				box.setSelectedIndex(0);
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