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
	  termList = new TermList(testpath);
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
		String word4 = "matt";
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
	}
	
	@Test
	public void testMatches1()
	{
		String prefix = "hel";
		int k = 3;
		Iterable<String> tmiterable = brute.matches(prefix, k);
		List<String> tmlist = Lists.newArrayList(tmiterable);
		assertEquals(tmlist.toString(), "[help, hell, hello]");
		
		String prefix2 = "hex";
		int k2 = 5;
		Iterable<String> tmiterable2 = brute.matches(prefix2, k2);
		List<String> tmlist2 = Lists.newArrayList(tmiterable2);
		assertEquals(tmlist2.toString(), "[hexagon]");
	}
	
	public void testMatches2()
	{
		
		String prefix2 = "sat";
		int k2 = 5;
		Iterable<String> tmiterable2 = brute.matches(prefix2, k2);
		List<String> tmlist2 = Lists.newArrayList(tmiterable2);
		assertEquals(tmlist2.toString(), "[satchel]");
	}


}
