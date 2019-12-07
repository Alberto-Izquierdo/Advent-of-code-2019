package com.alberto.adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class App {
	static Map<String, OrbitObject> orbitObjects;

	public static void main(String[] args) {
		Map<String, OrbitObject> orbitObjects = getOrbitObjectsFromInput("input");
		int result = getShortestPathBetweenOrbitObjects(orbitObjects, "YOU", "SAN");
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
					orbitObject1 = new OrbitObject(tokens[0]);
					orbitObjects.put(tokens[0], orbitObject1);
				}
				if (orbitObject2 == null) {
					orbitObject2 = new OrbitObject(tokens[1]);
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
		return orbitObjects.entrySet().stream().mapToInt(x -> x.getValue().getNumberOfOrbits()).sum();
	}

	public static int getShortestPathBetweenOrbitObjects(Map<String, OrbitObject> map, String source,
			String objectToFind) {
		OrbitObject beginObject = map.get(source);
		OrbitObject endObject = map.get(objectToFind);
		if (beginObject == null || endObject == null) {
			return -1;
		}
		int result = 0;
		Set<OrbitObject> alreadyCheckedObjects = new HashSet<OrbitObject>();
		Set<OrbitObject> adjacentObjects = new HashSet<OrbitObject>();
		adjacentObjects.add(beginObject);
		do {
			++result;
			Set<OrbitObject> newAdjacentObjects = new HashSet<OrbitObject>();
			for (OrbitObject object : adjacentObjects) {
				newAdjacentObjects.addAll(object.getAdjacentObjects().stream()
						.filter(x -> !alreadyCheckedObjects.contains(x)).collect(Collectors.toSet()));
			}
			if (newAdjacentObjects.contains(endObject)) {
				return result;
			}
			if (newAdjacentObjects.isEmpty()) {
				return -1;
			}
			alreadyCheckedObjects.addAll(adjacentObjects);
			adjacentObjects = newAdjacentObjects;
		} while (alreadyCheckedObjects.size() < map.size());
		return -1;
	}
}
