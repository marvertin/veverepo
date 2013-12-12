package test;

import java.io.File;

import api.Block;
import api.Dim;
import api.World;

public class MengerovaHouba {

  static final int M = 100;

  Dim world;
  int citac;

  private void vymaz() {
    for (int i = 0; i < 243; i++) {
      for (int j = 0; j < 243; j++) {
        for (int k = 0; k < 243; k++) {
          world.setBlock(i, k, j + 11, new Block(0));
        }
      }
    }
  }

  private void houbuj(final int x, final int y, final int z, final int n) {
    if (n == 1) {
      //System.out.println(x + " " + y + " " + z + " ");
      world.setBlock(x, z, y + 11, new Block(133));
      return;
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          if (i%2 + j%2 + k%2 < 2) {
            houbuj(x + i * n/3, y + j * n/3, z + k * n/3, n/3);
            citac ++;
          }
        }
      }
    }

  }

  private void zahoubuj() {
    houbuj(0, 0, 0, 3 * 3 * 3 * 3 * 3);
    System.out.println("Pocet bloku: " + citac);
  }

  public static void main(final String[] args) {
    final Dim overworld = World.open(new File(args[0])).getOverworld();


    final MengerovaHouba mengerovaHouba = new MengerovaHouba();
    mengerovaHouba.world = overworld;
    mengerovaHouba.vymaz();
    mengerovaHouba.zahoubuj();

    for (int x = -M; x < M; x+=7) {
      for (int z = -M; z < M; z+=2) {
        //overworld.setBlock(x, z, 70, new Block(152));
        //overworld.setBlock(x, z, 70, new Block(0));
        //overworld.setBlock(x, z, 69, new Block(0));
        //        for (int y = 70; y < 74; y ++) {
        //          overworld.setBlock(x, z, y, new Block());
        //        }
      }
    }

    System.out.println("KONEC");

    overworld.save();

    //    for (int i = -33; i< 32; i++) {
    //      //System.out.println(i + " " + i % 10 + " " + i / 10 + " --------- " + Ma.mod(i, 10) + "  " + Ma.div(i, 10));
    //      System.out.println(i + " " + " --------- " + Ma.mod(i, 10) + "  " + Ma.div(i, 10));
    //    }
  }
}
