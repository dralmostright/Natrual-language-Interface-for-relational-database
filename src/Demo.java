import java.util.*;
public class Demo {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		//What are the capitals of the states that border the most populated state
		//What is the capital of the most populous state
		
	Scanner in = new Scanner(System.in);
	System.out.println("Enter the sentence to get dependency.");
	String text = in.nextLine();
	Collection coll = DependencyParser.GenerateList(text);
	
	
	in.close();
	
	List lists = (List)coll;
  	System.out.println("");
	System.out.println("********************Actual dependeincies.**********************");
	for(int i = 0; i < lists.size() ;i++){
		System.out.println(lists.get(i));
	}
	
	
	
	System.out.println("");
	System.out.println("*********Contents of the filtered list of dependencies ********");
	ArrayList<String>list  = HelperClass.FilterList(coll);
	for(int i = 0; i < list.size(); i++){
		System.out.println(list.get(i));
	}
	System.out.println("*************************************************************");
	System.out.println("");
	
	DivideList.PopulateList(list);
	
	System.out.println("");
	System.out.println("*********Contents of the projection list(Probable select)********");
	for(int i = 0; i < DivideList.projection.size(); i++){
		System.out.println(DivideList.projection.get(i));
	}
	System.out.println("*************************************************************");
	System.out.println("");
	
	System.out.println("");
	System.out.println("*********Contents of the selection list(Probable where)********");
	for(int i = 0; i < DivideList.selection.size(); i++){
		System.out.println(DivideList.selection.get(i));
	}
	System.out.println("*************************************************************");
	System.out.println("");
	
	QueryGenerator.GetAll();
	}
}
