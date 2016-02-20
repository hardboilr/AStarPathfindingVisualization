package aStar.heuristic;

import aStar.model.Node;

public class EulerHeristic implements IHeuristic {

    @Override
    public double getMinimumDist(Node a, Node b) {
        double dx = b.getXPos() - a.getXPos();
        double dy = b.getYPos() - a.getYPos();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
