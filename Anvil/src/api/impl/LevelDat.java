package api.impl;

/**
 * Copyright Mojang AB.
 * 
 * Don't do evil.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import api.Dim;
import api.Facing;
import api.Q;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.DoubleTag;
import com.mojang.nbt.FloatTag;
import com.mojang.nbt.ListTag;
import com.mojang.nbt.NbtIo;

public class LevelDat {

  private final File worldDir;


  private CompoundTag levelDatRoot;

  public LevelDat(final File aWorldDir) {
    worldDir = aWorldDir;
  }

  public boolean isCorrectVersion() {

    // check if there is old file format level data
    final int verze = levelDatRoot.getCompound("Data").getInt("version");
    System.out.println("VERZE: " + verze);
    if (verze != Const.ANVIL_VERSION_ID) {
      return false;
    }

    return true;
  }

  public void open() {
    final File dataFile = new File(worldDir, "level.dat");
    if (dataFile.exists()) {
      try {
        levelDatRoot = NbtIo.readCompressed(new FileInputStream(dataFile));
      } catch (final IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      throw new RuntimeException("The file doeas not exists: " + dataFile);
    }
  }

  public void save() {
    final File dataFile = new File(worldDir, "level.dat");
    if (levelDatRoot != null && dataFile.exists()) {
      try {
        NbtIo.writeCompressed(levelDatRoot, new FileOutputStream(dataFile));
      } catch (final Exception e) {
        throw new RuntimeException("The file doeas not exists: " + dataFile, e);
      }
    }
  }

  public Q _getPlayerPosition(final Dim dim) {
    final CompoundTag playerTag = levelDatRoot.getCompound("Data").getCompound("Player");
    //playerTag.print(System.out);

    final ListTag<DoubleTag> posList = (ListTag<DoubleTag>) playerTag.getList("Pos");
    final double x = posList.get(0).data;
    final double y = posList.get(1).data;
    final double z = posList.get(2).data;

    final ListTag<FloatTag> rotationList = (ListTag<FloatTag>) playerTag.getList("Rotation");
    final double natoceni = rotationList.get(0).data;
    final double natoceniPootoceneJednotkove = (270 - natoceni + 45) / 360;
    final double natoceniPootoceneNormovane = natoceniPootoceneJednotkove - Math.floor(natoceniPootoceneJednotkove);
    final int natoceniInt = (int) Math.floor(natoceniPootoceneNormovane * 4);

    final Q q = new Q(dim, (int)x, (int)z, (int)y, Facing.values()[natoceniInt]);

    System.out.println(natoceni);
    return q;
  }

  public int getPlayerDimension() {
    final CompoundTag playerTag = levelDatRoot.getCompound("Data").getCompound("Player");
    final int result = playerTag.getInt("Dimension");
    return result;
  }

  public static void main(final String[] args) {
    final LevelDat dat = new LevelDat(new File(args[0]));
    dat.open();
    System.out.println();
    System.out.println(dat._getPlayerPosition(null));
  }


}
