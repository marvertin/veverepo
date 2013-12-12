package test;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import api.B;
import api.Q;
import api.World;

public class Bludiste {

  static final int NX = 200;
  static final int NZ = 200;


  int citac;
  private Q start;
  private final Random random = new Random(111111);

  private List<Sou> pomezi = new LinkedList<Sou>();

  private int[][] p;

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
        start.rfu(x, z, -1).block(B.STONE);
        start.rfu(x, z, 0).block(B.STONE);
      }
    }
    p = new int[NX][NZ]; // pole pro hledání cesty 0 ... zeï, -1 ... díra neoèíslovaná, >=1 díra oèíslovaná

    //    for (int x = 0; x < NX; x++) {
    //      for (int z = 0; z < NZ; z++) {
    //        start.rfu(x, z, 11, B.STONE);
    //      }
    //  }

  }

  private Sou getAndRemoveRandom() {
    final int i = random.nextInt(pomezi.size());
    return pomezi.remove(i);
  }

  private void vrtej(final int x, final int z) {
    final Q q = start.rf(x, z);
    //System.out.println("Vyvrtani: " + q);
    //    System.out.println("Pred vrtanim: " + q.block());
    q.block(B.AIR);
    //System.out.println("Po vrtani: " + q.block());
    p[x][z] = -1;  //díra tam bude
  }

  private void oznac(final int x, final int z) {
    start.rf(x, z).block(B.REDSTONE_WIRE);
    System.out.println("OZNACENO: " + x + " " + z);
    //p[x][z] = -1;  //díra tam bude
  }

  private boolean jedira(final int x, final int z) {
    final boolean result = start.rf(x, z).block().id == 0;
    //System.out.println("JE DIRA " + result + " " + start.rf(x, z));
    return  result;
  }

  private int proverOkoli(final Sou sou, final int dx, final int dz) {
    if (jedira(sou.x + dx, sou.z + dz)) {
      return 1;
    } else {
      return 0;
    }
  }

  private boolean proverProtilehlyRohKDeraveHrane(final Sou sou, final int dx, final int dz) {
    if (! jedira(sou.x + dx, sou.z + dz)) {
      return false; // když na té stranì není díra, neprovìøujeme rohy není to
    }
    final int dx1 = dx == 0 ?  1 : -dx;
    final int dx2 = dx == 0 ? -1 : -dx;
    final int dz1 = dz == 0 ?  1 : -dz;
    final int dz2 = dz == 0 ? -1 : -dz;

    if (jedira(sou.x + dx1, sou.z + dz1))   {
      return false; // jeden protilehlý roh
    }
    if (jedira(sou.x + dx2, sou.z + dz2))  {
      return false; // druhý protilehlý roh
    }
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
    pomezi.add(sou(NX / 2, NZ / 2));
    vrtej(NX / 2, NZ / 2 + 1);

    while (! pomezi.isEmpty()) {
      final Sou sou = getAndRemoveRandom();
      if (! jedira(sou.x, sou.z)) {
        final int pocetOkolnichVolnych =
            proverOkoli(sou, 0 ,1) +
            proverOkoli(sou, 0,-1) +
            proverOkoli(sou, 1, 0) +
            proverOkoli(sou, -1, 0);
        if (pocetOkolnichVolnych <= 1) {
          // ještì budeme testovat, zda jsou protilehlé rohy volné
          if (proverRohy(sou)) {
            vrtej(sou.x, sou.z);
            pomezi.add(sou(sou.x, sou.z +1));
            pomezi.add(sou(sou.x, sou.z -1));
            pomezi.add(sou(sou.x +1, sou.z));
            pomezi.add(sou(sou.x -1, sou.z));
          }
        }
      }
    }
  }

  private int poèetNeprošetøenýchOkolí(final int x, final int z) {
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

  private int maximumZOkolí(final int x, final int z) {
    final int max = Math.max(Math.max(Math.max(p[x][z+1], p[x][z-1]), p[x+1][z]), p[x-1][z]);
    return max;
  }

  public Sou hledejSpojNejdelsiCesty() {
    System.out.println("Hledani nejdelsi cesty");
    for (;;) { // vylezeme returnem
      for (int x = 0; x < NX; x++) {
        for (int z = 0; z < NZ; z++) {
          if (p[x][z] == -1) {  // neprošetøená díra
            final int poèetNeprošetøenýchOkolí = poèetNeprošetøenýchOkolí(x, z);
            if (poèetNeprošetøenýchOkolí == 1) {
              p[x][z] = maximumZOkolí(x, z) + 1; // tak tam bude o jednièku vìtší èíslo
            } else if (poèetNeprošetøenýchOkolí == 0) { // je to ta úplnì poslední
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

  public static void main(final String[] args) {
    final World world = World.open(new File(args[0]));


    final Bludiste bludiste = new Bludiste();
    bludiste.start = world.getPlayerPosition().d(2).lb(NX /2, NZ /2);
    //bludiste.vycisti();
    bludiste.vytvorOblast();
    bludiste.generuj();

    bludiste.oznacNejdelsiCestu();

    System.out.println("KONEC");

    world.save();

    //    for (int i = -33; i< 32; i++) {
    //      //System.out.println(i + " " + i % 10 + " " + i / 10 + " --------- " + Ma.mod(i, 10) + "  " + Ma.div(i, 10));
    //      System.out.println(i + " " + " --------- " + Ma.mod(i, 10) + "  " + Ma.div(i, 10));
    //    }
  }

}
