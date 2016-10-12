package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Terms
{
	static List<String> allTerms = new ArrayList<>();
	
	public Terms()
	{
		
	}
	public void readTerms () 
	{
		
		File termsFile = new File("././terms.txt"); //The file
		Scanner rawTerms = null;
		try
		{
			rawTerms = new Scanner(termsFile); // Scanning the terms in
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Terms file not found omg wut you doin");
		} 
		

		// While there is a next term..
		while (rawTerms.hasNextLine())
		{
			allTerms.add(rawTerms.nextLine()); //get line and add to array
		}
		System.out.println(allTerms.size());
		rawTerms.close(); // Prevent leaks

	}

	public List<String> getAllTerms()
	{
		return allTerms;
	}
	
	public int getTermsSize()
	{
		return allTerms.size();
	}
	
	public String getTerm(int i)
	{
		return allTerms.get(i);
	}

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
