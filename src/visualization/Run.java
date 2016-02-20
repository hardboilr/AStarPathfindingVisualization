package visualization;

import aStar.AStarAlgorithm;
import aStar.Board;
import static aStar.Board.boardSizeX;
import static aStar.Board.boardSizeY;
import aStar.Scenario;
import aStar.heuristic.EulerHeristic;
import aStar.heuristic.IHeuristic;
import aStar.model.Type;
import app2dapi.Platform;
import app2dapi.panandzoom2dapp.PanAndZoom2DApp;
import app2dapi.panandzoom2dapp.PanAndZoomAdapter;
import app2dpcimpl.PCPlatformImpl;

public class Run {

    public static void main(String[] args) {

        IHeuristic h = new EulerHeristic();
        AStarAlgorithm aStar = new AStarAlgorithm(h);

        String b0
                = "-------\n"
                + "-------\n"
                + "-s---g-\n"
                + "-------\n"
                + "-------\n";
        
        String b1
                = "---g-\n"
                + "-xx--\n"
                + "-sx--\n"
                + "--x--\n"
                + "-----\n";

        String b2
                = "---g-\n"
                + "-----\n"
                + "-s---\n"
                + "-----\n"
                + "-----\n";

        String b3
                = "----g-\n"
                + "--x---\n"
                + "--x---\n"
                + "--x---\n"
                + "s-----\n";

        String b4
                = "-----g\n"
                + "-x----\n"
                + "-x-xxx\n"
                + "-x--x-\n"
                + "-x-x-x\n"
                + "-x---s\n"
                + "------\n";

        String b5
                = "-------x---x---\n"
                + "-------x---xg--\n"
                + "-------x---xxx-\n"
                + "---------------\n"
                + "-------x-------\n"
                + "-------x-------\n"
                + "-s-----x-------\n"
                + "--x----x-------\n";

        String b6
                = "-------------------------------------x-------\n"
                + "-------------x-------x---------------x----g--\n"
                + "-----xx-xxx--xx------x---------------x-------\n"
                + "-------------x-----x-----------------x-------\n"
                + "-----------------x-------------------x-------\n"
                + "----x--x--------x--------------------x-------\n"
                + "------sx---------x-------------------x-------\n"
                + "----xxxx----------x-------------------x-x----\n"
                + "-------x----x-x----x--------x-x-xx--x--------\n"
                + "--------------x-----xxx----------------------\n"
                + "-----------------------x-------------x--x----\n"
                + "-----------------------x---------------------\n"
                + "--x------------------------------------------\n";

        Board board = new Board(b2); //< ---- select board here. Feel free to create new boards.
        Scenario scen1 = new Scenario(boardSizeX, boardSizeY, board.getNodes(Type.BLOCK_NODE));

        PanAndZoom2DApp app = new SearchAlgorithmVisualizationApp(aStar, scen1, board.getNode(Type.START_NODE), board.getNode(Type.GOAL_NODE));
        Platform pc = new PCPlatformImpl();
        pc.runApplication(new PanAndZoomAdapter(app));
    }
}
