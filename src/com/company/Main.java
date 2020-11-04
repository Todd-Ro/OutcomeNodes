package com.company;

public class Main {

    public static void main(String[] args) {

        int[] valuesSeries;
        double[] probsSeries;

        valuesSeries = NodeCreation.getValuesSeries();
           //new int[] {20, 29, 10, 16, 11, 18, 15, 10};
        probsSeries = NodeCreation.getProbsSeries();

        // write your code here

        Node.setPointsLostToLose(78);
        Node.setPointsWonToWin(139);







    }


}
