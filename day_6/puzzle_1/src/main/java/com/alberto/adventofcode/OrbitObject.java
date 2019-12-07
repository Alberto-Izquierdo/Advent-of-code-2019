package com.alberto.adventofcode;

public class OrbitObject {
	public OrbitObject() {
		this.orbitCenter = null;
		this.numberOfOrbitsCached = -1;
	}

	public int getNumberOfOrbits() {
		if (numberOfOrbitsCached == -1) {
			if (orbitCenter == null) {
				numberOfOrbitsCached = 0;
			} else {
				numberOfOrbitsCached = orbitCenter.getNumberOfOrbits() + 1;
			}
		}
		return numberOfOrbitsCached;
	}

	public void setOrbitCenter(OrbitObject orbitCenter) {
		this.orbitCenter = orbitCenter;
	}

	public OrbitObject orbitCenter;
	private int numberOfOrbitsCached;
}
