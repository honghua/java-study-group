package com.autovaluetutorial;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Element {
    public abstract int getRow();
    public abstract int getCol();

    public static Element create(int row, int col) {
        return new AutoValue_Element(row, col);
    }

    public int multiply() {
        return getRow() * getCol();
    }

    /* convert a class to a AutoValue class
     1. class ->  abstract class
     2. field -> getter() abstract
     3. new instantiation -> static create()
     * */


//    private final int row;
//    private final int col;
//    public Element(int r, int c) {
//        this.row = r;
//        this.col = c;
//    }

    public static void main(String[] args) {
        Element e1 = Element.create(2, 5);
        Element e2 = Element.create(1, 1);
        System.out.println(e1.toString() + "\n" + e2.toString());
        System.out.println(e1.multiply());
//        System.out.println("compare value " + e1.equals(e2) + "\n compare hashcode: " + "\n" + e1.hashCode() +  "\n" + e2.hashCode());
    }
}




