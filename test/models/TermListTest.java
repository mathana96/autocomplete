package models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class TermListTest
{
	private TermList termList;
	
	@Before
	public void setup()
	{
		termList = new TermList();
	}
	
	@After
	public void tearDown()
	{
		termList.getAllTerms().clear();
	}
	
	@Test
	public void testTermsEmpty()
	{
		assertEquals(termList.getTermsSize(), 0);
	}
	
	@Test
	public void testTerms()
	{
		termList.readTerms();
		
		assertEquals(termList.getTerm(0).weight, 5627187200.0, 0.01); //tab as delimiter 
		assertEquals(termList.getTerm(0).theTerm, "the"); //tab as delimiter 

		//assertNotEquals(termList.getTerm(0), "3395006400 of"); //space as delimiter 
		//assertEquals(terms.getAllTerms().size(), 10000);
		
		List<Term> testTerms = new ArrayList<>();
		testTerms = termList.getAllTerms();
		assertEquals(testTerms.size(), termList.getAllTerms().size());
	}

}
