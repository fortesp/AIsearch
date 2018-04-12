package imp;

import search.State;

import java.util.ArrayList;
import java.util.List;

public class Buckets implements State {

    private int w1;
    private int w2;

    private final int b1 = 5;
    private final int b2 = 3;


    public Buckets(int w1, int w2) {

        this.w1 = w1;
        this.w2 = w2;
    }

    @Override
    public List getActions() {

        List<State> result = new ArrayList<State>();

        result.add(new Buckets(0, 0));
        result.add(new Buckets(b1, b2));
        result.add(new Buckets(b1, 0));
        result.add(new Buckets(0, b2));
        result.add(new Buckets(w1, 0));
        result.add(new Buckets(0, w2));
        result.add(new Buckets(b1, w2));
        result.add(new Buckets(w1, b2));

        int _w1 = w1;
        int _w2 = w2;

        // Transfer from w2 to w1
        while (_w2 > 0 && _w1 < b1) {
            _w2--;
            _w1++;
        }
        result.add(new Buckets(_w1, _w2));

        // Transfer from w1 to w2
        while (_w1 > 0 && _w2 < b2) {
            _w2++;
            _w1--;
        }
        result.add(new Buckets(_w1, _w2));

        return result;
    }

    @Override
    public boolean isGoal() {

        return (w1 + w2 == 4);
    }

    @Override
    public boolean equals(Object v) {

        if(v instanceof State) {

            Buckets bc = (Buckets) v;
            return (bc.w1 == this.w1 && bc.w2 == this.w2);
        }

        return false;
    }

    @Override
    public void printState() {

        System.out.print("B1: " + w1 + "\tB2: " + w2);
        System.out.println();
    }
}
