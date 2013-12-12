package api;

public enum Facing {
  EAST   ( 0, 1, 1, 0),
  NORTH  ( 1, 0, 0,-1),
  WEST   ( 0,-1,-1, 0),
  SOUTH  (-1, 0, 0, 1),
  ;

  private Facing(final int lrX, final int bfX, final int lrZ, final int bfZ) {
    this.lrX = lrX;
    this.bfX = bfX;
    this.lrZ = lrZ;
    this.bfZ = bfZ;

  }

  private int lrX;
  private int lrZ;
  private int bfX;
  private int bfZ;

  int x(final int lr, final int bf) {
    return lr * lrX + bf * bfX;

  }

  int z(final int lr, final int bf) {
    return lr * lrZ + bf * bfZ;
  }

}
