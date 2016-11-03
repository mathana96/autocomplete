/**
 * @author Mathana Sreedaran
 * 
 * TermListTest ensures the terms are read in correctly from the file specified 
 * and sorted as specified. Tested using test data
 */
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
	String testpath = "././data/termsTest.txt"; //Test data
	
	@Before
	public void setup() throws Exception
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
		
		assertEquals(999999.0, termList.getTerm(0).weight, 0.01); 
		assertEquals("bummer", termList.getTerm(0).theTerm);
	}
	
	@Test
	public void testTermsDelimiter()
	{
		assertNotEquals("00000 headlamp", termList.getTerm(5)); //space as delimiter 
	}
	
	@Test
	public void testNoDuplicates()
	{
		assertEquals(12, termList.getAllTerms().size());
		
		List<Term> testTerms = new ArrayList<>();
		testTerms = termList.getAllTerms();
		assertEquals(testTerms.size(), termList.getAllTerms().size());
	}
	
}
