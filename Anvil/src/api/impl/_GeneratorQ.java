package api.impl;

import java.io.PrintStream;

public class _GeneratorQ {

  private static String name(final int ss, final int smer) {
    switch (smer) {
    case 0:
      switch (ss) {
      case -1: return "left";
      case  1: return "right";
      }
    case 1:
      switch (ss) {
      case -1: return "back";
      case  1: return "front";
      }
    case 2:
      switch (ss) {
      case -1: return "down";
      case  1: return "up";
      }
      return null;
    }
    return null;
  }

  private static String jedineName(final int lr, final int bf, final int du) {
    String s = null;
    s = name(lr, 0);
    if (s != null) {
      return s;
    }
    s = name(bf, 1);
    if (s != null) {
      return s;
    }
    s = name(du, 2);
    return s;

  }

  private static String jeden(final int ss, final int smer) {
    final String name = name(ss, smer);
    return name == null ? "" : name.substring(0, 1);
  }

  private static String param(final int ss, final int smer) {
    final String name = name(ss, smer);
    switch (ss) {
    case -1: return "-" + name +", ";
    case 0 : return "0, ";
    case 1 : return name + ", ";
    }
    return null;
  }

  private static String argum(final int ss, final int smer) {
    final String name = name(ss, smer);
    switch (ss) {
    case -1: return "int " + name + ", ";
    case 0 : return "";
    case 1 : return "int " + name + ", ";
    }
    return null;
  }

  private static String odstranKonecnouCarku(final String s) {
    return s.substring(0, s.length() -2);
  }

  public static void main(final String[] args) {
    System.out.println("JEDj");
    final PrintStream o = System.out;
    for (int lr=-1; lr<=1; lr++) {
      for (int bf=-1; bf<=1; bf++) {
        for (int du=-1; du<=1; du++) {
          if ((lr | bf | du) == 0) {
            continue;
          }
          final String nazev = jeden(lr, 0) + jeden(bf, 1) + jeden(du, 2);
          final String argumy = odstranKonecnouCarku(argum(lr, 0) + argum(bf, 1) + argum(du, 2));
          final String paramy = odstranKonecnouCarku(param(lr, 0) + param(bf, 1) + param(du, 2));
          o.printf("public Q %s(%s) { return newShifted(%s); }%n", nazev, argumy, paramy);

          if (nazev.length() > 1) {
            final String volani = odstranKonecnouCarku((lr == 0 ? "" : nazev + ", ") + (bf == 0 ? "" : nazev + ", ") + (du == 0 ? "" : nazev + ", "));
            o.printf("public Q %s(int %s) { return %s(%s); }%n", nazev, nazev, nazev, volani);
            o.printf("public Q %s() { return %s(1); }%n", nazev, nazev );
          } else {
            final String jedineName = jedineName(lr, bf, du);
            o.printf("public Q %s() { return %s(1); }%n", nazev, nazev );
            o.printf("public Q %s(int %s) { return %s(%s); }%n", jedineName, jedineName, nazev, jedineName);
            o.printf("public Q %s() { return %s(1); }%n", jedineName, jedineName );
          }
          o.println();

        }
      }
    }


  }

}
