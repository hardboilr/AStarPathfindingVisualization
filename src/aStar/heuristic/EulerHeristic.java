package aStar.heuristic;

import aStar.model.Node;

public class EulerHeristic implements IHeuristic {

    @Override
    public double getMinimumDist(Node a, Node b) {
        double dx = (b.getXPos() - a.getXPos()) * 10;
        double dy = (b.getYPos() - a.getYPos()) * 10;
        return Math.sqrt((dx * dx) + (dy * dy));
    }
}
