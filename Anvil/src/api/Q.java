package api;

import java.util.ArrayList;


public class Q {

  private final Dim dim;

  private final int x;
  private final int y;
  private final int z;
  private final Facing facing;


  public Q(final Dim aDim, final int aX, final int aZ, final int aY, final Facing aFacing) {
    dim = aDim;
    x = aX;
    z = aZ;
    y = aY;
    facing = aFacing;
  }


  public Q lbd(final int left, final int back, final int down) { return newShifted(-left, -back, -down); }
  public Q lbd(final int lbd) { return lbd(lbd, lbd, lbd); }
  public Q lbd() { return lbd(1); }

  public Q lb(final int left, final int back) { return newShifted(-left, -back, 0); }
  public Q lb(final int lb) { return lb(lb, lb); }
  public Q lb() { return lb(1); }

  public Q lbu(final int left, final int back, final int up) { return newShifted(-left, -back, up); }
  public Q lbu(final int lbu) { return lbu(lbu, lbu, lbu); }
  public Q lbu() { return lbu(1); }

  public Q ld(final int left, final int down) { return newShifted(-left, 0, -down); }
  public Q ld(final int ld) { return ld(ld, ld); }
  public Q ld() { return ld(1); }

  public Q l(final int left) { return newShifted(-left, 0, 0); }
  public Q l() { return l(1); }
  public Q left(final int left) { return l(left); }
  public Q left() { return left(1); }

  public Q lu(final int left, final int up) { return newShifted(-left, 0, up); }
  public Q lu(final int lu) { return lu(lu, lu); }
  public Q lu() { return lu(1); }

  public Q lfd(final int left, final int front, final int down) { return newShifted(-left, front, -down); }
  public Q lfd(final int lfd) { return lfd(lfd, lfd, lfd); }
  public Q lfd() { return lfd(1); }

  public Q lf(final int left, final int front) { return newShifted(-left, front, 0); }
  public Q lf(final int lf) { return lf(lf, lf); }
  public Q lf() { return lf(1); }

  public Q lfu(final int left, final int front, final int up) { return newShifted(-left, front, up); }
  public Q lfu(final int lfu) { return lfu(lfu, lfu, lfu); }
  public Q lfu() { return lfu(1); }

  public Q bd(final int back, final int down) { return newShifted(0, -back, -down); }
  public Q bd(final int bd) { return bd(bd, bd); }
  public Q bd() { return bd(1); }

  public Q b(final int back) { return newShifted(0, -back, 0); }
  public Q b() { return b(1); }
  public Q back(final int back) { return b(back); }
  public Q back() { return back(1); }

  public Q bu(final int back, final int up) { return newShifted(0, -back, up); }
  public Q bu(final int bu) { return bu(bu, bu); }
  public Q bu() { return bu(1); }

  public Q d(final int down) { return newShifted(0, 0, -down); }
  public Q d() { return d(1); }
  public Q down(final int down) { return d(down); }
  public Q down() { return down(1); }

  public Q u(final int up) { return newShifted(0, 0, up); }
  public Q u() { return u(1); }
  public Q up(final int up) { return u(up); }
  public Q up() { return up(1); }

  public Q fd(final int front, final int down) { return newShifted(0, front, -down); }
  public Q fd(final int fd) { return fd(fd, fd); }
  public Q fd() { return fd(1); }

  public Q f(final int front) { return newShifted(0, front, 0); }
  public Q f() { return f(1); }
  public Q front(final int front) { return f(front); }
  public Q front() { return front(1); }

  public Q fu(final int front, final int up) { return newShifted(0, front, up); }
  public Q fu(final int fu) { return fu(fu, fu); }
  public Q fu() { return fu(1); }

  public Q rbd(final int right, final int back, final int down) { return newShifted(right, -back, -down); }
  public Q rbd(final int rbd) { return rbd(rbd, rbd, rbd); }
  public Q rbd() { return rbd(1); }

  public Q rb(final int right, final int back) { return newShifted(right, -back, 0); }
  public Q rb(final int rb) { return rb(rb, rb); }
  public Q rb() { return rb(1); }

  public Q rbu(final int right, final int back, final int up) { return newShifted(right, -back, up); }
  public Q rbu(final int rbu) { return rbu(rbu, rbu, rbu); }
  public Q rbu() { return rbu(1); }

