/**
 * @author Mathana Sreedaran
 * 
 * Class for QuickAutocomplete implementing binary search on
 * a data set sorted alphabetically. 
 */

package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.google.common.base.Preconditions;

import models.Term;
import models.TermList;

public class BruteAutocomplete implements Autocomplete
{
	TermList terms; //Object of termList
	Iterator<Term> sweep; //Store all terms from file


	public BruteAutocomplete(String path) throws Exception// throws IllegalArgumentException
	{
		terms = new TermList(path); //Path from Client
		sweep = terms.getAllTerms().iterator(); //Flood Iterator with terms
	}

	/**
	 * Returns the weight of the term, or 0.0 if no such term.
	 */
	@Override
	public double weightOf(String inputTerm)
	{
		Preconditions.checkNotNull(inputTerm, "Empty/Null String");
		Preconditions.checkArgument(inputTerm.length()>0, "Empty/Null String");
		
		while (sweep.hasNext())
		{
			Term t = sweep.next();
			if (t.theTerm.toLowerCase().contentEquals(inputTerm.toLowerCase())) 
			{
				return t.weight; //return weight of term if matches with input
			}
		}
		return 0.0; //0.0 otherwise
	}

	/**
	 * Returns the highest weighted matching term, or null if no matching term.
	 */
	@Override
	public String bestMatch(String prefix)
	{
		Preconditions.checkNotNull(prefix, "Empty/Null String");
		Preconditions.checkArgument(prefix.length()>0, "Empty/Null String");
		String bestTerm = null; //initiate variable as null
		while (sweep.hasNext())
		{
			Term t = sweep.next();

			//List of terms is pre-sorted by weight, allowing the first hit to be returned
			if(t.theTerm.toLowerCase().startsWith(prefix.toLowerCase()))
			{
				return t.theTerm; //return term if starts with prefix
			}

		}
		return bestTerm; //return null if no hit
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

		List<String> shortlist = new ArrayList<>();	//List to store terms which match with prefix
		int ctr = 0; //counter

		while (sweep.hasNext() && ctr < k) //Loop as long counter is less than k
		{
			Term t = sweep.next();
			if (t.theTerm.toLowerCase().startsWith(prefix.toLowerCase())) //Add to List if term matches with prefix
			{
				shortlist.add(t.theTerm);
				ctr++;
			}
		}
		return shortlist;

	}

}




