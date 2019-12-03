package day_3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Puzzle {

	public static void main(String[] args) {
		String[] lines = new String[2];

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("input"));
			lines[0] = reader.readLine();
			lines[1] = reader.readLine();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not open file \"input\"");
			return;
		} catch (IOException e) {
			System.out.println("Error reading file \"input\"");
			return;
		}
		Map<Integer, Set<Integer>> path1 = getMapPositionsVisited(lines[0]);
		Integer[] result = getClosestIntersectionToCenter(path1, lines[1]);
		int distance = (result[0] >= 0 ? result[0] : -result[0]) + (result[1] >= 0 ? result[1] : -result[1]);
		System.out.println(
				"Closest intersection to center is = " + result[0] + ", " + result[1] + ", distance = " + distance);

	}

	private static Map<Integer, Set<Integer>> getMapPositionsVisited(String line) {
		Map<Integer, Set<Integer>> result = new HashMap<Integer, Set<Integer>>();
		Map<Character, Integer[]> dimensions = new HashMap<Character, Integer[]>();
		dimensions.put('U', new Integer[] { 0, 1 });
		dimensions.put('D', new Integer[] { 0, -1 });
		dimensions.put('R', new Integer[] { 1, 0 });
		dimensions.put('L', new Integer[] { -1, 0 });
		String[] tokens = line.split(",");
		Integer[] currentPosition = new Integer[] { 0, 0 };
		for (String direction : tokens) {
			Integer[] currentDirection = dimensions.get(direction.charAt(0));
			Integer distance = Integer.valueOf(direction.substring(1));
			while (distance > 0) {
				currentPosition[0] += currentDirection[0];
				currentPosition[1] += currentDirection[1];
				if (result.containsKey(currentPosition[0])) {
					result.get(currentPosition[0]).add(currentPosition[1]);
				} else {
					Set<Integer> list = new HashSet<Integer>();
					list.add(currentPosition[1]);
					result.put(currentPosition[0], list);
				}
				--distance;
			}
		}
		return result;
	}

	private static Integer[] getClosestIntersectionToCenter(Map<Integer, Set<Integer>> path1, String line) {
		Integer[] result = new Integer[] { Integer.MAX_VALUE, Integer.MAX_VALUE };
		int distanceResult = Integer.MAX_VALUE;

		Map<Character, Integer[]> dimensions = new HashMap<Character, Integer[]>();
		dimensions.put('U', new Integer[] { 0, 1 });
		dimensions.put('D', new Integer[] { 0, -1 });
		dimensions.put('R', new Integer[] { 1, 0 });
		dimensions.put('L', new Integer[] { -1, 0 });
		String[] tokens = line.split(",");
		Integer[] currentPosition = new Integer[] { 0, 0 };
		for (String direction : tokens) {
			Integer[] currentDirection = dimensions.get(direction.charAt(0));
			Integer distance = Integer.valueOf(direction.substring(1));
			while (distance > 0) {
				currentPosition[0] += currentDirection[0];
				currentPosition[1] += currentDirection[1];
				if (path1.containsKey(currentPosition[0])) {
					Set<Integer> set = path1.get(currentPosition[0]);
					if (set.contains(currentPosition[1])) {
						int newDistance = (currentPosition[0] < 0 ? -currentPosition[0] : currentPosition[0])
								+ (currentPosition[1] < 0 ? -currentPosition[1] : currentPosition[1]);
						if (newDistance < distanceResult) {
							distanceResult = newDistance;
							result[0] = currentPosition[0];
							result[1] = currentPosition[1];
						}

					}
				}
				--distance;
			}
		}
		return result;
	}
}
