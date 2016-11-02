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
		assertEquals(6.789, term.weight, 0.01);
		assertEquals("A string", term.theTerm);
	}

}
