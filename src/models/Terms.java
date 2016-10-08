package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Terms
{
	static List<String> allTerms = new ArrayList<>();
	public static void main(String[] args) throws Exception
	{
		
		
		File termsFile = new File("././terms.txt");
		Scanner rawTerms = new Scanner(termsFile); // Scanning the terms in
		String delimiters = "	"; // Delimiter - a tab space

		// While there is a next term..
		while (rawTerms.hasNextLine())
		{
			String term = rawTerms.nextLine(); // Get that line
			String[] termTokens = term.split(delimiters); // Split it according to
																										// specified delimiter

			if (termTokens.length == 2) // If the split was successful, print it out
			{
				allTerms.add(term);
				System.out.println("Weight: " + termTokens[0] + " Word: " + termTokens[1]);
			} else // Throw an exception
			{
				rawTerms.close();
				throw new Exception("Invalid term length " + termTokens.length);
			}
		}
		System.out.println(allTerms.size());
		rawTerms.close(); // Prevent leaks

	}

	public List<String> getAllTerms()
	{
		return allTerms;
	}

	public void setAllTerms(List<String> allTerms)
	{
		this.allTerms = allTerms;
	}

}
