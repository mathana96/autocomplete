package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class TermTest
{
	Term term;

	@Test
	public void test()
	{
		term = new Term(6.789, "A string");
		assertEquals(term.weight, 6.789, 0.01);
		assertEquals(term.theTerm, "A string");
	}

}
