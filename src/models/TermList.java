package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.google.common.base.Objects;



public class TermList// implements Comparable<TermList>
{
	
	private List<Term> allTerms; 
	Term term; 
	String path = "././data/terms.txt";
	String testpath = "././data/termsTest.txt";
	
	public TermList()
	{
		allTerms = new ArrayList<>();
		//term = new Term();
	}
	
	public void readTerms () 
	{
		
		File termsFile = new File(testpath); //The file
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
			term = new Term();
			String[] tokens = rawTerms.nextLine().split(term.delimiter);
			term.weight = Double.parseDouble(tokens[0]);
			term.theTerm = tokens[1];
			allTerms.add(term); //add the term to array
		}
		//Collections.sort(allTerms);
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

//	@Override
//	public int compareTo(TermList o)
//	{
//		// TODO Auto-generated method stub
//		return 0;
//	}
	

}


//String delimiters = "	"; // Delimiter - a tab space
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
