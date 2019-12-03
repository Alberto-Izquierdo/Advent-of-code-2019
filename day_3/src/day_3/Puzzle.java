package day_3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		Map<Integer, Map<Integer, Integer>> path1 = getMapPositionsVisited(lines[0]);
		Integer[] result = getClosestIntersectionToCenter(path1, lines[1]);
		System.out.println(
				"Closest intersection to center is = " + result[0] + ", " + result[1] + ", distance = " + result[2]);

	}

	private static Map<Integer, Map<Integer, Integer>> getMapPositionsVisited(String line) {
		Map<Integer, Map<Integer, Integer>> result = new HashMap<Integer, Map<Integer, Integer>>();
		Map<Character, Integer[]> dimensions = new HashMap<Character, Integer[]>();
		dimensions.put('U', new Integer[] { 0, 1 });
		dimensions.put('D', new Integer[] { 0, -1 });
		dimensions.put('R', new Integer[] { 1, 0 });
		dimensions.put('L', new Integer[] { -1, 0 });
		String[] tokens = line.split(",");
		Integer[] currentPosition = new Integer[] { 0, 0 };
		int steps = 1;
		for (String direction : tokens) {
			Integer[] currentDirection = dimensions.get(direction.charAt(0));
			Integer distance = Integer.valueOf(direction.substring(1));
			while (distance > 0) {
				currentPosition[0] += currentDirection[0];
				currentPosition[1] += currentDirection[1];
				if (result.containsKey(currentPosition[0])) {
					Map<Integer, Integer> result2 = result.get(currentPosition[0]);
					if (!result2.containsKey(currentPosition[1])) {
						result2.put(currentPosition[1], steps);
					}
				} else {
					Map<Integer, Integer> list = new HashMap<Integer, Integer>();
					list.put(currentPosition[1], steps);
					result.put(currentPosition[0], list);
				}
				--distance;
				++steps;
			}
		}
		return result;
	}

	private static Integer[] getClosestIntersectionToCenter(Map<Integer, Map<Integer, Integer>> path1, String line) {
		Integer[] result = new Integer[] { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE };

		Map<Character, Integer[]> dimensions = new HashMap<Character, Integer[]>();
		dimensions.put('U', new Integer[] { 0, 1 });
		dimensions.put('D', new Integer[] { 0, -1 });
		dimensions.put('R', new Integer[] { 1, 0 });
		dimensions.put('L', new Integer[] { -1, 0 });
		String[] tokens = line.split(",");
		Integer[] currentPosition = new Integer[] { 0, 0 };
		int steps = 1;
		for (String direction : tokens) {
			Integer[] currentDirection = dimensions.get(direction.charAt(0));
			Integer distance = Integer.valueOf(direction.substring(1));
			while (distance > 0) {
				currentPosition[0] += currentDirection[0];
				currentPosition[1] += currentDirection[1];
				if (path1.containsKey(currentPosition[0])) {
					Map<Integer, Integer> set = path1.get(currentPosition[0]);
					if (set.containsKey(currentPosition[1])) {
						int newDistance = steps + set.get(currentPosition[1]);
						if (newDistance < result[2]) {
							result[0] = currentPosition[0];
							result[1] = currentPosition[1];
							result[2] = newDistance;
						}
					}
				}
				--distance;
				++steps;
			}
		}
		return result;
	}
}
