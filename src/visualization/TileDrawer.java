package visualization;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Transformation2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.ColorFactory;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TileDrawer {

    private final G2D g2d;
    private final ColorFactory colorFact;

    public TileDrawer(G2D g2d, ColorFactory cf) {
        this.g2d = g2d;
        this.colorFact = cf;
    }

    public void draw(Canvas canvas, Tile tile) {
        Transformation2D parent = canvas.getTransformation();
        Transformation2D t = g2d.translate(tile.getxPos(), tile.getyPos());
        Transformation2D c = g2d.combine(parent, t);

        canvas.setTransformation(c);

        canvas.setColor(colorFact.getBlack());
        canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 1, 1);

        canvas.setColor(colorFact.getWhite());
        canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        // draw name text
        canvas.setColor(colorFact.getRed());
        canvas.drawText(g2d.newPoint2D(0.5, 0.1), tile.getName(), 0.15, true, true);

        if (null != tile.getType()) {
            switch (tile.getType()) {

                case START_NODE:
                    canvas.setColor(colorFact.getBlue());
                    canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
                    
                    canvas.setColor(colorFact.getWhite());
                    canvas.drawText(g2d.newPoint2D(0.5, 0.5), "A", 0.5, true, true);
                    canvas.drawText(g2d.newPoint2D(0.5, 0.15), tile.getName(), 0.15, true, true);
                    
                    break;
                case VISITED:
                    canvas.setColor(colorFact.getRed());
                    canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
                    
                    canvas.setColor(colorFact.getWhite());
                    canvas.drawText(g2d.newPoint2D(0.5, 0.4), "F:" + df.format(tile.getfCost()), 0.25, true, true);
                    canvas.drawText(g2d.newPoint2D(0.75, 0.8), "H:" + df.format(tile.gethCost()), 0.15, true, true);
                    canvas.drawText(g2d.newPoint2D(0.25, 0.8), "G:" + df.format(tile.getgCost()), 0.15, true, true);
                    canvas.drawText(g2d.newPoint2D(0.5, 0.15), tile.getName(), 0.15, true, true);
                    break;
                case NEIGHBOUR:
                    canvas.setColor(colorFact.getGreen());
                    canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
                    
                    canvas.setColor(colorFact.getWhite());
                    canvas.drawText(g2d.newPoint2D(0.5, 0.4), "F:" + df.format(tile.getfCost()), 0.25, true, true);
                    canvas.drawText(g2d.newPoint2D(0.75, 0.8), "H:" + df.format(tile.gethCost()), 0.15, true, true);
                    canvas.drawText(g2d.newPoint2D(0.25, 0.8), "G:" + df.format(tile.getgCost()), 0.15, true, true);
                    canvas.drawText(g2d.newPoint2D(0.5, 0.15), tile.getName(), 0.15, true, true);
                    break;
                case GOAL_NODE:
                    canvas.setColor(colorFact.getBlue());
                    canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);

                    canvas.setColor(colorFact.getWhite());
                    canvas.drawText(g2d.newPoint2D(0.5, 0.5), "B", 0.5, true, true);
                    canvas.drawText(g2d.newPoint2D(0.5, 0.15), tile.getName(), 0.15, true, true);
                    break;
                case REGULAR_NODE:
                    canvas.setColor(colorFact.getBlack());
                    canvas.drawText(g2d.newPoint2D(0.5, 0.4), "F:" + df.format(tile.getfCost()), 0.25, true, true);
                    canvas.drawText(g2d.newPoint2D(0.75, 0.8), "H:" + df.format(tile.gethCost()), 0.15, true, true);
                    canvas.drawText(g2d.newPoint2D(0.25, 0.8), "G:" + df.format(tile.getgCost()), 0.15, true, true);
                    break;
                case BLOCK_NODE:
                    canvas.setColor(colorFact.getBlack());
                    canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
                    
                    canvas.setColor(colorFact.getWhite());
                    canvas.drawText(g2d.newPoint2D(0.5, 0.15), tile.getName(), 0.15, true, true);
                    break;

                case PATH_NODE:
                    canvas.setColor(colorFact.getGreen());
                    canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);

                    canvas.setColor(colorFact.getBlack());
                    canvas.drawText(g2d.newPoint2D(0.5, 0.4), "F:" + df.format(tile.getfCost()), 0.25, true, true);
                    canvas.drawText(g2d.newPoint2D(0.75, 0.8), "H:" + df.format(tile.gethCost()), 0.15, true, true);
                    canvas.drawText(g2d.newPoint2D(0.25, 0.8), "G:" + df.format(tile.getgCost()), 0.15, true, true);

                    canvas.setColor(colorFact.getRed());
                    canvas.drawText(g2d.newPoint2D(0.5, 0.1), tile.getName(), 0.15, true, true);
                    break;

                case EMPTY:
                    canvas.setColor(colorFact.getWhite());
                    canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
                    
                    canvas.setColor(colorFact.getBlack());
                    canvas.drawText(g2d.newPoint2D(0.5, 0.15), tile.getName(), 0.15, true, true);
                default:
                    break;
            }

        }
        canvas.setTransformation(parent);
    }
}
