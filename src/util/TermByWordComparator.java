/**
 * @author Mathana Sreedaran
 * 
 * Sorts theTerm of Term alphabetically 
 */
package util;

import java.util.Comparator;

import models.Term;

public class TermByWordComparator implements Comparator<Term>
{

	@Override
	public int compare(Term t1, Term t2)
	{
		return t1.theTerm.compareToIgnoreCase(t2.theTerm);
	}

}
