package visualization;

import aStar.AStarAlgorithm;
import aStar.Scenario;
import aStar.model.Node;
import aStar.model.Type;
import app2dapi.geometry.G2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.Color;
import app2dapi.graphics.ColorFactory;
import app2dapi.input.charinput.CharInputEvent;
import app2dapi.input.keyboard.KeyPressedEvent;
import app2dapi.input.keyboard.KeyReleasedEvent;
import app2dapi.panandzoom2dapp.PanAndZoom2DApp;
import app2dapi.panandzoom2dapp.PanAndZoomInit;
import app2dapi.panandzoom2dapp.PanAndZoomToolKit;
import java.util.List;
import visualization.model.Iteration;

public class SearchAlgorithmVisualizationApp implements PanAndZoom2DApp {

    private double hudHeight;
    private double hudWidth;
    private final int worldWidth;
    private final int worldHeight;
    private final int sizeX;
    private final int sizeY;
    private ColorFactory cf;
    private G2D g2d;
    private Tile[][] tiles;
    private TileDrawer tileDrawer;
    private final String keyRight = "VK_RIGHT";
    private final String keyLeft = "VK_LEFT";
    private final Node[][] scenarioNodes;
    private int step;
    private final Iterable<Node> shortestPathNodes;
    private final Node startNode;
    private final Node goalNode;
    private final AStarAlgorithm aStar;

    public SearchAlgorithmVisualizationApp(AStarAlgorithm aStar, Scenario scenario, Node startNode, Node goalNode) {
        this.sizeX = scenario.getSizeX();
        this.sizeY = scenario.getSizeY();
        worldWidth = sizeX * 2;
        worldHeight = sizeY * 2;
        this.aStar = aStar;
        this.startNode = startNode;
        this.goalNode = goalNode;
        scenarioNodes = scenario.createScenario();

//        for (int x = 0; x < sizeX; x++) {
//            for (int y = 0; y < sizeY; y++) {
//                Node n = scenarioNodes[x][y];
//                System.out.println("ScenarioNode: " + n);
//            }
//        }

        shortestPathNodes = aStar.findShortestPath(scenarioNodes[startNode.getXPos()][startNode.getYPos()], scenarioNodes[goalNode.getXPos()][goalNode.getYPos()]);
    }

    @Override
    public PanAndZoomInit initialize(PanAndZoomToolKit toolkit, double aspectRatio) {
        System.out.println("init");
        this.cf = toolkit.cf();
        this.g2d = toolkit.g2d();
        tileDrawer = new TileDrawer(g2d, cf);
        tiles = new Tile[worldWidth][worldHeight];

        createInitialTiles();

        this.hudHeight = 1000;
        this.hudWidth = hudHeight * aspectRatio;

        return new PanAndZoomInit(g2d.origo(),
                g2d.newPoint2D(hudWidth, hudHeight),
                g2d.origo(),
                g2d.newPoint2D(worldWidth, worldHeight),
                g2d.newPoint2D(sizeX * 0.5, sizeY * 0.5),
                worldWidth, 1, worldWidth);
    }

    @Override
    public boolean showMouseCursor() {
        return false;
    }

    @Override
    public void onMouseMoved(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos) {

    }

    @Override
    public void onMousePressed(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos) {

    }

    @Override
    public void onMouseReleased(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos) {

    }

    @Override
    public void onKeyPressed(KeyPressedEvent e) {
    }

