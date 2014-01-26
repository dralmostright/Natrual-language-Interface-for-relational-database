import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings({ "rawtypes", "serial"})
public class QueryPanel extends JPanel {
	public JTextField txtQuestion;
	public JTextArea txtAnswer;
	public static JPanel resPanel;
	//public static JTable listSyms;
	Font fnt = new Font("Barkentina Test", Font.PLAIN, 15);
	public QueryPanel(){
		//Romance Fatal Serif Std
		//Barkentina Test,Bellota
		this.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.add(this.Question(),BorderLayout.NORTH);
		this.add(this.ShowQuery(), BorderLayout.CENTER);
		this.add(this.Answer(), BorderLayout.SOUTH);
	}
	
	public JPanel Question(){
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(2, 1));
		JLabel lblQuestion = new JLabel(new ImageIcon("icons\\nl-label.png"));
		lblQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuestion.setForeground(new Color(52,124,243));
		lblQuestion.setFont(fnt);
		JPanel hold = new JPanel();
		temp.add(lblQuestion);
		temp.add(this.fieldB());
		temp.setOpaque(false);
		JLabel lblhold = new JLabel(new ImageIcon("icons\\query-panel.png"));
		lblhold.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 550));
		hold.setLayout(new BorderLayout());
		hold.add(lblhold, BorderLayout.NORTH);
		hold.add(temp,BorderLayout.CENTER);
		hold.setOpaque(false);
		return hold;
	}
	
	public JPanel fieldB(){
		JPanel temp = new JPanel();
		temp.setLayout(new BorderLayout());
		txtQuestion = new JTextField(80);
		//txtQuestion.setBorder(BorderFactory.createLineBorder(new Color(10,167,90) , 1));
		JPanel btn = new JPanel();
		btn.setLayout(new GridLayout(1,1));
		btn.setOpaque(false);
		btn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		JButton btnExecute = new JButton("Execute");
		QueryHandler hdl = new QueryHandler();
		btnExecute.addActionListener(hdl);
		btn.add(btnExecute);
		temp.add(txtQuestion, BorderLayout.WEST);
		temp.add(btn, BorderLayout.EAST);
		temp.setOpaque(false);
		return temp;
	}
	
	public JPanel ShowQuery(){
		JPanel temp = new JPanel();
		temp.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		temp.setLayout(new BorderLayout());
		txtAnswer = new JTextArea(2,2);
		txtAnswer.setPreferredSize(new Dimension(500,50));
		txtAnswer.setBorder(BorderFactory.createLineBorder(new Color(184,184,184), 1));
		JLabel lbl = new JLabel(new ImageIcon("icons\\nl-sql-label.png"));
		lbl.setHorizontalAlignment(SwingConstants.LEFT);
		lbl.setForeground(new Color(5,115,68));
		lbl.setFont(fnt);
		temp.add(lbl,BorderLayout.NORTH);
		temp.add(txtAnswer,BorderLayout.CENTER);
		temp.setOpaque(false);
		return temp;
	}
	
	public JPanel Answer(){
		JPanel allInOne = new JPanel();
		resPanel = new JPanel();
		//resPanel.setSize(700, 50);
		String[] cols = {};
		Object[][] rows = {{}};
		//ArrayList<ArrayList<String>>list= new ArrayList<ArrayList<String>>(); 
		JTable listSyms = new JTable(rows,cols);
		JScrollPane pane = new JScrollPane(listSyms);
		//pane.setPreferredSize(new Dimension(700, 100));
		listSyms.setPreferredScrollableViewportSize(new Dimension(700,120));
		listSyms.setVerifyInputWhenFocusTarget(true);
		listSyms.setRowHeight(25);
		allInOne.setLayout(new BorderLayout());
		resPanel.setLayout(new FlowLayout());
		resPanel.add(pane);
		allInOne.add(resPanel,BorderLayout.CENTER);
		allInOne.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		JLabel lblans = new JLabel(new ImageIcon("icons\\nl-label-answer.png"));
		lblans.setHorizontalAlignment(SwingConstants.LEFT);
		lblans.setForeground(new Color(221,75,57));
		lblans.setFont(fnt);
		allInOne.add(lblans,BorderLayout.NORTH);
		JPanel hold = new JPanel();
		hold.setLayout(new FlowLayout());
		hold.add(new ExitOut().ExitOutFunc());
		hold.setOpaque(false);
		hold.setBorder(BorderFactory.createEmptyBorder(0, 480, 0, 0));
		allInOne.add(hold,BorderLayout.SOUTH);
		resPanel.setOpaque(false);
		allInOne.setOpaque(false);
		return allInOne;
	}
	
	public class QueryHandler implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String question = txtQuestion.getText();
			//System.out.println("demo is not completed");
			if(question.length() < 15){
				//throw exception
				System.out.println("demo is not completed");
			}
			else{
				try{
				resPanel.removeAll();
				txtAnswer.setText("");
				//check and list dependencies as collection
				System.out.println("What is happening here");
				Collection col = DependencyParser.GenerateList(question);
				
				//filer the unwanted noise from the collection
				ArrayList<String>list = HelperClass.FilterList(col);
				
				//divide the list in possible selection or projection list
				DivideList.PopulateList(list);
				
				//process the populate list and finally generate the query
				QueryGenerator.GetAll();
				String text = QueryGenerator.GenerateSelect()+"\n";
				text += QueryGenerator.GenerateFrom()+"\n";
				text += QueryGenerator.GenerateWhere();
				txtAnswer.setText(text);
				String[] heading = new String[QueryGenerator.select.size()];
				QueryGenerator.select.toArray(heading);
				ArrayList<ArrayList<String>> test = SqlLibrary.GetQueryResult(text, QueryGenerator.select);
				Object[][] result = new Object[test.size()][];
				for(int i = 0; i < test.size(); i++){
					ArrayList<String>temp = test.get(i);
					result[i] = new Object[test.size()];
					//temp.toArray(result[i]);
					for(int j = 0; j < temp.size(); j++){
						result[i][j] = temp.get(j);
						System.out.println(temp.get(j));
					}
				}
				
//				System.out.println("Two dimensional array.");
//				for(Object[] obj : result){
//					for(Object objs : obj){
//						System.out.println(objs);
//					}
//				}
				
				JTable tbl = new JTable(result,heading);
				tbl.setPreferredScrollableViewportSize(new Dimension(700,100));
				tbl.setVerifyInputWhenFocusTarget(true);
				tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tbl.getTableHeader().setForeground(new Color(51,122,242));
				tbl.getTableHeader().setBackground(new Color(191,192,167));
				tbl.setRowHeight(25);
				resPanel.add(new JScrollPane(tbl));
				repaint();
				revalidate();
				
				DivideList.projection.clear();// =new ArrayList<String>();
				DivideList.selection.clear();// =new ArrayList<String>();
				QueryGenerator.select.clear();// =new ArrayList<String>();
				QueryGenerator.from.clear();// =new ArrayList<String>();
				QueryGenerator.where.clear();// =new ArrayList<String>();
			}
			
			
			catch(Exception e){
				
			}
			}
		}
		public void GenearteList(){
			
		}
		
	}
	
}