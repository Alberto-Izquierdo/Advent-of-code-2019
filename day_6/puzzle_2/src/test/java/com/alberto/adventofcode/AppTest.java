package com.alberto.adventofcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class AppTest {
	@Test
	public void testOrbitObjects() {
		OrbitObject object = new OrbitObject("object");
		assertEquals(object.getNumberOfOrbits(), 0);
		OrbitObject childObject = new OrbitObject("child");
		childObject.setOrbitCenter(object);
		assertEquals(childObject.getNumberOfOrbits(), 1);
	}

	@Test
	public void testCountNumberOfTotalOrbits() {
		Map<String, OrbitObject> map = new HashMap<String, OrbitObject>();
		OrbitObject object1 = new OrbitObject("1");
		map.put("test1", object1);
		assertEquals(App.countNumberOfTotalOrbits(map), 0);

		OrbitObject object2 = new OrbitObject("2");
		object2.setOrbitCenter(object1);
		map.put("test2", object2);
		assertEquals(App.countNumberOfTotalOrbits(map), 1);

		OrbitObject object3 = new OrbitObject("3");
		object3.setOrbitCenter(object1);
		map.put("test3", object3);
		assertEquals(App.countNumberOfTotalOrbits(map), 2);
	}

	@Test
	public void testCountNumberOfTotalOrbits2() {
		Map<String, OrbitObject> map = new HashMap<String, OrbitObject>();
		OrbitObject object1 = new OrbitObject("1");
		map.put("test1", object1);

		OrbitObject object2 = new OrbitObject("2");
		object2.setOrbitCenter(object1);
		map.put("test2", object2);

		OrbitObject object3 = new OrbitObject("3");
		object3.setOrbitCenter(object1);
		map.put("test3", object3);

		OrbitObject object4 = new OrbitObject("4");
		object1.setOrbitCenter(object4);
		map.put("test4", object4);
		assertEquals(App.countNumberOfTotalOrbits(map), 5);
	}

	@Test
	public void testFindShortestPathBetweenOrbitObjects() {
		{
			Map<String, OrbitObject> map = new HashMap<String, OrbitObject>();
			assertEquals(App.getShortestPathBetweenOrbitObjects(map, "object1", "object2"), -1);
		}
		{
			Map<String, OrbitObject> map = new HashMap<String, OrbitObject>();
			OrbitObject object1 = new OrbitObject("object1");
			OrbitObject object2 = new OrbitObject("object2");
			object1.setOrbitCenter(object2);
			map.put(object1.getName(), object1);
			map.put(object2.getName(), object2);
			assertEquals(App.getShortestPathBetweenOrbitObjects(map, "object1", "object2"), 1);
		}
		{
			Map<String, OrbitObject> map = new HashMap<String, OrbitObject>();
			ArrayList<OrbitObject> objects = new ArrayList<OrbitObject>();
			for (int i = 0; i < 10; ++i) {
				OrbitObject object = new OrbitObject("Object" + i);
				objects.add(object);
				map.put(object.getName(), object);
				if (i > 0) {
					objects.get(i).setOrbitCenter(objects.get(i - 1));
				}
			}
			assertEquals(App.getShortestPathBetweenOrbitObjects(map, "Object0", "Object9"), 9);
		}
		{
			Map<String, OrbitObject> map = new HashMap<String, OrbitObject>();
			ArrayList<OrbitObject> objects = new ArrayList<OrbitObject>();
			for (int i = 0; i < 5; ++i) {
				OrbitObject object = new OrbitObject("Object" + i);
				objects.add(object);
				map.put(object.getName(), object);
				if (i > 0) {
					objects.get(i).setOrbitCenter(objects.get(i - 1));
				}
			}
			for (int i = 5; i < 10; ++i) {
				OrbitObject object = new OrbitObject("Object" + i);
				objects.add(object);
				map.put(object.getName(), object);
				if (i > 0) {
					objects.get(i - 1).setOrbitCenter(objects.get(i));
				}
			}
			objects.get(4).setOrbitCenter(objects.get(5));
			assertEquals(App.getShortestPathBetweenOrbitObjects(map, "Object0", "Object9"), 9);
		}
	}
}
