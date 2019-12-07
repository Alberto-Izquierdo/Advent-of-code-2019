package com.alberto.adventofcode;

import java.util.HashSet;
import java.util.Set;

public class OrbitObject {
	public OrbitObject(String name) {
		this.name = name;
		this.orbitCenter = null;
		this.children = new HashSet<OrbitObject>();
	}

	public String getName() {
		return name;
	}

	public int getNumberOfOrbits() {
		int numberOfOrbitsCached = 0;
		if (orbitCenter != null) {
			numberOfOrbitsCached = orbitCenter.getNumberOfOrbits() + 1;
		}
		return numberOfOrbitsCached;
	}

	public void setOrbitCenter(OrbitObject orbitCenter) {
		this.orbitCenter = orbitCenter;
		orbitCenter.addChild(this);
	}

	public OrbitObject getOrbitCenter() {
		return orbitCenter;
	}

	private void addChild(OrbitObject child) {
		this.children.add(child);
	}

	public Set<OrbitObject> getChildren() {
		return children;
	}

	public boolean isObjectAdjacent(OrbitObject other) {
		if (other == orbitCenter) {
			return true;
		}
		return children.contains(other);
	}

	public Set<OrbitObject> getAdjacentObjects() {
		Set<OrbitObject> result = new HashSet<OrbitObject>();
		if (orbitCenter != null) {
			result.add(orbitCenter);
		}
		result.addAll(children);
		return result;
	}

	private String name;
	private OrbitObject orbitCenter;
	private Set<OrbitObject> children;
}
