/**
 * @author Mathana Sreedaran
 * 
 * Defining how Terms are sorted by weight
 */
package util;

import java.util.Comparator;

import models.Term;

public class TermByWeightComparator implements Comparator<Term>
{

	@Override
	public int compare(Term t1, Term t2)
	{
		if (t1.weight < t2.weight) 
			 return +1;
		 else if (t1.weight > t2.weight) 
			 return -1;
		 else return  0;
	}

}
