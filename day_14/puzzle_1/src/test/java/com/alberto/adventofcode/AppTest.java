package com.alberto.adventofcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class AppTest {
	@Test
	public void testSimpleReaction() {
		Map<String, Reaction> reactions = new HashMap<String, Reaction>();
		ArrayList<ElementQuantityPair> elementsNecessaryForReaction = new ArrayList<ElementQuantityPair>();
		elementsNecessaryForReaction.add(new ElementQuantityPair("ORE", 3));
		reactions.put("FUEL", new Reaction(new ElementQuantityPair("FUEL", 1), elementsNecessaryForReaction));
		Nanofactory factory = new Nanofactory(reactions);
		try {
			assertEquals(3, factory.getNumberOfElementsToGenerate("FUEL", "ORE"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testComplexReaction() {
		Map<String, Reaction> reactions = new HashMap<String, Reaction>();
		ArrayList<ElementQuantityPair> elementsToGenerateAR = new ArrayList<ElementQuantityPair>();
		elementsToGenerateAR.add(new ElementQuantityPair("ORE", 3));
		reactions.put("AR", new Reaction(new ElementQuantityPair("AR", 1), elementsToGenerateAR));
		ArrayList<ElementQuantityPair> elementsToGenerateFuel = new ArrayList<ElementQuantityPair>();
		elementsToGenerateFuel.add(new ElementQuantityPair("AR", 2));
		reactions.put("FUEL", new Reaction(new ElementQuantityPair("FUEL", 1), elementsToGenerateFuel));
		Nanofactory factory = new Nanofactory(reactions);
		try {
			assertEquals(6, factory.getNumberOfElementsToGenerate("FUEL", "ORE"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testMoreComplexReaction() {
		Map<String, Reaction> reactions = new HashMap<String, Reaction>();
		ArrayList<ElementQuantityPair> elementsToGenerateAR = new ArrayList<ElementQuantityPair>();
		elementsToGenerateAR.add(new ElementQuantityPair("ORE", 3));
		reactions.put("AR", new Reaction(new ElementQuantityPair("AR", 1), elementsToGenerateAR));
		ArrayList<ElementQuantityPair> elementsToGenerateHG = new ArrayList<ElementQuantityPair>();
		elementsToGenerateHG.add(new ElementQuantityPair("ORE", 3));
		reactions.put("HG", new Reaction(new ElementQuantityPair("HG", 2), elementsToGenerateHG));
		ArrayList<ElementQuantityPair> elementsToGenerateFuel = new ArrayList<ElementQuantityPair>();
		elementsToGenerateFuel.add(new ElementQuantityPair("AR", 2));
		elementsToGenerateFuel.add(new ElementQuantityPair("HG", 2));
		reactions.put("FUEL", new Reaction(new ElementQuantityPair("FUEL", 1), elementsToGenerateFuel));
		Nanofactory factory = new Nanofactory(reactions);
		try {
			assertEquals(9, factory.getNumberOfElementsToGenerate("FUEL", "ORE"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInput01() {
		assertEquals(31, App.getOreQuantityToGenerate1Fuel("input_test_01.txt"));
	}

	@Test
	public void testInput02() {
		assertEquals(165, App.getOreQuantityToGenerate1Fuel("input_test_02.txt"));
	}

	@Test
	public void testInput03() {
		assertEquals(13312, App.getOreQuantityToGenerate1Fuel("input_test_03.txt"));
	}

	@Test
	public void testInput04() {
		assertEquals(180697, App.getOreQuantityToGenerate1Fuel("input_test_04.txt"));
	}

	@Test
	public void testInput05() {
		assertEquals(2210736, App.getOreQuantityToGenerate1Fuel("input_test_05.txt"));
	}
}
