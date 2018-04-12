package imp;

import search.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Nickel and Dimme
public class ND implements State {

    private int[] a;

    public ND(int[] a) {

        this.a = a;
    }

    public int[] getA() {

        return this.a;
    }

    public boolean isGoal() {

        int[] solution = {2, 2, 0, 1, 1};

        return Arrays.equals(this.a, solution);
    }

    public final List getActions() {

        List<State> result = new ArrayList<State>();

        int[] init = this.a;
        int p = 0;
        while (init[p] != 0) p++; // Search for the position of zero 0.

        int[] a = init.clone();

        if (p > 0) {

            // Move right
            if (a[p - 1] == 1) {

                a[p - 1] = a[p];
                a[p] = 1;

                result.add(new ND(a));
            }
        }

        a = init.clone();
        if (p > 1) {

            // Jump right
            if (a[p - 2] == 1 && a[p - 1] == 2) {

                a[p - 2] = a[p];
                a[p] = 1;

                result.add(new ND(a));
            }
        }

        a = init.clone();
        if(p < a.length - 1) { // If not the last empty

            // Move left
            if (a[p + 1] == 2) {

                a[p + 1] = a[p];
                a[p] = 2;

                result.add(new ND(a));
            }
        }

        a = init.clone();
        if(p < a.length - 2) {

            // Jump left
            if (a[p + 1] == 1 && a[p + 2] == 2) {

                a[p + 2] = a[p];
                a[p] = 2;

                result.add(new ND(a));
            }
        }

     return result;
    }

    public void printState() {

        if(this.a == null) return;

        for(int num : this.a) {
            System.out.print(num + " ");
        }

        System.out.println();
    }

}
