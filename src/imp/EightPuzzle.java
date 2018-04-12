package imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import search.Edge;
import search.State;

public class EightPuzzle implements State {
	
	private final int[][] p;
	private final int[][] solution = {
                                        {1, 2, 3},
                                        {4, 5, 6},
                                        {7, 8, 0}
                                    };
	  
    public EightPuzzle(int[][] p) {

        this.p = p; 
    }

	@Override
	public List getActions() {
		
		List<Edge> result = new ArrayList<Edge>();
		 
		int X = 0, Y = 0;
		
		// Locate empty space
		for(int i = 0; i < p.length; i++) {
			for(int j = 0; j < p[i].length; j++) {				
				if(p[i][j] == 0) {
					Y = i;
					X = j;
					break;
				}				
			}			
		}

		if(X > 0) {

			_action(result, X, Y, -1, 0); // left
		}

		if(X < p.length - 1) {

			_action(result, X, Y, 1, 0); // right
		}

		if(Y > 0) {

			_action(result, X, Y, 0, -1); // up
		}

		if(Y < p[0].length - 1) {

			_action(result, X, Y, 0, 1); // down
		}

		return result;
	}
	
	private void _action(List<Edge> result, int X, int Y, int dx, int dy) {

        // clone matrix
        int[][] temp = p.clone();
        for (int i = 0; i < p.length; i++) {
            temp[i] = p[i].clone();
        }
        // ----

		temp[Y][X] = temp[Y + dy][X + dx];
		temp[Y + dy][X + dx] = 0;

		Edge edge = new Edge(new EightPuzzle(temp), getHeuristicValue(temp));
		result.add(edge);
	}

	@Override
	public boolean isGoal() {

		return this.equals(new EightPuzzle(solution));
	}

    @Override
    public boolean equals(Object v) {

        if(v instanceof State) {

            EightPuzzle ep = (EightPuzzle) v;

            for(int i = 0; i < p.length; i++) {
                for(int j = 0; j < p[0].length; j++) {
                    if(this.p[i][j] != ep.p[i][j]) return false;
                }
            }

            return true;
        }

        return false;
    }

    /*
    @Override
    public int hashCode() {

        String str = "";
        for(int i = 0; i < p.length; i++) {
            for(int j = 0; j < p[0].length; j++) {
                str += this.p[i][j];
            }
        }

        return Integer.parseInt(str);
    }*/

    private int getHeuristicValue(int[][] p) {

        // Less consistent but little faster with deeper tree
      return calculateManhattanDistance(p) * h1(p);
    }

    private int calculateManhattanDistance(int[][] p) { //h2

        int total = 0;
        boolean jumpout = false;

        for(int i = 0; i < p.length; i++) {
            for(int j = 0; j < p[0].length; j++) {

                if(p[i][j] == 0) continue;

                // find same element in the solution array
                for(int ii = 0; ii < solution.length && !jumpout; ii++) {
                    for(int jj = 0; jj < solution[0].length; jj++) {

                        if(p[i][j] == solution[ii][jj]) {
                            total += Math.abs(i - ii) + Math.abs(j - jj);
                            jumpout = true;
                            break;
                        }

                    }
                }
                jumpout = false;

            }
        }

        return total;
    }

    private int h1(int[][] p) { //h1

        int total = 0;

        for(int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[0].length; j++) {
                if(p[i][j] != solution[i][j]) total++;
            }
        }

        return total;
    }


	public void printState() {

		if(this.p == null) return;

		for(int i = 0; i < p.length; i++) {
			for(int j = 0; j < p[i].length; j++) {
				System.out.print(p[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

}
