package controllers;

import models.Terms;

public class BruteAutocomplete implements Autocomplete
{
	
	Terms terms = new Terms();
	
	public BruteAutocomplete() throws IllegalArgumentException
	{
		
	}
	@Override
	public double weightOf(String term)
	{
		// TODO Auto-generated method stub
		return 0;
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
