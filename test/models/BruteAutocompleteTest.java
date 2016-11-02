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
	String testpath = "././data/termsTest.txt";

	@Before
	public void setup() throws Exception
	{
		brute = new BruteAutocomplete(testpath);
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
		assertEquals(55555, brute.weightOf(word), 0.01);
		String word2 = "HeaDlamp";
		assertEquals(00000, brute.weightOf(word2), 0.01);
		String word3 = "skskywgw";
		assertEquals(0.0, brute.weightOf(word3), 0.01);
		String word4 = ".,,@@";
		assertEquals(0.0, brute.weightOf(word4), 0.01);
	}

	@Test
	public void testBestMatch()
	{
		String top = "b";
		assertEquals("bummer", brute.bestMatch(top));

		String top2 = "br";
		assertEquals("brothel", brute.bestMatch(top2));

		String top3 = ",.,.";
		assertEquals(null, brute.bestMatch(top3));
		


	}

	@Test
	public void testMatches()
	{
		String prefix = "hel";
		int k = 3;
		Iterable<String> tmiterable = brute.matches(prefix, k);
		List<String> tmlist = Lists.newArrayList(tmiterable);
		assertEquals("[help, hell, hello]", tmlist.toString());
		assertEquals(3, tmlist.size());


	}

	@Test
	public void testMatchesForLargeK()
	{
		String prefix2 = "sat";
		int k2 = 5;
		Iterable<String> tmiterable2 = brute.matches(prefix2, k2);
		List<String> tmlist2 = Lists.newArrayList(tmiterable2);
		assertEquals("[satchel]", tmlist2.toString());
		assertEquals(1, tmlist2.size());
	}


	@Test
	public void testMatchesForMiscInput()
	{
		String prefix3 = "£.,money.,££$$";
		int k3 = 7;
		Iterable<String> tmiterable3 = brute.matches(prefix3, k3);
		List<String> tmlist3 = Lists.newArrayList(tmiterable3);
		assertEquals("[]", tmlist3.toString());
		assertEquals(0, tmlist3.size());
	}


	@Test (expected = Exception.class)
	public void testExceptions()
	{
		//weightOf
		String inputTerm = null;
		brute.weightOf(inputTerm);
		
		//bestMatch
		String top4 = null;
		brute.bestMatch(top4);

		//matches
		String prefix3 = null;
		int k3 = -1;
		brute.matches(prefix3, k3);
		
		String prefix4 = null;
		String k4 = null;
		brute.matches(prefix4, Integer.parseInt(k4));
		
		String prefix5 = "pop";
		String k5 = null;
		brute.matches(prefix5, Integer.parseInt(k5));

	}


}
