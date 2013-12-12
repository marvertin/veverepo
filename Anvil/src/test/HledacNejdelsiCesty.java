package test;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import api.B;
import api.Q;

public class HledacNejdelsiCesty {

  static final int NX = Bludiste2D.NX;
  static final int NZ = Bludiste2D.NZ;

  private int[][] p;
  private final Q start;

  HledacNejdelsiCesty(final Q start) {
    this.start = start;
  }

  private void oznac(final int x, final int z) {
    start.rf(x, z).block(B.REDSTONE_WIRE);
    System.out.println("OZNACENO: " + x + " " + z);
    //p[x][z] = -1;  //d�ra tam bude
  }

  private int po�etNepro�et�en�chOkol�(final int x, final int z) {
    int n = 0;
    if (p[x  ][z+1] == -1) {
      n++;
    }
    if (p[x  ][z-1] == -1) {
      n++;
    }
    if (p[x+1][z  ] == -1) {
      n++;
    }
    if (p[x-1][z  ] == -1) {
      n++;
    }
    return n;
  }

  private int maximumZOkol�(final int x, final int z) {
    final int max = Math.max(Math.max(Math.max(p[x][z+1], p[x][z-1]), p[x+1][z]), p[x-1][z]);
    return max;
  }

  public Sou hledejSpojNejdelsiCesty() {
    System.out.println("Hledani nejdelsi cesty");
    for (;;) { // vylezeme returnem
      for (int x = 0; x < NX; x++) {
        for (int z = 0; z < NZ; z++) {
          if (p[x][z] == -1) {  // nepro�et�en� d�ra
            final int po�etNepro�et�en�chOkol� = po�etNepro�et�en�chOkol�(x, z);
            if (po�etNepro�et�en�chOkol� == 1) {
              p[x][z] = maximumZOkol�(x, z) + 1; // tak tam bude o jedni�ku v�t�� ��slo
            } else if (po�etNepro�et�en�chOkol� == 0) { // je to ta �pln� posledn�
              return sou(x,z);
            }
          }
        }
      }
    }
  }

  Sou najdiVOkoliDalku(final int x, final int z, final int dalka) {
    final Sou s = new Sou();
    s.x = x;
    s.z = z +1;
    if (p[s.x][s.z] == dalka) {
      return s;
    }
    s.x = x;
    s.z = z -1;
    if (p[s.x][s.z] == dalka) {
      return s;
    }
    s.x = x +1;
    s.z = z;
    if (p[s.x][s.z] == dalka) {
      return s;
    }
    s.x = x -1;
    s.z = z;
    if (p[s.x][s.z] == dalka) {
      return s;
    }
    return null;
  }
  private void oznacCestu(final int x, final int z) {
    final int dalka = p[x][z];
    if (dalka <= 0) {
      return;
    }
    oznac(x, z);
    final Sou s = najdiVOkoliDalku(x, z, dalka - 1);
    oznacCestu(s.x, s.z);
  }

  public void oznacNejdelsiCestu() {
    p = new int[NX][NZ]; // pole pro hled�n� cesty 0 ... ze�, -1 ... d�ra neo��slovan�, >=1 d�ra o��slovan�
    for (int i=0; i<NX; i++) {
      for (int j=0; j<NZ; j++) {
        if (start.rf(i,j).isAir()) {
          p[i][j] = -1;
        }
      }
    }


    final Sou s = hledejSpojNejdelsiCesty();

    System.out.println("Nalezen spoj: " + s +  " "
        + p[s.x][s.z+1] + " | "
        + p[s.x][s.z-1] + " | "
        + p[s.x+1][s.z] + " | "
        + p[s.x-1][s.z] + " | ");

    final SortedSet<Dalka> set  = new TreeSet<Dalka>();
    set.add(new Dalka(s.x, s.z+1));
    set.add(new Dalka(s.x, s.z-1));
    set.add(new Dalka(s.x+1, s.z));
    set.add(new Dalka(s.x-1, s.z));

    final Iterator<Dalka> iterator = set.iterator();
    final Dalka dalka1 = iterator.next();
    oznacCestu(dalka1.x, dalka1.z);
    final Dalka dalka2 = iterator.next();
    oznacCestu(dalka2.x, dalka2.z);
    oznac(s.x, s.z);
    //    oznacCestu(s.x, s.z+1);
    //    oznacCestu(s.x, s.z-1);
    //    oznacCestu(s.x+1, s.z);
    //    oznacCestu(s.x-1, s.z);
  }

  private Sou sou(final int x, final int z) {
    final Sou sou = new Sou();
    sou.x = x;
    sou.z = z;
    return sou;
  }

  private class Sou {
    int x;
    int z;
    @Override
    public String toString() {
      return "Sou [x=" + x + ", z=" + z + "]";
    }
  }

  private class Dalka implements Comparable<Dalka> {
    private final int x;
    private final int z;
    private final int dalka;


    public Dalka(final int aX, final int aZ) {
      super();
      x = aX;
      z = aZ;
      dalka = p[x][z];
    }


    @Override
    public int compareTo(final Dalka aO) {
      return - (dalka - aO.dalka);
    }


  }

}
