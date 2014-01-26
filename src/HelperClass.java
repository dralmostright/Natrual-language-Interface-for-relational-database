import java.util.*;

/**
 *  This class consists of the methods that are frequently
 *  accessed and needed for the processing during the 
 *  dividing the dependencies into selection and projection
 *  items.
 */

public class HelperClass {
	
	@SuppressWarnings({ "rawtypes"})
	public static ArrayList<String> FilterList(Collection coll){
		ArrayList<String> processedList = new ArrayList<>();
		
		for(int i = 0; i < coll.size(); i++){
			
			// "\\" is used for escaping the character ( as it is the
			// reserved symbol used for grouping
			String[] part = ((List)coll).get(i).toString().split("\\(");
			//System.out.println(part[0]);
			if(part[0].equals("det")){
				
			}
			else if (part[0].equals("attr")){
				
			}
			else{
				processedList.add(((List)coll).get(i).toString());
			}
		}
		
		return processedList;
		
	}
	
	public static boolean IsNumeric(String num){
		boolean status = false;
		if(num.matches("([0-9]+(\\.[0-9]+)?)")){
			status = true;
		}
		return status;
	}
	
}