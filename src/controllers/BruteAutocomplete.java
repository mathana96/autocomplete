package controllers;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import com.google.common.base.Preconditions;

import edu.princeton.cs.introcs.Stopwatch;
import models.Term;
import models.TermList;

public class BruteAutocomplete implements Autocomplete
{
	TermList terms;
	Iterator<Term> sweep;


	public BruteAutocomplete(String path) throws Exception// throws IllegalArgumentException
	{
		terms = new TermList(path);
		sweep = terms.getAllTerms().iterator();
	}

	@Override
	public double weightOf(String inputTerm)
	{
    
		Preconditions.checkNotNull(inputTerm, "Empty/Null String");
		while (sweep.hasNext())
		{
			Term t = sweep.next();
			if (t.theTerm.toLowerCase().contentEquals(inputTerm.toLowerCase()))//
			{
				return t.weight;
			}
		}
		return 0.0;
	}

	@Override
	public String bestMatch(String prefix)
	{
		Preconditions.checkNotNull(prefix, "Empty/Null String");
		String bestTerm = null;
		while (sweep.hasNext() && prefix.length() > 0)
		{
			Term t = sweep.next();

			if(t.theTerm.toLowerCase().startsWith(prefix.toLowerCase()))
			{
				return t.theTerm;
			}

		}
		return bestTerm;
	}


	@Override
	public Iterable<String> matches(String prefix, int k)
	{
		Preconditions.checkNotNull(prefix, "Null String");
		Preconditions.checkNotNull(k, "Null k");
		Preconditions.checkArgument(prefix.length() > 0, "Empty String", prefix.length());
		Preconditions.checkArgument(k > 0, "Negative/Zero value: %s", k);

		List<String> shortlist = new ArrayList<>();		
		int ctr = 0;

		while (sweep.hasNext() && ctr < k)
		{
			Term t = sweep.next();
			if (t.theTerm.toLowerCase().startsWith(prefix.toLowerCase()))//
			{
				shortlist.add(t.theTerm);
				ctr++;
			}
		}
		return shortlist;

	}

}

//
//Collections.sort(shortlist);
//
//for (Term shortTerm: shortlist)
//{
//	sortedlist.add(shortTerm.theTerm);
//}
//if (shortlist.size() >= k)
//{
//
//	kList = FluentIterable.from(sortedlist).limit(k).toList();
//	return kList;
//}
//else
//{
//	return sortedlist;
//}



