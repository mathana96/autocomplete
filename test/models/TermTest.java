/**
 * @author Mathana Sreedaran
 * 
 * Test for Term class
 */
package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class TermTest
{
	Term term;

	@Test
	public void testTerm()
	{
		term = new Term(6.789, "A string"); //Create a new Term
		assertEquals(6.789, term.weight, 0.01); //Checking weight
		assertEquals("A string", term.theTerm); //Checking theTerm
	}

}
