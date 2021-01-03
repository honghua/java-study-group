
package com.yang;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_Element extends Element {

  private final int row;
  private final int col;

  AutoValue_Element(
      int row,
      int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getCol() {
    return col;
  }

  @Override
  public String toString() {
    return "Element{"
        + "row=" + row + ", "
        + "col=" + col
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Element) {
      Element that = (Element) o;
      return (this.row == that.getRow())
           && (this.col == that.getCol());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.row;
    h *= 1000003;
    h ^= this.col;
    return h;
  }

}
