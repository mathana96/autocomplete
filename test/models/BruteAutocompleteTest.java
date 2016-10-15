package models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

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
		String word = "burak";
		assertEquals(brute.weightOf(word), 66666, 0.01);
		String word2 = "burakadipo";
		assertEquals(brute.weightOf(word2), 99999, 0.01);
		String word3 = "skskywgw";
		assertEquals(brute.weightOf(word3), 0.0, 0.01);
		String word4 = "wa";
		assertEquals(brute.weightOf(word4), 0.0, 0.01);
	}
	
	//MAKE SURE TO TEST THE OBJECTS.EQUAL BY NOT GIVING IMPLICIT VALUES!
	
	@Test
	public void testBestMatch()
	{
		String top = "b";
		assertEquals(brute.bestMatch(top), "burakadipo");
		
		String top2 = "rak";
		assertEquals(brute.bestMatch(top2), "euraka");
	}
	
	@Test
	public void testMatches()
	{
		String prefix = "b";
		int k = 3;
		Iterable<String> tmiterable = brute.matches(prefix, k);
		List<String> tmlist = Lists.newArrayList(tmiterable);
		assertEquals(tmlist.toString(), "[burakadipo, burakadip, burakadi]");
	}
//
//	private void assertArrayEquals(Iterable<String> matches, String string)
//	{
//		// TODO Auto-generated method stub
//		
//	}

}
