package com.alberto.adventofcode;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
				if (x != 0) {
					field.add(new ArrayList<Asteroid>());
					x = 0;
					++y;
				}
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
	}

	public Asteroid getAsteroidWithMoreAsteroidsInSight() {
		Asteroid solution = null;
		int numberOfAsteroidsInSight = -1;
		for (Asteroid asteroid : asteroids) {
			ArrayList<Asteroid> asteroidsInSight = getAsteroidsInSightClockwiseOrdered(asteroid);
			int currentAsteroidsInSight = asteroidsInSight.size();
			asteroid.setAsteroidsInSight(currentAsteroidsInSight);
			if (currentAsteroidsInSight > numberOfAsteroidsInSight) {
				solution = asteroid;
				numberOfAsteroidsInSight = currentAsteroidsInSight;
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

	public ArrayList<Asteroid> getAsteroidsInSightClockwiseOrdered(Asteroid origin) {
		SortedMap<Float, Asteroid> anglesAlreadyChecked = new TreeMap<Float, Asteroid>();
		for (Asteroid other : asteroids) {
			if (origin != other) {
				float vecX = other.getX() - origin.getX();
				float vecY = other.getY() - origin.getY();
				float angle = (float) ((Math.atan2(vecY, vecX) + Math.PI - Math.PI / 2.0));
				while (angle < 0) {
					angle += Math.PI * 2.0;
				}
				if (!anglesAlreadyChecked.containsKey(angle)) {
					anglesAlreadyChecked.put(angle, other);
				} else {
					Asteroid previousSolution = anglesAlreadyChecked.get(angle);
					double previousVecX = previousSolution.getX() - origin.getX();
					double previousVecY = previousSolution.getY() - origin.getY();
					double currentSquaredLength = vecX * vecX + vecY * vecY;
					double previousSquaredLength = previousVecX * previousVecX + previousVecY * previousVecY;
					if (currentSquaredLength < previousSquaredLength) {
						// anglesAlreadyChecked.put(angle, other);
						anglesAlreadyChecked.replace(angle, other);
					}
				}
			}
		}
		// anglesAlreadyChecked.keySet().stream().forEach(x -> System.out.println(x));
		return new ArrayList<Asteroid>(anglesAlreadyChecked.values().stream().collect(Collectors.toList()));
	}

	public void removeAsteroids(ArrayList<Asteroid> asteroidsToRemove) {
		asteroidsToRemove.stream().forEach(x -> {
			field.get(x.getY()).set(x.getX(), null);
			assert (asteroids.remove(x) != false);
		});
	}

	private String data;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<ArrayList<Asteroid>> field;
}
