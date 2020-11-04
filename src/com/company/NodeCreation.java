package com.company;

import java.util.ArrayList;

public class NodeCreation {

    static int[] valuesSeries = new int[] {20, 29, 10, 16, 11, 18, 15, 10, 9, 6};
    static double[] probsSeriesWholes = SourceData.getProbsSeriesWholes();

    static int len = probsSeriesWholes.length;
    static double[] probsSeries;

    private static void setProbsSeries(double[] probsSeries) {
        NodeCreation.probsSeries = probsSeries;
    }

    private static void generateProbsSeries() {
        double[] probs = new double[len];
        int i = 0;
        while (i < len) {
            probs[i] = probsSeriesWholes[i] / 100;
            i++;
        }
        NodeCreation.setProbsSeries(probs);
    }



    public static int[] getValuesSeries() {
        return valuesSeries;
    }

    public static double[] getProbsSeries() {
        return probsSeries;
    }

    public ArrayList<Node> simplestWin() {
        NodeCreation.generateProbsSeries();
        if (Node.getPointsLostToLose() == null) {
            Node.setPointsLostToLose(78);
        }
        if (Node.getPointsWonToWin() == null) {
            Node.setPointsWonToWin(139);
        }
        Node.setFirstNodePointValue(valuesSeries[0]);
        Node.setFirstNodeWinProb(probsSeries[0]);


        Node baseNode = new Node();
        ArrayList<Node> winNodes = new ArrayList();
        winNodes.add(baseNode);
        int i = 1;
        boolean won=false;
        while (won == false) {
            Node newWinNode = new Node(valuesSeries[i], probsSeries[i], baseNode, true);
            winNodes.add(newWinNode);
            if (newWinNode.isWinConfirmed()) {
                won = true;
            }
            i++;
        }
        //First node giving overall win in straight win series is winNodes.get(winNodes.size()-1);
        return winNodes;
    }

}
