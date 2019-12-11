package com.alberto.adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	}
}
