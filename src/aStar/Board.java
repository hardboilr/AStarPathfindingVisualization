package aStar;

import aStar.model.Node;
import aStar.model.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {

    private final List<String> boardRows;
    public static int boardSizeX;
    public static int boardSizeY;

    public Board(String board) {
        boardRows = toList(board.split("\r\n|\r|\n"));
        boardSizeX = boardRows.get(0).length();
        boardSizeY = boardRows.size();
        Collections.reverse(boardRows);
        
    }

    public List<Node> getNodes(Type type) {
        String c = "";
        List<Node> nodes = new ArrayList();
        if (type.equals(Type.BLOCK_NODE)) {
            c = "x";
        }

        for (int i = 0; i < boardRows.size(); i++) {
            String currentRow = boardRows.get(i);
            for (int j = 0; j < currentRow.length(); j++) {
                String l = Character.toString(currentRow.charAt(j));
                if (l.equalsIgnoreCase("x")) {
                    Node n = new Node("(" + j + "," + i + ")", j, i, type);
                    nodes.add(n);
                }
            }
        }
        return nodes;
    }

    public Node getNode(Type type) {
        String c = "";
        if (type.equals(Type.START_NODE)) {
            c = "s";
        } else if (type.equals(Type.GOAL_NODE)) {
            c = "g";
        }

        for (int i = 0; i < boardRows.size(); i++) {
            String currentRow = boardRows.get(i);
            for (int j = 0; j < currentRow.length(); j++) {
                String l = Character.toString(currentRow.charAt(j));
                if (l.equalsIgnoreCase(c)) {
                    Node n = new Node("(" + j + "," + i + ")", j, i, type);
                    return n;
                }
            }
        }
        return null;
    }

    public int getBoardSizeX() {
        return boardRows.get(0).length();
    }

    public int getBoardSizeY() {
        return boardRows.size();
    }

    private List<String> toList(String[] board) {
        List<String> br = new ArrayList();
        br.addAll(Arrays.asList(board));
        return br;
    }

}
