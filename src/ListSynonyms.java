import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.*;

public class ListSynonyms {
	public static JTable listSyms;
	public static String[] holdStr = {"S.n","Symonym","Column","Table"};
	public static String[] flds = {"syname","scolumn","stable"};
	public static TableClass tblcls = new TableClass(holdStr,flds,"select syname,scolumn,stable from synonym");
	
	public JPanel AllGui(){
		JPanel temp = new JPanel();
		temp.setOpaque(false);
		temp.setLayout(new BorderLayout());
		JLabel icons = new JLabel(new ImageIcon("icons\\synonyms-list-panel.png")); 
		icons.setBorder(BorderFactory.createEmptyBorder(7, 0, 10, 525));
		temp.add(icons, BorderLayout.NORTH);
		temp.add(this.ListTable(),BorderLayout.CENTER);
		temp.add(this.EditDelete(),BorderLayout.SOUTH);
		temp.setOpaque(false);
		return temp;
	}
	
	public JPanel comBined(){
		JPanel temp = new JPanel();
		temp.setLayout(new BorderLayout());
		temp.add(this.AllGui(), BorderLayout.NORTH);JPanel hold = new JPanel();
		hold.setLayout(new FlowLayout());
		hold.add(new ExitOut().ExitOutFunc());
		hold.setOpaque(false);
		hold.setBorder(BorderFactory.createEmptyBorder(0, 480, 0, 0));
		temp.add(hold, BorderLayout.CENTER);
		temp.setOpaque(false);
		return temp;
	}
	
	public JPanel EditDelete(){
		JPanel temp = new JPanel();
		JLabel lblEdit = new JLabel(new ImageIcon("icons\\edit.png","edit"));
		lblEdit.setToolTipText("Edit Synonym");
		JLabel lblDel = new JLabel(new ImageIcon("icons\\delete.png","delete"));
		lblDel.setToolTipText("Delete Synonym");
		hdlevent evt = new hdlevent();
		lblDel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		lblEdit.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		lblDel.addMouseListener(evt);
		lblEdit.addMouseListener(evt);
		temp.setLayout(new FlowLayout());
		temp.add(lblEdit);
		temp.setOpaque(false);
		temp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 500));
		temp.add(lblDel);
		return temp;
	}
	
	
	
	public JPanel ListTable (){
		JPanel temp = new JPanel();
		JLabel lbl = new JLabel("Select the row to Edit or delete.");
		listSyms = new JTable(tblcls){
			private static final long serialVersionUID = 752495781927516743L;

				public Component prepareRenderer(TableCellRenderer r, int rows,
						int columns) {
					Component c = super.prepareRenderer(r, rows, columns);

					if (rows % 2 == 0) {
						c.setBackground(new Color(243,241,232));
					} else {
						c.setBackground(new Color(195,195,185));
					}

					if (isCellSelected(rows, columns)) {
						c.setBackground(new Color(52, 123,242));
					}

					return c;
				}};
		listSyms.setPreferredScrollableViewportSize(new Dimension(700,250));
		listSyms.setVerifyInputWhenFocusTarget(true);
		listSyms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSyms.getTableHeader().setForeground(new Color(51,122,242));
		listSyms.getTableHeader().setBackground(new Color(191,192,167));
		listSyms.setRowHeight(25);
		listSyms.setOpaque(false);
		JScrollPane jps = new JScrollPane(listSyms);
		jps.setBorder(BorderFactory.createLineBorder(new Color(191,192,167) ,1));
		jps.setOpaque(false);
		temp.setLayout(new BorderLayout());
		temp.setOpaque(false);
		temp.add(lbl, BorderLayout.NORTH);
		temp.add(jps, BorderLayout.CENTER);
		return temp;
	}
	
	public class hdlevent extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent event) {
			JLabel lbl = (JLabel) event.getSource();
			String test = (((ImageIcon)lbl.getIcon()).toString());
			int row = listSyms.convertRowIndexToModel(listSyms.getSelectedRow());
			if(listSyms.isRowSelected(row)){
				if(test.equals("edit")){
					UpdateSynonym.txtSym.setText((String)listSyms.getValueAt(row, 1));
					UpdateSynonym.txtCol.setText((String)listSyms.getValueAt(row, 2));
					UpdateSynonym.txtTbl.setText((String)listSyms.getValueAt(row, 3));
					UpdateSynonym.curRow = row;
					UpdateSynonym.prevName = (String)listSyms.getValueAt(row, 1);
					AllGui.layout.show(AllGui.allPanels, "UpdateSym");
				}
				
				else if(test.equals("delete")){
					int i = JOptionPane.showConfirmDialog(null, "Do You want to delete the synonym.", "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,new ImageIcon("icons\\alert.png"));
					if(i == JOptionPane.OK_OPTION){
						if(SqlLibrary.DeleteRow("synonym", "syname like '"+listSyms.getValueAt(row, 1)+"%'")){
							tblcls.deleteRow(row);
							JOptionPane.showMessageDialog(null, "The Synonymn is deleted successfully.", "Success", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\correct.png"));
							}
						}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Oops! You forget to select a row.", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\error.png"));
			}
			
		}

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
