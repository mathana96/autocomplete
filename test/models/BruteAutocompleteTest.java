package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.BruteAutocomplete;

public class BruteAutocompleteTest
{
	private BruteAutocomplete brute;
	
	@Before
	public void setup()
	{
		brute = new BruteAutocomplete();
	}
	
	@After
	public void tearDown()
	{
		brute = null;
	}
	
	@Test
	public void testWeightOf()
	{
		String word = "was";
		assertEquals(brute.weightOf(word), 1007824500, 0.01);
		String word2 = "that";
		assertEquals(brute.weightOf(word2), 1107331800, 0.01);
		String word3 = "skskywgw";
		assertEquals(brute.weightOf(word3), 0.0, 0.01);
	}
	
//	@Test
//	public void testBestMatch()
//	{
//		fail("Not implemented");
//	}
	
//	@Test
//	public void testMatches()
//	{
//		fail("Not implemented");
//	}

}
