package com.company;

public class Node {

    static Integer pointsWonToWin;
    static Integer pointsLostToLose;

    static Double firstNodeWinProb;
    static Integer firstNodePointValue;

    public static void setPointsWonToWin(int pointsWonToWin) {
        Node.pointsWonToWin = pointsWonToWin;
    }

    public static void setPointsLostToLose(int pointsWonToLose) {
        Node.pointsLostToLose = pointsWonToLose;
    }

    public static void setFirstNodeWinProb(double firstNodeWinProb) {
        Node.firstNodeWinProb = firstNodeWinProb;
    }

    public static void setFirstNodePointValue(int firstNodePointValue) {
        Node.firstNodePointValue = firstNodePointValue;
    }

    public static Integer getPointsWonToWin() {
        return pointsWonToWin;
    }

    public static Integer getPointsLostToLose() {
        return pointsLostToLose;
    }

    Node predecessor;
    Node winSucessor;
    Node loseSuccessor;
    int pointsWon;
    int pointsLost;
    int pointValue;
    double prob;
    double probReached;
    boolean lossConfirmed;
    boolean winConfirmed;
    int depth; //Represents how many steps in to the tree wee are

    public void setWinSucessor(Node winNextNode) {
        winSucessor = winNextNode;
    }

    public void setLoseSuccessor(Node loseNextNode) {
        loseSuccessor = loseNextNode;
    }

    public int getPointsWon() {
        return pointsWon;
    }

    public int getPointsLost() {
        return pointsLost;
    }

    public int getPointValue() {
        return pointValue;
    }

    public boolean isLossConfirmed() {
        return lossConfirmed;
    }

    public boolean isWinConfirmed() {
        return winConfirmed;
    }

    public int getDepth() {
        return depth;
    }

    public double getProbReached() {
        return probReached;
    }

    public double getProb() {
        return prob;
    }

    public Node() {
        if (this.predecessor == null) {
            /* boolean condition is always true, but helps remind us
            that we will usually use constructors that override these values,
            especially for nodes after the base node.
             */
            this.pointsWon = 0;
            this.pointsLost = 0;
            this.depth = 0;
            if (firstNodeWinProb == null) {this.prob = 0.5;}
            else {this.prob = firstNodeWinProb;}
            if (firstNodePointValue == null) {this.pointValue = 3;}
            else {this.pointValue = firstNodePointValue;}
            this.probReached = 1;
        }
    }

    public Node(int pointsWorth, double winProb) {
        this();
        this.prob = winProb;
        this.pointValue = pointsWorth;
    }

    public Node(int pointsWorth, double winProb,
                Node predecessor, boolean won) {
        // won represents whether the predecessor node was won or lost to generate this node
        this(pointsWorth, winProb);
        this.predecessor = predecessor;
        if (won) {
            this.pointsWon = predecessor.getPointsWon() + predecessor.getPointValue();
            this.pointsLost = predecessor.getPointsLost();
            this.probReached = predecessor.getProbReached() * predecessor.getProb();
            if (this.pointsWon >= pointsWonToWin) {
                this.winConfirmed = true;
            }
        }
        else {
            this.pointsWon = predecessor.getPointsWon();
            this.pointsLost = predecessor.getPointsLost() + predecessor.getPointValue();
            this.probReached = predecessor.getProbReached() * (1 - predecessor.getProb());
            if (this.pointsLost >= pointsLostToLose) {
                this.lossConfirmed = true;
            }
        }
        if (predecessor.isLossConfirmed()) { this.lossConfirmed = true; }
        if (predecessor.isWinConfirmed()) {this.winConfirmed = true; }
        this.depth = predecessor.getDepth() + 1;
    }



}
