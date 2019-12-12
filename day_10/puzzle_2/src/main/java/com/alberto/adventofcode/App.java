package com.alberto.adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {
	public static void main(String[] args) {
		String data = new String();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("input"));
			String line = reader.readLine();
			while (line != null) {
				data += "\n" + line;
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not open file \"input\"");
			return;
		} catch (IOException e) {
			System.out.println("Error reading file \"input\"");
			return;
		}
		AsteroidField field = new AsteroidField(data);
		field.build();
		Asteroid bestAsteroid = field.getAsteroidWithMoreAsteroidsInSight();
		System.out.println("The best asteroid is placed in " + bestAsteroid.getX() + ", " + bestAsteroid.getY()
				+ " with " + bestAsteroid.getAsteroidsInSight() + " asteroids in sight");
		ArrayList<Asteroid> asteroidsToRemove = field.getAsteroidsInSightClockwiseOrdered(bestAsteroid);
		Asteroid asteroid200th = asteroidsToRemove.get(199);
		System.out.println("The asteroid 200 is placed in " + asteroid200th.getX() + ", " + asteroid200th.getY());
		Asteroid asteroid199 = asteroidsToRemove.get(200);
		System.out.println("The asteroid 199 is placed in " + asteroid199.getX() + ", " + asteroid199.getY());
		int solution = (asteroid200th.getX() * 100) + asteroid200th.getY();
		System.out.println("The solution is " + solution);
	}
}
