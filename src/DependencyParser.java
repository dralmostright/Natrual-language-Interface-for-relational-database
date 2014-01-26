import java.io.*;
import java.util.*;

import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.*;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;


/**
 * Date : 2013/10/06
 * Author : Anup Pokhrel, Suman Adhikari
 * Purpose : Natural Language Interface for database
 * This uses the Stanford core NLP version 3.2.0 to get the
 * dependency among the words in a sentence.
 * This class returns the Collection of the Dependencies.
 ***/


public class DependencyParser {

	/**
	 * GenerateList() function takes argument as String 
	 * i.e sentence to get the dependencies
	 * and returns the dependencies in the sentence 
	 * as the collection.
	 * 
	 * The code is copied for site: http://chaoticity.com/
	 * 
	 * UP to now only typedDependencies is implemented
	 * but we actually need is collapsed dependency.
	 ***/
	
	@SuppressWarnings("rawtypes")
	public static Collection GenerateList(String sent){
		Collection Coll;
		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		
		LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
		
		lp.setOptionFlags(new String[] { "-maxLength", "500","-retainTmpSubcategories" });
		
		TokenizerFactory tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		List wordList = tokenizerFactory.getTokenizer(new StringReader(sent)).tokenize();
		
		@SuppressWarnings("unchecked")
		Tree tree = lp.apply(wordList);
		
		GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
		Coll = gs.typedDependenciesCollapsed(true);
		//collapsedDependencies
		return Coll;
	}
}