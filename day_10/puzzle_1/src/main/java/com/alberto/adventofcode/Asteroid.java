package com.alberto.adventofcode;

public class Asteroid {
	public Asteroid(int x, int y) {
		this.x = x;
		this.y = y;
		this.asteroidsInSight = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAsteroidsInSight() {
		return asteroidsInSight;
	}

	public void increaseAsteroidsInSight(int number) {
		asteroidsInSight += number;
	}

	private int x;
	private int y;
	private int asteroidsInSight;
}
