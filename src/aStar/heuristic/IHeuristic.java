package aStar.heuristic;

import aStar.model.Node;

public interface IHeuristic {

    public double getMinimumDist(Node a, Node b);
}
