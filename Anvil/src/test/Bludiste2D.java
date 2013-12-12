package test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import api.B;
import api.Q;
import api.World;

public class Bludiste2D {

  static final int NX = 30;
  static final int NZ = 10;


  int citac;
  private Q start;
  private final Random random = new Random(111111);

  private List<Q> pomezi = new LinkedList<Q>();


  //  private void vycisti() {
  //    for (int y = 0; y < 6; y++) {
  //      System.out.println("Cistim: " + y);
  //      for (int x = -512; x < 512; x++) {
  //        for (int z = -512; z < 512; z++) {
  //          start.rfu(x, z, y).block(B.AIR);
  //        }
  //      }
  //    }
  //  }

  private void vytvorOblast() {
    for (int x = -4; x < NX + 4; x++) {
      for (int z = -4; z < NZ + 4; z++) {
        start.rfu(x, z, -2).block(B.AIR);
        start.rfu(x, z, -1).block(B.AIR);
        start.rfu(x, z, 0).block(B.AIR);
        start.rfu(x, z, 1).block(B.AIR);
        start.rfu(x, z, 2).block(B.AIR);
      }
    }
    for (int x = 0; x < NX; x++) {
      for (int z = 0; z < NZ; z++) {
        start.rfu(x, z, 0).block(B.BRICK);
        start.rfu(x, z, -1).block(B.STONE);
      }
    }

    //    for (int x = 0; x < NX; x++) {
    //      for (int z = 0; z < NZ; z++) {
    //        start.rfu(x, z, 11, B.STONE);
    //      }
    //  }

  }

  private Q getAndRemoveRandom() {
    final int i = random.nextInt(pomezi.size());
    return pomezi.remove(i);
  }

  private void vrtej(final Q q) {
    //System.out.println("Vyvrtani: " + q);
    //    System.out.println("Pred vrtanim: " + q.block());
    q.block(B.AIR);
    //System.out.println("Po vrtani: " + q.block());
    //p[x][z] = -1;  //díra tam bude
    System.out.printf("[%s - %s,%s - %s]   { %4d , %4d }%n", q.getX(), start.getX(), q.getZ(), start.getZ(), Math.abs(q.getX() - start.getX()), Math.abs(q.getZ() - start.getZ()));
    System.out.flush();
    //[Math.abs(q.getX() - start.getX())][Math.abs(q.getZ() - start.getZ())] = -1;  //díra tam bude
  }


  private int pocetVolnychVOkoli(final Q qq) {
    int sum = 0;
    for (final Q q : qq.around()) {
      if (q.back().isAir()) {
        sum++;
      }
    }
    return sum;
  }


  private boolean proverRohy(final Q qq) {
    for (final Q q : qq.around()) {
      if (q.back().isAir() && ! q.lf().isAir() && ! q.rf().isAir()) {
        return true;
      }
    }
    return false;
  }

  public void generuj() {
    vytvorOblast();

    pomezi = new LinkedList<Q>();
    final Q stred = start.rf(NX / 2, NZ / 2);
    pomezi.add(stred);
    vrtej(stred.f());

    while (! pomezi.isEmpty()) {
      final Q q = getAndRemoveRandom();
      if (! q.isAir()) {
        final int pocetOkolnichVolnych = pocetVolnychVOkoli(q);
        if (pocetOkolnichVolnych <= 1) {
          // ještì budeme testovat, zda jsou protilehlé rohy volné
          if (proverRohy(q)) {
            vrtej(q);
            for (final Q qq : q.around()) {
              pomezi.add(qq.b());
            }
          }
        }
      }
    }
  }



  //////////////////////////////////////////////////////////////////////////////////////////////////////////////


  public static void main(final String[] args) {
    final World world = World.open(new File(args[0]));


    final Bludiste2D bludiste = new Bludiste2D();
    bludiste.start = world.getPlayerPosition().d(2).lb(NX /2, NZ /2);
    //bludiste.vycisti();
    bludiste.vytvorOblast();
    bludiste.generuj();

    new HledacNejdelsiCesty(bludiste.start).oznacNejdelsiCestu();

    System.out.println("KONEC");

    world.save();

    //    for (int i = -33; i< 32; i++) {
    //      //System.out.println(i + " " + i % 10 + " " + i / 10 + " --------- " + Ma.mod(i, 10) + "  " + Ma.div(i, 10));
    //      System.out.println(i + " " + " --------- " + Ma.mod(i, 10) + "  " + Ma.div(i, 10));
    //    }
  }

}
