package com.alberto.adventofcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Tree<K> {
	public Tree(K key) {
		this.cache = new HashMap<K, NodeTree<K>>();
	}

	public boolean containsKey(K key) {
		return cache.containsKey(key);
	}

	public void addChild(K parent, K child) {
		NodeTree<K> newChild = null;
		if (!cache.containsKey(child)) {
			newChild = addElement(child);
		} else {
			newChild = cache.get(child);
		}
		if (cache.containsKey(parent)) {
			newChild.addParent(cache.get(parent));
		} else {
			NodeTree<K> newParent = addElement(parent);
			newChild.addParent(newParent);
		}
	}

	public ArrayList<K> getParentsKeys(K parent) {
		return cache.get(parent).getParentsKeys();
	}

	private NodeTree<K> addElement(K element) {
		NodeTree<K> newElement = new NodeTree<K>(element);
		cache.put(element, newElement);
		return newElement;
	}

	private Map<K, NodeTree<K>> cache;
}

class NodeTree<K> {
	public NodeTree(K key) {
		this.key = key;
		this.parents = new HashMap<K, NodeTree<K>>();
	}

	public void addParent(NodeTree<K> parent) {
		if (!parents.containsKey(parent.key)) {
			parents.put(parent.key, parent);
		}
	}

	public ArrayList<K> getParentsKeys() {
		return (ArrayList<K>) parents.keySet().stream().collect(Collectors.toList());
	}

	public boolean hasParents() {
		return !parents.isEmpty();
	}

	public final K key;
	private Map<K, NodeTree<K>> parents;
}
