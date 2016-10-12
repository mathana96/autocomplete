package models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class TermsTest
{
	private Terms terms;
	
	@Before
	public void setup()
	{
		terms = new Terms();
	}
	
	@After
	public void tearDown()
	{
		terms.getAllTerms().clear();
	}
	
	@Test
	public void testTermsEmpty()
	{
		assertEquals(terms.getTermsSize(), 0);
	}
	
	@Test
	public void testTerms()
	{
		terms.readTerms();
		
		assertEquals(terms.getTerm(0), "5627187200	the"); //tab as delimiter 
		assertNotEquals(terms.getTerm(0), "3395006400 of"); //space as delimiter 
		//assertEquals(terms.getAllTerms().size(), 10000);
		
		List<String> testTerms = new ArrayList<>();
		testTerms = terms.getAllTerms();
		assertEquals(testTerms.size(), terms.getAllTerms().size());
	}

}
