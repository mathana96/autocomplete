package models;

import com.google.common.base.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.MoreObjects.ToStringHelper;

public class Term// implements Comparable<Term>
{

	public double weight;
	public String theTerm;
	String delimiter = "	";

	public Term()
	{

	}

//	@Override
//	public int compareTo(Term that) 
//	{
//		 if (this.weight < that.weight) 
//			 return +1;
//		 else if (this.weight > that.weight) 
//			 return -1;
//		 else return  0;
//	} 
//	@Override
//	public boolean equals(final Object obj)
//	{
//		if (obj instanceof Term)
//		{
//
//			final Term other = (Term) obj;
//			return Objects.equal(weight, other.weight)
//					&& Objects.equal(theTerm, other.theTerm);
//
//		}
//		else
//		{
//			return false;
//		}
//	}
	
	 public String toString()
	  {
	    return toStringHelper(this).addValue(weight)
	                               .addValue(theTerm).toString();
	                              
	  }
	

}
