package controllers;


import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import models.Term;
import models.TermList;

import util.TermByWordComparator;

public class QuickAutocomplete implements Autocomplete
{
	
	TermList terms;
	List<Term> sweep;
	Comparator<Term> wordcomp = new TermByWordComparator();
	
	int lower = 0;
	int upper;
	double bestWeight = 0.0;
	
	public QuickAutocomplete(String path) throws Exception
	{
		terms = new TermList(path);
		sweep = terms.getAllTerms();
		upper = sweep.size() - 1;
		Collections.sort(sweep, wordcomp);
	}
	
	

	@Override
	public double weightOf(String inputTerm)
	{
		
		while (upper - lower > 1) 
		{
			int mid = (upper + lower) / 2;
			if (sweep.get(mid).theTerm.equalsIgnoreCase(inputTerm))
			{
				return sweep.get(mid).weight;			
			}
			else if (sweep.get(mid).theTerm.compareToIgnoreCase(inputTerm) < 0)
			{
				lower = mid;
				//System.out.println("lower " + lower);
			}
			else if (sweep.get(mid).theTerm.compareToIgnoreCase(inputTerm) > 0)
			{
				upper = mid;
				//System.out.println("upper " + upper);
			}
		}
		if (sweep.get(lower).theTerm == inputTerm)
		{
			return sweep.get(lower).weight;
		}
		else
		{
			return 0.0;
		}
	
	}	

	@Override
	public String bestMatch(String prefix)
	{
		

		while (upper - lower > 1) 
		{
			int mid = (upper - lower) / 2;
			if (sweep.get(mid).theTerm.toLowerCase().compareTo(prefix.toLowerCase()) > 0 )
			{
				upper = mid;
			}	
			else
				lower = mid;
		
		}
		System.out.println(sweep.toString());
		System.out.println(sweep.get(lower).theTerm);
		return sweep.get(lower).theTerm;
	}

	@Override
	public Iterable<String> matches(String prefix, int k)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
