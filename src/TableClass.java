import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings({ "serial", "unused" })
class TableClass extends AbstractTableModel {
	 String columns[];
     ArrayList<ArrayList<Object>> data = null;
     public TableClass(String[] col,String[] flds, String query)
     {
    	columns = col;
        data = SqlLibrary.selectTable(query,flds);
     }
     
     @Override
     public String getColumnName(int columnIndex)
     {
         return columns[columnIndex];
     }
     @Override
     public int getRowCount()
     {
         return data.size();
     }
     @Override 
     public int getColumnCount()
     {
         return columns.length;
     }
     @Override
     public Object getValueAt(int row, int col)
     {
         return data.get(row).get(col);
     }
     @Override
     public void setValueAt(Object aValue, int rowIndex, int colIndex)
     {
         data.get(rowIndex).set(colIndex,(String)aValue);
         fireTableCellUpdated(rowIndex,colIndex);
     }
     @Override
     public boolean isCellEditable(int row, int col)
     {
         return false;
     }
     public void deleteRow(int row)
     {
         ArrayList<Object> temp = data.get(row);
         data.remove(row);
         fireTableRowsDeleted(row,row);            
     }
     public void AddRow(ArrayList<Object> obj){
    	 int row = this.getRowCount();
    	 int count = this.getRowCount()+1;
    	 data.add(obj);
    	 fireTableRowsInserted(row, row);
     }
     public void UpdateTow(ArrayList<Object> obj, int index){
    	data.remove(index);
    	data.add(index, obj);
    	fireTableRowsUpdated(index, index);
     }

}