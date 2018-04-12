package imp;

import java.util.*;

import search.State;

public class MC implements State {

    private final int[] a;
    private final boolean boatside;

    private static final int CANNIBAL   = 1;
    private static final int MISSIONARY = 2;

    public MC(int[] a, boolean boatside) {

        this.a = a;
        this.boatside = boatside;
    }

    public final int[] getA() {

        return this.a;
    }

    public final boolean getBoatside() {

        return this.boatside;
    }

    public boolean isGoal() {

      return ((getTotal(a, MISSIONARY, false) + getTotal(a, CANNIBAL, false)) == 0);
    }

    private int getTotal(int[] a, int e, boolean side) {

        int c = 0;

        if(side == false)
            for(int i = 0; i < 6; i++)
                if(a[i] == e) c++;

        if(side == true)
            for(int i = 6; i < a.length; i++)
                if(a[i] == e) c++;

        return c;
    }

    private int getIndex(int[] a, int e, boolean side) {

        if(side == false)
            for(int i = 0; i < 6; i++)
                if(a[i] == e) return i;

        if(side == true)
            for(int i = 6; i < a.length; i++)
                if(a[i] == e) return i;

        return -1;
    }

    private int[] sendElement(int[] a, int e, boolean toboatside) {

        int i;
        i = getIndex(a, e, !toboatside);

        if (i != -1) {
            a[(a.length - 1) - i] = a[i];
            a[i] = 0;
        }

        return a;
    }

    public final List getActions() {

        List<State> result = new ArrayList<State>();

        int tot_aM = getTotal(a, MISSIONARY,false);
        int tot_aC = getTotal(a, CANNIBAL,false);
        int tot_bM = getTotal(a, MISSIONARY,true);
        int tot_bC = getTotal(a, CANNIBAL,true);

        if((tot_aM > 0 && (tot_aM < tot_aC)) || (tot_bM > 0 && (tot_bM < tot_bC))) return result;

        if(this.boatside == false) {

            sendElements(result, tot_aM, tot_aC);

        } else {

            sendElements(result, tot_bM, tot_bC);
        }

    	return result;
    }

    private void sendElements(List<State> result, int tot_m, int tot_c) {

        int[] a = this.a;

        if(tot_m > 0 && tot_c > 0) {
            a = this.a.clone();
            a = sendElement(a, CANNIBAL, !this.boatside);
            a = sendElement(a, MISSIONARY, !this.boatside);
            result.add(new MC(a, !this.boatside));
        }

        if(tot_c > 1) {
            a = this.a.clone();
            a = sendElement(a, CANNIBAL, !this.boatside);
            a = sendElement(a, CANNIBAL, !this.boatside);
            result.add(new MC(a, !this.boatside));
        }

        if(tot_m > 1) {
            a = this.a.clone();
            a = sendElement(a, MISSIONARY, !this.boatside);
            a = sendElement(a, MISSIONARY, !this.boatside);
            result.add(new MC(a, !this.boatside));
        }

        if(tot_m > 0) {
            a = this.a.clone();
            a = sendElement(a, MISSIONARY, !this.boatside);
            result.add(new MC(a, !this.boatside));
        }

        if(tot_c > 0) {
            a = this.a.clone();
            a = sendElement(a, CANNIBAL, !this.boatside);
            result.add(new MC(a, !this.boatside));
        }

    }

    private char mask(int e) {

        char[] c = new char[]{' ', 'C', 'M'};

        return c[e];
    }

    private String boatmask(boolean boatside) {

        String[] bs = new String[]{"left","right"};

        return bs[(boatside)?1:0];
    }

    @Override
    public boolean equals(Object v) {

        if(v instanceof State) {

            MC mc = (MC) v;
            return (mc.getBoatside() == this.getBoatside() &&  Arrays.equals(this.a, mc.getA()));
        }

        return false;
    }

	public void printState() {

        if(this.a == null) return;

        int i = 0;
        for(int e : this.a) {
            if (i++ == 6) System.out.print("\t");
            System.out.print(mask(e) + " ");
        }

        System.out.print(" - boatside: " + boatmask(boatside));
        System.out.println();
	}
	
	 
}
