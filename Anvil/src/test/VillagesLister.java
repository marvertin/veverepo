package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import com.mojang.nbt.NbtIo;

public class VillagesLister {

  private static boolean stejne (final CompoundTag tag1, final CompoundTag tag2, final String name) {
    return tag1.getInt(name) == tag2.getInt(name);
  }

  private static boolean stejne (final CompoundTag t1, final CompoundTag t2) {
    if (t2 == null) {
      return false;
    }
    final int doors1 = t1.getList("Doors").size();
    final int doors2 = t2.getList("Doors").size();
    //    System.out.printf("%d=%d%n", doors1, doors2);
    return stejne(t1, t2, "CX")
        && stejne(t1, t2, "CZ")
        && stejne(t1, t2, "CY")
        && stejne(t1, t2, "Radius")
        && stejne(t1, t2, "PopSize")
        && stejne(t1, t2, "Golems")
        && doors1  == doors2;
  }

  public static void main(final String[] args) throws FileNotFoundException, IOException, InterruptedException {
    CompoundTag minulaVes = null;
    NbtIo.readCompressed(new FileInputStream(new File(args[0]))).getCompound("data").print(System.out);
    System.out.println("---------------------------------------------------");
    System.out.println("   Center            radius doors popsize golems");
    while(true) {
      final CompoundTag tag = NbtIo.readCompressed(new FileInputStream(new File(args[0]))).getCompound("data");
      //    tag = tag;
      //      tag.print(System.out);
      final ListTag<CompoundTag> list = (ListTag<CompoundTag>) tag.getList("Villages");
      for (final CompoundTag v : list) {
        if (! stejne(v, minulaVes)) {
          minulaVes = v;
          System.out.printf("   [%4d,%4d : %3d]%7d %5d%8d%7d%n", v.getInt("CX"), v.getInt("CZ"), v.getInt("CY"), v.getInt("Radius"), v.getList("Doors").size(), v.getInt("PopSize"), v.getInt("Golems"));
        } else {
          //System.out.print(".");
          Thread.sleep(2000);
        }
      }
      //      System.out.println("---------------------------------------------------");
    }
  }
}
