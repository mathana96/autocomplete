package models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class TermListTest
{
	
	TermList termList;
	String testpath = "././data/termsTest.txt";
	
	@Before
	public void setup()
	{
		termList = new TermList(testpath);
	}
	
	@After
	public void tearDown()
	{
		termList.getAllTerms().clear();
	}
	
	
	@Test
	public void testSortedTerms()
	{
		
		assertEquals(termList.getTerm(0).weight, 999990.0, 0.01); //tab as delimiter 
		assertEquals(termList.getTerm(0).theTerm, "bummer"); //tab as delimiter 
	}
	
	@Test
	public void testTermsDelimiter()
	{
		assertNotEquals(termList.getTerm(5), "00000 headlamp"); //space as delimiter 
	}
	
	@Test
	public void testNoDuplicates()
	{
		assertEquals(11, termList.getAllTerms().size());
		
		List<Term> testTerms = new ArrayList<>();
		testTerms = termList.getAllTerms();
		assertEquals(testTerms.size(), termList.getAllTerms().size());
	}
	
}
