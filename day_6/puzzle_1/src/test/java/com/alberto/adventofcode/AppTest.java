package com.alberto.adventofcode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class AppTest {
	@Test
	public void testOrbitObjects() {
		OrbitObject object = new OrbitObject();
		assertEquals(object.getNumberOfOrbits(), 0);
		OrbitObject childObject = new OrbitObject();
		childObject.setOrbitCenter(object);
		assertEquals(childObject.getNumberOfOrbits(), 1);
	}

    @Test
	public void testCountNumberOfTotalOrbits() {
		Map<String, OrbitObject> map = new HashMap<String, OrbitObject>();
		OrbitObject object1 = new OrbitObject();
		map.put("test1", object1);
		assertEquals(App.countNumberOfTotalOrbits(map), 0);

		OrbitObject object2 = new OrbitObject();
        object2.setOrbitCenter(object1);
		map.put("test2", object2);
		assertEquals(App.countNumberOfTotalOrbits(map), 1);

		OrbitObject object3 = new OrbitObject();
        object3.setOrbitCenter(object1);
		map.put("test3", object3);
		assertEquals(App.countNumberOfTotalOrbits(map), 2);
	}

    @Test
	public void testCountNumberOfTotalOrbits2() {
		Map<String, OrbitObject> map = new HashMap<String, OrbitObject>();
		OrbitObject object1 = new OrbitObject();
		map.put("test1", object1);

		OrbitObject object2 = new OrbitObject();
        object2.setOrbitCenter(object1);
		map.put("test2", object2);

		OrbitObject object3 = new OrbitObject();
        object3.setOrbitCenter(object1);
		map.put("test3", object3);

		OrbitObject object4 = new OrbitObject();
        object1.setOrbitCenter(object4);
		map.put("test4", object4);
		assertEquals(App.countNumberOfTotalOrbits(map), 5);
	}
}