    @Override
    public void onKeyReleased(KeyReleasedEvent e) {
        // Step 0. Draw empty map with A, B and blocks
        // Step 1. Draw all nodes surrounding currentNode (color them green) <- initially this will be A
        // Step 2. Select node with lowest fCost and mark "closed" (color in read) 
        // (if more nodes have same fCost, then select node with lowest hCost. This will be closest to goal)
        // Step 3. Repeat step 1
        // Step 4. When goal node is reaching, color the path nodes blue
        // Step 1 -> end of curNodes. Draw first node in consideration and so forth, until end of list
        // 2. Draw shortest path
        // misc: display step at top
        // 1. draw shortest path

        System.out.println("Keyboard step is: " + step);

        if (e.getKey().toString().equals(keyRight)) {
            if (step < aStar.getSteps() + 1) {
                step++;
            }

        } else if (e.getKey().toString().equals(keyLeft)) {
            if (step > 0) {
                step--;
            }
        }
        if (step >= 0 && step < aStar.getSteps()) {
            // rinse repeat through steps
            createInitialTiles();
            List<Iteration> iterations = aStar.getIterations(step);
            for (int i = 0; i < iterations.size(); i++) {
                Iteration iteration = iterations.get(i);
                System.out.println("---> Iteration: " + (i + 1));
                for (Node n : iteration.getNodes()) {
                    System.out.println(n + ", " + n.getType());
                    tiles[n.getXPos()][n.getYPos()] = new Tile(n.getName(), n.getXPos(), n.getYPos(), n.getGVal(), n.getHVal(), n.getFVal(), n.getType());
                }
            }
        }

        if (step == aStar.getSteps()) {
            // reached goal. Display final step
            createInitialTiles();
            List<Iteration> iterations = aStar.getIterations(step);

            for (int i = 0; i < iterations.size(); i++) {
                Iteration iteration = iterations.get(i);
                System.out.println("final ---> Iteration: " + (i + 1));

                for (int j = 0; j < iteration.getNodes().size(); j++) {
                    Node n = iteration.getNodes().get(j);
                    System.out.println(n + ", " + n.getType());
                    if (n.getXPos() == goalNode.getXPos() && n.getYPos() == goalNode.getYPos()) {
                        tiles[n.getXPos()][n.getYPos()] = new Tile(n.getName(), n.getXPos(), n.getYPos(), n.getGVal(), n.getHVal(), n.getFVal(), Type.GOAL_NODE);
                    } else {
                        tiles[n.getXPos()][n.getYPos()] = new Tile(n.getName(), n.getXPos(), n.getYPos(), n.getGVal(), n.getHVal(), n.getFVal(), n.getType());
                    }
                }
            }
        }

        if (step == aStar.getSteps() + 1) {
            // display path
            createInitialTiles();
            for (Node n : shortestPathNodes) {
                tiles[n.getXPos()][n.getYPos()] = new Tile(n.getName(), n.getXPos(), n.getYPos(), n.getGVal(), n.getHVal(), n.getFVal(), n.getType());
            }
        }
    }

    @Override
    public void onCharInput(CharInputEvent event) {

    }

    @Override
    public boolean update(double time) {
        return true;
    }

    @Override
    public Color getBackgroundColor() {
        return cf.getBlack();
    }

    @Override
    public void drawWorld(Canvas canvas) { // updated each frame
        for (int y = 0; y < sizeY; ++y) {
            for (int x = 0; x < sizeX; ++x) {
                tileDrawer.draw(canvas, tiles[x][y]);
            }
        }
    }

    @Override
    public void drawHUD(Canvas canvas) {

    }

    @Override
    public void destroy() {

    }

    private void createInitialTiles() {
        for (int y = 0; y < sizeY; ++y) {
            for (int x = 0; x < sizeX; ++x) {
                Node node = scenarioNodes[x][y];
                try {
                    tiles[x][y] = new Tile(node.getName(), node.getXPos(), node.getYPos(), node.getGVal(), node.getHVal(), node.getFVal(), node.getType()); // provoke nullpointer
                    tiles[x][y] = new Tile("(" + x + "," + y + ")", x, y, 0, 0, 0, Type.EMPTY);
                } catch (NullPointerException ex) {
                    tiles[x][y] = new Tile("(" + x + "," + y + ")", x, y, 0, 0, 0, Type.BLOCK_NODE);
                }
            }
        }
        tiles[startNode.getXPos()][startNode.getYPos()] = new Tile(startNode.getName(), startNode.getXPos(), startNode.getYPos(), startNode.getGVal(), startNode.getHVal(), startNode.getFVal(), startNode.getType());
        tiles[goalNode.getXPos()][goalNode.getYPos()] = new Tile(goalNode.getName(), goalNode.getXPos(), goalNode.getYPos(), goalNode.getGVal(), goalNode.getHVal(), goalNode.getFVal(), goalNode.getType());
    }
}
