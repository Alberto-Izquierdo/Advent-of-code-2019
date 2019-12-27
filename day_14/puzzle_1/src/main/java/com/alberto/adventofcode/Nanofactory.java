package com.alberto.adventofcode;

import java.util.ArrayList;
import java.util.Map;

public class Nanofactory {
	public Nanofactory(Map<String, Reaction> reactions) {
		this.reactions = reactions;
	}

	private double getNumber(Tree<String> tree, String elementToGenerate, String baseElement) {
		if (elementToGenerate == baseElement) {
			return 1.0;
		}
		ArrayList<String> parents = tree.getParentsKeys(baseElement);
		double solution = 0.0;
		for (String parent : parents) {
			double partialSolution = getNumber(tree, elementToGenerate, parent);
			Reaction reaction = this.reactions.get(parent);
			if (parent.equals(elementToGenerate)) {
				solution += reaction.elementsRequiredForReaction.stream().filter(x -> x.element.equals(baseElement))
						.findFirst().get().quantity / reaction.elementGenerated.quantity;
				continue;
			}
			ElementQuantityPair pair = reaction.elementsRequiredForReaction.stream()
					.filter(x -> x.element.equals(baseElement)).findFirst().get();
			solution += partialSolution * pair.quantity / reaction.elementGenerated.quantity;
		}
		return solution;
	}

	public int getNumberOfElementsToGenerate(String elementToGenerate, String baseElement) {
		ArrayList<String> elementsToGenerateRemaining = new ArrayList<String>();
		Tree<String> tree = new Tree<String>(elementToGenerate);
		elementsToGenerateRemaining.add(elementToGenerate);
		while (!elementsToGenerateRemaining.isEmpty()) {
			String nextElement = elementsToGenerateRemaining.get(0);
			elementsToGenerateRemaining.remove(0);
			if (reactions.containsKey(nextElement)) {
				Reaction nextReaction = reactions.get(nextElement);
				for (ElementQuantityPair pair : nextReaction.elementsRequiredForReaction) {
					tree.addChild(nextElement, pair.element);
					elementsToGenerateRemaining.add(pair.element);
				}
			}
		}

		ArrayList<String> baseParents = tree.getParentsKeys(baseElement);

		double solution = 0.0;
		for (String parent : baseParents) {
			double partialSolution = getNumber(tree, elementToGenerate, parent);
			Reaction currentReaction = reactions.get(parent);
			solution += Math.ceil(partialSolution / currentReaction.elementGenerated.quantity)
					* currentReaction.elementsRequiredForReaction.get(0).quantity;
		}
		return (int) solution;
	}

	private final Map<String, Reaction> reactions;
}
