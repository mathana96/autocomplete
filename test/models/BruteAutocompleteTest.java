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
	TermList termList;
	String testpath = "././data/termsTest.txt";
	
	@Before
	public void setup()
	{
		brute = new BruteAutocomplete(testpath);
	  //termList = new TermList(testpath);
	}
	
	@After
	public void tearDown()
	{
		brute = null;
	}
	
	@Test
	public void testWeightOf()
	{
		String word = "hell";
		assertEquals(brute.weightOf(word), 55555, 0.01);
		String word2 = "headlamp";
		assertEquals(brute.weightOf(word2), 00000, 0.01);
		String word3 = "skskywgw";
		assertEquals(brute.weightOf(word3), 0.0, 0.01);
		String word4 = ".,,@@";
		assertEquals(brute.weightOf(word4), 0.0, 0.01);
	}
	
	//MAKE SURE TO TEST THE OBJECTS.EQUAL BY NOT GIVING IMPLICIT VALUES!
	
	@Test
	public void testBestMatch()
	{
		String top = "b";
		assertEquals(brute.bestMatch(top), "bummer");
		
		String top2 = "br";
		assertEquals(brute.bestMatch(top2), "brothel");
		
		String top3 = ",.,.";
		assertEquals(brute.bestMatch(top3), null);
		
		
	}
	
	@Test
	public void testMatches()
	{
		String prefix = "hel";
		int k = 3;
		Iterable<String> tmiterable = brute.matches(prefix, k);
		List<String> tmlist = Lists.newArrayList(tmiterable);
		assertEquals(tmlist.toString(), "[help, hell, hello]");
		

	}
	
	@Test
	public void testMatchesForK()
	{
		String prefix3 = "sat";
		int k3 = 5;
		Iterable<String> tmiterable3 = brute.matches(prefix3, k3);
		List<String> tmlist3 = Lists.newArrayList(tmiterable3);
		assertEquals(tmlist3.toString(), "[satchel]");
	}
	
	@Test
	public void testMatchesForMiscInput()
	{
		String prefix3 = "£.,money.,££$$";
		int k3 = 5;
		Iterable<String> tmiterable3 = brute.matches(prefix3, k3);
		List<String> tmlist3 = Lists.newArrayList(tmiterable3);
		assertEquals(tmlist3.toString(), "[]");
	}
	
//	@Test
//	public void testMatchesForNullInput()
//	{
//		String prefix3 = "sat";
//		int k3 = ;
//		Iterable<String> tmiterable3 = brute.matches(prefix3, k3);
//		List<String> tmlist3 = Lists.newArrayList(tmiterable3);
//		assertNull(tmlist3, null);
//		
//	}


}
