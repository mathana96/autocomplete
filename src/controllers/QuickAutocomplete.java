/**
 * @author Mathana Sreedaran & FXWalsh
 * 
 * Class for QuickAutocomplete implementing binary search on
 * a data set sorted alphabetically. 
 */
package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;

import models.Term;
import models.TermList;
import util.TermByWeightComparator;
import util.TermByWordComparator;

public class QuickAutocomplete implements Autocomplete
{

	TermList terms; //Object of TermList class
	List<Term> sweep; //List of all terms read in by TermList
	Comparator<Term> wordcomp = new TermByWordComparator(); //Comparator which sorts list of words alphabetically 
	Comparator<Term> weightcomp = new TermByWeightComparator(); //Comparator which sorts list of words in ascending order of weight

	int lower = 0; //Initial lower bound for binary search
	int upper; //Initial upper bound for binary search


	public QuickAutocomplete(String path) throws Exception
	{
		terms = new TermList(path); //Path determined in Client
		sweep = terms.getAllTerms(); //Flooding list with terms from file found at path
		upper = sweep.size() - 1; //Redefining upper limit for binary search based on size of List
		Collections.sort(sweep, wordcomp); //Sort List alphabetically 
	}


	/**
	 * Returns the weight of the term, or 0.0 if no such term.
	 */
	@Override
	public double weightOf(String inputTerm)
	{
		Preconditions.checkNotNull(inputTerm, "Empty/Null String");
		Preconditions.checkArgument(inputTerm.length()>0, "Empty/Null String");
		while (upper - lower > 0) 
		{
			int mid = (upper + lower) / 2;
			if (sweep.get(mid).theTerm.equalsIgnoreCase(inputTerm)) 
			{
				return sweep.get(mid).weight;			//Jackpot
			}
			else if (sweep.get(mid).theTerm.compareToIgnoreCase(inputTerm) < 0) //Term at mid is alphabetically smaller than input
			{
				lower = mid+1; 
			}
			else if (sweep.get(mid).theTerm.compareToIgnoreCase(inputTerm) > 0) //Term at mid is alphabetically larger than input
			{
				upper = mid-1;
			}
		}
		if (sweep.get(lower).theTerm.equals(inputTerm)) //Checks if input exists in List, returns 0.0 otherwise
		{
			return sweep.get(lower).weight;
		}
		else
		{
			return 0.0;
		}

	}	

	/**
	 * Returns the highest weighted matching term, or null if no matching term.
	 */
	@Override
	public String bestMatch(String prefix)
	{
		Preconditions.checkNotNull(prefix, "Empty/Null String");
		Preconditions.checkArgument(prefix.length()>0, "Empty/Null String");

		while (upper - lower > 0) 
		{
			int mid = (upper + lower) / 2;
			if (sweep.get(mid).theTerm.compareToIgnoreCase(prefix) < 0) //Term at mid is alphabetically smaller than prefix
			{
				lower = mid+1;
			}
			else if (sweep.get(mid).theTerm.compareToIgnoreCase(prefix) > 0) //Term at mid is alphabetically smaller than prefix
			{
				upper = mid-1;
			}
		}
		int newLow = lower; //New lower bound for block of like terms
		int newUp = lower; //New upper bound for block of like terms
		List<Term> filteredList = new ArrayList<>(); //List to store block of like terms
		
		if (sweep.get(lower).theTerm.startsWith(prefix))
		{
			filteredList.add(sweep.get(lower)); //Add result from binary search if starts with prefix
		}
		
		//Search down the List of all terms for terms that start with prefix
		boolean lowerBound = false;
		while (lowerBound != true && newLow > 0) //Prevents out of bounds
		{
			newLow--;
			if (sweep.get(newLow).theTerm.startsWith(prefix))
			{
				filteredList.add(sweep.get(newLow)); //Add to new List if found
			}
			else
			{
				lowerBound = false; //Break out of loop
			}
		}
		
	//Search up the List of all terms for terms that start with prefix
		boolean upperBound = false;
		while (upperBound != true && newUp < sweep.size() - 1)  //Prevents out of bounds
		{
			newUp++;
			if (sweep.get(newUp).theTerm.startsWith(prefix))
			{
				filteredList.add(sweep.get(newUp)); //Add to new List if found
			}
			else
			{
				upperBound = false; //Break out of loop
			}
		}
		Collections.sort(filteredList, weightcomp); //Sort filtered list according to weight

		//Return list if any terms found, null if none
		if (filteredList.size() > 0)
		{
			return filteredList.get(0).theTerm;
		}
		else
		{
			return null;
		}
	}

	/**
	 *  Returns the highest weighted k matching terms (in descending order of weight), 
	 *  as aniterable.If fewer than k matches, return all 
	 *  matching terms (in descending orderof weight).
	 */
	@Override
	public Iterable<String> matches(String prefix, int k)
	{
		Preconditions.checkNotNull(prefix, "Null String");
		Preconditions.checkNotNull(k, "Null k");
		Preconditions.checkArgument(prefix.length() > 0, "Empty String", prefix.length());
		Preconditions.checkArgument(k > 0, "Negative/Zero value: %s", k);

		while (upper - lower > 1) 
		{
			int mid = (upper + lower) / 2;
			if (sweep.get(mid).theTerm.compareToIgnoreCase(prefix) < 0) //Term at mid is alphabetically smaller than prefix
			{
				lower = mid+1;
			}
			else if (sweep.get(mid).theTerm.compareToIgnoreCase(prefix) > 0) //Term at mid is alphabetically smaller than prefix
			{
				upper = mid-1;
			}
		}

		int newLow = lower; //New lower bound for block of like terms
		int newUp = lower; //New upper bound for block of like terms
		List<Term> filteredList = new ArrayList<>();  //List to store block of like terms
		
		
		if (sweep.get(lower).theTerm.startsWith(prefix))
		{
			filteredList.add(sweep.get(lower)); //Add result from binary search if starts with prefix
		}
		
		//Search down the List of all terms for terms that start with prefix
		boolean lowerBound = false;
		while (lowerBound != true && newLow > 0)
		{
			newLow--;
			if (sweep.get(newLow).theTerm.startsWith(prefix))
			{
				filteredList.add(sweep.get(newLow)); //Add to new List if found
			}
			else
			{
				lowerBound = false; //Break out of loop
			}
		}
		
		//Search up the List of all terms for terms that start with prefix
		boolean upperBound = false;
		while (upperBound != true && newUp < sweep.size() - 1)
		{
			newUp++;
			if (sweep.get(newUp).theTerm.startsWith(prefix))
			{
				filteredList.add(sweep.get(newUp)); //Add to new List if found
			}
			else
			{
				upperBound = false; //Break out of loop
			}
		}
		Collections.sort(filteredList, weightcomp); //Sort by weight

		List<String> shortList = new ArrayList<>(); //Short list of k terms

		for (Term t: filteredList)
		{
			shortList.add(t.theTerm); //Add term from Term object to shortList
		}

		if (filteredList.size() <= k)
		{
			return shortList;
		}
		else
		{
			List<String> slicedList = FluentIterable.from(shortList).limit(k).toList(); //Slice list based on k
			return slicedList;
		}
		
	}
}
