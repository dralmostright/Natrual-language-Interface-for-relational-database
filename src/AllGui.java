import javax.swing.*;
import java.awt.*;

public class AllGui {
public static CardLayout layout = new CardLayout();
public static JPanel allPanels;
public QueryPanel qpnl;
public static DashPanel dash = new DashPanel();
public static JPanel createPanel(){
	allPanels = new JPanel();
	allPanels.setOpaque(false);
	allPanels.setLayout(layout);
	allPanels.add(dash.CreateAndList(),"Dash");
	allPanels.add(new QueryPanel(),"Query");
	allPanels.add(new Synonyms().CreateListPanel(),"AddSym");
	allPanels.add(new ListSynonyms().comBined(),"ListSym");
	allPanels.add(new UpdateSynonym().CreateListPanel(),"UpdateSym");
	allPanels.add(new FunctionsOperators().CreateListPanel(),"AddFun");
	allPanels.add(new ListFunctionOperators().comBined(),"ListFun");
	allPanels.add(new UpdateFunctionOperators().CreateListPanel(),"UpdateFun");
	return allPanels;
}
}
