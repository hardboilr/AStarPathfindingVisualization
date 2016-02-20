package aStar.heuristic;

import aStar.model.Node;

public class ZeroHeuristic implements IHeuristic {

    @Override
    public double getMinimumDist(Node a, Node b) {
        return 0;
    }
}
