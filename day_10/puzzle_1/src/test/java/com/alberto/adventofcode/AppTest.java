package com.alberto.adventofcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AppTest {
	@Test
	public void testSimpleAsteroidField() {
		AsteroidField field = new AsteroidField(".#\n#.");
		field.build();
		assertEquals(null, field.getAsteroidAt(0, 0));
		{
			Asteroid asteroid = field.getAsteroidAt(1, 0);
			assertNotNull(asteroid);
			assertEquals(1, asteroid.getX());
			assertEquals(0, asteroid.getY());
			assertEquals(1, asteroid.getAsteroidsInSight());
		}
		{
			Asteroid asteroid = field.getAsteroidAt(0, 1);
			assertNotNull(asteroid);
			assertEquals(0, asteroid.getX());
			assertEquals(1, asteroid.getY());
			assertEquals(1, asteroid.getAsteroidsInSight());
		}
		assertEquals(null, field.getAsteroidAt(1, 1));
	}

	@Test
	public void testLineOfSight() {
		AsteroidField field = new AsteroidField("###");
		field.build();
		{
			Asteroid asteroid = field.getAsteroidAt(0, 0);
			assertEquals(1, asteroid.getAsteroidsInSight());
		}
		{
			Asteroid asteroid = field.getAsteroidAt(1, 0);
			assertEquals(2, asteroid.getAsteroidsInSight());
		}
		{
			Asteroid asteroid = field.getAsteroidAt(2, 0);
			assertEquals(1, asteroid.getAsteroidsInSight());
		}
		Asteroid bestAsteroid = field.getAsteroidWithMoreAsteroidsInSight();
		assertEquals(bestAsteroid, field.getAsteroidAt(1, 0));
		field.build();
	}

	@Test
	public void testAdventOfCodeExamples() {
		{
			AsteroidField field = new AsteroidField(".#..#\n" + ".....\n" + "#####\n" + "....#\n" + "...##");
			field.build();
			Asteroid bestAsteroid = field.getAsteroidWithMoreAsteroidsInSight();
			assertEquals(bestAsteroid, field.getAsteroidAt(3, 4));
			assertEquals(bestAsteroid.getAsteroidsInSight(), 8);
		}
		{
			AsteroidField field = new AsteroidField(
					"......#.#.\n" + "#..#.#....\n" + "..#######.\n" + ".#.#.###..\n" + ".#..#.....\n" + "..#....#.#\n"
							+ "#..#....#.\n" + ".##.#..###\n" + "##...#..#.\n" + ".#....####");
			field.build();
			Asteroid bestAsteroid = field.getAsteroidWithMoreAsteroidsInSight();
			assertEquals(bestAsteroid, field.getAsteroidAt(5, 8));
			assertEquals(bestAsteroid.getAsteroidsInSight(), 33);
		}
		{
			AsteroidField field = new AsteroidField(
					"#.#...#.#.\n" + ".###....#.\n" + ".#....#...\n" + "##.#.#.#.#\n" + "....#.#.#.\n" + ".##..###.#\n"
							+ "..#...##..\n" + "..##....##\n" + "......#...\n" + ".####.###.\n");
			field.build();
			Asteroid bestAsteroid = field.getAsteroidWithMoreAsteroidsInSight();
			assertEquals(bestAsteroid, field.getAsteroidAt(1, 2));
			assertEquals(bestAsteroid.getAsteroidsInSight(), 35);
		}
		{
			AsteroidField field = new AsteroidField(
					".#..#..###\n" + "####.###.#\n" + "....###.#.\n" + "..###.##.#\n" + "##.##.#.#.\n" + "....###..#\n"
							+ "..#.#..#.#\n" + "#..#.#.###\n" + ".##...##.#\n" + ".....#.#..\n");
			field.build();
			Asteroid bestAsteroid = field.getAsteroidWithMoreAsteroidsInSight();
			assertEquals(bestAsteroid, field.getAsteroidAt(6, 3));
			assertEquals(bestAsteroid.getAsteroidsInSight(), 41);
		}
		{
			AsteroidField field = new AsteroidField(".#..##.###...#######\n" + "##.############..##.\n"
					+ ".#.######.########.#\n" + ".###.#######.####.#.\n" + "#####.##.#.##.###.##\n"
					+ "..#####..#.#########\n" + "####################\n" + "#.####....###.#.#.##\n"
					+ "##.#################\n" + "#####.##.###..####..\n" + "..######..##.#######\n"
					+ "####.##.####...##..#\n" + ".#####..#.######.###\n" + "##...#.##########...\n"
					+ "#.##########.#######\n" + ".####.#.###.###.#.##\n" + "....##.##.###..#####\n"
					+ ".#.#.###########.###\n" + "#.#.#.#####.####.###\n" + "###.##.####.##.#..##");
			field.build();
			Asteroid bestAsteroid = field.getAsteroidWithMoreAsteroidsInSight();
			assertEquals(bestAsteroid, field.getAsteroidAt(11, 13));
			assertEquals(bestAsteroid.getAsteroidsInSight(), 210);
		}
	}
}
