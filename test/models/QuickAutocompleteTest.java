package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.QuickAutocomplete;

public class QuickAutocompleteTest
{
	private QuickAutocomplete quick;
	String testpath = "././data/termsTest.txt";

	@Before
	public void setUp() throws Exception
	{
		quick = new QuickAutocomplete(testpath);
	}

	@After
	public void tearDown() throws Exception
	{
		quick = null;
	}

	//@Test
	public void testBestMatch()
	{
		String top = "b";
		assertEquals("bummer", quick.bestMatch(top));

		//		String top2 = "br";
		//		assertEquals(quick.bestMatch(top2), "brothel");

		//		String top3 = "hec";
		//		assertEquals(quick.bestMatch(top3), null);
	}

	@Test
	public void testWeightOf()
	{
		String word = "hello";
		assertEquals(44444, quick.weightOf(word), 0.01);

	}	

	@Test
	public void testWeightOf2()
	{
		String word2 = "headlamp";
		assertEquals(00000, quick.weightOf(word2), 0.01);
	}

	@Test
	public void testWeightOf3()
	{
		String word3 = "skskywgw";
		assertEquals(0.0, quick.weightOf(word3), 0.01);
	}
	
	@Test
	public void testWeightOf4()
	{
				String word4 = ".,,@@";
				assertEquals(0.0, quick.weightOf(word4), 0.01);
	}

}
