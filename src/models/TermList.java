package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


import util.TermByWeightComparator;




public class TermList// implements Comparable<TermList>
{
	
	private List<Term> allTerms; 
	Set<Term> uniqueSet = new HashSet<>();
	Comparator<Term> weightcomp = new TermByWeightComparator();
	String delimiter = "	"; // Delimiter - a tab space
	
	public TermList(String path) throws Exception
	{
		File termsFile = new File(path); //The file
		Scanner rawTerms = null;
		try
		{
			rawTerms = new Scanner(termsFile); // Scanning the terms in
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("File not found at specified path");
		} 
		
		// While there is a next term..
		while (rawTerms.hasNextLine())
		{
			String[] tokens = rawTerms.nextLine().trim().split(delimiter);
			if (tokens.length == 2) 
			{				
				uniqueSet.add(new Term(Double.parseDouble(tokens[0]), tokens[1])); //add the term to set
	    }
			else
	    {
	      rawTerms.close();
	      throw new Exception("Invalid member length: "+ tokens.length);
	    }
			
		}
		allTerms = new ArrayList<>(uniqueSet); //Covert set into an ArrayList
		Collections.sort(allTerms, weightcomp); //Sort arraylist according to weight
		System.out.println(allTerms.size());
		rawTerms.close(); // Prevent leaks

	}

	public List<Term> getAllTerms()
	{
		return allTerms;
	}
	
	public int getTermsSize()
	{
		return allTerms.size();
	}
	
	public Term getTerm(int i)
	{
		return allTerms.get(i);
	}

	

}



//String[] termTokens = term.split(delimiters); // Split it according to
// specified delimiter

//if (termTokens.length == 2) // If the split was successful, print it out
//{
//	
//	System.out.println();
//
//} 
//else // Throw an exception
//{
//	rawTerms.close();
//	throw new Exception("Invalid term length " + termTokens.length);
//}
