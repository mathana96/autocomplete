package models;

import com.google.common.base.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;


public class Term// implements Comparable<Term>
{

	public double weight;
	public String theTerm;

	public Term(double weight, String theTerm)
	{
		this.weight = weight;
		this.theTerm = theTerm;
	}
	
	@Override  
  public int hashCode()  
  {  
     return Objects.hashCode(weight, theTerm);  
  }
	
	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof Term)
		{

			final Term other = (Term)obj;
			return Objects.equal(weight, other.weight)
					&& Objects.equal(theTerm, other.theTerm);

		}
		else
		{
			return false;
		}
	}
	
	 public String toString()
	  {
	    return toStringHelper(this).addValue(weight)
	                               .addValue(theTerm).toString();
	                              
	  }
	

}
