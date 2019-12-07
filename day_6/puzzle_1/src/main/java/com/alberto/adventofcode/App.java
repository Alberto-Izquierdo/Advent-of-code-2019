package com.alberto.adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
	static Map<String, OrbitObject> orbitObjects;

	public static void main(String[] args) {
		Map<String, OrbitObject> orbitObjects = getOrbitObjectsFromInput("input");
		int result = countNumberOfTotalOrbits(orbitObjects);
		System.out.println(result);
	}

	private static Map<String, OrbitObject> getOrbitObjectsFromInput(String inputPath) {
		Map<String, OrbitObject> orbitObjects = new HashMap<String, OrbitObject>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(inputPath));
			String line = reader.readLine();
			while (line != null) {
				String[] tokens = line.split("[)]");
				OrbitObject orbitObject1 = orbitObjects.get(tokens[0]);
				OrbitObject orbitObject2 = orbitObjects.get(tokens[1]);
				if (orbitObject1 == null) {
					orbitObject1 = new OrbitObject();
					orbitObjects.put(tokens[0], orbitObject1);
				}
				if (orbitObject2 == null) {
					orbitObject2 = new OrbitObject();
					orbitObjects.put(tokens[1], orbitObject2);
				}
				orbitObject2.setOrbitCenter(orbitObject1);
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not open file \"input\"");
			return null;
		} catch (IOException e) {
			System.out.println("Error reading file \"input\"");
			return null;
		}
		return orbitObjects;
	}

	public static int countNumberOfTotalOrbits(Map<String, OrbitObject> orbitObjects) {
		Optional<Integer> result = orbitObjects.entrySet().stream().map(x -> x.getValue().getNumberOfOrbits())
				.collect(Collectors.reducing(Integer::sum));
		if (result.isPresent()) {
			return result.get();
		}
		return 0;
	}
}
