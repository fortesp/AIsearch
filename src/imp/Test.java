package imp;

import search.*;

import java.util.InputMismatchException;
import java.util.Scanner;


// Pedro Fortes, 23095


public class Test {

    static private void searchResult(Algorithm algo, State initialstate) {

        long startTime = System.currentTimeMillis();

        Node lastnode = algo.search(initialstate);

        if(lastnode == null)
            System.out.println("No solution");
        else {
            System.out.print("Last state: \n");
            lastnode.getState().printState();

            System.out.print("Total accumulated cost: " + lastnode.getPathcost() + "\n");

            System.out.println("Path: ");
            Algorithm.printPath(lastnode, true);

            System.out.println("\n");

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;

            System.out.println(elapsedTime + "ms\n\n");
        }

    }

    static public void MissionariesAndCannibalsProblem() {

        int[] a = {2, 2, 2, 1, 1, 1,   0, 0, 0, 0, 0, 0};
        MC mc = new MC(a, false);

        submenu(mc);
    }

    // --------------

    static public void BucketsProblem() {

        Buckets bc = new Buckets(0, 0);

        submenu(bc);
    }

    static public void NickelAndDimmeProblem() {

        int[] a = {1, 1, 0, 2, 2};

        ND nd = new ND(a);

        submenu(nd);
    }

    static public void UCGraphProblem() { // Lab 4

        int[][] c = {
        //         1           5             10             15             20             25             30
       /*  1 */ {-1,10, 8,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1, 5,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                { 8,-1,-1,-1,-1,-1, 5,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {10,-1,-1,-1,-1,-1, 7,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
       /*  5 */ {-1,-1,-1,-1,-1,-1,-1,-1, 6, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1, 6,-1,-1,-1,-1,-1,-1,-1, 9,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1, 7,-1,-1,-1,-1,-1,-1,-1,-1,20, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
       /* 10 */ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1, 2, 9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1, 4,-1,-1,-1,-1,-1,-1,-1, 5,-1,-1,-1,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
       /* 15 */ {-1,-1,-1,-1,-1,-1,-1,18,-1,-1,-1,-1,-1, 5,-1,-1,-1,-1,-1,15, 2,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1, 6,-1,-1,-1,-1,-1,-1,-1, 6,-1,-1,-1,-1,30,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1,-1,-1, 6,-1,-1,-1,-1,-1,-1,20,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 5, 2,-1,-1,-1,-1,-1, 2,-1,-1,-1,-1,40,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,12,-1,-1,-1, 2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
       /* 20 */ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,15,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 6,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,20,-1,-1,-1,-1,-1,-1, 2,-1,-1, 8,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2,-1,-1,-1, 2,-1,-1,-1,-1, 2, 5,-1},
       /* 25 */ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1,20,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1,-1,-1, 2},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10, 8,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 6,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 5,20,-1,-1,-1,-1,-1},
       /* 30 */ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 2,-1,-1,-1,-1},
                };

        GraphPath gp = new GraphPath(c, 1,29);
        searchResult(new UC(), gp);
    }
    
    static public void EightPuzzleProblem() {

        int[][] p = { // assignment initial state
                {2, 3, 7},
                {1, 4, 8},
                {0, 5, 6}
        };

        /*
    	int[][] p2 = { // > 1 second
	    			 {1, 8, 0},
	    			 {2, 3, 4},
	    			 {7, 5, 6}
    	 			};

        int[][] p3 = {  // Example of never ending initial state. No solution.
                {1, 8, 3},
                {5, 6, 2},
                {4, 7, 0}
        }; */

        EightPuzzle ep = new EightPuzzle(p);

        submenu(ep);
    }

    static private void submenu(State problem) {

        int opt = 0;
        Algorithm a = null;

        do {

            System.out.println("Which search algorithm do you want to use?");
            System.out.println("1 - A*");
            System.out.println("2 - Breath Search First (BFS)");
            System.out.println("3 - Uniform Cost (UC)");
            System.out.println("4 - Depth First Search (DFS)");
            System.out.println("0 - Quit\n");

            try {

                Scanner reader = new Scanner(System.in);
                opt = reader.nextInt();

            } catch(InputMismatchException e) { opt = -1; }

            switch(opt) {
                case 1 : a = new AS();  break;
                case 2 : a = new BFS(); break;
                case 3 : a = new UC();  break;
                case 4 : a = new DFS(); break;
            }

            try {

                if (opt != 0) searchResult(a, problem);

            }catch(Exception e) { System.out.println("Problem not prepared for this algorithm in this version. Please try another one."); }

        } while(opt != 0);
    }

    static public void main(String[] args) {

        int opt = 0;

        do {
            System.out.println("Please select a problem to solve.");
            System.out.println("1 - Missionaries and Cannibals");
            System.out.println("2 - Nickel and Dimme");
            System.out.println("3 - Specific Graph problem");
            System.out.println("4 - Eight puzzle");
            System.out.println("5 - Buckets");
            System.out.println("0 - Quit\n");

            try {

                Scanner reader = new Scanner(System.in);
                opt = reader.nextInt();

            } catch(InputMismatchException e) { opt = -1; }

            switch(opt) {

                case 1 : MissionariesAndCannibalsProblem(); break;
                case 2 : NickelAndDimmeProblem(); break;
                case 3 : UCGraphProblem(); break;
                case 4 : EightPuzzleProblem(); break;
                case 5 : BucketsProblem(); break;
            }

        } while(opt != 0);
    }

}
