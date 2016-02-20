package visualization;

import aStar.model.Type;

public class Tile {

    private final String name;
    private final int xPos;
    private final int yPos;
    private final double gCost;
    private final double hCost;
    private final double fCost;
    private final Type type;

    public Tile(String name, int xPos, int yPos, double gCost, double hCost, double fCost, Type type) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = fCost;
        this.type = type;
    }

    public double getgCost() {
        return gCost;
    }
    
    public double gethCost() {
        return hCost;
    }
    
    public double getfCost() {
        return fCost;
    }

    public String getName() {
        return name;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Type getType() {
        return type;
    }
}
