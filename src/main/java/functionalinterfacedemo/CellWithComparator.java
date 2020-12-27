package functionalinterfacedemo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CellWithComparator {
    int val;
    String name;

    public CellWithComparator(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        // Anonymous class
    	PriorityQueue<CellWithComparator> pqAnonymousComparator = new PriorityQueue<>(
        		new Comparator<CellWithComparator>() {
                    @Override
                    public int compare(CellWithComparator c1, CellWithComparator c2) {
                        return c1.val == c2.val ? 0 : (c1.val < c2.val ? -1 : 1);
                    }
                }
        );

    	// Lambda
        Comparator<CellWithComparator> cellWithComparatorComparator = (c1, c2) -> compareFunc(c1, c2);

        // Comparator static methods
        PriorityQueue<CellWithComparator> pqComparatorStaticMethod = new PriorityQueue<>(
                Comparator.comparingInt((CellWithComparator c) -> c.val)
                        .thenComparing( (c1,  c2) -> c1.name.compareTo(c2.name) )
        );

        // Static method reference
        PriorityQueue<CellWithComparator> pqMethodRef = new PriorityQueue<>(CellWithComparator::compareFunc);

        Arrays.stream(new int[]{1, 2});
    }


    private static int compareFunc(CellWithComparator c1, CellWithComparator c2) {
        return Integer.compare(c1.val, c2.val);
    }


}

