package com.alberto.adventofcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

public class AppTest {
	@Test
	public void testSimpleAsteroidField() {
		AsteroidField field = new AsteroidField(".#\n#.");
		field.build();
		assertEquals(null, field.getAsteroidAt(0, 0));
		field.getAsteroidWithMoreAsteroidsInSight();
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
		Asteroid bestAsteroid = field.getAsteroidWithMoreAsteroidsInSight();
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

	@Test
	public void testLaser() {
		{
			AsteroidField field = new AsteroidField("###");
			field.build();
			Asteroid bestAsteroid = field.getAsteroidWithMoreAsteroidsInSight();
			ArrayList<Asteroid> asteroidsAffectedByLaser = field.getAsteroidsInSightClockwiseOrdered(bestAsteroid);
			assertEquals(2, asteroidsAffectedByLaser.size());
			assertEquals(field.getAsteroidAt(2, 0), asteroidsAffectedByLaser.get(0));
			assertEquals(field.getAsteroidAt(0, 0), asteroidsAffectedByLaser.get(1));
		}
		{
			AsteroidField field = new AsteroidField("###\n###\n###");
			field.build();
			Asteroid bestAsteroid = field.getAsteroidWithMoreAsteroidsInSight();
			assertEquals(field.getAsteroidAt(1, 1), bestAsteroid);
			ArrayList<Asteroid> asteroidsAffectedByLaser = field.getAsteroidsInSightClockwiseOrdered(bestAsteroid);
			assertEquals(8, asteroidsAffectedByLaser.size());
			assertEquals(field.getAsteroidAt(1, 0), asteroidsAffectedByLaser.get(0));
			assertEquals(field.getAsteroidAt(2, 0), asteroidsAffectedByLaser.get(1));
			assertEquals(field.getAsteroidAt(2, 1), asteroidsAffectedByLaser.get(2));
			assertEquals(field.getAsteroidAt(2, 2), asteroidsAffectedByLaser.get(3));
			assertEquals(field.getAsteroidAt(1, 2), asteroidsAffectedByLaser.get(4));
			assertEquals(field.getAsteroidAt(0, 2), asteroidsAffectedByLaser.get(5));
			assertEquals(field.getAsteroidAt(0, 1), asteroidsAffectedByLaser.get(6));
			assertEquals(field.getAsteroidAt(0, 0), asteroidsAffectedByLaser.get(7));
			field.removeAsteroids(asteroidsAffectedByLaser);
			assertNull(field.getAsteroidAt(1, 0));
			assertNull(field.getAsteroidAt(2, 0));
			assertNull(field.getAsteroidAt(2, 1));
			assertNull(field.getAsteroidAt(2, 2));
			assertNull(field.getAsteroidAt(1, 2));
			assertNull(field.getAsteroidAt(0, 2));
			assertNull(field.getAsteroidAt(0, 1));
			assertNull(field.getAsteroidAt(0, 0));
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
			ArrayList<Asteroid> asteroidsToRemove = field.getAsteroidsInSightClockwiseOrdered(bestAsteroid);
			assertEquals(asteroidsToRemove.get(0), field.getAsteroidAt(11, 12));
			assertEquals(asteroidsToRemove.get(1), field.getAsteroidAt(12, 1));
			assertEquals(asteroidsToRemove.get(2), field.getAsteroidAt(12, 2));
			assertEquals(asteroidsToRemove.get(9), field.getAsteroidAt(12, 8));
			assertEquals(asteroidsToRemove.get(19), field.getAsteroidAt(16, 0));
			assertEquals(asteroidsToRemove.get(49), field.getAsteroidAt(16, 9));
			assertEquals(asteroidsToRemove.get(99), field.getAsteroidAt(10, 16));
			assertEquals(asteroidsToRemove.get(198), field.getAsteroidAt(9, 6));
			assertEquals(asteroidsToRemove.get(199), field.getAsteroidAt(8, 2));
			assertEquals(asteroidsToRemove.get(200), field.getAsteroidAt(10, 9));
			/*
			 * int size = asteroidsToRemove.size();
			 * field.removeAsteroids(asteroidsToRemove); asteroidsToRemove =
			 * field.getAsteroidsInSightClockwiseOrdered(bestAsteroid);
			 * assertEquals(asteroidsToRemove.get(298 - size), field.getAsteroidAt(11, 1));
			 */
		}
	}
}
