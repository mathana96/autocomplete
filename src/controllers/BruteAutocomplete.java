package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.FluentIterable;

import models.Term;
import models.TermList;

public class BruteAutocomplete implements Autocomplete
{
	
	TermList terms = new TermList();
	List<Term> sweep = terms.getAllTerms();//.iterator();

	
	public BruteAutocomplete() throws IllegalArgumentException
	{
		terms.readTerms();
	}
	
	@Override
	public double weightOf(String inputTerm)
	{
		for (Term t: sweep)
		{
		  if (t.theTerm.toLowerCase().contentEquals(inputTerm.toLowerCase()))
		  {
		  	return t.weight;
		  }

		}
//		while (sweep.hasNext())
//		{
//			if (sweep.next().theTerm.toLowerCase().contentEquals(inputTerm.toLowerCase()))//
//			{
//				return sweep.next().weight;
//			}
//		}
		return 0.0;
	}

	@Override
	public String bestMatch(String prefix)
	{
		Iterable<String> bestList = this.matches(prefix, 1);
		//bestTerm.iterator().remove();
		return bestList.iterator().next();//bestTerm.iterator().next();
	}
	
	
	@Override
	public Iterable<String> matches(String prefix, int k)
	{
		List<Term> shortlist = new ArrayList<>();
		List<String> sortedlist = new ArrayList<>();
		List<String> kList;
		
		for (Term t: sweep)
		{
			if (t.theTerm.toLowerCase().contains(prefix.toLowerCase()))
			{
				shortlist.add(t);
			}

		}
//		while (sweep.hasNext())
//		{
//			if (sweep.next().theTerm.toLowerCase().contains(prefix.toLowerCase()))//
//			{
//				shortlist.add(sweep.next());
//			}
//		}
		Collections.sort(shortlist);
		
		for (Term shortTerm: shortlist)
		{
			sortedlist.add(shortTerm.theTerm);
		}
		
		//return null;
		
		if (shortlist.size() >= k)
		{

			kList = FluentIterable.from(sortedlist).limit(k).toList();
			return kList;
		}
		else
		{
			return sortedlist;
		}
		
	}
	
//	@Override
//	public int compare(Term o1) {
//		 if      (this.count < that.count) return -1;
//	     else if (this.count > that.count) return +1;
//	     else                              return  0;
//	}

	

//	@Override
//	public int compareTo(Term o)
//	{
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