  public Q rd(final int right, final int down) { return newShifted(right, 0, -down); }
  public Q rd(final int rd) { return rd(rd, rd); }
  public Q rd() { return rd(1); }

  public Q r(final int right) { return newShifted(right, 0, 0); }
  public Q r() { return r(1); }
  public Q right(final int right) { return r(right); }
  public Q right() { return right(1); }

  public Q ru(final int right, final int up) { return newShifted(right, 0, up); }
  public Q ru(final int ru) { return ru(ru, ru); }
  public Q ru() { return ru(1); }

  public Q rfd(final int right, final int front, final int down) { return newShifted(right, front, -down); }
  public Q rfd(final int rfd) { return rfd(rfd, rfd, rfd); }
  public Q rfd() { return rfd(1); }

  public Q rf(final int right, final int front) { return newShifted(right, front, 0); }
  public Q rf(final int rf) { return rf(rf, rf); }
  public Q rf() { return rf(1); }

  public Q rfu(final int right, final int front, final int up) { return newShifted(right, front, up); }
  public Q rfu(final int rfu) { return rfu(rfu, rfu, rfu); }
  public Q rfu() { return rfu(1); }


  public Q turnLeft(final int fi) {
    return newRotated(fi);
  }

  public Q turnRight(final int fi) {
    return newRotated(-fi);
  }

  public Q turnLeft() {
    return turnLeft(1);
  }

  public Q turnRight() {
    return turnRight(1);
  }

  public Q turnBack() {
    return newRotated(2);
  }

  public Q turnEast() {
    return turn(Facing.EAST);
  }

  public Q turnNorth() {
    return turn(Facing.NORTH);
  }

  public Q turnWest() {
    return turn(Facing.WEST);
  }

  public Q turnSouth() {
    return turn(Facing.SOUTH);
  }


  public Q turn(final Facing facing) {
    return newRotated(facing);
  }

  public Q translocate(final int x, final int z, final int y) {
    return newShiftedGlobally(x, z, y);
  }

  public Q translocate(final int x, final int z) {
    return newShiftedGlobally(x, z, y);
  }


  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // methods creating new object and supporting public methods

  private Q newShifted(final int lr, final int bf, final int du) {
    return newShiftedGlobally(x + facing.x(lr, bf), z + facing.z(lr, bf), y + du);
  }

  private Q newShiftedGlobally(final int x, final int z, final int y) {
    return new Q(dim, x, z, y, facing);
  }

  private Q newRotated(final int fi) { // kladny smer je doleva
    final int novyFacing = facing.ordinal() + fi & 0x3;
    System.out.println("novyfacing: " + novyFacing);
    return newRotated(Facing.values()[novyFacing]);
  }

  private Q newRotated(final Facing facing) { // kladny smer je doleva
    return new Q(dim, x, z, y, facing);
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // block manipulation


  public Q block(final Block block) {
    dim.setBlock(x, z, y, block);
    return this;
  }


  public Block block() {
    return dim.getBlock(x, z, y);
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // getter methods



  public Dim getDim() {
    return dim;
  }


  public int getX() {
    return x;
  }


  public int getY() {
    return y;
  }


  public int getZ() {
    return z;
  }


  public Facing getFacing() {
    return facing;
  }


  @Override
  public String toString() {
    return String.format("[x=%d, z=%d, y=%d : %s-%s]",x ,z, y, facing, dim);
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (dim == null ? 0 : dim.hashCode());
    result = prime * result + (facing == null ? 0 : facing.hashCode());
    result = prime * result + x;
    result = prime * result + y;
    result = prime * result + z;
    return result;
  }


  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Q other = (Q) obj;
    if (dim == null) {
      if (other.dim != null) {
        return false;
      }
    } else if (!dim.equals(other.dim)) {
      return false;
    }
    if (facing != other.facing) {
      return false;
    }
    if (x != other.x) {
      return false;
    }
    if (y != other.y) {
      return false;
    }
    if (z != other.z) {
      return false;
    }
    return true;
  }

  public boolean isAir() {
    return block().id == 0;
  }

  public void setAir() {
    block(new Block(0, 0));
  }


  public Iterable<Q> around() {
    final ArrayList<Q> list = new ArrayList<Q>(4);
    list.add(turnEast());
    list.add(turnNorth());
    list.add(turnWest());
    list.add(turnSouth());
    return list;
  }

}
