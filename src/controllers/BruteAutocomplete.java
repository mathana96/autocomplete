package controllers;

import java.util.List;

import models.Terms;

public class BruteAutocomplete implements Autocomplete
{
	
	Terms terms = new Terms();
	
	public BruteAutocomplete() throws IllegalArgumentException
	{
		terms.readTerms();
	}
	
	@Override
	public double weightOf(String term)
	{
		for (int i=0; i<terms.getTermsSize(); i++)	
		{
			if (terms.getTerm(i).contains(term))
			{
				String[] tokens = terms.getTerm(i).split("	");
				return Double.parseDouble(tokens[0]); 
			}
		}
		return 0.0;
	}

	@Override
	public String bestMatch(String prefix)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<String> matches(String prefix, int k)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
