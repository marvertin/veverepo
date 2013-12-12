package test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import api.B;
import api.Block;
import api.Dim;
import api.World;

public class Bludiste3D {

  static final int NX = 15;
  static final int NZ = 15;
  static final int NY = 10;

  static final int DX = 3;
  static final int DZ = 3;
  static final int DY = 4;

  static final int NAD_ZEMI = 40;


  int citac;
  private Dim w;
  private final Random random = new Random(111111);

  private List<Sou> pomezi = new LinkedList<Sou>();

  private void vytvorOblast() {
    for (int x = -4; x < NX + 4; x++) {
      for (int z = -4; z < NZ + 4; z++) {
        for (int y = -4; y < NY + 4; y++) {
          vrtej(x, z, y, B.AIR);
        }
      }
    }
    for (int x = 0; x < NX; x++) {
      for (int z = 0; z < NZ; z++) {
        for (int y = 0; y < NY; y++) {
          vrtej(x, z, y, B.BRICK);
        }
      }
    }
  }

  private Sou getAndRemoveRandom() {
    final int i = random.nextInt(pomezi.size());
    return pomezi.remove(i);
  }

  private void vrtej(final int x, final int z, final int y, final Block block) {
    for (int dx = 0; dx < DX; dx++) {
      for (int dz = 0; dz < DZ; dz++) {
        for (int dy = 0; dy < DY; dy++) {
          w.setBlock(x*DX + dx, z*DZ + dz, y*DY + dy + NAD_ZEMI, block);
        }
      }
    }
    //p[x][z] = -1;  //díra tam bude
  }


  private boolean jedira(final int x, final int z, final int y) {
    return  w.getBlock(x*DX, z*DZ, y*DY + NAD_ZEMI).id == 0;
  }

  private int proverOkoli(final Sou sou, final int dx, final int dz, final int dy) {
    if (jedira(sou.x + dx, sou.z + dz, sou.y + dy)) {
      return 1;
    } else {
      return 0;
    }
  }

  private boolean proverProtilehlyRohKDeraveHrane(final Sou sou, final int dx, final int dz) {
    //      if (! jedira(sou.x + dx, sou.z + dz)) {
    //        return false; // když na té stranì není díra, neprovìøujeme rohy není to
    //      }
    //      final int dx1 = dx == 0 ?  1 : -dx;
    //      final int dx2 = dx == 0 ? -1 : -dx;
    //      final int dz1 = dz == 0 ?  1 : -dz;
    //      final int dz2 = dz == 0 ? -1 : -dz;
    //
    //      if (jedira(sou.x + dx1, sou.z + dz1))   {
    //        return false; // jeden protilehlý roh
    //      }
    //      if (jedira(sou.x + dx2, sou.z + dz2))  {
    //        return false; // druhý protilehlý roh
    //      }
    return true;
  }

  private boolean proverRohy(final Sou sou) {
    boolean result = false;
    result = result || proverProtilehlyRohKDeraveHrane(sou,  0,  1);
    result = result || proverProtilehlyRohKDeraveHrane(sou,  0, -1);
    result = result || proverProtilehlyRohKDeraveHrane(sou,  1,  0);
    result = result || proverProtilehlyRohKDeraveHrane(sou, -1,  0);
    return result;
  }

  public void generuj() {
    vytvorOblast();
    pomezi = new LinkedList<Sou>();
    pomezi.add(sou(NX / 2, NZ / 2, NY / 2));
    vrtej(NX / 2, NZ / 2 + 1, NY / 2, B.AIR);

    while (! pomezi.isEmpty()) {
      final Sou sou = getAndRemoveRandom();
      if (! jedira(sou.x, sou.z, sou.y)) {
        final int pocetOkolnichVolnych =
            proverOkoli(sou, 0 ,0, 1) +
            proverOkoli(sou, 0, 0,-1) +
            proverOkoli(sou, 0 ,1, 0) +
            proverOkoli(sou, 0,-1, 0) +
            proverOkoli(sou, 1, 0, 0) +
            proverOkoli(sou,-1, 0, 0);
        if (pocetOkolnichVolnych <= 1) {
          // ještì budeme testovat, zda jsou protilehlé rohy volné
          if (proverRohy(sou)) {
            vrtej(sou.x, sou.z, sou.y, B.AIR);
            pomezi.add(sou(sou.x, sou.z, sou.y+1));
            pomezi.add(sou(sou.x, sou.z, sou.y-1));
            pomezi.add(sou(sou.x, sou.z +1, sou.y));
            pomezi.add(sou(sou.x, sou.z -1, sou.y));
            pomezi.add(sou(sou.x +1, sou.z, sou.y));
            pomezi.add(sou(sou.x -1, sou.z, sou.y));
          }
        }
      }
    }
  }

  private Sou sou(final int x, final int z, final int y) {
    final Sou sou = new Sou();
    sou.x = x;
    sou.z = z;
    sou.y = y;
    return sou;
  }

  private class Sou {
    int x;
    int z;
    int y;

    @Override
    public String toString() {
      return "Sou [x=" + x + ", z=" + z + ", y=" + y + "]";
    }


  }


  public static void main(final String[] args) {
    final Dim overworld = World.open(new File(args[0])).getOverworld();

    final Bludiste3D bludiste = new Bludiste3D();
    bludiste.w = overworld;
    bludiste.vytvorOblast();
    //bludiste.generuj();

    voda(overworld);

    System.out.println("KONEC");

    overworld.save();

    //    for (int i = -33; i< 32; i++) {
    //      //System.out.println(i + " " + i % 10 + " " + i / 10 + " --------- " + Ma.mod(i, 10) + "  " + Ma.div(i, 10));
    //      System.out.println(i + " " + " --------- " + Ma.mod(i, 10) + "  " + Ma.div(i, 10));
    //    }
  }

  private static void voda(final Dim w) {
    w.setBlock(0, 0, 6, B.REDSTONE_BLOCK);
    w.setBlock(0, 0, 5, B.STONE);
    w.setBlock(0, 0, 5, B.AIR);
    w.setBlock(0, 0, 4, new Block(8, 5));
    w.setBlock(0, 3, 4, new Block(9, 0));
    w.setBlock(0, 6, 4, new Block(8, 8));
    w.setBlock(0, 4, 4, new Block(79, 0));

    for (int i= 0; i < 16; i++) {
      w.setBlock(-100, i*10, 5, new Block(8, i));
      w.setBlock(-130, i*10, 5, new Block(9, i));
      w.setBlock(-160, i*10, 5, new Block(10, i));
      w.setBlock(-190, i*10, 5, new Block(11, i));
    }
  }

}
