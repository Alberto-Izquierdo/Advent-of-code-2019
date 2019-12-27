package com.alberto.adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {
	public static int getOreQuantityToGenerate1Fuel(String path) {
		Map<String, Reaction> reactions = loadReactionMap(path);
		Nanofactory factory = new Nanofactory(reactions);
		return factory.getNumberOfElementsToGenerate("FUEL", "ORE");
	}

	public static Map<String, Reaction> loadReactionMap(String path) {
		Map<String, Reaction> reactions = new HashMap<String, Reaction>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				String[] reactionParts = line.split(" => ");
				String[] generated = reactionParts[1].split(" ");
				String[] reactives = reactionParts[0].split(", ");
				ArrayList<ElementQuantityPair> elements = new ArrayList<ElementQuantityPair>();
				boolean isOreReaction = false;
				for (String reactive : reactives) {
					String[] reactiveParts = reactive.split(" ");
					if (reactiveParts[1].equals("ORE")) {
						isOreReaction = true;
					}
					elements.add(new ElementQuantityPair(reactiveParts[1], Integer.parseInt(reactiveParts[0])));
				}
				if (isOreReaction) {
					reactions.put(generated[1], new Reaction(
							new ElementQuantityPair(generated[1], Integer.parseInt(generated[0])), elements));

				} else {
					reactions.put(generated[1], new Reaction(
							new ElementQuantityPair(generated[1], Integer.parseInt(generated[0])), elements));
				}
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return reactions;
	}

	public static void main(String[] args) {
		int ore = App.getOreQuantityToGenerate1Fuel("input");
		System.out.println("Ore quantity: " + ore);
	}
}
