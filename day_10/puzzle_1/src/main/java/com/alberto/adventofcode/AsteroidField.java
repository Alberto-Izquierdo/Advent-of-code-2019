package com.alberto.adventofcode;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class AsteroidField {
	public AsteroidField(String data) {
		this.data = data;
		this.asteroids = new ArrayList<Asteroid>();
		this.field = new ArrayList<ArrayList<Asteroid>>();
	}

	public void build() {
		field.add(new ArrayList<Asteroid>());
		int x = 0;
		int y = 0;
		for (int i = 0; i < data.length(); ++i) {
			char letter = data.charAt(i);
			if (letter == '\n') {
				field.add(new ArrayList<Asteroid>());
				x = 0;
				++y;
			} else {
				if (letter == '#') {
					Asteroid asteroid = new Asteroid(x, y);
					asteroids.add(asteroid);
					field.get(y).add(asteroid);
				} else {
					field.get(y).add(null);
				}
				++x;
			}
		}
		for (Asteroid asteroid : asteroids) {
			Set<Double> anglesAlreadyChecked = new HashSet<Double>();
			double x1 = asteroid.getX();
			double y1 = asteroid.getY();
			for (Asteroid other : asteroids) {
				if (asteroid != other) {
					double x2 = other.getX();
					double y2 = other.getY();
					double vecX = x2 - x1;
					double vecY = y2 - y1;
					double angle = Math.atan2(vecX, vecY);
					double angleInDegrees = Math.toDegrees(angle);
					if (!anglesAlreadyChecked.contains(angleInDegrees)) {
						anglesAlreadyChecked.add(angleInDegrees);
						asteroid.increaseAsteroidsInSight(1);
					}
				}
			}
		}
	}

	public Asteroid getAsteroidWithMoreAsteroidsInSight() {
		Asteroid solution = null;
		int asteroidsInSight = -1;
		for (Asteroid asteroid : asteroids) {
			int currentAsteroidsInSight = asteroid.getAsteroidsInSight();
			if (currentAsteroidsInSight > asteroidsInSight) {
				solution = asteroid;
				asteroidsInSight = currentAsteroidsInSight;
			}
		}
		return solution;
	}

	public Asteroid getAsteroidAt(Integer x, Integer y) {
		if (y < field.size()) {
			ArrayList<Asteroid> row = field.get(y);
			if (x < row.size()) {
				return row.get(x);
			}
		}
		return null;
	}

	private String data;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<ArrayList<Asteroid>> field;
}
