package com.alberto.adventofcode;

import java.util.ArrayList;

public class Reaction
{
    public Reaction(ElementQuantityPair elementGenerated, ArrayList<ElementQuantityPair> elementsRequiredForReaction)
    {
        this.elementGenerated = elementGenerated;
        this.elementsRequiredForReaction = elementsRequiredForReaction;
    }

    public final ElementQuantityPair elementGenerated;
    public final ArrayList<ElementQuantityPair> elementsRequiredForReaction;
}
