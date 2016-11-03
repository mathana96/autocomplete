/**
 * @author Mathana Sreedaran
 * TermList class reads in terms from a file in the project structure using a 
 * specified path. The terms are split into two tokens - weight and theTerm - 
 * and stored as objects of the Term class in a List. A HashSet is used to prevent
 * duplicate Term values, determined using the equals() and hashcode() methods in 
 * Term class. Final List is sorted alphabetically. 
 */
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

public class TermList
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


