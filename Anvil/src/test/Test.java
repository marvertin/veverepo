package test;

import java.io.File;

import api.B;
import api.Block;
import api.Dim;
import api.Q;
import api.World;

public class Test {

  static final int M = 100;

  public static void main(final String[] args) {
    final Dim overworld = World.open(new File(args[0])).getOverworld();
    //    for (int x = -M; x < M; x++) {
    //      for (int z = -M; z < M; z++) {
    //        final Block block = overworld.getBlock(x, z, 70);
    //      }
    //    }
    //stareTesty(overworld);

    System.out.println("CESTA");
    if (true) {
      final int SIRKA = 16 ;
      final int ZAKLADNA = 62;
      for (int x = 0; x < 6000; x++) {
        for (int y=0; y < 5; y++) {
          for (int z=0; z < SIRKA; z++) {
            overworld.setBlock(x,  z, y + ZAKLADNA, B.AIR);
          }
        }
        for (int z=0; z < SIRKA; z++) {
          final boolean okraj = x % 16 == 0 || x % 16 == 15 || z % 16 == 0 || z % 16 == 15;
          overworld.setBlock(x,  z, ZAKLADNA, okraj ? B.PUMPKIN :  B.STONE);
          overworld.setBlock(x, 3, ZAKLADNA +1, new Block(B.RAIL.id, 1));
          overworld.setBlock(x, 5, ZAKLADNA +1, new Block(B.RAIL.id, 1));
          overworld.setBlock(x, 7, ZAKLADNA +1, new Block(B.RAIL.id, 1));
          //System.out.println("DAVAM");
        }
      }
    }
    System.out.println("SLOUP");
    for (int i = 0; i < 100; i ++) {
      for (int x = 0; x < 6; x++) {
        for (int z= 0; z < 6; z++) {
          for (int y=0; y < 256; y++) {
            overworld.setBlock(x + 2400 + i * 32 -3, z + 13, y, y % 16 == 15 | y % 16 == 0  ? B.STONE : B.DIRT);
          }
        }
      }
    }


    Q q = overworld.getWorld().getPlayerPosition().u(7);
    System.out.println(q);
    final Block b = B.STONE;
    //q.lf(2).block(b).rf(2).block(b);

    for (int i=0; i<4; i++) {
      for (int j=0; j <14; j++) {
        q = q.block(b).f();
      }
      q = q.turnRight();

    }


    //overworld.vypisSeckci(2400, 8, 68);
    System.out.println("KONEC");

    overworld.getWorld().save();

    //    for (int i = -33; i< 32; i++) {
    //      //System.out.println(i + " " + i % 10 + " " + i / 10 + " --------- " + Ma.mod(i, 10) + "  " + Ma.div(i, 10));
    //      System.out.println(i + " " + " --------- " + Ma.mod(i, 10) + "  " + Ma.div(i, 10));
    //    }
  }

  @SuppressWarnings("unused")
  private static void stareTesty(final Dim overworld) {
    System.out.println("----------------");
    for (int x = -M; x < M; x++) {
      for (int z = -M; z < M; z++) {
        final Block block = overworld.getBlock(x, z, 88);
        if (block.id == 0) {
          System.out.print('.');
        } else {
          System.out.print('*');
        }
      }
      System.out.println();
    }
    System.out.println("----------------");
    for (int x = -M; x < M; x+=1) {
      for (int z = -M; z < M; z+=1) {
        for (int y = 68; y < 76; y ++) {
          //          overworld.setBlock(x, z, y, new Block());
        }
      }
    }
    for (int x = -M; x < M; x+=7) {
      for (int z = -M; z < M; z+=2) {
        //overworld.setBlock(x, z, 70, new Block(152));
        overworld.setBlock(x, z, 70, new Block(133));
        overworld.setBlock(x, z, 69, new Block(152));
        //        for (int y = 70; y < 74; y ++) {
        //          overworld.setBlock(x, z, y, new Block());
        //        }
      }
    }
  }
}
